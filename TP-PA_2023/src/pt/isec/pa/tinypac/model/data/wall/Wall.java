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
     * @param gameData Game Data Model
     * @param x Initial Position (x cord.)
     * @param y Initial Position (y cord.)
     */
    public Wall(Game gameData, int x, int y) {
        super(gameData, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    /**
     * Gets Element Symbol
     * @return Symbol of the Element (Wall)
     */
    @Override
    public char getSymbol() {
        return 'x';
    }

    //Internal Functions


}