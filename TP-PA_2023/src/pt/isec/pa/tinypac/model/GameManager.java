package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.model.data.element.ElementType;
import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.entity.EntityType;
import pt.isec.pa.tinypac.model.data.player.Player;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.ui.gui.resources.presets.ColorPreset;
import pt.isec.pa.tinypac.ui.gui.resources.presets.EQPreset;
import pt.isec.pa.tinypac.ui.gui.resources.presets.MusicPreset;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.ArrayList;

/**
 * Game Manager Class
 * <p>Class that represents the Game Manager</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class GameManager {
    //Internal Data
    private GameContext fsm;
    PropertyChangeSupport pcs;

    //Constructor

    /**
     * Constructor
     */
    public GameManager() {
        fsm = new GameContext();
        pcs = new PropertyChangeSupport(this);
    }

    //Get Methods

    /**
     * getMainMenuState
     * @return mainMenuState
     */
    public boolean getMainMenuState() { return fsm.getMainMenuState(); }

    /**
     * getTop5MenuState
     * @return top5MenuState
     */
    public boolean getTop5MenuState() { return fsm.getTop5MenuState(); }

    /**
     * getMusicVolume
     * @return music Volume
     */
    public int getMusicVolume() { return fsm.getMusicVolume(); }

    /**
     * getMusicPlayStatus
     * @return musicPlayStatus
     */
    public boolean getMusicPlayStatus() { return fsm.getMusicPlayStatus(); }

    /**
     * getMuted
     * @return muted
     */
    public boolean getMuted() { return fsm.getMuted(); }

    /**
     * getMainColorPreset
     * @return color preset
     */
    public ColorPreset getMainColorPreset() { return fsm.getMainColorPreset(); }

    /**
     * getMusicPreset
     * @return music preset
     */
    public MusicPreset getMusicPreset() { return fsm.getMusicPreset(); }

    /**
     * getMainEQPreset
     * @return eq preset
     */
    public EQPreset getMainEQPreset() { return fsm.getMainEQPreset(); }

    /**
     * getMazeHeight
     * @return maze height
     */
    public int getMazeHeight() { return fsm.getMazeHeight(); }

    /**
     * getMazeLength
     * @return maze length
     */
    public int getMazeLength() { return fsm.getMazeLength(); }

    /**
     * getMazeElement
     * @param x x coordinate
     * @param y y coordinate
     * @return element
     */
    public char getMazeElement(int x, int y) {
        return fsm.getMazeElement(x,y);
    }

    /**
     * getPacmanDirection
     * @return pacman direction
     */
    public Directions getPacmanDirection() { return fsm.getPacmanDirection(); }

    /**
     * getPacmanPoints
     * @return pacman points
     */
    public int getPacmanPoints() { return fsm.getPacmanPoints(); }

    /**
     * getCurrentLevel
     * @return current level
     */
    public int getCurrentLevel() { return fsm.getCurrentLevel(); }

    /**
     * getTotalBalls
     * @return total balls
     */
    public int getTotalBalls() { return fsm.getTotalBalls(); }

    /**
     * getPacmanLives
     * @return pacman lives
     */
    public int getPacmanLives() { return fsm.getPacmanLives(); }

    /**
     * getElementActive
     * @param type ElementType
     * @return element active
     */
    public boolean getElementActive(ElementType type) { return fsm.getElementActive(type); }

    /**
     * getEntityActive
     * @param type EntityType
     * @return entity active
     */
    public boolean getEntityActive(EntityType type) { return fsm.getEntityActive(type); }

    /**
     * getEntityCord
     * @param type Entity Type
     * @return entity cord
     */
    public int[] getEntityCord(EntityType type) { return fsm.getEntityCord(type); }

    /**
     * getTop5List
     * @return top5 list
     */
    public ArrayList<Player> getTop5List() { return fsm.getTop5List(); }

    /**
     * getEnchancedPhase
     * @return enhacned phase
     */
    public boolean getEnchancedPhase() { return fsm.getEnchancedPhase(); }

    /**
     * isTopPlayer
     * @return top player
     */
    public boolean isTopPlayer() { return fsm.isTopPlayer(); }

    //Set Methods

    /**
     * setMainMenuState
     * @param value boolean
     */
    public void setMainMenuState(boolean value) {
        boolean old = fsm.getMainMenuState();
        fsm.setMainMenuState(value);
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * setTop5MenuState
     * @param value boolean
     */
    public void setTop5MenuState(boolean value) {
        boolean old = fsm.getTop5MenuState();
        fsm.setTop5MenuState(value);
        pcs.firePropertyChange(null, old, value);
    }

    /**
     * setMusicVolume
     * @param musicVolume music volume
     */
    public void setMusicVolume(int musicVolume) {
        int old = fsm.getMusicVolume();
        fsm.setMusicVolume(musicVolume);
        pcs.firePropertyChange(null, old, musicVolume);
    }

    /**
     * toogleMusicPlayStatus
     */
    public void toogleMusicPlayStatus() {
        fsm.setMusicPlayStatus(!fsm.getMusicPlayStatus());
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * toogleMute
     */
    public void toogleMute() {
        fsm.setMuted(!fsm.getMuted());
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * setMainColorPreset
     * @param colorPreset color preset
     */
    public void setMainColorPreset(ColorPreset colorPreset) {
        ColorPreset old = fsm.getMainColorPreset();
        fsm.setMainColorPreset(colorPreset);
        pcs.firePropertyChange(null, old, colorPreset);
    }

    /**
     * setMusicPreset
     * @param musicPreset music preset
     */
    public void setMusicPreset(MusicPreset musicPreset) {
        MusicPreset old = fsm.getMusicPreset();
        fsm.setMusicPreset(musicPreset);
        pcs.firePropertyChange(null, old, musicPreset);
    }

    /**
     * setMainEQPreset
     * @param eqPreset eq preset
     */
    public void setMainEQPreset(EQPreset eqPreset) {
        EQPreset old = fsm.getMainEQPreset();
        fsm.setMainEQPreset(eqPreset);
        pcs.firePropertyChange(null, old, eqPreset);
    }

    //Methods

    /**
     * addPropertyChangeListener
     * @param property property
     * @param listener listener
     */
    public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(property, listener);
    }

    /**
     * addPropertyChangeListener
     * @param listener listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * update
     */
    public void update() {
        fsm.update();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * checkSavedGame
     * @return boolean
     */
    public boolean checkSavedGame() {
        File saveGame_temp = new File("savedGame/saveGame.pac");
        return saveGame_temp.exists();
    }

    /**
     * saveTop5
     */
    public void saveTop5() {
        fsm.saveTop5();
    }

    /**
     * loadTop5
     */
    public void loadTop5() {
        fsm.loadTop5();
    }

    /**
     * insertPlayerTop5
     * @param name player name
     */
    public void insertPlayerTop5(String name) { fsm.insertPlayerTop5(name); }

    /**
     * saveGame
     */
    public void saveGame() {
        File file = new File("savedGame/saveGame.pac");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(fsm);
        } catch (Exception e) {
            System.err.println("ERROR WRITING FSM");
            e.printStackTrace();
        }
    }

    /**
     * loadGame
     */
    public void loadGame() {
        File file = new File("savedGame/saveGame.pac");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            fsm = (GameContext) ois.readObject();
        } catch (Exception e) {
            System.err.println("ERROR LOADING FSM");
            e.printStackTrace();
        }

        fsm.setEntitysActive();
    }

    /**
     * up
     */
    public void up() {
        fsm.up();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * down
     */
    public void down() {
        fsm.down();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * left
     */
    public void left() {
        fsm.left();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * right
     */
    public void right() {
        fsm.right();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * pauseGame
     */
    public void pauseGame() {
        fsm.pauseGame();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * resumeGame
     */
    public void resumeGame() {
        fsm.resumeGame();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * enhancedPacman
     */
    public void enhancedPacman() {
        fsm.enhancedPacman();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * disableEnhancedPacman
     */
    public void disableEnhancedPacman() {
        fsm.disableEnhancedPacman();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * restart
     */
    public void restart() {
        fsm.restart();
        //gameEngine.stop();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * endGame
     */
    public void endGame() {
        fsm.endGame();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * exitGame
     */
    public void exitGame() {
        fsm.exitGame();
        pcs.firePropertyChange(null, null, null);
    }

    /**
     * getState
     * @return current state
     */
    public GameState getState() {
        return fsm.getState();
    }

    //Overrides


    //Internal Functions


}