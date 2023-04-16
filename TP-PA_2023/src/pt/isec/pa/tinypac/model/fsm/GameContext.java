package pt.isec.pa.tinypac.model.fsm;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.game.Game;

public class GameContext implements IGameEngineEvolve {
    //Internal Data
    IGameState state;
    IGameEngine gameEngine;
    Game game;
    int globalTimer = 0;

    //Constructor
    public GameContext() {
        this.game = new Game();
        this.gameEngine = new GameEngine();
        //gameEngine.registerClient(this.game);
        gameEngine.registerClient(this);
        this.state = new InitState(this, game);
    }

    //Get Methods
    public GameState getState() {
        return state.getState();
    }

    //Set Methods
    void changeState(IGameState newState) {
        this.state = newState;
    }

    //Methods
    public boolean actionkey() {
        //gameEngine.start(1000);
        return state.actionKey();
    }
    public boolean pacmanFreeTimeOut() {
        return state.pacmanFreeTimeOut();
    }
    public boolean enhancedPacman() {
        return state.enhancedPacman();
    }
    public boolean disableEnhancedPacman() {
        return state.disableEnhancedPacman();
    }
    public boolean pauseGame() {
        return state.pauseGame();
    }
    public boolean resumeGame() {
        return state.resumeGame();
    }
    public boolean endGame() {
        return state.endGame();
    }

    //Overrides
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        System.out.printf("[%d] %d\n",currentTime,++globalTimer);
        if (globalTimer >= 5) pacmanFreeTimeOut();
    }

    //Internal Functions


}