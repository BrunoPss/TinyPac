package pt.isec.pa.tinypac.model.fsm;
import pt.isec.pa.tinypac.model.data.game.Game;

public class PacmanFreeState extends GameStateAdapter {
    //Internal Data


    //Constructor
    PacmanFreeState(GameContext context, Game game) {
        super(context, game);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public boolean pacmanFreeTimeOut() {
        changeState(new NormalRunState(context, game));
        return true;
    }
    @Override
    public GameState getState() {
        return GameState.PACMANFREESTATE;
    }

    //Internal Functions


}