package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.Game;

public class PausedState extends GameStateAdapter {
    //Internal Data


    //Constructor
    PausedState(GameContext context, Game game) {
        super(context, game);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public boolean resumeGame() {
        changeState(new NormalRunState(context, game));
        return true;
    }
    @Override
    public GameState getState() {
        return GameState.PAUSEDSTATE;
    }

    //Internal Functions


}