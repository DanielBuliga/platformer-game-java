package PaooGame.Tiles;

import java.awt.image.BufferedImage;

public class BlackTile extends Tile {

    public BlackTile(BufferedImage img, int id) {
        super(img, id);
        collision = true;   // acesta este un perete solid
    }
}
