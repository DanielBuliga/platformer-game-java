package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.Input.KeyManager;
import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class Hero extends Character
{
    private BufferedImage image;

    // Jump system
    private boolean jumping = false;
    private boolean falling = false;

    private float jumpSpeed = 8f;     // viteza initiala de saritura
    private float gravity = 0.4f;     // cat de repede cade
    private float maxFallSpeed = 10f; // viteza maxima de cadere

    private int health = 5;

    public Hero(RefLinks refLink, float x, float y)
    {
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);

        image = Assets.heroRight;

        normalBounds.x = (int)(26 * SCALE);
        normalBounds.y = (int)(18 * SCALE);
        normalBounds.width = (int)(24 * SCALE);
        normalBounds.height = (int)(31 * SCALE);

        // detectare daca eroul incepe pe sol
        int tileBelow = (int)((y + normalBounds.y + normalBounds.height + 1) / Tile.TILE_HEIGHT);
        int tileLeft  = (int)((x + normalBounds.x) / Tile.TILE_WIDTH);
        int tileRight = (int)((x + normalBounds.x + normalBounds.width) / Tile.TILE_WIDTH);

        if(refLink.GetMap().GetTile(tileLeft, tileBelow).IsSolid() ||
                refLink.GetMap().GetTile(tileRight, tileBelow).IsSolid())
        {
            falling = false;
            jumping = false;
        }
        else
        {
            falling = true;
        }
    }


    @Override
    public void Update()
    {
        GetInput();

        // Jump logic
        if(jumping)
        {
            yMove = -jumpSpeed;
            jumpSpeed -= gravity;

            if(jumpSpeed <= 0)
            {
                jumping = false;
                falling = true;
            }
        }
        else if(falling)
        {
            yMove += gravity;

            if(yMove > maxFallSpeed)
                yMove = maxFallSpeed;
        }

        // coliziune cu tile-uri
        Move();

        // actualizare imagine
        if(refLink.GetKeyManager().left)
            image = Assets.heroLeft;

        if(refLink.GetKeyManager().right)
            image = Assets.heroRight;
    }

    private void GetInput()
    {
        KeyManager km = refLink.GetKeyManager();
        xMove = 0;

        if(km.left)
            xMove = -speed;

        if(km.right)
            xMove = speed;

        // declansare saritura
        if(km.jump && !jumping && !falling)
        {
            jumping = true;
            jumpSpeed = 8f; // resetam viteza de saritura
        }
    }

    @Override
    public void Move()
    {
        MoveX();
        MoveY();
    }

    public void MoveX()
    {
        if(xMove > 0) // dreapta
        {
            int tx = (int)(x + xMove + normalBounds.x + normalBounds.width) / Tile.TILE_WIDTH;

            if(!collisionWithTile(tx, (int)(y + normalBounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int)(y + normalBounds.y + normalBounds.height) / Tile.TILE_HEIGHT))
            {
                x += xMove;
            }
        }
        else if(xMove < 0) // stanga
        {
            int tx = (int)(x + xMove + normalBounds.x) / Tile.TILE_WIDTH;

            if(!collisionWithTile(tx, (int)(y + normalBounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int)(y + normalBounds.y + normalBounds.height) / Tile.TILE_HEIGHT))
            {
                x += xMove;
            }
        }
    }

    public void MoveY()
    {
        if(yMove > 0) // cadere
        {
            int ty = (int)(y + yMove + normalBounds.y + normalBounds.height) / Tile.TILE_HEIGHT;

            if(!collisionWithTile((int)(x + normalBounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int)(x + normalBounds.x + normalBounds.width) / Tile.TILE_WIDTH, ty))
            {
                y += yMove;
            }
            else
            {
                // aterizare
                falling = false;
                jumping = false;

                y = ty * Tile.TILE_HEIGHT - normalBounds.y - normalBounds.height - 1;
            }
        }
        else if(yMove < 0) // saritura
        {
            int ty = (int)(y + yMove + normalBounds.y) / Tile.TILE_HEIGHT;

            if(!collisionWithTile((int)(x + normalBounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int)(x + normalBounds.x + normalBounds.width) / Tile.TILE_WIDTH, ty))
            {
                y += yMove;
            }
            else
            {
                // loveste tavanul
                jumping = false;
                falling = true;
            }
        }
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

        // DEBUG bounding box
        g.setColor(Color.RED);
        g.drawRect(
                screenX + normalBounds.x,
                screenY + normalBounds.y,
                normalBounds.width,
                normalBounds.height
        );
    }

    public void TakeDamage(int amount)
    {
        health -= amount;

        if(health <= 0)
        {
            System.out.println("Hero died");
            // aici poti face respawn sau game over
        }
    }

}
