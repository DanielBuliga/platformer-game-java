package PaooGame.Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage heroLeft;
    public static BufferedImage heroRight;
    public static BufferedImage e1Left;
    public static BufferedImage e1Right;
    public static BufferedImage space;
    public static BufferedImage soil;
    public static BufferedImage soilL;
    public static BufferedImage soilR;
    public static BufferedImage soilBase;
    public static BufferedImage soilBase1;
    public static BufferedImage grass;
    public static BufferedImage mountain;
    public static BufferedImage townGrass;
    public static BufferedImage townGrassDestroyed;
    public static BufferedImage townSoil;
    public static BufferedImage water;
    public static BufferedImage[] hIdle;
    public static BufferedImage[] hAttack;
    public static BufferedImage[] hWalk;

    public static BufferedImage tree;

    public static BufferedImage stone;
    public static Image menuBackground;

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/PaooGameSpriteSheet.png"));
        SpriteSheet sheet1 = new SpriteSheet(ImageLoader.LoadImage("/textures/tiles.png"));
        SpriteSheet sheetHR = new SpriteSheet(ImageLoader.LoadImage("/hero/idle.png"));
        SpriteSheet sheetHL = new SpriteSheet(ImageLoader.LoadImage("/hero/idle_left.png"));
        SpriteSheet sheetER = new SpriteSheet(ImageLoader.LoadImage("/enemy/Idle_right.png"));
        SpriteSheet sheetEL = new SpriteSheet(ImageLoader.LoadImage("/enemy/Idle.png"));
            /// Se obtin subimaginile corespunzatoare elementelor necesare.

        soil = sheet1.crop(0, 2);
        soilL = sheet1.crop(1, 0);
        soilR = sheet1.crop(2, 0);;
        soilBase = sheet1.crop(0, 0);
        soilBase1 = sheet1.crop(0, 1);

        grass = sheet.crop(0, 0);
        water = sheet.crop(2, 0);
        mountain = sheet.crop(3, 0);
        townGrass = sheet.crop(0, 1);
        townGrassDestroyed = sheet.crop(1, 1);
        townSoil = sheet.crop(2, 1);
        tree = sheet.crop(3, 1);
        heroLeft = sheetHL.cropCharacter(6,0);
        heroRight = sheetHR.cropCharacter(0,0);

        stone = sheet1.crop(0,0);
        space = sheet1.crop(3,7);

        e1Left = sheetEL.cropEnemy(0,0);
        e1Right = sheetER.cropEnemy(4,0);

        menuBackground = ImageLoader.LoadImage("/background/background.png");

        System.out.println("EnemyLeft width = " + Assets.e1Left.getWidth());
        System.out.println("EnemyLeft height = " + Assets.e1Left.getHeight());

    }
}
