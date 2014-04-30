/*
 * The MIT License
 *
 * Copyright 2014 Robert Schütte
 */
package de.dhbw.star.game;

/**
 *
 * @author Robert Schütte
 */
public class CheckGameStatus {

    private GameMap gameMap;

    /**
     * Creates a new CheckGameStatus object for the GameMap
     *
     * @param gameMap
     */
    public CheckGameStatus(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    /**
     * This function returns true when the game is lost
     *
     * @return
     */
    public boolean checkIfLost() {
        for (int x = 0; x < gameMap.getGameSize(); x++) {
            for (int y = 0; y < gameMap.getGameSize(); y++) {
                if (gameMap.getContentBox(x, y).getValue() == 0 || checkBoxesAround(x, y)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * This functions returns true when a move is possible
     *
     * @param x
     * @param y
     * @return
     */
    private boolean checkBoxesAround(int x, int y) {
        if (gameMap.getContentBox(x + 1, y) != null
                && gameMap.getContentBox(x + 1, y).getValue() == gameMap.getContentBox(x, y).getValue()) {
            return true;
        }

        if (gameMap.getContentBox(x - 1, y) != null
                && gameMap.getContentBox(x - 1, y).getValue() == gameMap.getContentBox(x, y).getValue()) {
            return true;
        }

        if (gameMap.getContentBox(x, y + 1) != null
                && gameMap.getContentBox(x, y + 1).getValue() == gameMap.getContentBox(x, y).getValue()) {
            return true;
        }

        if (gameMap.getContentBox(x, y - 1) != null
                && gameMap.getContentBox(x, y - 1).getValue() == gameMap.getContentBox(x, y).getValue()) {
            return true;
        }

        return false;
    }

    /**
     * Returns true when the game has a 2048 Box
     *
     * @return
     */
    public boolean checkIfWon() {
        for (int x = 0; x < gameMap.getGameSize(); x++) {
            for (int y = 0; y < gameMap.getGameSize(); y++) {
                if (gameMap.getContentBox(x, y).getValue() == 2048) {
                    return true;
                }
            }
        }

        return false;
    }
}
