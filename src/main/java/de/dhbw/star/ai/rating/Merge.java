/*
 * The MIT License
 *
 * Copyright 2014 Robert Schütte
 *
 */
package de.dhbw.star.ai.rating;

import de.dhbw.star.game.GameMap;

/**
 *
 * @author Robert Schütte
 */
public class Merge {

    private GameMap gameMap;

    public Merge(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    /**
     * This function returns point's for merged boxes.
     *
     * @return
     */
    public int getMergeRating() {
        int rating = 0;

        double point = 1.0;
        double add = 2.5;
        for (int i = 4; i < 10000; i += i) {
            rating += (Util.countBoxes(gameMap, i) * point);
            point *= add;
        }

        return rating;
    }
}
