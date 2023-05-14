package pt.isec.pa.tinypac.model.data.game;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.element.ElementType;
import pt.isec.pa.tinypac.model.data.entity.Entity;
import pt.isec.pa.tinypac.model.data.entity.EntityType;
import pt.isec.pa.tinypac.model.data.fruit.Fruit;
import pt.isec.pa.tinypac.model.data.ghosts.Ghost;
import pt.isec.pa.tinypac.model.data.maze.Maze;
import pt.isec.pa.tinypac.model.data.maze.MazeManager;
import pt.isec.pa.tinypac.model.data.pacman.Pacman;
import pt.isec.pa.tinypac.model.fsm.GameContext;

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


    //Constructor
    public Game(GameContext context) {
        this.entities = new HashMap<>();
        this.levels = new ArrayList<>();
        levelMap();
        this.context = context;
        this.ghostDoor = new int[2];
        this.enchancedPhase = false;
        this.currentLevel = 0;
    }

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