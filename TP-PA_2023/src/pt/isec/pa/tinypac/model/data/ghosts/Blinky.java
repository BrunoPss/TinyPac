package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.model.data.maze.Maze;

public class Blinky extends Ghost {
    //Internal Data


    //Constructor
    public Blinky(Maze maze, int x, int y) {
        super(maze, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public char getSymbol() {
        return 'B';
    }

    //Internal Functions


}