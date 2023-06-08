package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.Game;

public class PausedState extends GameStateAdapter {
    //Internal Data


    //Constructor
    PausedState(GameContext context, Game gameData) {
        super(context, gameData);
        System.out.println("PAUSED STATE");
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public boolean resumeGame() {
        changeState(new NormalRunState(context, gameData));
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