package pt.isec.pa.tinypac.model.data.element;

import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.maze.IMazeElement;

import java.io.Serializable;

/**
 * Element Abstract Base Class
 * <p>Abstract Base class that represents all concrete Elements</p>
 *
 * @ author Bruno Guiomar
 * @ version 1.0.0
 */

public abstract class Element implements IMazeElement, Serializable {
    //Internal Data
    protected Game gameData;

    //Constructor
    public Element(Game gameData) {
        this.gameData = gameData;
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides


    //Internal Functions


}