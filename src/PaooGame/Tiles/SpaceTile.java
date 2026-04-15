package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import PaooGame.Graphics.Assets;

/*! \class public class GrassTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip iarba.
 */

public class SpaceTile extends Tile
{
    /*! \fn public GrassTile(int id)
       \brief Constructorul de initializare al clasei

       \param id Id-ul dalei util in desenarea hartii.
    */
    public SpaceTile(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.space, id);
        collision = false;
    }

}