/*
 * The MIT License
 *
 * Copyright 2014 Robert Schütte
 *
 */
package de.dhbw.star.ai.rating;

import de.dhbw.star.game.CheckGameStatus;
import de.dhbw.star.game.GameMap;

/**
 *
 * @author Robert Schütte
 */
public class Rating {

    private GameMap gameMap;

    public Rating(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    /**
     * This function trys to rate a GameMap.
     * @return 
     */
    public int getRatingScore() {
        int rating = 0;

        if (new CheckGameStatus(gameMap).checkIfLost()) {
            return 0;
        }

        //points for less boxes
        rating += (Util.countBoxes(gameMap, 0)) * 5;

        rating += (new Merge(gameMap).getMergeRating() / 1);

        //rating *= (new Strategie(gameMap).getStrategieRating() / 100);

        return rating;
    }

}
