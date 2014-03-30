/*
 * The MIT License
 *
 * Copyright 2014 Robert Schütte.
 */
package de.dhbw.star.gui;

import de.dhbw.star.game.CoreGame;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Robert Schütte
 */
public class GameWindow extends JFrame {

    private final CoreGame coreGame;
    private final ContentBoxPane[][] contentBoxPanes;

    public GameWindow(CoreGame coreGame) {
        super("2048 Star");
        this.coreGame = coreGame;

        //window options
        setLayout(new GridLayout(coreGame.getGameSize(), coreGame.getGameSize()));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        //generate the panes
        contentBoxPanes = new ContentBoxPane[coreGame.getGameSize()][coreGame.getGameSize()];
        for (int y = 0; y < coreGame.getGameSize(); y++) {
            for (int x = 0; x < coreGame.getGameSize(); x++) {
                contentBoxPanes[x][y] = new ContentBoxPane();
                add(contentBoxPanes[x][y], y, x);
            }
        }

        //Add Keboard Dispatcher
        KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        kfm.addKeyEventDispatcher(new KeyboardDispatcher(this));

        updateWinow();
        setVisible(true);
    }

    public void updateWinow() {
        int map[][] = coreGame.getValues();

        //update the values
        for (int y = 0; y < coreGame.getGameSize(); y++) {
            for (int x = 0; x < coreGame.getGameSize(); x++) {
                int yDisplay = coreGame.getGameSize() - 1 - y;
                String text = Integer.toString(map[x][y]);
                contentBoxPanes[x][yDisplay].setText(text);
            }
        }

        //draw the window again
        repaint();
    }

    public CoreGame getCoreGame() {
        return coreGame;
    }

}
