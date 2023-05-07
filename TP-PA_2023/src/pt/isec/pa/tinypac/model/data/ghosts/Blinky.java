package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.Directions;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.maze.IMazeElement;

import java.util.Random;

public class Blinky extends Ghost {
    //Internal Data
    public static char SYMBOL = 'B';
    private IMazeElement auxF = null;
    private IMazeElement auxA = null;
    Random rnd;

    //Constructor
    public Blinky(Game gameData, int x, int y) {
        super(gameData, x, y);
        this.direction = Directions.RIGHT;
        this.rnd = new Random();
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        if (initTime == 0) {
            if (cave) {
                /*
                //Algoritmo de caminho para Y
                if (gameData.getGhostDoor()[1] < y) {
                    //direction = Directions.UP;
                    System.out.println("up");
                }
                else if (gameData.getGhostDoor()[1] > y) {
                    System.out.println("down");
                    //direction = Directions.DOWN;
                }
                else if (gameData.getGhostDoor()[0] < x) {
                    //direction = Directions.RIGHT;
                    System.out.println("right");
                }
                else if (gameData.getGhostDoor()[0] > x) {
                    //direction = Directions.LEFT;
                    System.out.println("left");
                }
                */
                x = gameData.getGhostDoor()[0];
                y = gameData.getGhostDoor()[1];
                cave = false;
            }
            switch (direction) {
                case UP -> {
                    //Detecao Paredes
                    if ((gameData.getMaze().get(y-1,x) == null && y > 0) || gameData.getMaze().get(y-1,x).getSymbol() != 'x') {
                        auxF = gameData.getMaze().get(y - 1, x);
                        y--;
                    }
                    else {
                        //Parede esquerda
                        if (gameData.getMaze().get(y, x-1).getSymbol() == 'x')
                            //Parede direita
                            if (gameData.getMaze().get(y, x+1).getSymbol() == 'x')
                                direction = Directions.DOWN;
                            else
                                direction = Directions.RIGHT;
                            //Parede direita
                        else if (gameData.getMaze().get(y, x+1).getSymbol() == 'x')
                            direction = Directions.LEFT;
                        else {
                            if (rnd.nextBoolean())
                                direction = Directions.LEFT;
                            else
                                direction = Directions.RIGHT;
                        }
                    }

                    gameData.getMaze().set(y, x, this);
                    gameData.getMaze().set(y+1,x, auxA);
                    auxA = auxF;
                }
                case DOWN -> {
                    //Detecao Paredes
                    if ((gameData.getMaze().get(y+1, x) == null && y < gameData.getMaze().getMaze().length - 1) || gameData.getMaze().get(y+1, x).getSymbol() != 'x') {
                        auxF = gameData.getMaze().get(y + 1, x);
                        y++;
                    }
                    else {
                        //Parede esquerda
                        if (gameData.getMaze().get(y, x-1).getSymbol() == 'x')
                            //Parede direita
                            if (gameData.getMaze().get(y, x+1).getSymbol() == 'x')
                                direction = Directions.UP;
                            else
                                direction = Directions.RIGHT;
                        //Parede direita
                        else if (gameData.getMaze().get(y, x+1).getSymbol() == 'x')
                            direction = Directions.LEFT;
                        else {
                            if (rnd.nextBoolean())
                                direction = Directions.LEFT;
                            else
                                direction = Directions.RIGHT;
                        }
                    }

                    gameData.getMaze().set(y, x, this);
                    gameData.getMaze().set(y-1,x, auxA);
                    auxA = auxF;
                }
                case LEFT -> {
                    //Detecao Paredes
                    if ((gameData.getMaze().get(y, x-1) == null && x > 0) || gameData.getMaze().get(y, x-1).getSymbol() != 'x') {
                        auxF = gameData.getMaze().get(y, x - 1);
                        x--;
                    }
                    else {
                        //Parede acima
                        if (gameData.getMaze().get(y-1, x).getSymbol() == 'x')
                            //Parede abaixo
                            if (gameData.getMaze().get(y+1, x).getSymbol() == 'x')
                                direction = Directions.RIGHT;
                            else
                                direction = Directions.DOWN;
                        //Parede abaixo
                        else if (gameData.getMaze().get(y+1, x).getSymbol() == 'x')
                            direction = Directions.UP;
                        else {
                            if (rnd.nextBoolean())
                                direction = Directions.UP;
                            else
                                direction = Directions.DOWN;
                        }
                    }

                    gameData.getMaze().set(y, x, this);
                    gameData.getMaze().set(y,x+1, auxA);
                    auxA = auxF;
                }
                case RIGHT -> {
                    //Detecao Paredes
                    if ((gameData.getMaze().get(y, x+1) == null && x < gameData.getMaze().getMaze()[0].length - 1) || gameData.getMaze().get(y, x+1).getSymbol() != 'x') {
                        auxF = gameData.getMaze().get(y, x + 1);
                        x++;
                    }
                    else {
                        //Parede acima
                        if (gameData.getMaze().get(y-1, x).getSymbol() == 'x')
                            //Parede abaixo
                            if (gameData.getMaze().get(y+1, x).getSymbol() == 'x')
                                direction = Directions.LEFT;
                            else
                                direction = Directions.DOWN;
                        //Parede abaixo
                        else if (gameData.getMaze().get(y+1, x).getSymbol() == 'x')
                            direction = Directions.UP;
                        else {
                            if (rnd.nextBoolean())
                                direction = Directions.UP;
                            else
                                direction = Directions.DOWN;
                        }
                    }

                    gameData.getMaze().set(y, x, this);
                    gameData.getMaze().set(y,x-1, auxA);
                    auxA = auxF;
                }
            }

        }
        else
            initTime--;
    }

    @Override
    public char getSymbol() {
        return 'B';
    }

    //Internal Functions


}