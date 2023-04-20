package pt.isec.pa.tinypac.model.data.Pacman;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.Directions;
import pt.isec.pa.tinypac.model.data.maze.IMazeElement;
import pt.isec.pa.tinypac.model.data.maze.Maze;

public class Pacman implements IGameEngineEvolve, IMazeElement {
    //Internal Data
    private Directions direction;
    private Maze maze;
    private int x, y;

    //Constructor
    public Pacman(Maze maze) {
        this.direction = null;
        this.x = 5;
        this.y = 5;
        this.maze = maze;
        this.maze.set(y,x,this);
    }

    //Get Methods
    public String getCurrentDirection() {
        return direction.toString();
    }
    public String getCurrentPosition() { return Integer.toString(x) + " " + Integer.toString(y); }

    //Set Methods
    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    //Methods


    //Overrides
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        maze.set(y,x,null);
        switch (direction) {
            case UP -> y--;
            case DOWN -> y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }
        maze.set(y,x,this);
    }
    @Override
    public char getSymbol() {
        return 'M';
    }
    @Override
    public String toString() {
        return "Pacman";
    }

    //Internal Functions


}