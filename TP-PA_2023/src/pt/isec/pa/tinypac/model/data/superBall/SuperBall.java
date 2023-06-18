package pt.isec.pa.tinypac.model.data.superBall;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * SuperBall Class
 * <p>Class that represents the SuperBall Element</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class SuperBall extends Element {
    //Internal Data
    /**
     * Symbol of the Element (SuperBall)
     */
    public static final char SYMBOL = 'O';
    /**
     * State of the Element
     */
    public static boolean ACTIVE = false;

    //Constructor
    /**
     * Constructor
     * @param gameData Game Data Model
     */
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
     * @return Element Symbol (SuperBall)
     */
    @Override
    public char getSymbol() {
        return 'O';
    }

    //Internal Functions


}