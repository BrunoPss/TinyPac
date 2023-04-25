package pt.isec.pa.tinypac.model.data.Pacman;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.Directions;
import pt.isec.pa.tinypac.model.data.maze.IMazeElement;
import pt.isec.pa.tinypac.model.data.maze.Maze;

public class Pacman implements IGameEngineEvolve, IMazeElement {
    //Internal Data
    public static final char SYMBOL = 'M';
    private Directions direction;
    private final Maze maze;
    private int x, y;

    //Constructor
    public Pacman(Maze maze, int x, int y) {
        this.direction = null;
        this.x = x;
        this.y = y;
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
            case UP -> {
                if (y > 0) {    // Condicao if (y > 0 && maze.get(y-1, x) == ' ') no bloco que se pretende ir nao tem parede
                    y--;
                }
            }
            case DOWN -> {
                if (y < (maze.getMaze().length - 1))
                    y++;
            }
            case LEFT -> {
                if (x > 0)
                    x--;
            }
            case RIGHT -> {
                if (x < (maze.getMaze()[0].length - 1))
                    x++;
            }
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