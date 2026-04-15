package PaooGame.States;

import java.awt.*;
import java.util.ArrayList;

import PaooGame.Items.Enemy;
import PaooGame.Items.EnemyType;
import PaooGame.RefLinks;
import PaooGame.Items.Hero;
import PaooGame.Items.Collision;
import PaooGame.Maps.Map;
import PaooGame.GameUI;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{
    public Hero hero;
    public Map map;
    public static Collision collision;

    // Camera position
    public float cameraX = 0;
    public float cameraY = 0;

    // Enemies
    public ArrayList<Enemy> enemies = new ArrayList<>();

    // UI (score + lives)
    public GameUI ui;

    public PlayState(RefLinks refLink)
    {
        super(refLink);

        // Make PlayState globally accessible
        refLink.SetPlayState(this);

        // UI
        ui = new GameUI();

        // Load map
        map = new Map(refLink);
        refLink.SetMap(map);

        // Create hero
        hero = new Hero(refLink, 150, 100);

        // Collision system (legacy)
        collision = new Collision(refLink.GetGame());

        // Load enemies based on level
        int level = refLink.GetGame().level;

        if(level == 1)
        {
            enemies.add(new Enemy(refLink, 500, 312, EnemyType.NORMAL, 450, 650));
            enemies.add(new Enemy(refLink, 800, 350, EnemyType.NORMAL, 750, 1000));
        }
        else if(level == 2)
        {
            enemies.add(new Enemy(refLink, 500, 350, EnemyType.FAST, 450, 900));
            enemies.add(new Enemy(refLink, 900, 350, EnemyType.NORMAL, 850, 1200));
        }
        else if(level == 3)
        {
            enemies.add(new Enemy(refLink, 800, 312, EnemyType.TANK, 750, 1000));
        }
    }

    @Override
    public void Update()
    {
        // Update hero
        hero.Update();

        // Update enemies
        for(Enemy e : enemies)
            e.Update();

        // Update camera
        updateCamera();
    }

    private void updateCamera()
    {
        cameraX = hero.GetX() - refLink.GetGame().GetWidth() / 2 + hero.width / 2;

        if(cameraX < 0)
            cameraX = 0;

        if(cameraX > map.worldWidth - refLink.GetGame().GetWidth())
            cameraX = map.worldWidth - refLink.GetGame().GetWidth();
    }

    @Override
    public void Draw(Graphics g)
    {
        // Draw map
        map.Draw(this, g);

        // Draw hero
        hero.Draw(g);

        // Draw enemies
        for(Enemy e : enemies)
            e.Draw(g);

        // Draw UI
        ui.Draw(g);
    }
}
