package pt.isec.pa.tinypac.model.data.portal;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Portal Class
 * <p>Class that represents the Portal Element</p>
 *
 * @ author Bruno Guiomar
 * @ version 1.0.0
 */

public class Portal extends Element {
    //Internal Data
    /**
     * Symbol of the Element (Portal)
     */
    public static final char SYMBOL = 'Y';

    //Constructor
    /**
     * Constructor
     * @param gameData Game Data Model
     * @param x Initial Position (x cord.)
     * @param y Initial Position (y cord.)
     */
    public Portal(Game gameData, int x, int y) {
        super(gameData, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    /**
     * Gets Element Symbol
     * @return Symbol of the Element (Portal)
     */
    @Override
    public char getSymbol() {
        return 'Y';
    }

    //Internal Functions


}