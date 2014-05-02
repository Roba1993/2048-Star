/*
 * The MIT License
 *
 * Copyright 2014 Robert Schütte.
 *
 */
package de.dhbw.star.game;

import java.util.ArrayList;

/**
 *
 * @author Robert Schütte
 */
public class CoreGame {

    public static final int RUN = 0;
    public static final int WON = 1;
    public static final int LOST = 2;

    private final double tileValue4Random = 0.1;
    private final GameMap gameMap;
    private int gameStatus = RUN;

    //Public Area
    /**
     * This fucntion creates a new CoreGame object
     */
    public CoreGame() {
        gameMap = new GameMap();

        //set two start values
        getRandomFreeBox().setValue(getRandomNewValue());
        getRandomFreeBox().setValue(getRandomNewValue());
    }

    /**
     * Creates a new CoreGame object with pre definded Game Map
     *
     * @param gameMap
     */
    public CoreGame(int[][] gameMap) {
        this.gameMap = new GameMap(gameMap);
    }

    public void move(int direction) {
        int map[][] = getValues();

        gameMap.gameMove(direction);

        afterMove(map);
    }

 
    /**
     * This fucntion returns the map with the actual values as String
     *
     * @return String of the map
     */
    @Override
    public String toString() {
        return "Game Status: " + gameStatus + "\n";
    }

    /**
     * This fucntion returns the actual map values as int
     *
     * @return int[][] map values
     */
    public int[][] getValues() {
        return gameMap.getValues();
    }

    public void restartGame() {
        gameMap.createNewGameMap();

        //set two start values
        getRandomFreeBox().setValue(getRandomNewValue());
        getRandomFreeBox().setValue(getRandomNewValue());
        gameStatus = RUN;
    }

    //Private Area
    private void afterMove(int map[][]) {
        ContentBox cbox = getRandomFreeBox();

        //only add a new value when there was a move or change
        // and the field is not full
        if (!gameMap.equals(map) && cbox != null) {
            cbox.setValue(getRandomNewValue());
        }

        //Check the game status
        CheckGameStatus cgs = new CheckGameStatus(gameMap);

        if (cgs.checkIfLost()) {
            gameStatus = LOST;
        }

        if (cgs.checkIfWon()) {
            gameStatus = WON;
        }
    }

    /**
     * This function returns a ContentBox from the game with the value 0 - the
     * box is choosen random
     *
     * @return random ContentBox from this coreGame object
     */
    private ContentBox getRandomFreeBox() {
        ArrayList<ContentBox> free = new ArrayList<ContentBox>();

        //search for all boxes with the value 0 and add them to the free list
        for (int x = 0; x < gameMap.getGameSize(); x++) {
            for (int y = 0; y < gameMap.getGameSize(); y++) {
                //add contentBoxes with 0 as value
                if (gameMap.getContentBox(x, y).getValue() == 0) {
                    free.add(gameMap.getContentBox(x, y));
                }
            }
        }

        if (free.isEmpty()) {
            return null;
        }

        //choose one random box from the free list
        int randNumber = (int) (free.size() * Math.random());

        //return the choosen box
        return free.get(randNumber);
    }

    private int getRandomNewValue() {
        if (Math.random() <= tileValue4Random) {
            return 4;
        } else {
            return 2;
        }
    }

    //Getter & Setter
    public int getGameSize() {
        return gameMap.getGameSize();
    }

    public int getGameStatus() {
        return gameStatus;
    }
}
