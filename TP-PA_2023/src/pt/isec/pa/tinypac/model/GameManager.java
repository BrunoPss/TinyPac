package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameManager {
    //Internal Data
    private GameContext fsm;
    PropertyChangeSupport pcs;

    //Constructor
    public GameManager(IGameEngine gameEngine) {
        fsm = new GameContext(gameEngine);
        pcs = new PropertyChangeSupport(this);
    }

    //Get Methods


    //Set Methods


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