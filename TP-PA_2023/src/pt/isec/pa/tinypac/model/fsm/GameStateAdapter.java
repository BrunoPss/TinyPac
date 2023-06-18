package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.Game;

import java.io.Serializable;

/**
 * Game State Adapter Class
 * <p>Class that represents the adapter for the Finite State Machine</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

abstract class GameStateAdapter implements IGameState, Serializable {
    //Internal Data
    /**
     * Data Model
     */
    protected Game gameData;
    /**
     * Game Context
     */
    protected GameContext context;

    //Constructor
    /**
     * Constructor
     * @param context Game Context
     * @param gameData DataModel
     */
    protected GameStateAdapter(GameContext context, Game gameData) {
        this.gameData = gameData;
        this.context = context;
    }

    //Get Methods


    //Set Methods


    //Methods

    /**
     * Change current state
     * @param newState new State
     */
    protected void changeState(IGameState newState) {
        context.changeState(newState);
    }

    //Overrides

    /**
     * @see pt.isec.pa.tinypac.model.fsm.IGameState
     */
    @Override
    public boolean update() { return false; }
    @Override
    public boolean up() { return false; }
    @Override
    public boolean down() { return false; }
    @Override
    public boolean left() { return false; }
    @Override
    public boolean right() { return false; }
    @Override
    public boolean enhancedPacman() { return false; }
    @Override
    public boolean disableEnhancedPacman() { return false; }
    @Override
    public boolean pauseGame() { return false; }
    @Override
    public boolean resumeGame() { return false; }
    @Override
    public boolean restart() { return false; }
    @Override
    public boolean endGame() { return false; }
    @Override
    public boolean exitGame() { return false; }

    //Internal Functions


}