/*
 * The MIT License
 *
 * Copyright 2014 Robert Schütte
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
public class Strategie {

    private GameMap gameMap;

    public Strategie(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    /**
     * This function try's to give points for a strategy.
     * @return 
     */
    public int getStrategieRating() {
        int rating = 0;

        List<ContentBox> cboxes = Util.getBiggestNumber(gameMap, 100000);

        for (ContentBox cbox : cboxes) {
            if(inEdge(cbox)) {
                rating += 30;
                
                List<ContentBox> lboxes = Util.getBiggestNumbers(gameMap, 4);
                if(inLineX(lboxes) || inLineY(lboxes)) {
                    rating += 70;
                }
            }
            break;
        }

        return rating;
    }

    private boolean inEdge(ContentBox cbox) {
        return (cbox.getXpos() == 0 || cbox.getXpos() == gameMap.getGameSize() - 1)
                && (cbox.getYpos() == 0 || cbox.getYpos() == gameMap.getGameSize() - 1);
    }
    
    private boolean inLineX(List<ContentBox> boxes) {
        int x = boxes.get(0).getXpos();

        for(ContentBox box : boxes) {
            if(box.getXpos() != x) {
                return false;
            }
        }
        
        return true;
    }
    
        private boolean inLineY(List<ContentBox> boxes) {
        int y = boxes.get(0).getYpos();

        for(ContentBox box : boxes) {
            if(box.getYpos() != y) {
                return false;
            }
        }
        
        return true;
    }
}
