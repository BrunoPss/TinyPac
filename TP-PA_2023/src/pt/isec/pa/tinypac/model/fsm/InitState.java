package pt.isec.pa.tinypac.model.fsm;
import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.maze.MazeManager;

public class InitState extends GameStateAdapter {
    //Internal Data

    //Constructor
    InitState(GameContext context, Game gameData) {
        super(context, gameData);
        System.out.println("INIT");
        gameData.initMaze();
        MazeManager.loadLevel(gameData, gameData.getCurrentLevelFilePath());
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public boolean up() {
        gameData.getPacman().setDirection(Directions.UP);
        changeState(new NormalRunState(context, gameData));
        context.gameEngine.start(1000);
        return true;
    }
    @Override
    public boolean down() {
        gameData.getPacman().setDirection(Directions.DOWN);
        changeState(new NormalRunState(context, gameData));
        context.gameEngine.start(1000);
        return true;
    }
    @Override
    public boolean left() {
        gameData.getPacman().setDirection(Directions.LEFT);
        changeState(new NormalRunState(context, gameData));
        context.gameEngine.start(1000);
        return true;
    }
    @Override
    public boolean right() {
        gameData.getPacman().setDirection(Directions.RIGHT);
        changeState(new NormalRunState(context, gameData));
        context.gameEngine.start(1000);
        return true;
    }
    @Override
    public GameState getState() {
        return GameState.INITSTATE;
    }

    //Internal Functions


}