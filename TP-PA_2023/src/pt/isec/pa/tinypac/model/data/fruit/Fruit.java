package pt.isec.pa.tinypac.model.data.fruit;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Fruit Class
 * <p>Class that represents the Fruit Element</p>
 *
 * @ author Bruno Guiomar
 * @ version 1.0.0
 */

public class Fruit extends Element {
    //Internal Data
    /**
     * Symbol of the Element (Fruit)
     */
    public static final char SYMBOL = 'F';

    //Constructor
    /**
     * Constructor
     * @param gameData Game Data Model
     * @param x Initial Position (x cord.)
     * @param y Initial Position (y cord.)
     */
    public Fruit(Game gameData, int x, int y) {
        super(gameData, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    /**
     * Gets Element Symbol
     * @return Symbol of the Element (Fruit)
     */
    @Override
    public char getSymbol() {
        return 'F';
    }

    //Internal Functions


}