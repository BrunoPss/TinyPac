package pt.isec.pa.tinypac.model.fsm;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.Directions;
import pt.isec.pa.tinypac.model.data.Pacman.Pacman;
import pt.isec.pa.tinypac.model.data.maze.Maze;

public class GameContext {
    //Internal Data
    IGameState state;
    IGameEngine gameEngine;
    Maze maze;
    Pacman pacman;

    //Constructor
    public GameContext(IGameEngine gameEngine) {
        this.maze = new Maze(10, 10);
        this.pacman = new Pacman(maze, 5, 5);
        this.gameEngine = gameEngine;

        gameEngine.registerClient(pacman);

        this.state = new InitState(this, maze);
    }

    //Get Methods
    public GameState getState() {
        return state.getState();
    }
    public String getPacmanDirection() { return pacman.getCurrentDirection(); }
    public String getPacmanPosition() { return pacman.getCurrentPosition(); }
    public char[][] getMaze() { return maze.getMaze(); }

    //Set Methods
    void changeState(IGameState newState) {
        this.state = newState;
    }

    //Methods
    public boolean up() {
        this.pacman.setDirection(Directions.UP);
        return state.up();
    }
    public boolean down() {
        this.pacman.setDirection(Directions.DOWN);
        return state.down();
    }
    public boolean left() {
        this.pacman.setDirection(Directions.LEFT);
        return state.left();
    }
    public boolean right() {
        this.pacman.setDirection(Directions.RIGHT);
        return state.right();
    }
    public boolean pauseGame() { return state.pauseGame(); }
    public boolean resumeGame() { return state.resumeGame(); }

    //Overrides


    //Internal Functions


}