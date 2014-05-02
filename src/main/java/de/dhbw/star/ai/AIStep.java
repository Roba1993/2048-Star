/*
 * The MIT License
 *
 * Copyright 2014 Robert Schütte
 *
 */
package de.dhbw.star.ai;

import de.dhbw.star.ai.rating.Rating;
import de.dhbw.star.game.GameMap;

/**
 *
 * @author Robert Schütte
 */
public class AIStep {

    private final int depthMax = 6;
    private final double forgetRate = 0.8;

    private AIStep[] nextSteps;

    private GameMap gameMap;
    private int rating;
    private int nextDirection = -1;
    private int depth;

    /**
     * Create a step in the decision tree.
     *
     * @param gameMap
     * @param depth
     */
    public AIStep(GameMap gameMap, int depth) {
        this.gameMap = new GameMap(gameMap.getValues());
        this.depth = depth + 1;
        this.nextSteps = new AIStep[4];

        createNewSteps();
        rateSteps();
    }

    public int getRating() {
        return rating;
    }

    public int getNextDirection() {
        return nextDirection;
    }

    public int getDepth() {
        return depth;
    }

    protected AIStep[] getNextSteps() {
        return nextSteps;
    }

    private void createNewSteps() {
        //check if there is a need for a new step
        if (depth >= depthMax) {
            return;
        }

        //create nextSteps for all 4 directions
        for (int i = 0; i < 4; i++) {
            //create a new game Map
            GameMap gm = new GameMap(gameMap.getValues());
            //move the game map in one direction
            gm.gameMove(i);

            nextSteps[i] = new AIStep(gm, depth);
        }
    }

    private void rateSteps() {
        //if there a next steps
        if (depth < depthMax) {
            //loop all next steps
            for (int i = 0; i < 4; i++) {
                //catch the step with the most points
                if (nextSteps[i].getRating() > rating) {
                    rating = nextSteps[i].getRating();
                    nextDirection = i;
                }
            }

        }

        rating *= forgetRate;
        rating += new Rating(gameMap).getRatingScore();
    }
}
