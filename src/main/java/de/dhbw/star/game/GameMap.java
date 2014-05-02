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
public class GameMap {

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;

    private int gameSize = 4;
    private ContentBox[][] gameArea;

    public GameMap() {
        gameArea = new ContentBox[gameSize][gameSize];
        createNewGameMap();
    }

    public GameMap(int gameMap[][]) {
        createNewGameMap(gameMap);
    }

    /**
     * Make a move on the GameMap
     *
     * @param direction
     */
    public void gameMove(int direction) {
        clearMergedAttributes();
        
        switch (direction) {
            //Move all boxes up
            case GameMap.UP:
                for (int x = 0; x < getGameSize(); x++) {
                    for (int y = 0; y < getGameSize(); y++) {
                        getContentBox(x, y).moveBoxValues(UP);
                    }
                }
                break;
            //Move all boxes down
            case GameMap.DOWN:
                for (int x = 0; x < getGameSize(); x++) {
                    for (int y = getGameSize() - 1; y >= 0; y--) {
                        getContentBox(x, y).moveBoxValues(DOWN);
                    }
                }
                break;
            //Move all boxes left
            case GameMap.LEFT:
                for (int y = 0; y < getGameSize(); y++) {
                    for (int x = 0; x < getGameSize(); x++) {
                        getContentBox(x, y).moveBoxValues(LEFT);
                    }
                }
                break;
            //Move all boxes right
            case GameMap.RIGHT:
                for (int y = 0; y < getGameSize(); y++) {
                    for (int x = getGameSize() - 1; x >= 0; x--) {
                        getContentBox(x, y).moveBoxValues(RIGHT);
                    }
                }
                break;
            default:
                return;
        }
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

    /**
     * This fucntion creates a new gameMap with all values 0
     */
    public void createNewGameMap() {
        for (int x = 0; x < gameSize; x++) {
            for (int y = 0; y < gameSize; y++) {
                gameArea[x][y] = new ContentBox(this, x, y);
            }
        }
    }

    /**
     * This function creates a new game Map and set the values from the given
     * int array
     *
     * @param gameMap
     */
    public void createNewGameMap(int gameMap[][]) {
        if (gameMap.length > 0 && gameMap.length == gameMap[0].length) {
            gameSize = gameMap.length;
            gameArea = new ContentBox[gameSize][gameSize];

            for (int x = 0; x < gameSize; x++) {
                for (int y = 0; y < gameSize; y++) {
                    gameArea[x][y] = new ContentBox(this, x, y, gameMap[x][y]);
                }
            }
        } else {
            createNewGameMap();
        }
    }

    /**
     * This function returns a ContentBox from the requested position
     *
     * @param xpos
     * @param ypos
     * @return
     */
    public ContentBox getContentBox(int xpos, int ypos) {
        if (xpos < 0 || ypos < 0 || xpos >= gameSize || ypos >= gameSize) {
            return null;
        }

        return gameArea[xpos][ypos];
    }

    public boolean equals(int[][] map) {
        if (gameArea.length != map.length && gameArea[0].length == map[0].length) {
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
    
    public static String intToDirection(int dir) {
        switch (dir) {
            case GameMap.UP:
                return "Up";
            case GameMap.DOWN:
                return "Down";
            case GameMap.LEFT:
                return "Left";
            case GameMap.RIGHT:
                return "Right";
            default:
                return Integer.toString(dir);
        }
    }

    //Getter & Setter
    public int getGameSize() {
        return gameSize;
    }

    //Private Section
     private void clearMergedAttributes() {
        for (int x = 0; x < getGameSize(); x++) {
            for (int y = 0; y < getGameSize(); y++) {
                getContentBox(x, y).setMerged(false);
            }
        }
    }
}
