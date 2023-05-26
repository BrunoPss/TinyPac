package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.Game;

public class GameEndState extends GameStateAdapter {
    //Internal Data


    //Constructor
    GameEndState(GameContext context, Game gameData) {
        super(context, gameData);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public GameState getState() {
        return GameState.GAME_ENDSTATE;
    }

    //Internal Functions


}