package pt.isec.pa.tinypac.model.fsm;
import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.maze.MazeManager;

/**
 * Init State State Class
 * <p>Class that represents the Init State</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class InitState extends GameStateAdapter {
    //Internal Data

    //Constructor
    /**
     * Constructor
     * @param context Game Context
     * @param gameData DataModel
     */
    InitState(GameContext context, Game gameData) {
        super(context, gameData);
        System.out.println("INIT STATE");
        gameData.initMaze();
        gameData.initializeEvolveInstantsPacman();
        gameData.initializeEvolveInstantsGhosts();
        gameData.initTotalBalls();
        MazeManager.loadLevel(gameData, gameData.getCurrentLevelFilePath());
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    /**
     * @see pt.isec.pa.tinypac.model.fsm.IGameState
     */
    @Override
    public boolean up() {
        gameData.setPacmanDirection(Directions.UP);
        changeState(new NormalRunState(context, gameData));
        //context.gameEngine.start(1000);
        return true;
    }
    @Override
    public boolean down() {
        gameData.setPacmanDirection(Directions.DOWN);
        changeState(new NormalRunState(context, gameData));
        //context.gameEngine.start(1000);
        return true;
    }
    @Override
    public boolean left() {
        gameData.setPacmanDirection(Directions.LEFT);
        changeState(new NormalRunState(context, gameData));
        //context.gameEngine.start(1000);
        return true;
    }
    @Override
    public boolean right() {
        gameData.setPacmanDirection(Directions.RIGHT);
        changeState(new NormalRunState(context, gameData));
        //context.gameEngine.start(1000);
        return true;
    }
    @Override
    public GameState getState() {
        return GameState.INITSTATE;
    }

    //Internal Functions


}