package pt.isec.pa.tinypac.model.data.game;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.element.ElementType;
import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.entity.Entity;
import pt.isec.pa.tinypac.model.data.entity.EntityType;
import pt.isec.pa.tinypac.model.data.fruit.Fruit;
import pt.isec.pa.tinypac.model.data.ghosts.Blinky;
import pt.isec.pa.tinypac.model.data.ghosts.Clyde;
import pt.isec.pa.tinypac.model.data.ghosts.Inky;
import pt.isec.pa.tinypac.model.data.ghosts.Pinky;
import pt.isec.pa.tinypac.model.data.maze.IMazeElement;
import pt.isec.pa.tinypac.model.data.maze.Maze;
import pt.isec.pa.tinypac.model.data.maze.MazeManager;
import pt.isec.pa.tinypac.model.data.pacman.Pacman;
import pt.isec.pa.tinypac.model.data.player.Player;
import pt.isec.pa.tinypac.model.data.superBall.SuperBall;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.ui.gui.resources.presets.ColorPreset;
import pt.isec.pa.tinypac.ui.gui.resources.presets.EQPreset;
import pt.isec.pa.tinypac.ui.gui.resources.presets.MusicPreset;

import java.io.*;
import java.util.*;

/**
 * Game Data Model
 * <p>Class that contains and manages all game data</p>
 *
 * @ author Bruno Guiomar
 * @ version 1.0.0
 */

public class Game implements Serializable {
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
    private ArrayList<Player> top5List;

    //Game Configurations
    private boolean mainMenuState;
    private boolean top5MenuState;
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

        File top5_temp = new File("top5/tinypac_top5.top");
        if (top5_temp.exists())
            loadTop5();
        else {
            this.top5List = new ArrayList<>();
            top5List.add(new Player("Player 1", 5)); //APAGAR
            top5List.add(new Player("Player 2", 3)); //APAGAR
            //top5List.add(new Player("Player 3", 3)); //APAGAR
            //top5List.add(new Player("Player 4", 3)); //APAGAR
            //top5List.add(new Player("Player 5", 3)); //APAGAR
        }

        //Game Configurations
        this.mainMenuState = true;
        this.top5MenuState = false;
        this.musicVolume = 12;
        this.musicPlayStatus = true;
        this.muted = false;
        this.mainColorPreset = ColorPreset.COLOR_PRESET1;
        this.musicPreset = MusicPreset.ELECTRONIC;
        this.mainEQPreset = EQPreset.NONE;
    }

    //Game Configurations
    //Getters Config
    public boolean getMainMenuState() { return mainMenuState; }
    public boolean getTop5MenuState() { return top5MenuState; }
    public boolean getMusicPlayStatus() {
        return musicPlayStatus;
    }
    public int getMusicVolume() { return musicVolume; }
    public boolean getMuted() { return muted; }
    public ColorPreset getMainColorPreset() { return mainColorPreset; }
    public MusicPreset getMusicPreset() { return musicPreset; }
    public EQPreset getMainEQPreset() { return mainEQPreset; }
    public ArrayList<Player> getTop5List() { return top5List; }

    //Setters Config
    public void setMainMenuState(boolean value) { this.mainMenuState = value; }
    public void setTop5MenuState(boolean value) { this.top5MenuState = value; }
    public void setMusicPlayStatus(boolean value) {
        this.musicPlayStatus = value;
    }
    public void setMusicVolume(int musicVolume) { this.musicVolume = musicVolume; }
    public void setMuted(boolean value) { this.muted = value; }
    public void setMainColorPreset(ColorPreset colorPreset) { this.mainColorPreset = colorPreset; }
    public void setMusicPreset(MusicPreset musicPreset) { this.musicPreset = musicPreset; }
    public void setMainEQPreset(EQPreset eqPreset) { this.mainEQPreset = eqPreset; }

    //Get Methods
    public Entity getEntity(EntityType type) { return entities.get(type); } //ELIMINAR (FAZER SET CONTROLADO DA DIRECTION)
    public int[] getGhostDoor() { return ghostDoor; }
    public GameContext getContext() { return context; }
    public String getCurrentLevelFilePath() { return levels.get(currentLevel); }
    public Maze getMaze() { return maze; }
    public int getMazeHeight() { return maze.getMaze().length; }
    public int getMazeLength() { return maze.getMaze()[0].length; }
    public char getMazeElement(int x, int y) {
        IMazeElement element = maze.get(y,x);
        if (element != null) {
            return element.getSymbol();
        }
        else {
            return ' ';
        }
    }
    public boolean getEnchancedPhase() { return enchancedPhase; }
    public int getEnchancedTimeout() { return enchancedTimeout; }
    public int getTotalBalls() { return totalBalls; }
    public int getCurrentLevel() { return currentLevel; }
    public Directions getPacmanDirections() { return ((Pacman) entities.get(EntityType.PACMAN)).getDirection(); }
    public int getPacmanPoints() { return ((Pacman) entities.get(EntityType.PACMAN)).getPoints(); }
    public int getPacmanLives() { return ((Pacman) entities.get(EntityType.PACMAN)).getLives(); }
    public int[] getEntityCord(EntityType type) {
        return new int[]{entities.get(type).getX(), entities.get(type).getY()};
    }
    public boolean getElementActive(ElementType type) {
        return switch (type) {
            case FRUIT -> Fruit.ACTIVE;
            case SUPER_BALL -> SuperBall.ACTIVE;
            default -> false;
        };
    }
    public boolean getEntityActive(EntityType type) {
        return switch (type) {
            case PINKY -> Pinky.ACTIVE;
            case BLINKY -> Blinky.ACTIVE;
            case INKY -> Inky.ACTIVE;
            case CLYDE -> Clyde.ACTIVE;
            default -> false;
        };
    }

    //Set Methods
    public void setGhostDoor(int x, int y) { this.ghostDoor[0] = x; this.ghostDoor[1] = y; }
    public void setEntity(EntityType type, Entity entity) { entities.put(type, entity); }
    public void setEnchancedPhase(boolean state) { enchancedPhase = state; }
    public void setEnchancedTimeout(int timeout) { enchancedTimeout = timeout; }
    public void setSuperBallInactive() { SuperBall.ACTIVE = false; }
    public void decreaseEnchancedTimeout() { enchancedTimeout--; }
    public void incrementTotalBalls() { totalBalls++; }
    public void decrementTotalBalls() {
        totalBalls--;
        if (!((Fruit) fruit).getVisible()) {
            ((Fruit) fruit).decrementNextFruitTimeout();
        }
    }
    public void setFruit(Element fruit) { this.fruit = fruit; }
    public void catchFruit() {
        ((Fruit) fruit).setVisible(false);
        Fruit.ACTIVE = false;
    }
    public void increaseCurrentLevel() { currentLevel++; }

    //Methods
    public void initMaze() {
        System.out.println("CURRENT LEVEL " + currentLevel);
        maze = new Maze(MazeManager.getYSize(levels.get(currentLevel)), MazeManager.getXSize(levels.get(currentLevel)));
    }

    public void saveTop5() {
        File file = new File("top5/tinypac_top5.top");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(top5List);
        } catch (Exception e) {
            System.err.println("ERROR WRITING TOP5");
            System.out.println(e);
        }
    }
    public void loadTop5() {
        File file = new File("top5/tinypac_top5.top");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            top5List = (ArrayList<Player>) ois.readObject();
        } catch (Exception e) {
            System.err.println("ERROR LOADING TOP5");
            System.out.println(e);
        }
    }

    public void newFruit() {
        Fruit.ACTIVE = true;
        maze.set(fruit.getY(), fruit.getX(), fruit);
    }

    public void checkEnchancedMode() {
        //Enchanced Mode
        if (enchancedPhase) {
            if (enchancedTimeout > 0) {
                enchancedTimeout--;
            }
            else {
                context.disableEnhancedPacman();
            }
        }
    }
    public void checkLevelChange() {
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
    }
    public void checkGameEnd() {
        //End Game
        if (((Pacman) entities.get(EntityType.PACMAN)).getLives() == 0) {
            context.endGame();
        }
    }
    public void evolveEntities() {
        //Entities Evolve
        entities.forEach((ElementType, Element) -> Element.move());
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