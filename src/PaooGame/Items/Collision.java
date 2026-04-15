package PaooGame.Items;

import PaooGame.Game;
import PaooGame.Tiles.Tile;

public class Collision
{
    Game g;

    public Collision(Game g)
    {
        this.g = g;
    }

    public void CheckTile(Character i)
    {
        i.collisionOnX = false;
        i.collisionOnY = false;

        int itemLeftX   = (int)(i.x + i.normalBounds.x);
        int itemRightX  = (int)(i.x + i.normalBounds.x + i.normalBounds.width);
        int itemTopY    = (int)(i.y + i.normalBounds.y);
        int itemBottomY = (int)(i.y + i.normalBounds.y + i.normalBounds.height);

        int itemLeftCol   = itemLeftX / Tile.TILE_WIDTH;
        int itemRightCol  = itemRightX / Tile.TILE_WIDTH;
        int itemTopRow    = itemTopY / Tile.TILE_HEIGHT;
        int itemBottomRow = itemBottomY / Tile.TILE_HEIGHT;

        int maxCol = g.refLink.GetMap().maxWorldCol - 1;
        int maxRow = g.refLink.GetMap().maxWorldRow - 1;

        int tile1, tile2;

        //  Coliziune sus
        if(i.yMove < 0)
        {
            itemTopRow = (itemTopY + (int)i.yMove) / Tile.TILE_HEIGHT;

            if(itemTopRow < 0) itemTopRow = 0;
            if(itemTopRow > maxRow) itemTopRow = maxRow;

            if(itemLeftCol < 0) itemLeftCol = 0;
            if(itemLeftCol > maxCol) itemLeftCol = maxCol;

            if(itemRightCol < 0) itemRightCol = 0;
            if(itemRightCol > maxCol) itemRightCol = maxCol;

            tile1 = g.refLink.GetMap().tiles[itemLeftCol][itemTopRow];
            tile2 = g.refLink.GetMap().tiles[itemRightCol][itemTopRow];

            if(Tile.tiles[tile1] != null && Tile.tiles[tile1].collision) i.collisionOnY = true;
            if(Tile.tiles[tile2] != null && Tile.tiles[tile2].collision) i.collisionOnY = true;
        }


        //  Coliziune jos
        if(i.yMove > 0)
        {
            itemBottomRow = (itemBottomY + (int)i.yMove) / Tile.TILE_HEIGHT;

            if(itemBottomRow < 0) itemBottomRow = 0;
            if(itemBottomRow > maxRow) itemBottomRow = maxRow;

            if(itemLeftCol < 0) itemLeftCol = 0;
            if(itemLeftCol > maxCol) itemLeftCol = maxCol;

            if(itemRightCol < 0) itemRightCol = 0;
            if(itemRightCol > maxCol) itemRightCol = maxCol;

            tile1 = g.refLink.GetMap().tiles[itemLeftCol][itemBottomRow];
            tile2 = g.refLink.GetMap().tiles[itemRightCol][itemBottomRow];

            if(Tile.tiles[tile1] != null && Tile.tiles[tile1].collision) i.collisionOnY = true;
            if(Tile.tiles[tile2] != null && Tile.tiles[tile2].collision) i.collisionOnY = true;
        }

        //  Coliziune stanga
        if(i.xMove < 0)
        {
            itemLeftCol = (itemLeftX + (int)i.xMove) / Tile.TILE_WIDTH;

            if(itemLeftCol < 0) itemLeftCol = 0;
            if(itemLeftCol > maxCol) itemLeftCol = maxCol;

            if(itemTopRow < 0) itemTopRow = 0;
            if(itemTopRow > maxRow) itemTopRow = maxRow;

            if(itemBottomRow < 0) itemBottomRow = 0;
            if(itemBottomRow > maxRow) itemBottomRow = maxRow;

            tile1 = g.refLink.GetMap().tiles[itemLeftCol][itemTopRow];
            tile2 = g.refLink.GetMap().tiles[itemLeftCol][itemBottomRow];

            if(Tile.tiles[tile1] != null && Tile.tiles[tile1].collision) i.collisionOnX = true;
            if(Tile.tiles[tile2] != null && Tile.tiles[tile2].collision) i.collisionOnX = true;
        }


        //  Coliziune dreapta
        if(i.xMove > 0)
        {
            itemRightCol = (itemRightX + (int)i.xMove) / Tile.TILE_WIDTH;

            if(itemRightCol < 0) itemRightCol = 0;
            if(itemRightCol > maxCol) itemRightCol = maxCol;

            if(itemTopRow < 0) itemTopRow = 0;
            if(itemTopRow > maxRow) itemTopRow = maxRow;

            if(itemBottomRow < 0) itemBottomRow = 0;
            if(itemBottomRow > maxRow) itemBottomRow = maxRow;

            tile1 = g.refLink.GetMap().tiles[itemRightCol][itemTopRow];
            tile2 = g.refLink.GetMap().tiles[itemRightCol][itemBottomRow];

            if(Tile.tiles[tile1] != null && Tile.tiles[tile1].collision) i.collisionOnX = true;
            if(Tile.tiles[tile2] != null && Tile.tiles[tile2].collision) i.collisionOnX = true;
        }
    }
}
