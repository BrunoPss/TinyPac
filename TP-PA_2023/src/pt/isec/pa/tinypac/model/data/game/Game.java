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
    public void fsmEnchance() { context.enhancedPacman(); }
    public int getEvolveInstantsPacman() { return evolveInstantsPacman; }
    public int getEvolveInstantsGhosts() { return evolveInstantsGhosts; }
    public String getCurrentLevelFilePath() { return levels.get(currentLevel); }
    public int getPacmanGhostsEaten() { return pacmanGhostsEaten; }
    public void incPacmanGhostsEaten() {
        if (pacmanGhostsEaten < 4)
            pacmanGhostsEaten++;
        else
            pacmanGhostsEaten = 0;
    }
    public IMazeElement getIMazeElement(int y, int x) { return maze.get(y,x); }
    public void setIMazeElement(int y, int x, IMazeElement element) { maze.set(y,x,element); }
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
    public void setPacmanDirection(Directions direction) { ((Pacman)entities.get(EntityType.PACMAN)).setDirection(direction); }
    public Directions getPacmanDirections() { return ((Pacman) entities.get(EntityType.PACMAN)).getDirection(); }
    public int getPacmanPoints() { return ((Pacman) entities.get(EntityType.PACMAN)).getPoints(); }
    public int getPacmanLives() { return pacmanLives; }
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
    public void setEntityActive(EntityType type, boolean value) {
        switch (type) {
            case PINKY -> Pinky.ACTIVE = value;
            case BLINKY -> Blinky.ACTIVE = value;
            case INKY -> Inky.ACTIVE = value;
            case CLYDE -> Clyde.ACTIVE = value;
        };
    }
    public void setEntitiesActive() {
        setEntityActive(EntityType.BLINKY, true);
        setEntityActive(EntityType.CLYDE, true);
        setEntityActive(EntityType.INKY, true);
        setEntityActive(EntityType.PINKY, true);
    }

    //Set Methods
    public void setGhostDoor(int x, int y) { this.ghostDoor[0] = x; this.ghostDoor[1] = y; }
    public void setEntity(EntityType type, Entity entity) { entities.put(type, entity); }
    public void setEnchancedPhase(boolean state) { enchancedPhase = state; }
    public void setEnchancedTimeout(int timeout) { enchancedTimeout = timeout; }
    public void setSuperBallInactive() { SuperBall.ACTIVE = false; }
    public void decreaseEnchancedTimeout() { enchancedTimeout--; }
    public void incrementTotalBalls() { totalBalls++; }
    public void initTotalBalls() { totalBalls = 0; }
    public void insertPlayerTop5(String name) {
        //Place = 0 alterar
        top5List.add(0, new Player(0, name, getPacmanPoints()));
        Collections.sort(top5List);
        top5List.forEach( (p) -> p.setPlace(top5List.indexOf(p)+1));
        if (top5List.size() > 5) {
            top5List.remove(top5List.size()-1);
        }
    }
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
    public void pacmanDead() {
        checkGameEnd();
        pacmanLives--;
        context.restart();
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

    public boolean isTopPlayer() {
        return checkPlayerPointsTop5(getPacmanPoints());
    }

    public void newFruit() {
        Fruit.ACTIVE = true;
        maze.set(((Fruit)fruit).getY(), ((Fruit)fruit).getX(), fruit);
    }

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
        if (pacmanLives <= 1) {
            context.endGame();
        }
    }
    public void evolveEntities() {
        //Entities Evolve
        entities.forEach((ElementType, Element) -> Element.move());
    }
    public void initializeEvolveInstantsPacman() {
        evolveInstantsPacman = 1;
        evolveInstantsGhosts = 1;
        validInstants = findValidInstantsPacman();
    }
    public void initializeEvolveInstantsGhosts() {
        evolveInstantsPacman = 1;
        evolveInstantsGhosts = 1;
        validInstants = findValidInstantsGhosts();
    }
    public void incrementEvolveInstantsPacman() {
        if (evolveInstantsPacman < 20)
            evolveInstantsPacman++;
        else
            evolveInstantsPacman = 1;
    }
    public void incrementEvolveInstantsGhosts() {
        if (evolveInstantsGhosts < 20)
            evolveInstantsGhosts++;
        else
            evolveInstantsGhosts = 1;
    }
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