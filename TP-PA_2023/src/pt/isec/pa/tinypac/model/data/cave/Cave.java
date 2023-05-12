package pt.isec.pa.tinypac.model.data.cave;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Cave Class
 * <p>Class that represents the Cave Element</p>
 *
 * @ author Bruno Guiomar
 * @ version 1.0.0
 */

public class Cave extends Element {
    //Internal Data
    /**
     * Symbol of the Element (Cave)
     */
    public static final char SYMBOL = 'y';

    //Constructor
    /**
     * Constructor
     * @param gameData Game Data Model
     * @param x Initial Position (x cord.)
     * @param y Initial Position (y cord.)
     */
    public Cave(Game gameData, int x, int y) {
        super(gameData, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    /**
     * Gets Element Symbol
     * @return Symbol of the Element (Cave)
     */
    @Override
    public char getSymbol() {
        return 'y';
    }

    //Internal Functions


}