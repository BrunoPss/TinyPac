package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.Game;

public class GameEndState extends GameStateAdapter {
    //Internal Data


    //Constructor
    GameEndState(GameContext context, Game game) {
        super(context, game);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public GameState getState() {
        return GameState.GAMEENDSTATE;
    }

    //Internal Functions


}