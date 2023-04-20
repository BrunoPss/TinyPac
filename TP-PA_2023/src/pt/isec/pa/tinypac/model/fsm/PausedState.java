package pt.isec.pa.tinypac.model.fsm;
import pt.isec.pa.tinypac.model.data.maze.Maze;

public class PausedState extends GameStateAdapter {
    //Internal Data


    //Constructor
    PausedState(GameContext context, Maze maze) {
        super(context, maze);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public boolean resumeGame() {
        changeState(new NormalRunState(context, maze));
        context.gameEngine.resume();
        return true;
    }
    @Override
    public GameState getState() {
        return GameState.PAUSEDSTATE;
    }

    //Internal Functions


}