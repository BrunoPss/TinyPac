package pt.isec.pa.tinypac.model.data.wall;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Wall Class
 * <p>Class that represents the Wall Element</p>
 *
 * @ author Bruno Guiomar
 * @ version 1.0.0
 */

public class Wall extends Element {
    //Internal Data
    /**
     * Symbol of the Element (Wall)
     */
    public static final char SYMBOL = 'x';

    //Constructor
    /**
     * Constructor
     * @param gameData DataModel
     */
    public Wall(Game gameData) {
        super(gameData);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    /**
     * Gets Element Symbol
     * @return Element Symbol (Wall)
     */
    @Override
    public char getSymbol() {
        return 'x';
    }

    //Internal Functions


}