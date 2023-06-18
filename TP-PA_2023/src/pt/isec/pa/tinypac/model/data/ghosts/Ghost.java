package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.entity.Entity;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Ghost Class
 * <p>Abstract class that represents the Ghosts</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

abstract public class Ghost extends Entity {
    //Internal Data
    /**
     * Represents the current Direction
     */
    protected Directions direction;
    /**
     * Ghost presence in the cave
     */
    protected boolean cave = true;

    //Constructor
    /**
     * Constructor
     * @param gameData DataModel
     * @param x coordinate (x)
     * @param y coordinate (y)
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