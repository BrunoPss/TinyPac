package pt.isec.pa.tinypac.model.fsm;

/**
 * Game State Interface
 * <p>Class that represents the Game State Interface</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public interface IGameState {
    /**
     * Update game elements
     * @return status
     */
    boolean update();

    /**
     * Sets Pacman direction Up
     * @return status
     */
    boolean up();

    /**
     * Sets Pacman direction Down
     * @return status
     */
    boolean down();

    /**
     * Sets Pacman direction Left
     * @return status
     */
    boolean left();

    /**
     * Sets Pacman direction Right
     * @return status
     */
    boolean right();

    /**
     * Sets Game in enhanced Mode
     * @return status
     */
    boolean enhancedPacman();

    /**
     * Disables Enhanced mode
     * @return status
     */
    boolean disableEnhancedPacman();

    /**
     * Pauses game
     * @return status
     */
    boolean pauseGame();

    /**
     * Resumes game
     * @return status
     */
    boolean resumeGame();

    /**
     * Restarts the level
     * @return status
     */
    boolean restart();

    /**
     * Ends the game
     * @return status
     */
    boolean endGame();

    /**
     * Exit the game
     * @return status
     */
    boolean exitGame();

    /**
     * Gets the current State
     * @return current State
     */
    GameState getState();
}