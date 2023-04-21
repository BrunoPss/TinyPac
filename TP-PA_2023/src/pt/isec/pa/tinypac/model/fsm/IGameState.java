package pt.isec.pa.tinypac.model.fsm;

public interface IGameState {
    boolean up();
    boolean down();
    boolean left();
    boolean right();
    boolean pauseGame();
    boolean resumeGame();
    GameState getState();
}