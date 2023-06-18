package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.element.ElementType;
import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.entity.EntityType;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.player.Player;
import pt.isec.pa.tinypac.ui.gui.resources.presets.ColorPreset;
import pt.isec.pa.tinypac.ui.gui.resources.presets.EQPreset;
import pt.isec.pa.tinypac.ui.gui.resources.presets.MusicPreset;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Game Context Class
 * <p>Class that represents the Game Context</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class GameContext implements Serializable {
    //Internal Data
    /**
     * Represents the current state
     */
    IGameState state;
    /**
     * Represents the Data Model
     */
    Game gameData;

    //Constructor

    /**
     * Constructor
     */
    public GameContext() {
        this.gameData = new Game(this);
        this.state = new InitState(this, gameData);
    }

    //Get Methods

    /**
     * getState
     * @return current State
     */
    public GameState getState() {
        return state.getState();
    }

    /**
     * getPacmanPoints
     * @return pacman Points
     */
    public int getPacmanPoints() { return gameData.getPacmanPoints(); }

    /**
     * getMazeHeight
     * @return MazeHeight
     */
    public int getMazeHeight() { return gameData.getMazeHeight(); }

    /**
     * getMazeLength
     * @return MazeLength
     */
    public int getMazeLength() { return gameData.getMazeLength(); }

    /**
     * getMazeElement
     * @param x x coordinate
     * @param y y coordinate
     * @return element
     */
    public char getMazeElement(int x, int y) {
        return gameData.getMazeElement(x,y);
    }

    /**
     * getMainMenuState
     * @return mainMenuState
     */
    public boolean getMainMenuState() { return gameData.getMainMenuState(); }

    /**
     * getTop5MenuState
     * @return top5MenuState
     */
    public boolean getTop5MenuState() { return gameData.getTop5MenuState(); }

    /**
     * getMusicPlayStatus
     * @return musicPlayStatus
     */
    public boolean getMusicPlayStatus() { return gameData.getMusicPlayStatus(); }

    /**
     * getMusicVolume
     * @return musicVolume
     */
    public int getMusicVolume() { return gameData.getMusicVolume(); }

    /**
     * getMuted
     * @return muted
     */
    public boolean getMuted() { return gameData.getMuted(); }

    /**
     * getMainColorPreset
     * @return mainColorPreset
     */
    public ColorPreset getMainColorPreset() { return gameData.getMainColorPreset(); }

    /**
     * getMusicPreset
     * @return musicPreset
     */
    public MusicPreset getMusicPreset() { return gameData.getMusicPreset(); }

    /**
     * getMainEQPreset
     * @return mainEQPreset
     */
    public EQPreset getMainEQPreset() { return gameData.getMainEQPreset(); }

    /**
     * getPacmanDirection
     * @return pacman Direction
     */
    public Directions getPacmanDirection() { return gameData.getPacmanDirections(); }

    /**
     * getTop5List
     * @return top5List
     */
    public ArrayList<Player> getTop5List() { return gameData.getTop5List(); }

    /**
     * getCurrentLevel
     * @return currentLevel
     */
    public int getCurrentLevel() { return gameData.getCurrentLevel(); }

    /**
     * getTotalBalls
     * @return total Balls
     */
    public int getTotalBalls() { return gameData.getTotalBalls(); }

    /**
     * getPacmanLives
     * @return pacman Lives
     */
    public int getPacmanLives() { return gameData.getPacmanLives(); }

    /**
     * getElementActive
     * @param type ElementType
     * @return element Active
     */
    public boolean getElementActive(ElementType type) { return gameData.getElementActive(type); }

    /**
     * getEntityActive
     * @param type EntityType
     * @return Entity
     */
    public boolean getEntityActive(EntityType type) { return gameData.getEntityActive(type); }

    /**
     * getEnchancedPhase
     * @return enhanced Phase
     */
    public boolean getEnchancedPhase() { return gameData.getEnchancedPhase(); }

    //Set Methods

    /**
     * changeState
     * @param newState new State
     */
    void changeState(IGameState newState) {
        this.state = newState;
    }

    /**
     * setMainMenuState
     * @param value boolean
     */
    public void setMainMenuState(boolean value) { gameData.setMainMenuState(value); }

    /**
     * setTop5MenuState
     * @param value boolean
     */
    public void setTop5MenuState(boolean value) { gameData.setTop5MenuState(value); }

    /**
     * setMusicPlayStatus
     * @param value boolean
     */
    public void setMusicPlayStatus(boolean value) { gameData.setMusicPlayStatus(value); }

    /**
     * setMusicVolume
     * @param musicVolume musicVolume
     */
    public void setMusicVolume(int musicVolume) { gameData.setMusicVolume(musicVolume); }

    /**
     * setMuted
     * @param value boolean
     */
    public void setMuted(boolean value) { gameData.setMuted(value); }

    /**
     * setMainColorPreset
     * @param colorPreset ColorPreset
     */
    public void setMainColorPreset(ColorPreset colorPreset) { gameData.setMainColorPreset(colorPreset); }

    /**
     * setMusicPreset
     * @param musicPreset MusicPreset
     */
    public void setMusicPreset(MusicPreset musicPreset) { gameData.setMusicPreset(musicPreset); }

    /**
     * setMainEQPreset
     * @param eqPreset EQPreset
     */
    public void setMainEQPreset(EQPreset eqPreset) { gameData.setMainEQPreset(eqPreset); }

    /**
     * getEntityCord
     * @param type EntityType
     * @return Entity Cord
     */
    public int[] getEntityCord(EntityType type) { return gameData.getEntityCord(type); }

    /**
     * isTopPlayer
     * @return boolean
     */
    public boolean isTopPlayer() { return gameData.isTopPlayer(); }

    /**
     * setEntitysActive
     */
    public void setEntitysActive() { gameData.setEntitiesActive(); }

    //Methods

    /**
     * up
     * @return boolean
     */
    public boolean up() {
        return state.up();
    }

    /**
     * down
     * @return boolean
     */
    public boolean down() {
        return state.down();
    }

    /**
     * left
     * @return boolean
     */
    public boolean left() {
        return state.left();
    }

    /**
     * right
     * @return boolean
     */
    public boolean right() {
        return state.right();
    }

    /**
     * pauseGame
     * @return boolean
     */
    public boolean pauseGame() { return state.pauseGame(); }

    /**
     * resumeGame
     * @return boolean
     */
    public boolean resumeGame() { return state.resumeGame(); }

    /**
     * enhancedPacman
     * @return boolean
     */
    public boolean enhancedPacman() { return state.enhancedPacman(); }

    /**
     * disableEnhancedPacman
     * @return boolean
     */
    public boolean disableEnhancedPacman() { return state.disableEnhancedPacman(); }

    /**
     * restart
     * @return boolean
     */
    public boolean restart() { return state.restart(); }

    /**
     * endGame
     * @return boolean
     */
    public boolean endGame() { return state.endGame(); }

    /**
     * exitGame
     * @return boolean
     */
    public boolean exitGame() { return state.exitGame(); }

    /**
     * Save Top5
     */
    public void saveTop5() {
        gameData.saveTop5();
    }

    /**
     * Load Top5
     */
    public void loadTop5() {
        gameData.loadTop5();
    }

    /**
     * insertPlayerTop5
     * @param name Player Name
     */
    public void insertPlayerTop5(String name) { gameData.insertPlayerTop5(name); }

    //Overrides

    /**
     * update
     * Update Game
     */
    public void update() {
        state.update();
    }

    //Internal Functions


}