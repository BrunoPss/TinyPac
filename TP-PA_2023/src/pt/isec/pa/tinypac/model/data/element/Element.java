package pt.isec.pa.tinypac.model.data.element;

import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.maze.IMazeElement;

import java.io.Serializable;

/**
 * Element Class
 * <p>Abstract Class that represents the Elements</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public abstract class Element implements IMazeElement, Serializable {
    //Internal Data
    /**
     * Data Model
     */
    protected Game gameData;

    //Constructor
    /**
     * Constructor
     * @param gameData DataModel
     */
    public Element(Game gameData) {
        this.gameData = gameData;
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides


    //Internal Functions


}