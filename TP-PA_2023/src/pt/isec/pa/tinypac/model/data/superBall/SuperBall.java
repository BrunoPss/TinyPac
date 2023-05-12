package pt.isec.pa.tinypac.model.data.superBall;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Super Ball Class
 * <p>Class that represents the Super Ball Element</p>
 *
 * @ author Bruno Guiomar
 * @ version 1.0.0
 */

public class SuperBall extends Element {
    //Internal Data
    /**
     * Symbol of the Element (Super Ball)
     */
    public static final char SYMBOL = 'O';

    //Constructor
    /**
     * Constructor
     * @param gameData Game Data Model
     * @param x Initial Position (x cord.)
     * @param y Initial Position (y cord.)
     */
    public SuperBall(Game gameData, int x, int y) {
        super(gameData, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    /**
     * Gets Element Symbol
     * @return Symbol of the Element (Super Ball)
     */
    @Override
    public char getSymbol() {
        return 'O';
    }

    //Internal Functions


}