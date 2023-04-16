package pt.isec.pa.tinypac.model.fsm;
import pt.isec.pa.tinypac.model.data.game.Game;

public class NormalRunState extends GameStateAdapter {
    //Internal Data


    //Constructor
    NormalRunState(GameContext context, Game game) {
        super(context, game);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public boolean enhancedPacman() {
        changeState(new SuperPacmanState(context, game));
        return true;
    }
    @Override
    public boolean pauseGame() {
        changeState(new PausedState(context, game));
        return true;
    }
    @Override
    public boolean endGame() {
        changeState(new GameEndState(context, game));
        return true;
    }
    @Override
    public GameState getState() {
        return GameState.NORMALRUNSTATE;
    }

    //Internal Functions


}