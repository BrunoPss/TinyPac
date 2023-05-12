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


    //Constructor
    /**
     * Constructor
     * @param gameData Game Data Model
     * @param x Initial Position (x cord.)
     * @param y Initial Position (y cord.)
     */
    public Inky(Game gameData, int x, int y) {
        super(gameData, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

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