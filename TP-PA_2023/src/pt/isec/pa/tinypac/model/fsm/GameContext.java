package pt.isec.pa.tinypac.model.fsm;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Directions;
import pt.isec.pa.tinypac.model.data.Pacman.Pacman;
import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.element.ElementType;
import pt.isec.pa.tinypac.model.data.maze.Maze;
import pt.isec.pa.tinypac.model.data.maze.MazeManager;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

public class GameContext {
    //Internal Data
    IGameState state;
    IGameEngine gameEngine;
    Maze maze;
    Dictionary<ElementType, Element> entities;

    //Constructor
    public GameContext(IGameEngine gameEngine) {
        this.maze = new Maze(MazeManager.getYSize("C:\\Projects\\TP-PA_2023\\TP-PA_2023\\src\\pt\\isec\\pa\\tinypac\\model\\data\\levels\\level01.txt"), MazeManager.getXSize("C:\\Projects\\TP-PA_2023\\TP-PA_2023\\src\\pt\\isec\\pa\\tinypac\\model\\data\\levels\\level01.txt"));
        this.entities = MazeManager.loadLevel(maze, this.gameEngine, "C:\\Projects\\TP-PA_2023\\TP-PA_2023\\src\\pt\\isec\\pa\\tinypac\\model\\data\\levels\\level01.txt");
        //this.pacman = new Pacman(maze, 1, 1);
        this.gameEngine = gameEngine;

        gameEngine.registerClient((IGameEngineEvolve) entities.get(ElementType.PACMAN));

        this.state = new InitState(this, maze);
    }

    //Get Methods
    public GameState getState() {
        return state.getState();
    }
    //public String getPacmanDirection() { return entities.get(ElementType.PACMAN).getCurrentDirection(); }
    //public String getPacmanPosition() { return entities.get(ElementType.PACMAN).getCurrentPosition(); }
    public char[][] getMaze() { return maze.getMaze(); }

    //Set Methods
    void changeState(IGameState newState) {
        this.state = newState;
    }

    //Methods
    public boolean up() {
        ((Pacman) this.entities.get(ElementType.PACMAN)).setDirection(Directions.UP);
        return state.up();
    }
    public boolean down() {
        ((Pacman) this.entities.get(ElementType.PACMAN)).setDirection(Directions.DOWN);
        return state.down();
    }
    public boolean left() {
        ((Pacman) this.entities.get(ElementType.PACMAN)).setDirection(Directions.LEFT);
        return state.left();
    }
    public boolean right() {
        ((Pacman) this.entities.get(ElementType.PACMAN)).setDirection(Directions.RIGHT);
        return state.right();
    }
    public boolean pauseGame() { return state.pauseGame(); }
    public boolean resumeGame() { return state.resumeGame(); }

    //Overrides


    //Internal Functions


}