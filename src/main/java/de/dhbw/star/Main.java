/*
 * The MIT License
 *
 * Copyright 2014 Robert Schütte.
 *
 */
package de.dhbw.star;

import de.dhbw.star.ai.AI;
import de.dhbw.star.game.CoreGame;
import de.dhbw.star.game.GameMap;
import de.dhbw.star.gui.GameWindow;

/**
 *
 * @author Robert Schütte
 */
public class Main {

    public static void main(String args[]) {
        CoreGame game = new CoreGame();
        AI ai = new AI(game);
        
        GameWindow gameWindow = new GameWindow(game, ai);
    }
}
