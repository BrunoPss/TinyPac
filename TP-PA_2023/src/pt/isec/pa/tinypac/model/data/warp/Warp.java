package pt.isec.pa.tinypac.model.data.warp;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.maze.Maze;

public class Warp extends Element {
    //Internal Data
    public static final char SYMBOL = 'W';

    //Constructor
    public Warp(Maze maze, int x, int y) {
        super(maze, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public char getSymbol() {
        return 'W';
    }

    //Internal Functions


}