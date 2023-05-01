package pt.isec.pa.tinypac.model.fsm;
import pt.isec.pa.tinypac.model.data.maze.Maze;

public class NormalRunState extends GameStateAdapter {
    //Internal Data


    //Constructor
    NormalRunState(GameContext context, Maze maze) {
        super(context, maze);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public boolean up() {
        changeState(new NormalRunState(context, maze));
        return true;
    }
    @Override
    public boolean down() {
        changeState(new NormalRunState(context, maze));
        return true;
    }
    @Override
    public boolean left() {
        changeState(new NormalRunState(context, maze));
        return true;
    }
    @Override
    public boolean right() {
        changeState(new NormalRunState(context, maze));
        return true;
    }
    @Override
    public boolean enhancedPacman() {
        changeState(new SuperPacmanState(context, maze));
        return true;
    }
    @Override
    public boolean pauseGame() {
        changeState(new PausedState(context, maze));
        context.gameEngine.pause();
        return true;
    }
    @Override
    public boolean restart() {
        changeState(new InitState(context, maze));
        return true;
    }
    @Override
    public GameState getState() {
        return GameState.NORMALRUNSTATE;
    }

    //Internal Functions


}