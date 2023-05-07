package pt.isec.pa.tinypac.model.data.game;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.element.ElementType;
import pt.isec.pa.tinypac.model.data.ghosts.Ghost;
import pt.isec.pa.tinypac.model.data.maze.Maze;
import pt.isec.pa.tinypac.model.data.maze.MazeManager;
import pt.isec.pa.tinypac.model.data.pacman.Pacman;

public class Game {
    //Internal Data
    private Pacman pacman;
    private Ghost blinky;
    private Ghost clyde;
    private Ghost inky;
    private Ghost pinky;
    private Maze maze;
    private IGameEngine gameEngine;
    private int[] ghostDoor;

    //Constructor
    public Game(IGameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.ghostDoor = new int[2];
    }

    //Get Methods
    public Pacman getPacman() {
        return pacman;
    }
    public Maze getMaze() {
        return maze;
    }
    public IGameEngine getGameEngine() { return gameEngine; }
    public int[] getGhostDoor() { return ghostDoor; }

    //Set Methods
    public void setPacman(Element pacman) {
        this.pacman = (Pacman) pacman;
    }
    public void setBlinky(Element blinky) { this.blinky = (Ghost) blinky; }
    public void setClyde(Element clyde) { this.clyde = (Ghost) clyde; }
    public void setInky(Element inky) { this.inky = (Ghost) inky; }
    public void setPinky(Element pinky) { this.pinky = (Ghost) pinky; }
    public void setGhostDoor(int x, int y) { this.ghostDoor[0] = x; this.ghostDoor[1] = y; }

    //Methods
    public void initMaze() {
        maze = new Maze(MazeManager.getYSize("C:\\Projects\\TP-PA_2023\\TP-PA_2023\\src\\pt\\isec\\pa\\tinypac\\model\\data\\levels\\level01.txt"), MazeManager.getXSize("C:\\Projects\\TP-PA_2023\\TP-PA_2023\\src\\pt\\isec\\pa\\tinypac\\model\\data\\levels\\level01.txt"));
    }
    public void registerEntity(ElementType type) {
        switch (type) {
            case PACMAN -> gameEngine.registerClient(pacman);
            case BLINKY -> gameEngine.registerClient(blinky);
            case CLYDE -> gameEngine.registerClient(clyde);
            case INKY -> gameEngine.registerClient(inky);
            case PINKY -> gameEngine.registerClient(pinky);
        }
    }

    //Overrides


    //Internal Functions


}