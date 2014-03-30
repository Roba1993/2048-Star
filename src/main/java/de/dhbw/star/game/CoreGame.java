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

    private final int gameSize = 4;
    private final ContentBox[][] gameArea = new ContentBox[gameSize][gameSize];
    private int gameStatus = RUN;

    //Public Area
    /**
     * This fucntion creates a new CoreGame object
     */
    public CoreGame() {
        //generate all content boxes
        for (int x = 0; x < gameSize; x++) {
            for (int y = 0; y < gameSize; y++) {
                gameArea[x][y] = new ContentBox(this, x, y);
            }
        }

        //set two start values
        getRandomFreeBox().setValue(getRandomNewValue());
        getRandomFreeBox().setValue(getRandomNewValue());
    }

    /**
     * This function moves the values up
     */
    public void moveUp() {
        beforeMove();
        int map[][] = getValues();

        for (int x = 0; x < gameSize; x++) {
            for (int y = 0; y < gameSize; y++) {
                gameArea[x][y].moveBoxValues(ContentBox.UP);
            }
        }

        afterMove(map);
    }

    /**
     * This function moves the values down
     */
    public void moveDown() {
        beforeMove();
        int map[][] = getValues();

        for (int x = 0; x < gameSize; x++) {
            for (int y = gameSize - 1; y >= 0; y--) {
                gameArea[x][y].moveBoxValues(ContentBox.DOWN);
            }
        }

        afterMove(map);
    }

    /**
     * This function moves the values left
     */
    public void moveLeft() {
        beforeMove();
        int map[][] = getValues();

        for (int y = 0; y < gameSize; y++) {
            for (int x = 0; x < gameSize; x++) {
                gameArea[x][y].moveBoxValues(ContentBox.LEFT);
            }
        }

        afterMove(map);
    }

    /**
     * This fucntion moves the values right
     */
    public void moveRight() {
        beforeMove();
        int map[][] = getValues();

        for (int y = 0; y < gameSize; y++) {
            for (int x = gameSize - 1; x >= 0; x--) {
                gameArea[x][y].moveBoxValues(ContentBox.RIGHT);
            }
        }

        afterMove(map);
    }

    /**
     * This fucntion returns the map with the actual values as String
     *
     * @return String of the map
     */
    @Override
    public String toString() {
        int map[][] = getValues();
        StringBuffer out = new StringBuffer();

        for (int y = 0; y < map.length; y++) {
            //draw the divide line
            for (int i = 0; i < (gameSize * 7); i++) {
                out.append("-");
            }
            out.append("\n|");

            //draw the colums
            for (int x = 0; x < map[y].length; x++) {
                //get the space
                int pos = 5 - (map[x][y] / 10);
                //make enough space
                for (int i = 0; i < pos; i++) {
                    out.append(" ");
                }
                out.append(map[x][y] + "|");
            }

            out.append("\n");
        }

        //draw the divide line
        for (int i = 0; i < (gameSize * 7); i++) {
            out.append("-");
        }

        return out.toString();
    }

    /**
     * This fucntion returns the actual map values as int
     *
     * @return int[][] map values
     */
    public int[][] getValues() {
        int map[][] = new int[gameSize][gameSize];

        for (int x = 0; x < gameSize; x++) {
            for (int y = 0; y < gameSize; y++) {
                map[x][y] = gameArea[x][y].getValue();
            }
        }

        return map;
    }

    public void restartGame() {
        for (int x = 0; x < gameSize; x++) {
            for (int y = 0; y < gameSize; y++) {
                gameArea[x][y].setValue(0);
                gameArea[x][y].setMerged(false);
            }
        }

        //set two start values
        getRandomFreeBox().setValue(getRandomNewValue());
        getRandomFreeBox().setValue(getRandomNewValue());
        gameStatus = RUN;
    }

    //Protected Area
    protected ContentBox getContentBox(int xpos, int ypos) {
        if (xpos < 0 || ypos < 0 || xpos >= gameSize || ypos >= gameSize) {
            return null;
        }

        return gameArea[xpos][ypos];
    }

    //Private Area
    private void beforeMove() {
        clearMergedAttributes();
    }

    private void afterMove(int map[][]) {
        //only add a new value when there was a moe or change
        if (!sameAsGameArea(map)) {
            getRandomFreeBox().setValue(getRandomNewValue());
        } else if (getRandomFreeBox() == null) {
            gameStatus = LOST;
        }
        checkIfWon();
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
        for (int x = 0; x < gameSize; x++) {
            for (int y = 0; y < gameSize; y++) {
                //add contentBoxes with 0 as value
                if (gameArea[x][y].getValue() == 0) {
                    free.add(gameArea[x][y]);
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
        int randomNumber = (int) (Math.random() * 3);
        if (randomNumber == 0) {
            return 4;
        } else {
            return 2;
        }
    }

    private void clearMergedAttributes() {
        for (int x = 0; x < gameSize; x++) {
            for (int y = 0; y < gameSize; y++) {
                gameArea[x][y].setMerged(false);
            }
        }
    }

    private boolean sameAsGameArea(int[][] map) {
        if (gameArea.length != map.length) {
            return false;
        }

        for (int x = 0; x < gameSize; x++) {
            for (int y = 0; y < gameSize; y++) {
                if (gameArea[x][y].getValue() != map[x][y]) {
                    return false;
                }
            }
        }

        return true;
    }

    private void checkIfWon() {
        if (gameStatus == RUN) {
            int map[][] = getValues();
            for (int x = 0; x < gameSize; x++) {
                for (int y = 0; y < gameSize; y++) {
                    if (map[x][y] == 2048) {
                        gameStatus = WON;
                    }
                }
            }
        }
    }

    //Getter & Setter
    public int getGameSize() {
        return gameSize;
    }

    public int getGameStatus() {
        return gameStatus;
    }
}
