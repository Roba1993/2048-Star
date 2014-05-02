/*
 * The MIT License
 *
 * Copyright 2014 Robert Schütte
 */
package de.dhbw.star.ai;

import de.dhbw.star.game.GameMap;

/**
 *
 * @author Robert Schütte
 */
public class RootAIStep extends AIStep {

    /**
     * The root step in the decision tree. He write develeopment inforations to
     * the console.
     *
     * @param gameMap
     * @param depth
     */
    public RootAIStep(GameMap gameMap, int depth) {
        super(gameMap, depth);

        AIStep steps[] = getNextSteps();

        System.out.println("==> " + GameMap.intToDirection(getNextDirection()));
        for (int i = 0; i < steps.length; i++) {
            System.out.println("Direction: " + GameMap.intToDirection(i) + "  Points: " + steps[i].getRating());
        }
        System.out.println("");
    }

}
