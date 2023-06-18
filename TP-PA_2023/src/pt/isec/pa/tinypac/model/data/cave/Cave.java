package pt.isec.pa.tinypac.model.data.cave;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Cave Class
 * <p>Class that represents the Cave Element</p>
 * @author Bruno Guiomar
 * @version 1.0.0
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
     * @param gameData DataModel
     */
    public Cave(Game gameData) {
        super(gameData);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    /**
     * Gets Element Symbol
     * @return Element Symbol (Cave)
     */
    @Override
    public char getSymbol() {
        return 'y';
    }

    //Internal Functions


}