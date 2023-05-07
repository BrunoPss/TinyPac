package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.game.Game;

public class GameContext {
    //Internal Data
    IGameState state;
    //IGameEngine gameEngine;
    Game gameData;
    //Maze maze;
    //Dictionary<ElementType, Element> entities;

    //Constructor
    public GameContext(IGameEngine gameEngine) {
        //this.maze = new Maze(MazeManager.getYSize("C:\\Projects\\TP-PA_2023\\TP-PA_2023\\src\\pt\\isec\\pa\\tinypac\\model\\data\\levels\\level01.txt"), MazeManager.getXSize("C:\\Projects\\TP-PA_2023\\TP-PA_2023\\src\\pt\\isec\\pa\\tinypac\\model\\data\\levels\\level01.txt"));
        //this.gameEngine = gameEngine;
        this.gameData = new Game(gameEngine);
        this.state = new InitState(this, gameData);
    }

    //Get Methods
    public GameState getState() {
        return state.getState();
    }
    public int getPacmanPoints() { return gameData.getPacman().getPoints(); }
    //public String getPacmanDirection() { return entities.get(ElementType.PACMAN).getCurrentDirection(); }
    //public String getPacmanPosition() { return entities.get(ElementType.PACMAN).getCurrentPosition(); }
    public char[][] getMaze() { return gameData.getMaze().getMaze(); }

    //Set Methods
    void changeState(IGameState newState) {
        this.state = newState;
    }

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

    //Overrides


    //Internal Functions


}