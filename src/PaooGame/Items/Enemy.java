package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.*;

import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class Enemy extends Character
{
    private BufferedImage image;

    private EnemyType type;

    private float patrolSpeed;
    private int damage;
    private int health;

    private int leftLimit;
    private int rightLimit;

    private boolean movingRight = true;


    public Enemy(RefLinks refLink, float x, float y, EnemyType type, int leftLimit, int rightLimit)
    {
        super(refLink, x, y, Character.DEFAULT_ENEMY_WIDTH, Character.DEFAULT_ENEMY_HEIGHT);

        this.type = type;
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;

        // Set stats based on type
        switch(type)
        {
            case NORMAL:
                patrolSpeed = 1.5f;
                damage = 1;
                health = 2;
                image = Assets.e1Left;
                break;

            case FAST:
                patrolSpeed = 3.0f;
                damage = 1;
                health = 1;
                image = Assets.e1Left;
                break;

            case TANK:
                patrolSpeed = 1.0f;
                damage = 2;
                health = 5;
                image = Assets.e1Left;
                break;
        }

        // Collision box
        normalBounds.x = width / 4;        // 112 / 4 = 28
        normalBounds.y = height / 3 - 10;       // 64 / 3 ≈ 21
        normalBounds.width = width / 2;    // 56
        normalBounds.height = height / 2;  // 32

        xMove = patrolSpeed;
    }

    @Override
    public void Update()
    {
        Patrol();
        Move();
        CheckHeroCollision();
    }

    private void Patrol()
    {
        if(movingRight)
        {
            xMove = patrolSpeed;
            image = Assets.e1Right;
        }
        else
        {
            xMove = -patrolSpeed;
            image = Assets.e1Left;
        }

        if(x < leftLimit)
            movingRight = true;

        if(x > rightLimit)
            movingRight = false;
    }

    private void CheckHeroCollision()
    {
        Hero hero = refLink.GetPlayState().hero;

        Rectangle enemyBox = new Rectangle(
                (int)x + normalBounds.x,
                (int)y + normalBounds.y,
                normalBounds.width,
                normalBounds.height
        );

        Rectangle heroBox = new Rectangle(
                (int)hero.x + hero.normalBounds.x,
                (int)hero.y + hero.normalBounds.y,
                hero.normalBounds.width,
                hero.normalBounds.height
        );

        if(enemyBox.intersects(heroBox))
        {
            hero.TakeDamage(damage);
        }
    }

    @Override
    public void Move()
    {
        MoveX();
        MoveY();
    }

    @Override
    public void MoveX()
    {
        if(xMove > 0)
        {
            int tx = (int)(x + xMove + normalBounds.x + normalBounds.width) / Tile.TILE_WIDTH;

            if(!collisionWithTile(tx, (int)(y + normalBounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int)(y + normalBounds.y + normalBounds.height) / Tile.TILE_HEIGHT))
            {
                x += xMove;
            }
            else
            {
                movingRight = false;
            }
        }
        else if(xMove < 0)
        {
            int tx = (int)(x + xMove + normalBounds.x) / Tile.TILE_WIDTH;

            if(!collisionWithTile(tx, (int)(y + normalBounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int)(y + normalBounds.y + normalBounds.height) / Tile.TILE_HEIGHT))
            {
                x += xMove;
            }
            else
            {
                movingRight = true;
            }
        }
    }

    @Override
    public void MoveY()
    {
        // Enemy does not jump or fall
    }

    private boolean collisionWithTile(int x, int y)
    {
        return refLink.GetMap().GetTile(x, y).IsSolid();
    }

    @Override
    public void Draw(Graphics g)
    {
        int screenX = (int)(x - refLink.GetPlayState().cameraX);
        int screenY = (int)(y - refLink.GetPlayState().cameraY);

        g.drawImage(image, screenX, screenY, width, height, null);

        // DEBUG
        g.setColor(Color.RED);
        g.drawRect(
                screenX + normalBounds.x,
                screenY + normalBounds.y,
                normalBounds.width,
                normalBounds.height
        );
    }


public static void dataBase(){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement stmt = c.createStatement();

            String name = "ENEMY";
            String sql = "SELECT EXISTS (SELECT 1 FROM sqlite_master WHERE type = 'table' AND name = '" + name + "')";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            int tableExists = rs.getInt(1);
            if (tableExists != 1) {
                sql = "CREATE TABLE ENEMY " +
                        "(ID INT PRIMARY KEY NOT NULL," +
                        " DIRECTION TEXT NOT NULL, " +
                        " x INT NOT NULL, " +
                        " y INT NOT NULL)";

                stmt.execute(sql);
            }

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }

    public void save(int id){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement stmt = c.createStatement();

            String s = "DELETE FROM ENEMY WHERE ID = ?";
            PreparedStatement pstmt = c.prepareStatement(s);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();



            String sql = "INSERT INTO ENEMY (ID,DIRECTION,x,y) " +
                    "VALUES (?, ?, ?, ?);";

            pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, direction);
            pstmt.setFloat(3, x);
            pstmt.setFloat(4, y);

            pstmt.executeUpdate();

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void load(int id){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            stmt = c.createStatement();

            int idToSelect = id;
            String sql = "SELECT * FROM ENEMY WHERE ID = ?";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, idToSelect);
            ResultSet rs = pstmt.executeQuery();
            while ( rs.next() ) {
                direction = rs.getString("DIRECTION");
                x = rs.getFloat("x");
                y = rs.getFloat("y");

            }

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}


