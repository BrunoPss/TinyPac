package pt.isec.pa.tinypac.model.fsm;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;
import pt.isec.pa.tinypac.model.fsm.PacmanFreeState;

public class InitState extends GameStateAdapter {
    //Internal Data


    //Constructor
    InitState(GameContext context, Game game) {
        super(context, game);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public boolean actionKey() {
        changeState(new PacmanFreeState(context, game));
        return true;
    }
    @Override
    public GameState getState() {
        return GameState.INITSTATE;
    }

    //Internal Functions


}