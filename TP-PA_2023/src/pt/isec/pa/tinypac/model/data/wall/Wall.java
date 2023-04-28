package pt.isec.pa.tinypac.model.data.wall;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.maze.Maze;

public class Wall extends Element {
    //Internal Data
    public static final char SYMBOL = 'x';

    //Constructor
    public Wall(Maze maze, int x, int y) {
        super(maze, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public char getSymbol() {
        return 'x';
    }

    //Internal Functions


}