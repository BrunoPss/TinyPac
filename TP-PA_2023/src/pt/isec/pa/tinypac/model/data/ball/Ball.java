package pt.isec.pa.tinypac.model.data.ball;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Ball Class
 * <p>Class that represents the Ball Element</p>
 * @author Bruno Guiomar
 * @version 1.0.0
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
     * @param gameData DataModel
     */
    public Ball(Game gameData) {
        super(gameData);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides

    /**
     * Gets Element Symbol
     * @return Element Symbol (Ball)
     */
    @Override
    public char getSymbol() {
        return 'o';
    }

    //Internal Functions


}