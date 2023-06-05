package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.entity.EntityType;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.pacman.Pacman;
import pt.isec.pa.tinypac.ui.gui.resources.presets.ColorPreset;
import pt.isec.pa.tinypac.ui.gui.resources.presets.EQPreset;
import pt.isec.pa.tinypac.ui.gui.resources.presets.MusicPreset;

public class GameContext {
    //Internal Data
    IGameState state;
    IGameEngine gameEngine;
    Game gameData;
    //Maze maze;
    //Dictionary<ElementType, Element> entities;

    //Constructor
    public GameContext(IGameEngine gameEngine) {
        //this.maze = new Maze(MazeManager.getYSize("C:\\Projects\\TP-PA_2023\\TP-PA_2023\\src\\pt\\isec\\pa\\tinypac\\model\\data\\levels\\level01.txt"), MazeManager.getXSize("C:\\Projects\\TP-PA_2023\\TP-PA_2023\\src\\pt\\isec\\pa\\tinypac\\model\\data\\levels\\level01.txt"));
        this.gameEngine = gameEngine;
        this.gameData = new Game(this);
        this.state = new InitState(this, gameData);
        //gameEngine.registerClient(this);
    }

    //Get Methods
    public GameState getState() {
        return state.getState();
    }
    public int getPacmanPoints() { return ((Pacman) gameData.getEntity(EntityType.PACMAN)).getPoints(); }
    //public String getPacmanDirection() { return entities.get(ElementType.PACMAN).getCurrentDirection(); }
    //public String getPacmanPosition() { return entities.get(ElementType.PACMAN).getCurrentPosition(); }
    public char[][] getMaze() { return gameData.getMaze().getMaze(); }
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

    //Overrides
    //@Override
    public void update() {
        state.update();  //Implementacao Correta
    }

    //Internal Functions


}