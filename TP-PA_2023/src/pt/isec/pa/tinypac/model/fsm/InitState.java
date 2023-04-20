package pt.isec.pa.tinypac.model.fsm;
import pt.isec.pa.tinypac.model.data.maze.Maze;

public class InitState extends GameStateAdapter {
    //Internal Data


    //Constructor
    InitState(GameContext context, Maze maze) {
        super(context, maze);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public boolean up() {
        changeState(new NormalRunState(context, maze));
        context.gameEngine.start(1000);
        return true;
    }
    @Override
    public boolean down() {
        changeState(new NormalRunState(context, maze));
        context.gameEngine.start(1000);
        return true;
    }
    @Override
    public boolean left() {
        changeState(new NormalRunState(context, maze));
        context.gameEngine.start(1000);
        return true;
    }
    @Override
    public boolean right() {
        changeState(new NormalRunState(context, maze));
        context.gameEngine.start(1000);
        return true;
    }
    @Override
    public GameState getState() {
        return GameState.INITSTATE;
    }

    //Internal Functions


}