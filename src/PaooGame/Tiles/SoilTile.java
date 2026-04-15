package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.image.BufferedImage;

/*! \class public class SoilTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip sol/pamant.
 */
public class SoilTile extends Tile {
    //public static BufferedImage type;
    /*public enum SoilType
    {
        BASE,
        NORMAL,
        LEFT,
        RIGHT
    }
    */
    public SoilTile(BufferedImage image, int idd) {
        super(image, idd);
        collision = true;
    }
}



    /*! \fn public SoilTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    /*public SoilTile(int id, SoilType type)
    {
        //super(Assets.soil, id);

        switch(type)
        {
            case BASE:
                this.type = Assets.soilBase;
                break;
            case NORMAL:
                this.type = Assets.soil;
                break;
            case LEFT:
                this.type = Assets.soilL;
                break;
            case RIGHT:
                this.type = Assets.soilR;
                break;
            default:
                this.type = Assets.soil;
                break;
        }

        super(type, id);
    }

}
*/