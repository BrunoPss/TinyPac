package pt.isec.pa.tinypac.model.data.element;

import pt.isec.pa.tinypac.model.data.Directions;
import pt.isec.pa.tinypac.model.data.maze.IMazeElement;
import pt.isec.pa.tinypac.model.data.maze.Maze;

public abstract class Element implements IMazeElement {
    //Internal Data
    protected Maze maze;
    protected int x, y;

    //Constructor
    public Element(Maze maze, int x, int y) {
        this.maze = maze;
        this.x = x;
        this.y = y;
    }

    //Get Methods


    //Set Methods


    //Methods
    //GET ELEMENTO FROM DIRECTION()


    //Overrides


    //Internal Functions


}