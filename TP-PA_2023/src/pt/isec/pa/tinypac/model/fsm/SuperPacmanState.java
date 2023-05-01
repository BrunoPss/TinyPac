package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.maze.Maze;

public class SuperPacmanState extends GameStateAdapter {
    //Internal Data


    //Constructor
    SuperPacmanState(GameContext context, Maze maze) {
        super(context, maze);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public boolean disableEnhancedPacman() {
        changeState(new NormalRunState(context, maze));
        return true;
    }
    @Override
    public GameState getState() {
        return GameState.NORMALRUNSTATE;
    }

    //Internal Functions


}