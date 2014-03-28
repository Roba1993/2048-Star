/*
 * The MIT License
 *
 * Copyright 2014 Robert Schütte.
 *
 */
package de.dhbw.star;

import de.dhbw.star.game.CoreGame;
import de.dhbw.star.gui.GameWindow;

/**
 *
 * @author Robert Schütte
 */
public class Main {

    public static void main(String args[]) {
        CoreGame game = new CoreGame();
        
        GameWindow gameWindow = new GameWindow(game);
    }
}
