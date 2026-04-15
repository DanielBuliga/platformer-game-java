package PaooGame.Maps;

import PaooGame.Game;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;
import PaooGame.States.PlayState;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map {
    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width = 20;          /*!< Latimea hartii in numar de dale.*/
    private int height = 10;         /*!< Inaltimea hartii in numar de dale.*/

    public final int maxWorldCol = 40;
    public final int maxWorldRow = 10;

    public final int worldWidth =  Tile.TILE_WIDTH * maxWorldCol;
    public final int worldHeight =  Tile.TILE_HEIGHT * maxWorldRow;



    public int[][] tiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/

    /*! \fn public Map(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public Map(RefLinks refLink) {
        /// Retine referinta "shortcut".
        this.refLink = refLink;
        ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.
        LoadWorld();
    }

    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */
    public void Update() {

    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextl grafi in care se realizeaza desenarea.
     */
    public void Draw(PlayState play, Graphics g)
    {
        // Preluam pozitia camerei din PlayState
        float cameraX = play.cameraX;
        float cameraY = play.cameraY;

        // Parcurgem toate tile-urile din harta
        for(int col = 0; col < maxWorldCol; col++)
        {
            for(int row = 0; row < maxWorldRow; row++)
            {
                int tileId = tiles[col][row];

                // Coordonatele reale ale tile-ului in lumea jocului
                int worldX = col * Tile.TILE_WIDTH;
                int worldY = row * Tile.TILE_HEIGHT;

                // Convertim coordonatele din "world space" in "screen space"
                int screenX = (int)(worldX - cameraX);
                int screenY = (int)(worldY - cameraY);

                // Desenam doar tile-urile vizibile pe ecran
                if(screenX + Tile.TILE_WIDTH >= 0 &&
                        screenX <= refLink.GetGame().GetWidth() &&
                        screenY + Tile.TILE_HEIGHT >= 0 &&
                        screenY <= refLink.GetGame().GetHeight())
                {
                    // Desenam tile
                    Tile.tiles[tileId].Draw(g, screenX, screenY);

                    // DEBUG: contur rosu pentru tile-urile solide
                    /*if(Tile.tiles[tileId].collision)
                    {
                        g.setColor(Color.RED);
                        g.drawRect(screenX, screenY, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
                    }*/
                }
            }
        }
    }

    /*! \fn public Tile GetTile(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

        In situatia in care dala nu este gasita datorita unei erori ce tine de cod dala, coordonate gresite etc se
        intoarce o dala predefinita (ex. grassTile, mountainTile)
     */
    public Tile GetTile(int x, int y) {
        if (x < 0 || y < 0 || x >= maxWorldCol || y >= maxWorldRow) {
            return Tile.grassTile;
        }

        Tile t = Tile.tiles[tiles[x][y]];

        if (t == null) {
            return Tile.mountainTile;
        }
        return t;
    }

    /*! \fn private void LoadWorld()
        \brief Functie de incarcare a hartii jocului.
        Aici se poate genera sau incarca din fisier harta. Momentan este incarcata static.
     */
    private void LoadWorld() {
        //atentie latimea si inaltimea trebuiesc corelate cu dimensiunile ferestrei sau
        //se poate implementa notiunea de camera/cadru de vizualizare al hartii
        ///Se stabileste latimea hartii in numar de dale.
        //width = 20;
        ///Se stabileste inaltimea hartii in numar de dale
        //height = 10;
        ///Se construieste matricea de coduri de dale
        tiles = new int[maxWorldCol][maxWorldRow];
        //Se incarca matricea cu coduri
        /*for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = LoadMap();             //MiddleEastMap(y, x);
            }
        }
        */
        try {
            String adress = "/maps/map2.txt";
            if (Game.level == 2)
                adress = "/maps/map1.txt";
            if (Game.level == 3)
                adress = "/maps/map3.txt";

            InputStream is = getClass().getResourceAsStream(adress);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));


            for (int row = 0; row < maxWorldRow; row++) {
                String line = br.readLine();
                String[] nr = line.split(" ");

                for (int col = 0; col < maxWorldCol; col++) {
                    tiles[col][row] = Integer.parseInt(nr[col]);
                }
            }

            br.close();
        } catch (Exception e) {
        }
    }

}
