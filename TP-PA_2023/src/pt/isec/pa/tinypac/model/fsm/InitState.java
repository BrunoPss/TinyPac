package pt.isec.pa.tinypac.model.fsm;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.element.ElementType;
import pt.isec.pa.tinypac.model.data.maze.Maze;
import pt.isec.pa.tinypac.model.data.maze.MazeManager;

public class InitState extends GameStateAdapter {
    //Internal Data


    //Constructor
    InitState(GameContext context, Maze maze) {
        super(context, maze);
        super.context.entities = MazeManager.loadLevel(maze, "C:\\Projects\\TP-PA_2023\\TP-PA_2023\\src\\pt\\isec\\pa\\tinypac\\model\\data\\levels\\level01.txt");
        this.context.gameEngine.registerClient((IGameEngineEvolve) super.context.entities.get(ElementType.PACMAN));
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