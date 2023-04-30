package pt.isec.pa.tinypac.model.data.pacman;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.Directions;
import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.maze.Maze;
import pt.isec.pa.tinypac.model.data.warp.Warp;

public class Pacman extends Element implements IGameEngineEvolve {
    //Internal Data
    public static final char SYMBOL = 'M';
    private Directions direction;
    private int points;

    //Constructor
    public Pacman(Maze maze, int x, int y) {
        super(maze, x, y);
        this.points = 0;
        this.direction = null;
    }

    //Get Methods
    public int getPoints() {
        return this.points;
    }

    //Set Methods
    public void setDirection(Directions direction) { this.direction = direction; }

    //Methods


    //Overrides
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        maze.set(y,x,null);
        switch (direction) {
            case UP -> {
                //Mudar cadeia de if's para switch
                //Detecao Paredes
                if ((maze.get(y-1,x) == null && y > 0) || maze.get(y-1,x).getSymbol() != 'x')
                    y--;

                //Detecao Ball
                ballDetection();

                //Detecao Zona Warp
                warpDetection();
            }
            case DOWN -> {
                //Detecao Paredes
                if ((maze.get(y+1, x) == null && y < maze.getMaze().length - 1) || maze.get(y+1, x).getSymbol() != 'x')
                    y++;

                //Detecao Ball
                ballDetection();

                //Detecao Zona Warp
                warpDetection();
            }
            case LEFT -> {
                //Detecao Paredes
                if ((maze.get(y, x-1) == null && x > 0) || maze.get(y, x-1).getSymbol() != 'x')
                    x--;

                //Detecao Ball
                ballDetection();

                //Detecao Zona Warp
                warpDetection();
            }
            case RIGHT -> {
                //Detecao Paredes
                if ((maze.get(y, x+1) == null && x < maze.getMaze()[0].length - 1) || maze.get(y, x+1).getSymbol() != 'x')
                    x++;

                //Detecao Ball
                ballDetection();

                //Detecao Zona Warp
                warpDetection();
            }
        }
        maze.set(y,x,this);
    }

    //Overrides
    @Override
    public char getSymbol() {
        return 'M';
    }
    @Override
    public String toString() {
        return "Pacman";
    }

    //Internal Functions
    private void warpDetection() {
        if (maze.get(y,x) != null && maze.get(y,x).getSymbol() == 'W') {
            Element warp = (Element) maze.get(y,x);
            maze.set(((Warp) warp).getComplementWarp().getY(), ((Warp) warp).getComplementWarp().getX(), this);
            this.x = ((Warp) warp).getComplementWarp().getX();
            this.y = ((Warp) warp).getComplementWarp().getY();
        }
    }
    private void ballDetection() {
        if (maze.get(y,x) != null && maze.get(y,x).getSymbol() == 'o') {
            this.points++;
        }
    }
}