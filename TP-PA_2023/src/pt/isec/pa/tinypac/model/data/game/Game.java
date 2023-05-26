package pt.isec.pa.tinypac.model.data.game;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.entity.Entity;
import pt.isec.pa.tinypac.model.data.entity.EntityType;
import pt.isec.pa.tinypac.model.data.fruit.Fruit;
import pt.isec.pa.tinypac.model.data.maze.Maze;
import pt.isec.pa.tinypac.model.data.maze.MazeManager;
import pt.isec.pa.tinypac.model.data.pacman.Pacman;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.ui.gui.resources.presets.ColorPreset;
import pt.isec.pa.tinypac.ui.gui.resources.presets.EQPreset;
import pt.isec.pa.tinypac.ui.gui.resources.presets.MusicPreset;

import java.io.File;
import java.util.*;

/**
 * Game Data Model
 * <p>Class that contains and manages all game data</p>
 *
 * @ author Bruno Guiomar
 * @ version 1.0.0
 */

public class Game {
    //Internal Data
    private Map<EntityType, Entity> entities;
    private Maze maze;
    private GameContext context;
    private int[] ghostDoor;
    private boolean enchancedPhase;
    private int enchancedTimeout;
    private int totalBalls;
    private List<String> levels;
    private int currentLevel;
    private Element fruit;
    private int nextFruit;

    //Game Configurations
    private boolean mainMenuState;
    private boolean musicPlayStatus;
    private int musicVolume;
    private boolean muted;
    private ColorPreset mainColorPreset;
    private MusicPreset musicPreset;
    private EQPreset mainEQPreset;


    //Constructor
    public Game(GameContext context) {
        this.entities = new HashMap<>();
        this.levels = new ArrayList<>();
        levelMap();
        this.context = context;
        this.ghostDoor = new int[2];
        this.enchancedPhase = false;
        this.currentLevel = 0;

        //Game Configurations
        this.mainMenuState = true;
        this.musicVolume = 8;
        this.musicPlayStatus = true;
        this.muted = false;
        this.mainColorPreset = ColorPreset.COLOR_PRESET1;
        this.musicPreset = MusicPreset.LOFI;
        this.mainEQPreset = EQPreset.EQ_PRESET1;
    }

    //Game Configurations
    //Getters
    public boolean getMainMenuState() { return mainMenuState; }
    public boolean getMusicPlayStatus() {
        return musicPlayStatus;
    }
    public int getMusicVolume() { return musicVolume; }
    public boolean getMuted() { return muted; }
    public ColorPreset getMainColorPreset() { return mainColorPreset; }
    public MusicPreset getMusicPreset() { return musicPreset; }
    public EQPreset getMainEQPreset() { return mainEQPreset; }

    //Setters
    public void setMainMenuState(boolean value) { this.mainMenuState = value; }
    public void setMusicPlayStatus(boolean value) {
        this.musicPlayStatus = value;
    }
    public void setMusicVolume(int musicVolume) { this.musicVolume = musicVolume; }
    public void setMuted(boolean value) { this.muted = value; }
    public void setMainColorPreset(ColorPreset colorPreset) { this.mainColorPreset = colorPreset; }
    public void setMusicPreset(MusicPreset musicPreset) { this.musicPreset = musicPreset; }
    public void setMainEQPreset(EQPreset eqPreset) { this.mainEQPreset = eqPreset; }

    //Get Methods
    public Pacman getPacman() {
        return (Pacman) entities.get(EntityType.PACMAN);
    }
    public Maze getMaze() {
        return maze;
    }
    public int[] getGhostDoor() { return ghostDoor; }
    public GameContext getContext() { return context; }
    public String getCurrentLevelFilePath() { return levels.get(currentLevel); }

    //Set Methods
    public void setGhostDoor(int x, int y) { this.ghostDoor[0] = x; this.ghostDoor[1] = y; }
    public void setEntity(EntityType type, Entity entity) { entities.put(type, entity); }
    public void setEnchancedPhase(boolean state) { enchancedPhase = state; }
    public void setEnchancedTimeout(int timeout) { enchancedTimeout = timeout; }
    public void incrementTotalBalls() { totalBalls++; }
    public void decrementTotalBalls() {
        totalBalls--;
        if (!((Fruit) fruit).getVisible()) {
            ((Fruit) fruit).decrementNextFruitTimeout();
        }
    }
    public void setFruit(Element fruit) { this.fruit = fruit; }
    public void catchFruit() { ((Fruit) fruit).setVisible(false); }

    //Methods
    public void initMaze() {
        System.out.println("CURRENT LEVEL " + currentLevel);
        maze = new Maze(MazeManager.getYSize(levels.get(currentLevel)), MazeManager.getXSize(levels.get(currentLevel)));
    }
    public void newFruit() {
        maze.set(fruit.getY(), fruit.getX(), fruit);
    }
    public void gameEvolve() {
        //Enchanced Mode
        if (enchancedPhase) {
            if (enchancedTimeout > 0) {
                enchancedTimeout--;
            }
            else {
                context.disableEnhancedPacman();
            }
        }

        //Change Level
        if (totalBalls == 0) {
            if (currentLevel < 19) {
                currentLevel++;
                context.restart();
            }
            else {
                context.endGame();
            }
        }

        //End Game
        if (((Pacman) entities.get(EntityType.PACMAN)).getLives() == 0) {
            context.endGame();
        }

        //Entities Evolve
        entities.forEach((ElementType, Element) -> Element.evolve());
    }

    //Overrides


    //Internal Functions
    private void levelMap() {
        File[] files = new File("src/pt/isec/pa/tinypac/model/data/levels/").listFiles();

        for (File file : files) {
            if (file.isFile()) {
                levels.add(file.getPath());
            }
        }
    }

}