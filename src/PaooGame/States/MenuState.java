package PaooGame.States;

import PaooGame.GameWindow.GameWindow;
import PaooGame.RefLinks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */

import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends State {
    private String[] options = {"New Game", "Continue", "Exit"};
    private int currentSelection = 0; // Selecția curentă
    private boolean selectLevel = false;
    private String[] levels = {"Level 1", "Level 2", "Level 3"};
    private int currentLevel = 0;

    public MenuState(RefLinks refLink) {
        super(refLink);
    }

    @Override
    public void Update() {
        if (selectLevel) {
            // Navigarea prin niveluri
            if (refLink.GetKeyManager().up) {
                currentLevel--;
                if (currentLevel < 0) {
                    currentLevel = levels.length - 1;
                }
            }
            if (refLink.GetKeyManager().down) {
                currentLevel++;
                if (currentLevel >= levels.length) {
                    currentLevel = 0;
                }
            }
            if (refLink.GetKeyManager().enter) {
                // Selectează nivelul și începe jocul
                //refLink.GetGame().SetPlayState(currentLevel + 1);
                State.SetState(refLink.GetGame().playState);
            }
        } else {
            // Navigarea prin meniul principal
            if (refLink.GetKeyManager().up) {
                currentSelection--;
                if (currentSelection < 0) {
                    currentSelection = options.length - 1;
                }
            }
            if (refLink.GetKeyManager().down) {
                currentSelection++;
                if (currentSelection >= options.length) {
                    currentSelection = 0;
                }
            }
            if (refLink.GetKeyManager().enter) {
                switch (currentSelection) {
                    case 0: // New Game
                        selectLevel = true; // Mergi la selectarea nivelului
                        break;
                    case 1: // Continue
                        //refLink.GetGame().LoadGame(); // Încarcă jocul salvat
                        State.SetState(refLink.GetGame().playState);
                        break;
                    case 2: // Exit
                        System.exit(0); // Închide jocul
                        break;
                }
            }
        }
    }

    @Override
    public void Draw(Graphics g) {
        // Desenează background-ul pentru meniu
        g.drawImage(Assets.menuBackground, 0, 0, refLink.GetGame().GetWidth(), refLink.GetGame().GetHeight(), null);

        g.setFont(new Font("Arial", Font.PLAIN, 50));
        if (!selectLevel) {
            // Desenează opțiunile din meniu
            for (int i = 0; i < options.length; i++) {
                if (i == currentSelection) {
                    g.setColor(Color.BLUE);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.drawString(options[i], refLink.GetGame().GetWidth() / 2 - 100, 200 + i * 100);
            }
        } else {
            // Desenează selecția nivelului
            for (int i = 0; i < levels.length; i++) {
                if (i == currentLevel) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.drawString(levels[i], refLink.GetGame().GetWidth() / 2 - 100, 200 + i * 100);
            }
        }
    }
}

