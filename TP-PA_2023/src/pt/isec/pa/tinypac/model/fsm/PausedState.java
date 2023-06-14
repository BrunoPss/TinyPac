package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.Game;

public class PausedState extends GameStateAdapter {
    //Internal Data
    GameState previousState;

    //Constructor
    PausedState(GameContext context, Game gameData, GameState previousState) {
        super(context, gameData);
        this.previousState = previousState;
        System.out.println("PAUSED STATE");
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
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