package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.entity.Entity;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Ghost Abstract Base Class
 * <p>Abstract Base class that represents all concrete Ghosts</p>
 *
 * @ author Bruno Guiomar
 * @ version 1.0.0
 */

abstract public class Ghost extends Entity {
    //Internal Data
    //protected int initTime = 2; //5
    protected Directions direction;
    protected boolean cave = true;

    //Constructor
    /**
     * Constructor
     * @param gameData Game Data Model
     * @param x Initial Position (x cord.)
     * @param y Initial Position (y cord.)
     */
    public Ghost(Game gameData, int x, int y) {
        super(gameData, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides


    //Internal Functions


}