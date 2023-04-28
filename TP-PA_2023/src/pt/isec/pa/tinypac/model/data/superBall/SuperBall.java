package pt.isec.pa.tinypac.model.data.superBall;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.maze.Maze;

public class SuperBall extends Element {
    //Internal Data
    public static final char SYMBOL = 'O';

    //Constructor
    public SuperBall(Maze maze, int x, int y) {
        super(maze, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public char getSymbol() {
        return 'O';
    }

    //Internal Functions


}