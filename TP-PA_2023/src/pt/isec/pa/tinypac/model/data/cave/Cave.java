package pt.isec.pa.tinypac.model.data.cave;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.maze.Maze;

public class Cave extends Element {
    //Internal Data
    public static final char SYMBOL = 'y';

    //Constructor
    public Cave(Maze maze, int x, int y) {
        super(maze, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public char getSymbol() {
        return 'y';
    }

    //Internal Functions


}