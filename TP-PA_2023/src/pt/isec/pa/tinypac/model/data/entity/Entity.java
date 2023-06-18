package pt.isec.pa.tinypac.model.data.entity;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Entity Class
 * <p>Abstract class that represents the entities</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public abstract class Entity extends Element {
    //Internal Data
    /**
     * Entity coordinates
     */
    protected int x, y;

    //Constructor
    /**
     * Constructor
     * @param gameData DataModel
     * @param x coordinate (x)
     * @param y coordinate (y)
     */
    public Entity(Game gameData, int x, int y) {
        super(gameData);
        this.x = x;
        this.y = y;
    }

    //Get Methods
    /**
     * Get X Coordinate
     * @return x - current x position
     */
    public int getX() { return x; }
    /**
     * Get Y Coordinate
     * @return y - current y position
     */
    public int getY() { return y; }

    //Set Methods


    //Methods
    /**
     * Abstract method to move entities
     */
    abstract public void move();

    //Overrides


    //Internal Functions


}