package pt.isec.pa.tinypac.model.fsm;

public interface IGameState {
    boolean actionKey();
    boolean pacmanFreeTimeOut();
    boolean enhancedPacman();
    boolean disableEnhancedPacman();
    boolean pauseGame();
    boolean resumeGame();
    boolean endGame();
    GameState getState();
}