package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.Game;

public class SuperPacmanState extends GameStateAdapter {
    //Internal Data


    //Constructor
    SuperPacmanState(GameContext context, Game gameData) {
        super(context, gameData);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public boolean disableEnhancedPacman() {
        changeState(new NormalRunState(context, gameData));
        return true;
    }
    @Override
    public GameState getState() {
        return GameState.NORMALRUNSTATE;
    }

    //Internal Functions


}