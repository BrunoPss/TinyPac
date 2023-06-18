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
 * Game Class
 * <p>Class that represents the Data Model</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class Game implements Serializable {
    //Internal Data
    private Map<EntityType, Entity> entities;
    private Maze maze;
    private GameContext context;
    private int[] ghostDoor;
    private int evolveInstantsPacman = 0;
    private int evolveInstantsGhosts = 0;
    private List<Integer> validInstants;
    private boolean enchancedPhase;
    private int enchancedTimeout;
    private int totalBalls;
    private int pacmanLives;
    private int pacmanGhostsEaten = 0;
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
    /**
     * Constructor
     * @param context Finite State Machine
     */
    public Game(GameContext context) {
        this.entities = new HashMap<>();
        this.levels = new ArrayList<>();
        levelMap();
        this.context = context;
        this.ghostDoor = new int[2];
        this.enchancedPhase = false;
        this.currentLevel = 0;
        this.pacmanLives = 3;

        File top5_temp = new File("top5/tinypac_top5.top");
        if (top5_temp.exists())
            loadTop5();
        else {
            this.top5List = new ArrayList<>();
        }

        //Game Configurations
        this.mainMenuState = true;
        this.top5MenuState = false;
        this.musicVolume = 12;
        this.musicPlayStatus = true;
        this.muted = false;
        this.mainColorPreset = ColorPreset.COLOR_PRESET1;
        this.musicPreset = MusicPreset.CALM;
        this.mainEQPreset = EQPreset.NONE;
    }

    //Game Configurations
    //Getters Config

    /**
     * getMainMenuState
     * @return mainMenuState
     */
    public boolean getMainMenuState() { return mainMenuState; }
    /**
     * getTop5MenuState
     * @return top5MenuState
     */
    public boolean getTop5MenuState() { return top5MenuState; }
    /**
     * getMusicPlayStatus
     * @return musicPlayStatus
     */
    public boolean getMusicPlayStatus() {
        return musicPlayStatus;
    }
    /**
     * getMusicVolume
     * @return musicVolume
     */
    public int getMusicVolume() { return musicVolume; }
    /**
     * getMuted
     * @return muted
     */
    public boolean getMuted() { return muted; }
    /**
     * getMainColorPreset
     * @return mainColorPreset
     */
    public ColorPreset getMainColorPreset() { return mainColorPreset; }
    /**
     * getMusicPreset
     * @return musicPreset
     */
    public MusicPreset getMusicPreset() { return musicPreset; }
    /**
     * getMainEQPreset
     * @return mainEQPreset
     */
    public EQPreset getMainEQPreset() { return mainEQPreset; }
    /**
     * getTop5List
     * @return top5List
     */
    public ArrayList<Player> getTop5List() { return top5List; }

    //Setters Config

    /**
     * setMainMenuState
     * @param value boolean
     */
    public void setMainMenuState(boolean value) { this.mainMenuState = value; }
    /**
     * setTop5MenuState
     * @param value boolean
     */
    public void setTop5MenuState(boolean value) { this.top5MenuState = value; }
    /**
     * setMusicPlayStatus
     * @param value boolean
     */
    public void setMusicPlayStatus(boolean value) {
        this.musicPlayStatus = value;
    }

    /**
     * setMusicVolume
     * @param musicVolume int
     */
    public void setMusicVolume(int musicVolume) { this.musicVolume = musicVolume; }

    /**
     * setMuted
     * @param value boolean
     */
    public void setMuted(boolean value) { this.muted = value; }

    /**
     * setMainColorPreset
     * @param colorPreset ColorPreset
      */
    public void setMainColorPreset(ColorPreset colorPreset) { this.mainColorPreset = colorPreset; }

    /**
     * setMusicPreset
     * @param musicPreset MusicPreset
     */
    public void setMusicPreset(MusicPreset musicPreset) { this.musicPreset = musicPreset; }

    /**
     * setMainEQPreset
     * @param eqPreset EQPreset
     */
    public void setMainEQPreset(EQPreset eqPreset) { this.mainEQPreset = eqPreset; }

    //Get Methods

    /**
     * getEntity
     * @param type EntityType
     * @return entity
     */
    public Entity getEntity(EntityType type) { return entities.get(type); }

    /**
     * getGhostDoor
     * @return ghostDoor
     */
    public int[] getGhostDoor() { return ghostDoor; }

    /**
     * fsmEnchance
     * Enhance Pacman
     */
    public void fsmEnchance() { context.enhancedPacman(); }

    /**
     * getEvolveInstantsPacman
     * @return evolveInstantsPacman
     */
    public int getEvolveInstantsPacman() { return evolveInstantsPacman; }

    /**
     * getEvolveInstantsGhosts
     * @return evolveInstantsGhosts
     */
    public int getEvolveInstantsGhosts() { return evolveInstantsGhosts; }

    /**
     * getCurrentLevelFilePath
     * @return currentLevelFilePath
     */
    public String getCurrentLevelFilePath() { return levels.get(currentLevel); }

    /**
     * getPacmanGhostsEaten
     * @return pacmanGhostsEaten
     */
    public int getPacmanGhostsEaten() { return pacmanGhostsEaten; }

    /**
     * Increment Ghosts Eaten
     */
    public void incPacmanGhostsEaten() {
        if (pacmanGhostsEaten < 4)
            pacmanGhostsEaten++;
        else
            pacmanGhostsEaten = 0;
    }

    /**
     * getIMazeElement
     * @param y y coordinate
     * @param x x coordinate
     * @return element
     */
    public IMazeElement getIMazeElement(int y, int x) { return maze.get(y,x); }

    /**
     * setIMazeElement
     * @param y y coordinate
     * @param x x coordinate
     * @param element element
     */
    public void setIMazeElement(int y, int x, IMazeElement element) { maze.set(y,x,element); }

    /**
     * getMazeHeight
     * @return mazeHeight
     */
    public int getMazeHeight() { return maze.getMaze().length; }

    /**
     * getMazeLength
     * @return mazeLength
     */
    public int getMazeLength() { return maze.getMaze()[0].length; }

    /**
     * getMazeElement
     * @param x x coordinate
     * @param y y coordinate
     * @return element char
     */
    public char getMazeElement(int x, int y) {
        IMazeElement element = maze.get(y,x);
        if (element != null) {
            return element.getSymbol();
        }
        else {
            return ' ';
        }
    }

    /**
     * getEnchancedPhase
     * @return enchancedPhase
     */
    public boolean getEnchancedPhase() { return enchancedPhase; }

    /**
     * getEnchancedTimeout
     * @return enchancedTimeout
     */
    public int getEnchancedTimeout() { return enchancedTimeout; }

    /**
     * getTotalBalls
     * @return totalBalls
     */
    public int getTotalBalls() { return totalBalls; }

    /**
     * getCurrentLevel
     * @return currentLevel
     */
    public int getCurrentLevel() { return currentLevel; }

    /**
     * setPacmanDirection
     * @param direction pacmanDirection
     */
    public void setPacmanDirection(Directions direction) { ((Pacman)entities.get(EntityType.PACMAN)).setDirection(direction); }

    /**
     * getPacmanDirections
     * @return pacman Direction
     */
    public Directions getPacmanDirections() { return ((Pacman) entities.get(EntityType.PACMAN)).getDirection(); }

    /**
     * getPacmanPoints
     * @return pacmanPoints
     */
    public int getPacmanPoints() { return ((Pacman) entities.get(EntityType.PACMAN)).getPoints(); }

    /**
     * getPacmanLives
     * @return pacmanLives
     */
    public int getPacmanLives() { return pacmanLives; }

    /**
     * getEntityCord
     * @param type EntityType
     * @return Entity Cord
     */
    public int[] getEntityCord(EntityType type) {
        return new int[]{entities.get(type).getX(), entities.get(type).getY()};
    }

    /**
     * getElementActive
     * @param type ElementType
     * @return elementActive
     */
    public boolean getElementActive(ElementType type) {
        return switch (type) {
            case FRUIT -> Fruit.ACTIVE;
            case SUPER_BALL -> SuperBall.ACTIVE;
            default -> false;
        };
    }

    /**
     * getEntityActive
     * @param type EntityType
     * @return entityActive
     */
    public boolean getEntityActive(EntityType type) {
        return switch (type) {
            case PINKY -> Pinky.ACTIVE;
            case BLINKY -> Blinky.ACTIVE;
            case INKY -> Inky.ACTIVE;
            case CLYDE -> Clyde.ACTIVE;
            default -> false;
        };
    }

    /**
     * setEntityActive
     * @param type EntityType
     * @param value boolean
     */
    public void setEntityActive(EntityType type, boolean value) {
        switch (type) {
            case PINKY -> Pinky.ACTIVE = value;
            case BLINKY -> Blinky.ACTIVE = value;
            case INKY -> Inky.ACTIVE = value;
            case CLYDE -> Clyde.ACTIVE = value;
        };
    }

    /**
     * Set Entities Active
     */
    public void setEntitiesActive() {
        setEntityActive(EntityType.BLINKY, true);
        setEntityActive(EntityType.CLYDE, true);
        setEntityActive(EntityType.INKY, true);
        setEntityActive(EntityType.PINKY, true);
    }

    //Set Methods

    /**
     * setGhostDoor
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setGhostDoor(int x, int y) { this.ghostDoor[0] = x; this.ghostDoor[1] = y; }

    /**
     * setEntity
     * @param type EntityType
     * @param entity Entity
     */
    public void setEntity(EntityType type, Entity entity) { entities.put(type, entity); }

    /**
     * setEnchancedPhase
     * @param state boolean
     */
    public void setEnchancedPhase(boolean state) { enchancedPhase = state; }

    /**
     * setEnchancedTimeout
     * @param timeout Enhanced Timeout
     */
    public void setEnchancedTimeout(int timeout) { enchancedTimeout = timeout; }

    /**
     * Set Super Ball Inactive
     */
    public void setSuperBallInactive() { SuperBall.ACTIVE = false; }

    /**
     * Decrease Enhanced Timeout
     */
    public void decreaseEnchancedTimeout() { enchancedTimeout--; }

    /**
     * Increment Total Balls
     */
    public void incrementTotalBalls() { totalBalls++; }

    /**
     * Initialize Total Balls (0)
     */
    public void initTotalBalls() { totalBalls = 0; }

    /**
     * insertPlayerTop5
     * @param name Player Name
     */
    public void insertPlayerTop5(String name) {
        top5List.add(0, new Player(0, name, getPacmanPoints()));
        Collections.sort(top5List);
        top5List.forEach( (p) -> p.setPlace(top5List.indexOf(p)+1));
        if (top5List.size() > 5) {
            top5List.remove(top5List.size()-1);
        }
    }

    /**
     * Decrement Total Balls
     */
    public void decrementTotalBalls() {
        totalBalls--;
        if (!((Fruit) fruit).getVisible()) {
            ((Fruit) fruit).decrementNextFruitTimeout();
        }
    }

    /**
     * setFruit
     * @param fruit Element
     */
    public void setFruit(Element fruit) { this.fruit = fruit; }

    /**
     * Catch Fruit
     */
    public void catchFruit() {
        ((Fruit) fruit).setVisible(false);
        Fruit.ACTIVE = false;
    }

    /**
     * Pacman Dead
     */
    public void pacmanDead() {
        checkGameEnd();
        pacmanLives--;
        context.restart();
    }

    //Methods

    /**
     * Initialize Maze
     */
    public void initMaze() {
        System.out.println("CURRENT LEVEL " + currentLevel);
        maze = new Maze(MazeManager.getYSize(levels.get(currentLevel)), MazeManager.getXSize(levels.get(currentLevel)));
    }

    /**
     * Save Top5
     */
    public void saveTop5() {
        File file = new File("top5/tinypac_top5.top");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(top5List);
        } catch (Exception e) {
            System.err.println("ERROR WRITING TOP5");
            System.out.println(e);
        }
    }

    /**
     * Load Top5
     */
    public void loadTop5() {
        File file = new File("top5/tinypac_top5.top");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            top5List = (ArrayList<Player>) ois.readObject();
        } catch (Exception e) {
            System.err.println("ERROR LOADING TOP5");
            System.out.println(e);
        }
    }

    /**
     * isTopPlayer
     * @return boolean (checks if a player enters the Top5)
     */
    public boolean isTopPlayer() {
        return checkPlayerPointsTop5(getPacmanPoints());
    }

    /**
     * New Fruit
     */
    public void newFruit() {
        Fruit.ACTIVE = true;
        maze.set(((Fruit)fruit).getY(), ((Fruit)fruit).getX(), fruit);
    }

    /**
     * Checks Enhanced Mode
     */
    public void checkEnchancedMode() {
        //Enchanced Mode
        if (findValidInstantsPacman().contains(getEvolveInstantsPacman())) {
            if (enchancedPhase) {
                if (enchancedTimeout > 0) {
                    enchancedTimeout--;
                } else {
                    context.disableEnhancedPacman();
                }
            }
            if (entities.get(EntityType.BLINKY).getX() == ghostDoor[0] && entities.get(EntityType.BLINKY).getY()+1 == ghostDoor[1]
            &&  entities.get(EntityType.CLYDE).getX() == ghostDoor[0] && entities.get(EntityType.CLYDE).getY()+1 == ghostDoor[1]
            &&  entities.get(EntityType.INKY).getX() == ghostDoor[0] && entities.get(EntityType.INKY).getY()+1 == ghostDoor[1]
            &&  entities.get(EntityType.PINKY).getX() == ghostDoor[0] && entities.get(EntityType.PINKY).getY()+1 == ghostDoor[1]) {
                context.disableEnhancedPacman();
            }
        }
    }

    /**
     * Checks Level Change
     */
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

    /**
     * Checks Game End
     */
    public void checkGameEnd() {
        //End Game
        if (pacmanLives <= 1) {
            context.endGame();
        }
    }

    /**
     * Evolve Entities
     */
    public void evolveEntities() {
        //Entities Evolve
        entities.forEach((ElementType, Element) -> Element.move());
    }

    /**
     * Initialize Evolve Instants Pacman
     */
    public void initializeEvolveInstantsPacman() {
        evolveInstantsPacman = 1;
        evolveInstantsGhosts = 1;
        validInstants = findValidInstantsPacman();
    }

    /**
     * Initialize Evolve Instants Ghosts
     */
    public void initializeEvolveInstantsGhosts() {
        evolveInstantsPacman = 1;
        evolveInstantsGhosts = 1;
        validInstants = findValidInstantsGhosts();
    }

    /**
     * Increment Evolve Instants Pacman
     */
    public void incrementEvolveInstantsPacman() {
        if (evolveInstantsPacman < 20)
            evolveInstantsPacman++;
        else
            evolveInstantsPacman = 1;
    }

    /**
     * Increment Evolve Instants Ghosts
     */
    public void incrementEvolveInstantsGhosts() {
        if (evolveInstantsGhosts < 20)
            evolveInstantsGhosts++;
        else
            evolveInstantsGhosts = 1;
    }

    /**
     * findValidInstantsPacman
     * @return Integer List
     */
    public List<Integer> findValidInstantsPacman() {
        int range = 20;
        int partSize = range / 2;
        List<Integer> pontos = new ArrayList<>();

        pontos.add(1);

        for (int i = 1; i < 2; i++) {
            int turningPoint = i * partSize;
            pontos.add(turningPoint);
        }
        return pontos;
    }

    /**
     * findValidInstantsGhosts
     * @return Integer List
     */
    public List<Integer> findValidInstantsGhosts() {
        int range = 20;
        int partSize = range / (currentLevel+2);
        List<Integer> pontos = new ArrayList<>();

        pontos.add(1);

        for (int i = 1; i < (currentLevel+2); i++) {
            int turningPoint = i * partSize;
            pontos.add(turningPoint);
        }
        return pontos;
    }

    //Overrides


    //Internal Functions
    private boolean checkPlayerPointsTop5(int num) {
        if (top5List.isEmpty())
            return true;

        return num > top5List.stream().max(Comparator.comparing(Player::getPoints)).get().getPoints();
    }
    private void levelMap() {
        File[] files = new File("src/pt/isec/pa/tinypac/model/data/levels/").listFiles();

        for (File file : files) {
            if (file.isFile()) {
                levels.add(file.getPath());
            }
        }
    }
}