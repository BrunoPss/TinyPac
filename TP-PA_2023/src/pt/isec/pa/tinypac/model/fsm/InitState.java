package pt.isec.pa.tinypac.model.fsm;
import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.entity.EntityType;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.maze.MazeManager;
import pt.isec.pa.tinypac.model.data.pacman.Pacman;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;
import pt.isec.pa.tinypac.model.fsm.NormalRunState;

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
        ((Pacman) gameData.getEntity(EntityType.PACMAN)).setDirection(Directions.UP);
        changeState(new NormalRunState(context, gameData));
        //context.gameEngine.start(1000);
        return true;
    }
    @Override
    public boolean down() {
        ((Pacman) gameData.getEntity(EntityType.PACMAN)).setDirection(Directions.DOWN);
        changeState(new NormalRunState(context, gameData));
        //context.gameEngine.start(1000);
        return true;
    }
    @Override
    public boolean left() {
        ((Pacman) gameData.getEntity(EntityType.PACMAN)).setDirection(Directions.LEFT);
        changeState(new NormalRunState(context, gameData));
        //context.gameEngine.start(1000);
        return true;
    }
    @Override
    public boolean right() {
        ((Pacman) gameData.getEntity(EntityType.PACMAN)).setDirection(Directions.RIGHT);
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