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

    protected static final int UP = 0;
    protected static final int DOWN = 1;
    protected static final int RIGHT = 2;
    protected static final int LEFT = 3;

    private final CoreGame coreGame;
    private int value = 0;
    private boolean merged = false;
    private int xpos;
    private int ypos;

    //Public Area
    public ContentBox(CoreGame coreGame, int xpos, int ypos) {
        this.coreGame = coreGame;
        this.xpos = xpos;
        this.ypos = ypos;
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
            case UP:
                nextBox = coreGame.getContentBox(xpos, ypos - 1);
                break;
            case DOWN:
                nextBox = coreGame.getContentBox(xpos, ypos + 1);
                break;
            case LEFT:
                nextBox = coreGame.getContentBox(xpos - 1, ypos);
                break;
            case RIGHT:
                nextBox = coreGame.getContentBox(xpos + 1, ypos);
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
