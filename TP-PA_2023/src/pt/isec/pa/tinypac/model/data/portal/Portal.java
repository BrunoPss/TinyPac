package pt.isec.pa.tinypac.model.data.portal;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.maze.Maze;

public class Portal extends Element {
    //Internal Data
    public static final char SYMBOL = 'Y';

    //Constructor
    public Portal(Maze maze, int x, int y) {
        super(maze, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public char getSymbol() {
        return 'Y';
    }

    //Internal Functions


}