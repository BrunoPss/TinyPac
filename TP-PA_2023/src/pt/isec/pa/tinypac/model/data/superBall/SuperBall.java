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
    public static boolean ACTIVE = false;

    //Constructor
    public SuperBall(Game gameData) {
        super(gameData);
        ACTIVE = true;
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