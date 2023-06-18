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

public class GameManager {
    //Internal Data
    private GameContext fsm;
    PropertyChangeSupport pcs;

    //Constructor
    public GameManager() {
        fsm = new GameContext();
        pcs = new PropertyChangeSupport(this);
    }

    //Get Methods
    public boolean getMainMenuState() { return fsm.getMainMenuState(); }
    public boolean getTop5MenuState() { return fsm.getTop5MenuState(); }
    public int getMusicVolume() { return fsm.getMusicVolume(); }
    public boolean getMusicPlayStatus() { return fsm.getMusicPlayStatus(); }
    public boolean getMuted() { return fsm.getMuted(); }
    public ColorPreset getMainColorPreset() { return fsm.getMainColorPreset(); }
    public MusicPreset getMusicPreset() { return fsm.getMusicPreset(); }
    public EQPreset getMainEQPreset() { return fsm.getMainEQPreset(); }
    public int getMazeHeight() { return fsm.getMazeHeight(); }
    public int getMazeLength() { return fsm.getMazeLength(); }
    public char getMazeElement(int x, int y) {
        return fsm.getMazeElement(x,y);
    }
    public Directions getPacmanDirection() { return fsm.getPacmanDirection(); }
    public int getPacmanPoints() { return fsm.getPacmanPoints(); }
    public int getCurrentLevel() { return fsm.getCurrentLevel(); }
    public int getTotalBalls() { return fsm.getTotalBalls(); }
    public int getPacmanLives() { return fsm.getPacmanLives(); }
    public boolean getElementActive(ElementType type) { return fsm.getElementActive(type); }
    public boolean getEntityActive(EntityType type) { return fsm.getEntityActive(type); }
    public int[] getEntityCord(EntityType type) { return fsm.getEntityCord(type); }
    public ArrayList<Player> getTop5List() { return fsm.getTop5List(); }
    public boolean getEnchancedPhase() { return fsm.getEnchancedPhase(); }
    public boolean isTopPlayer() { return fsm.isTopPlayer(); }

    //Set Methods
    public void setMainMenuState(boolean value) {
        boolean old = fsm.getMainMenuState();
        fsm.setMainMenuState(value);
        pcs.firePropertyChange(null, null, null);
    }
    public void setTop5MenuState(boolean value) {
        boolean old = fsm.getTop5MenuState();
        fsm.setTop5MenuState(value);
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
    public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(property, listener);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    public void update() {
        fsm.update();
        pcs.firePropertyChange(null, null, null);
    }
    public boolean checkSavedGame() {
        File saveGame_temp = new File("savedGame/saveGame.pac");
        return saveGame_temp.exists();
    }

    public void saveTop5() {
        fsm.saveTop5();
    }
    public void loadTop5() {
        fsm.loadTop5();
    }
    public void insertPlayerTop5(String name) { fsm.insertPlayerTop5(name); }
    public void saveGame() {
        File file = new File("savedGame/saveGame.pac");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(fsm);
        } catch (Exception e) {
            System.err.println("ERROR WRITING FSM");
            e.printStackTrace();
        }
    }
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

    public void up() {
        fsm.up();
        //gameEngine.start(1000);
        pcs.firePropertyChange(null, null, null);
    }
    public void down() {
        fsm.down();
        //gameEngine.start(1000);
        pcs.firePropertyChange(null, null, null);
    }
    public void left() {
        fsm.left();
        //gameEngine.start(1000);
        pcs.firePropertyChange(null, null, null);
    }
    public void right() {
        fsm.right();
        //gameEngine.start(1000);
        pcs.firePropertyChange(null, null, null);
    }
    public void pauseGame() {
        fsm.pauseGame();
        //gameEngine.pause();
        pcs.firePropertyChange(null, null, null);
    }
    public void resumeGame() {
        fsm.resumeGame();
        //gameEngine.resume();
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
        //gameEngine.stop();
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