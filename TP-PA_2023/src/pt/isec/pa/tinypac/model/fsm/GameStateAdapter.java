package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.Game;

abstract class GameStateAdapter implements IGameState {
    //Internal Data
    protected Game gameData;
    protected GameContext context;

    //Constructor
    protected GameStateAdapter(GameContext context, Game gameData) {
        this.gameData = gameData;
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
    public boolean evolve() { return false; }
    @Override
    public boolean up() { return false; }
    @Override
    public boolean down() { return false; }
    @Override
    public boolean left() { return false; }
    @Override
    public boolean right() { return false; }
    @Override
    public boolean enhancedPacman() { return false; }
    @Override
    public boolean disableEnhancedPacman() { return false; }
    @Override
    public boolean pauseGame() { return false; }
    @Override
    public boolean resumeGame() { return false; }
    @Override
    public boolean restart() { return false; }
    @Override
    public boolean endGame() { return false; }
    @Override
    public boolean exitGame() { return false; }

    //Internal Functions


}