package pt.isec.pa.tinypac.model.data.element;

import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.maze.IMazeElement;

/**
 * Element Abstract Base Class
 * <p>Abstract Base class that represents all concrete Elements</p>
 *
 * @ author Bruno Guiomar
 * @ version 1.0.0
 */

public abstract class Element implements IMazeElement {
    //Internal Data
    /**
     * Element Position
     */
    protected int x, y;

    /**
     * Game Data Model
     */
    protected Game gameData;

    //Constructor
    /**
     * Constructor
     * @param gameData Game Data Model
     * @param x Initial Position (x cord.)
     * @param y Initial Position (y cord.)
     */
    public Element(Game gameData, int x, int y) {
        this.x = x;
        this.y = y;
        this.gameData = gameData;
    }

    //Get Methods

    /**
     * Gets Element X Coordinate
     * @return Element X Coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets Element Y Coordinate
     * @return Element Y Coordinate
     */
    public int getY() {
        return this.y;
    }

    //Set Methods


    //Methods
    //GET ELEMENTO FROM DIRECTION()


    //Overrides


    //Internal Functions


}