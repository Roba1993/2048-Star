/*
 * The MIT License
 *
 * Copyright 2014 Robert Schütte
 *
 */
package de.dhbw.star.ai.rating;

import de.dhbw.star.game.ContentBox;
import de.dhbw.star.game.GameMap;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Robert Schütte
 */
public class Util {

    /**
     * Count how much boxes with the given value are in the game map.
     *
     * @param gameMap
     * @param num
     * @return
     */
    public static int countBoxes(GameMap gameMap, int num) {
        int empty = 0;

        for (int x = 0; x < gameMap.getGameSize(); x++) {
            for (int y = 0; y < gameMap.getGameSize(); y++) {
                if (gameMap.getContentBox(x, y).getValue() == num) {
                    empty++;
                }
            }
        }

        return empty;
    }

    /**
     * Returns all Boxes which the highest Point and under the limit number.
     *
     * @param gameMap
     * @param number
     * @return
     */
    public static List<ContentBox> getBiggestNumber(GameMap gameMap, int limit) {
        List<ContentBox> scores = new ArrayList<ContentBox>();

        brk:
        for (int x = 0; x < gameMap.getGameSize(); x++) {
            for (int y = 0; y < gameMap.getGameSize(); y++) {
                if (gameMap.getContentBox(x, y).getValue() < limit) {
                    scores.add(gameMap.getContentBox(0, 0));
                    break brk;
                }
            }
        }

        for (int x = 0; x < gameMap.getGameSize(); x++) {
            for (int y = 0; y < gameMap.getGameSize(); y++) {
                if (gameMap.getContentBox(x, y).getValue() > scores.get(0).getValue()
                        && gameMap.getContentBox(x, y).getValue() < limit) {
                    scores.clear();
                    scores.add(gameMap.getContentBox(x, y));
                } else if (scores.size() > 0
                        && gameMap.getContentBox(x, y).getValue() == scores.get(0).getValue()
                        && gameMap.getContentBox(x, y).getValue() < limit) {
                    scores.add(gameMap.getContentBox(x, y));
                }
            }
        }
        return scores;
    }

    /**
     * Returns limit Boxes which the highest Points.
     *
     * @param gameMap
     * @param number
     * @return
     */
    public static List<ContentBox> getBiggestNumbers(GameMap gameMap, int limit) {
        List<ContentBox> scores = new ArrayList<ContentBox>();
        scores.add(gameMap.getContentBox(0, 0));

        for (int x = 0; x < gameMap.getGameSize(); x++) {
            for (int y = 0; y < gameMap.getGameSize(); y++) {
                ContentBox cin = gameMap.getContentBox(x, y);

                for (ContentBox cbox : scores) {
                    if (cin.getValue() > cbox.getValue()) {
                        scores.remove(cbox);
                        scores.add(cin);
                        break;
                    }
                }
            }
        }

        return scores;
    }

}
