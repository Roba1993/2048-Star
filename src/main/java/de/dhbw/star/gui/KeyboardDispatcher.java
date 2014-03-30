/*
 * The MIT License
 *
 * Copyright 2014 Robert Schütte.
 *
 */
package de.dhbw.star.gui;

import de.dhbw.star.game.CoreGame;
import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Robert Schütte
 */
public class KeyboardDispatcher implements KeyEventDispatcher {

    private GameWindow gameWindow;
    private boolean gameRun = true;

    public KeyboardDispatcher(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_RELEASED && gameRun) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                gameWindow.getCoreGame().moveDown();
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                gameWindow.getCoreGame().moveUp();
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                gameWindow.getCoreGame().moveLeft();
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                gameWindow.getCoreGame().moveRight();
            }

            gameWindow.updateWinow();

            //check the game status
            if (gameWindow.getCoreGame().getGameStatus() == CoreGame.LOST) {
                gameRun = false;
                JOptionPane.showMessageDialog(null, "Lost", "You lost this game", JOptionPane.INFORMATION_MESSAGE);
                gameWindow.getCoreGame().restartGame();
                gameRun = true;
                gameWindow.updateWinow();
            }
            if (gameWindow.getCoreGame().getGameStatus() == CoreGame.WON) {
                gameRun = false;
                JOptionPane.showMessageDialog(null, "Win", "You won this game", JOptionPane.INFORMATION_MESSAGE);
                gameWindow.getCoreGame().restartGame();
                gameRun = true;
                gameWindow.updateWinow();
            }
        }
        return false;
    }
}
