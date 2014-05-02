/*
 * The MIT License
 *
 * Copyright 2014 Robert Schütte
 *
 */
package de.dhbw.star.ai;

import de.dhbw.star.game.CoreGame;
import de.dhbw.star.game.GameMap;

/**
 *
 * @author Robert Schütte
 */
public class AI {

    private CoreGame coreGame;

    public AI(CoreGame coreGame) {
        this.coreGame = coreGame;
    }

    /**
     * Make a game move, calculatet from the AI.
     */
    public void AIMove() {
        GameMap gm = new GameMap(coreGame.getValues());

        RootAIStep rais = new RootAIStep(gm, 0);

        coreGame.move(rais.getNextDirection());

        if (gm.equals(coreGame.getValues())) {
            randomMove();
        }
    }

    /**
     * Random move when the AI is blocked.
     */
    private void randomMove() {
        int rand = (int) (Math.random() * 4);
        System.out.println("rand: " + rand);
        coreGame.move(rand);
    }
}
