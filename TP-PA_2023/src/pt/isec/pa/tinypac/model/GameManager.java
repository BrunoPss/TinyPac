package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.ui.gui.resources.presets.ColorPreset;
import pt.isec.pa.tinypac.ui.gui.resources.presets.EQPreset;
import pt.isec.pa.tinypac.ui.gui.resources.presets.MusicPreset;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameManager {
    //Internal Data
    private final GameContext fsm;
    PropertyChangeSupport pcs;

    //Constructor
    public GameManager(IGameEngine gameEngine) {
        fsm = new GameContext(gameEngine);
        pcs = new PropertyChangeSupport(this);
    }

    //Get Methods
    public boolean getMainMenuState() { return fsm.getMainMenuState(); }
    public int getMusicVolume() { return fsm.getMusicVolume(); }
    public boolean getMusicPlayStatus() { return fsm.getMusicPlayStatus(); }
    public boolean getMuted() { return fsm.getMuted(); }
    public ColorPreset getMainColorPreset() { return fsm.getMainColorPreset(); }
    public MusicPreset getMusicPreset() { return fsm.getMusicPreset(); }
    public EQPreset getMainEQPreset() { return fsm.getMainEQPreset(); }
    public int getMazeHeight() { return fsm.getMazeHeight(); }
    public int getMazeLength() { return fsm.getMazeLength(); }
    public char getMazeElement(int x, int y) { return fsm.getMazeElement(x,y); }

    //Set Methods
    public void setMainMenuState(boolean value) {
        boolean old = fsm.getMainMenuState();
        fsm.setMainMenuState(value);
        pcs.firePropertyChange(null, old, value);
    }
    public void setMusicVolume(int musicVolume) {
        int old = fsm.getMusicVolume();
        fsm.setMusicVolume(musicVolume);
        pcs.firePropertyChange(null, old, musicVolume);
    }
    public void toogleMusicPlayStatus() {
        fsm.setMusicPlayStatus(!fsm.getMusicPlayStatus());
        pcs.firePropertyChange(null, null, null);
    }
    public void toogleMute() {
        fsm.setMuted(!fsm.getMuted());
        pcs.firePropertyChange(null, null, null);
    }
    public void setMainColorPreset(ColorPreset colorPreset) {
        ColorPreset old = fsm.getMainColorPreset();
        fsm.setMainColorPreset(colorPreset);
        pcs.firePropertyChange(null, old, colorPreset);
    }
    public void setMusicPreset(MusicPreset musicPreset) {
        MusicPreset old = fsm.getMusicPreset();
        fsm.setMusicPreset(musicPreset);
        pcs.firePropertyChange(null, old, musicPreset);
    }
    public void setMainEQPreset(EQPreset eqPreset) {
        EQPreset old = fsm.getMainEQPreset();
        fsm.setMainEQPreset(eqPreset);
        pcs.firePropertyChange(null, old, eqPreset);
    }

    //Methods
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    public void up() {
        fsm.up();
        pcs.firePropertyChange(null, null, null);
    }
    public void down() {
        fsm.down();
        pcs.firePropertyChange(null, null, null);
    }
    public void left() {
        fsm.left();
        pcs.firePropertyChange(null, null, null);
    }
    public void right() {
        fsm.right();
        pcs.firePropertyChange(null, null, null);
    }
    public void pauseGame() {
        fsm.pauseGame();
        pcs.firePropertyChange(null, null, null);
    }
    public void resumeGame() {
        fsm.resumeGame();
        pcs.firePropertyChange(null, null, null);
    }
    public void enhancedPacman() {
        fsm.enhancedPacman();
        pcs.firePropertyChange(null, null, null);
    }
    public void disableEnhancedPacman() {
        fsm.disableEnhancedPacman();
        pcs.firePropertyChange(null, null, null);
    }
    public void restart() {
        fsm.restart();
        pcs.firePropertyChange(null, null, null);
    }
    public void endGame() {
        fsm.endGame();
        pcs.firePropertyChange(null, null, null);
    }
    public void exitGame() {
        fsm.exitGame();
        pcs.firePropertyChange(null, null, null);
    }
    public GameState getState() {
        return fsm.getState();
    }

    //Overrides


    //Internal Functions


}