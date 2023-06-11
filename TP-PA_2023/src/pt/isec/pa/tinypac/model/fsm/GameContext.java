package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.element.ElementType;
import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.entity.EntityType;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.pacman.Pacman;
import pt.isec.pa.tinypac.model.data.player.Player;
import pt.isec.pa.tinypac.ui.gui.resources.presets.ColorPreset;
import pt.isec.pa.tinypac.ui.gui.resources.presets.EQPreset;
import pt.isec.pa.tinypac.ui.gui.resources.presets.MusicPreset;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameContext implements Serializable {
    //Internal Data
    IGameState state;
    Game gameData;
    //Maze maze;
    //Dictionary<ElementType, Element> entities;

    //Constructor
    public GameContext(IGameEngine gameEngine) {
        //this.maze = new Maze(MazeManager.getYSize("C:\\Projects\\TP-PA_2023\\TP-PA_2023\\src\\pt\\isec\\pa\\tinypac\\model\\data\\levels\\level01.txt"), MazeManager.getXSize("C:\\Projects\\TP-PA_2023\\TP-PA_2023\\src\\pt\\isec\\pa\\tinypac\\model\\data\\levels\\level01.txt"));
        this.gameData = new Game(this);
        this.state = new InitState(this, gameData);
    }

    //Get Methods
    public GameState getState() {
        return state.getState();
    }
    public int getPacmanPoints() { return gameData.getPacmanPoints(); }
    //public String getPacmanDirection() { return entities.get(ElementType.PACMAN).getCurrentDirection(); }
    //public String getPacmanPosition() { return entities.get(ElementType.PACMAN).getCurrentPosition(); }
    //public char[][] getMaze() { return gameData.getMaze().getMaze(); }
    public int getMazeHeight() { return gameData.getMazeHeight(); }
    public int getMazeLength() { return gameData.getMazeLength(); }
    public char getMazeElement(int x, int y) {
        return gameData.getMazeElement(x,y);
    }
    public boolean getMainMenuState() { return gameData.getMainMenuState(); }
    public boolean getTop5MenuState() { return gameData.getTop5MenuState(); }
    public boolean getMusicPlayStatus() { return gameData.getMusicPlayStatus(); }
    public int getMusicVolume() { return gameData.getMusicVolume(); }
    public boolean getMuted() { return gameData.getMuted(); }
    public ColorPreset getMainColorPreset() { return gameData.getMainColorPreset(); }
    public MusicPreset getMusicPreset() { return gameData.getMusicPreset(); }
    public EQPreset getMainEQPreset() { return gameData.getMainEQPreset(); }
    public Directions getPacmanDirection() { return gameData.getPacmanDirections(); }
    public ArrayList<Player> getTop5List() { return gameData.getTop5List(); }
    public int getCurrentLevel() { return gameData.getCurrentLevel(); }
    public int getTotalBalls() { return gameData.getTotalBalls(); }
    public int getPacmanLives() { return gameData.getPacmanLives(); }
    public boolean getElementActive(ElementType type) { return gameData.getElementActive(type); }
    public boolean getEntityActive(EntityType type) { return gameData.getEntityActive(type); }

    //Set Methods
    void changeState(IGameState newState) {
        this.state = newState;
    }
    public void setMainMenuState(boolean value) { gameData.setMainMenuState(value); }
    public void setTop5MenuState(boolean value) { gameData.setTop5MenuState(value); }
    public void setMusicPlayStatus(boolean value) { gameData.setMusicPlayStatus(value); }
    public void setMusicVolume(int musicVolume) { gameData.setMusicVolume(musicVolume); }
    public void setMuted(boolean value) { gameData.setMuted(value); }
    public void setMainColorPreset(ColorPreset colorPreset) { gameData.setMainColorPreset(colorPreset); }
    public void setMusicPreset(MusicPreset musicPreset) { gameData.setMusicPreset(musicPreset); }
    public void setMainEQPreset(EQPreset eqPreset) { gameData.setMainEQPreset(eqPreset); }
    public int[] getEntityCord(EntityType type) { return gameData.getEntityCord(type); }

    //Methods
    public boolean up() {
        return state.up();
    }
    public boolean down() {
        return state.down();
    }
    public boolean left() {
        return state.left();
    }
    public boolean right() {
        return state.right();
    }
    public boolean pauseGame() { return state.pauseGame(); }
    public boolean resumeGame() { return state.resumeGame(); }
    public boolean enhancedPacman() { return state.enhancedPacman(); }
    public boolean disableEnhancedPacman() { return state.disableEnhancedPacman(); }
    public boolean restart() { return state.restart(); }
    public boolean endGame() { return state.endGame(); }
    public boolean exitGame() { return state.exitGame(); }

    public void saveTop5() {
        gameData.saveTop5();
    }
    public void loadTop5() {
        gameData.loadTop5();
    }

    //Overrides
    public void update() {
        state.update();  //Implementacao Correta
    }

    //Internal Functions


}