package pt.isec.pa.tinypac.model.data.fruit;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.maze.Maze;

public class Fruit extends Element {
    //Internal Data
    public static final char SYMBOL = 'F';

    //Constructor
    public Fruit(Maze maze, int x, int y) {
        super(maze, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public char getSymbol() {
        return 'F';
    }

    //Internal Functions


}