package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.Game;

public class GameEnd extends GameStateAdapter {
    //Internal Data


    //Constructor
    GameEnd(GameContext context, Game gameData) {
        super(context, gameData);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public GameState getState() {
        return GameState.GAME_END;
    }

    //Internal Functions


}