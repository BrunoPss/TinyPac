package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Clyde Class
 * <p>Class that represents the Clyde Ghost Element</p>
 *
 * @ author Bruno Guiomar
 * @ version 1.0.0
 */

public class Clyde extends Ghost {
    //Internal Data


    //Constructor
    /**
     * Constructor
     * @param gameData Game Data Model
     * @param x Initial Position (x cord.)
     * @param y Initial Position (y cord.)
     */
    public Clyde(Game gameData, int x, int y) {
        super(gameData, x, y);
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
     * @return Symbol of the Element (Clyde)
     */
    @Override
    public char getSymbol() {
        return 'C';
    }

    //Internal Functions


}