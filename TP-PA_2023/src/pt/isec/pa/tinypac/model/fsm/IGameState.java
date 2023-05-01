package pt.isec.pa.tinypac.model.fsm;


public interface IGameState {
    boolean up();
    boolean down();
    boolean left();
    boolean right();
    boolean enhancedPacman();
    boolean disableEnhancedPacman();
    boolean pauseGame();
    boolean resumeGame();
    boolean restart();
    boolean endGame();
    GameState getState();
}