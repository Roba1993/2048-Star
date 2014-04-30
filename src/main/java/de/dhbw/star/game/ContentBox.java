/*
 * The MIT License
 *
 * Copyright 2014 Robert Schütte.
 *
 */
package de.dhbw.star.game;

/**
 *
 * @author Robert Schütte
 */
public class ContentBox {



    private final GameMap gameMap;
    private int value = 0;
    private boolean merged = false;
    private int xpos;
    private int ypos;

    //Public Area
    public ContentBox(GameMap gameMap, int xpos, int ypos) {
        this.gameMap = gameMap;
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public ContentBox(GameMap gameMap, int xpos, int ypos, int value) {
        this.gameMap = gameMap;
        this.xpos = xpos;
        this.ypos = ypos;
        this.value = value;
    }

    //protected area
    /**
     * This fucntion moves the box in the direction
     *
     * @param direction The direction for the move
     */
    protected void moveBoxValues(int direction) {
        ContentBox nextBox;

        switch (direction) {
            case GameMap.UP:
                nextBox = gameMap.getContentBox(xpos, ypos - 1);
                break;
            case GameMap.DOWN:
                nextBox = gameMap.getContentBox(xpos, ypos + 1);
                break;
            case GameMap.LEFT:
                nextBox = gameMap.getContentBox(xpos - 1, ypos);
                break;
            case GameMap.RIGHT:
                nextBox = gameMap.getContentBox(xpos + 1, ypos);
                break;
            default:
                return;
        }

        if (nextBox != null) {
            //move box up when the box above is 0
            if (nextBox.getValue() == 0) {
                nextBox.setValue(this.value);
                this.value = 0;
            } //merge the boxes if the above box has the same value ans isn't merged
            else if (nextBox.getValue() == this.value && !nextBox.isMerged() && !this.merged) {
                nextBox.setValue(this.value + nextBox.getValue());
                nextBox.setMerged(true);
                this.value = 0;
            } //when there was no action, the box hasn't other move options

            //call the above box to move forward up
            nextBox.moveBoxValues(direction);
        }
    }

    //Getter & Setter
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isMerged() {
        return merged;
    }

    public void setMerged(boolean merged) {
        this.merged = merged;
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }

}
