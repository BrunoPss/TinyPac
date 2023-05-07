package pt.isec.pa.tinypac.model.data.pacman;

import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.Directions;
import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.warp.Warp;

public class Pacman extends Element implements IGameEngineEvolve {
    //Internal Data
    public static final char SYMBOL = 'M';
    private Directions direction;
    private int points;

    //Constructor
    public Pacman(Game gameData, int x, int y) {
        super(gameData, x, y);
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
        gameData.getMaze().set(y,x,null);
        switch (direction) {
            case UP -> {
                //Mudar cadeia de if's para switch
                //Detecao Paredes
                if ((gameData.getMaze().get(y-1,x) == null && y > 0) || gameData.getMaze().get(y-1,x).getSymbol() != 'x')
                    y--;

                //Detecao Ball
                ballDetection();

                //Detecao Zona Warp
                warpDetection();
            }
            case DOWN -> {
                //Detecao Paredes
                if ((gameData.getMaze().get(y+1, x) == null && y < gameData.getMaze().getMaze().length - 1) || gameData.getMaze().get(y+1, x).getSymbol() != 'x')
                    y++;

                //Detecao Ball
                ballDetection();

                //Detecao Zona Warp
                warpDetection();
            }
            case LEFT -> {
                //Detecao Paredes
                if ((gameData.getMaze().get(y, x-1) == null && x > 0) || gameData.getMaze().get(y, x-1).getSymbol() != 'x')
                    x--;

                //Detecao Ball
                ballDetection();

                //Detecao Zona Warp
                warpDetection();
            }
            case RIGHT -> {
                //Detecao Paredes
                if ((gameData.getMaze().get(y, x+1) == null && x < gameData.getMaze().getMaze()[0].length - 1) || gameData.getMaze().get(y, x+1).getSymbol() != 'x')
                    x++;

                //Detecao Ball
                ballDetection();

                //Detecao Zona Warp
                warpDetection();
            }
        }
        gameData.getMaze().set(y,x,this);
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
    private void warpDetection() {
        if (gameData.getMaze().get(y,x) != null && gameData.getMaze().get(y,x).getSymbol() == 'W') {
            Element warp = (Element) gameData.getMaze().get(y,x);
            gameData.getMaze().set(((Warp) warp).getComplementWarp().getY(), ((Warp) warp).getComplementWarp().getX(), this);
            this.x = ((Warp) warp).getComplementWarp().getX();
            this.y = ((Warp) warp).getComplementWarp().getY();
        }
    }
    private void ballDetection() {
        if (gameData.getMaze().get(y,x) != null && gameData.getMaze().get(y,x).getSymbol() == 'o') {
            this.points++;
        }
    }
}