package pt.isec.pa.tinypac.model.fsm;
import pt.isec.pa.tinypac.model.data.game.Game;

abstract class GameStateAdapter implements IGameState {
    //Internal Data
    protected Game game;
    protected GameContext context;

    //Constructor
    protected GameStateAdapter(GameContext context, Game game) {
        this.game = game;
        this.context = context;
    }

    //Get Methods


    //Set Methods


    //Methods
    protected void changeState(IGameState newState) {
        context.changeState(newState);
    }

    //Overrides
    @Override
    public boolean actionKey() { return false; }
    @Override
    public boolean pacmanFreeTimeOut() { return false; }
    @Override
    public boolean enhancedPacman() { return false; }
    @Override
    public boolean disableEnhancedPacman() { return false; }
    @Override
    public boolean pauseGame() { return false; }
    @Override
    public boolean resumeGame() { return false; }
    @Override
    public boolean endGame() { return false; }

    //Internal Functions


}