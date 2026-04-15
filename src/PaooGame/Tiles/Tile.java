package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    private static final int NO_TILES   = 32;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/
    public boolean collision = false;

        /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
        /// o singura data in memorie

    public static Tile spaceTile         = new SpaceTile(0);      /*!< Dala de tip spatiu*/
    public static Tile soilTileB         = new SoilTile(Assets.soilBase,1);
    public static Tile soilTile         = new SoilTile(Assets.soil,2);     /*!< Dala de tip sol/pamant*/
    public static Tile soilTileR         = new SoilTile(Assets.soilR,3);
    public static Tile soilTileL         = new SoilTile(Assets.soilL,4);
    public static Tile waterTile        = new WaterTile(5);     /*!< Dala de tip apa*/
    public static Tile grassTile        = new GrassTile(6);     /*!< Dala de tip iarba*/
    public static Tile mountainTile     = new MountainTile(7);  /*!< Dala de tip munte/piatra*/
    //public static Tile treeTile         = new TreeTile(8);      /*!< Dala de tip copac*/
    //public static Tile stoneTile         = new StoneTile(5);      /*!< Dala de tip piatra*/

    public static final int TILE_WIDTH  = 48;          //32*1.5     /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 48;                       /*!< Inaltimea unei dale.*/
    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;

        tiles[id] = this;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y)
    {
            /// Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid()
    {
        return collision;
    }

    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public int GetId()
    {
        return id;
    }
}
