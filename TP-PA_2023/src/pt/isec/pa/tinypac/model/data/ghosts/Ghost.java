package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.maze.Maze;

abstract public class Ghost extends Element implements IGameEngineEvolve {
    //Internal Data


    //Constructor
    public Ghost(Maze maze, int x, int y) {
        super(maze, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

    }

    //Internal Functions


}