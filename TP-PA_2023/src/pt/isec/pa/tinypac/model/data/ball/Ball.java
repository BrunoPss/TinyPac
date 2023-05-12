package pt.isec.pa.tinypac.model.data.ball;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Ball Class
 * <p>Class that represents the Ball Element</p>
 *
 * @ author Bruno Guiomar
 * @ version 1.0.0
 */

public class Ball extends Element {
    //Internal Data
    /**
     * Symbol of the Element (Ball)
     */
    public static final char SYMBOL = 'o';

    //Constructor
    /**
     * Constructor
     * @param gameData Game Data Model
     * @param x Initial Position (x cord.)
     * @param y Initial Position (y cord.)
     */
    public Ball(Game gameData, int x, int y) {
        super(gameData, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    /**
     * Gets Element Symbol
     * @return Symbol of the Element (Ball)
     */
    @Override
    public char getSymbol() {
        return 'o';
    }

    //Internal Functions


}