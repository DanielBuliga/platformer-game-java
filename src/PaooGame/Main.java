package PaooGame;

import PaooGame.GameWindow.GameWindow;
import PaooGame.Items.Collision;

public class Main
{
    public static void main(String[] args)
    {
        Game paooGame = new Game("WAR AGAINST ROBOTS", 960, 480);
        //Collision collision = new Collision(paooGame);

        paooGame.StartGame();
    }
}
