package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.model.data.maze.Maze;

public class Clyde extends Ghost {
    //Internal Data


    //Constructor
    public Clyde(Maze maze, int x, int y) {
        super(maze, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public char getSymbol() {
        return 'C';
    }

    //Internal Functions


}