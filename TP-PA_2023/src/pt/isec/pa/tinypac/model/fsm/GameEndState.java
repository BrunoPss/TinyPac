package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * Game End State Class
 * <p>Class that represents the Game End State</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class GameEndState extends GameStateAdapter {
    //Internal Data


    //Constructor
    /**
     * Constructor
     * @param context Game Context
     * @param gameData DataModel
     */
    GameEndState(GameContext context, Game gameData) {
        super(context, gameData);
        System.out.println("Game End State");
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides

    /**
     * Gets the current State
     * @return
     */
    @Override
    public GameState getState() {
        return GameState.GAME_ENDSTATE;
    }

    //Internal Functions


}