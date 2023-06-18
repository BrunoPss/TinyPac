package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Super Pacman State Class
 * <p>Class that represents the Super Pacman State</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class SuperPacmanState extends GameStateAdapter {
    //Internal Data


    //Constructor
    /**
     * Constructor
     * @param context Game Context
     * @param gameData DataModel
     */
    SuperPacmanState(GameContext context, Game gameData) {
        super(context, gameData);
        System.out.println("SUPER PACMAN STATE");
        gameData.setEnchancedTimeout(20 - gameData.getCurrentLevel());
        gameData.setEnchancedPhase(true);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    /**
     * @see pt.isec.pa.tinypac.model.fsm.IGameState
     */
    @Override
    public boolean update() {
        gameData.checkEnchancedMode();
        gameData.checkLevelChange();
        gameData.evolveEntities();
        return true;
    }

    @Override
    public boolean up() {
        gameData.setPacmanDirection(Directions.UP);
        return true;
    }
    @Override
    public boolean down() {
        gameData.setPacmanDirection(Directions.DOWN);
        return true;
    }
    @Override
    public boolean left() {
        gameData.setPacmanDirection(Directions.LEFT);
        return true;
    }
    @Override
    public boolean right() {
        gameData.setPacmanDirection(Directions.RIGHT);
        return true;
    }
    @Override
    public boolean disableEnhancedPacman() {
        gameData.setEnchancedPhase(false);
        changeState(new NormalRunState(context, gameData));
        return true;
    }
    @Override
    public boolean pauseGame() {
        changeState(new PausedState(context, gameData, GameState.SUPERPACMANSTATE));
        //context.gameEngine.pause();
        return true;
    }
    @Override
    public boolean endGame() {
        changeState(new GameEndState(context, gameData));
        return true;
    }

    @Override
    public GameState getState() {
        return GameState.SUPERPACMANSTATE;
    }

    //Internal Functions


}