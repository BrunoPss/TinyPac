package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Inky Class
 * <p>Class that represents the Inky Ghost Element</p>
 *
 * @ author Bruno Guiomar
 * @ version 1.0.0
 */

public class Inky extends Ghost {
    //Internal Data
    public static final char SYMBOL = 'I';
    public static boolean ACTIVE = false;

    //Constructor
    /**
     * Constructor
     * @param gameData Game Data Model
     * @param x Initial Position (x cord.)
     * @param y Initial Position (y cord.)
     */
    public Inky(Game gameData, int x, int y) {
        super(gameData, x, y);
        ACTIVE = true;
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public void evolve() {

    }
    /**
     * Gets Element Symbol
     * @return Symbol of the Element (Inky)
     */
    @Override
    public char getSymbol() {
        return 'I';
    }

    //Internal Functions


}