package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Paused State Class
 * <p>Class that represents the Paused State</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class PausedState extends GameStateAdapter {
    //Internal Data
    GameState previousState;

    //Constructor
    /**
     * Constructor
     * @param context Game Context
     * @param gameData DataModel
     */
    PausedState(GameContext context, Game gameData, GameState previousState) {
        super(context, gameData);
        this.previousState = previousState;
        System.out.println("PAUSED STATE");
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    /**
     * @see pt.isec.pa.tinypac.model.fsm.IGameState
     */
    @Override
    public boolean resumeGame() {
        if (previousState == GameState.NORMALRUNSTATE)
            changeState(new NormalRunState(context, gameData));
        else if (previousState == GameState.SUPERPACMANSTATE)
            changeState(new SuperPacmanState(context, gameData));
        //context.gameEngine.resume();
        return true;
    }
    @Override
    public boolean exitGame() {
        changeState(new GameEndState(context, gameData));
        return true;
    }
    @Override
    public GameState getState() {
        return GameState.PAUSEDSTATE;
    }

    //Internal Functions


}