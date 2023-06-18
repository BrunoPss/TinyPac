package pt.isec.pa.tinypac.model.data.portal;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Portal Class
 * <p>Class that represents the Portal Element</p>
 *
 * @author Bruno Guiomar
 * @version 1.0.0
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
     */
    public Portal(Game gameData) {
        super(gameData);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    /**
     * Gets Element Symbol
     * @return Element Symbol (Portal)
     */
    @Override
    public char getSymbol() {
        return 'Y';
    }

    //Internal Functions


}