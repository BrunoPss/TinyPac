package pt.isec.pa.tinypac.model.fsm;
import pt.isec.pa.tinypac.model.data.maze.Maze;

abstract class GameStateAdapter implements IGameState {
    //Internal Data
    protected Maze maze;
    protected GameContext context;

    //Constructor
    protected GameStateAdapter(GameContext context, Maze maze) {
        this.maze = maze;
        this.context = context;
    }

    //Get Methods


    //Set Methods


    //Methods
    protected void changeState(IGameState newState) {
        context.changeState(newState);
    }

    //Overrides
    @Override
    public boolean up() { return false; }
    @Override
    public boolean down() { return false; }
    @Override
    public boolean left() { return false; }
    @Override
    public boolean right() { return false; }
    @Override
    public boolean pauseGame() { return false; }
    @Override
    public boolean resumeGame() { return false; }

    //Internal Functions


}