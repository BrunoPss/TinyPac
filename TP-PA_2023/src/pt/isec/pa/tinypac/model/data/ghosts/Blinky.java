package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.entity.EntityType;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.maze.IMazeElement;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

/**
 * Blinky Class
 * <p>Class that represents the Blinky Ghost Element</p>
 *
 * @ author Bruno Guiomar
 * @ version 1.0.0
 */

public class Blinky extends Ghost {
    //Internal Data
    public static final char SYMBOL = 'B';
    private int initTime = 4;
    public static boolean ACTIVE = false;
    private IMazeElement auxF;
    private IMazeElement auxA;
    private Random rnd;
    private ArrayList<int[]> movementLog;
    private int movementIterator = 0;
    //private ListIterator<int[]> movementIterator;

    //Constructor
    /**
     * Constructor
     * @param gameData Game Data Model
     * @param x Initial Position (x cord.)
     * @param y Initial Position (y cord.)
     */
    public Blinky(Game gameData, int x, int y) {
        super(gameData, x, y);
        this.direction = Directions.UP;
        this.rnd = new Random();
        this.movementLog = new ArrayList<>();

        this.auxF = null;
        this.auxA = gameData.getIMazeElement(y,x);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public void move() {
        if (gameData.findValidInstantsGhosts().contains(gameData.getEvolveInstantsGhosts())) {

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
                    ACTIVE = true;
                }

                if (gameData.getEnchancedPhase()) {
                    if (movementIterator-1 >= 0) {
                        x = movementLog.get(movementIterator-1)[0];
                        y = movementLog.get(movementIterator-1)[1];
                        movementIterator--;
                    }

                    //if (movementIterator.hasPrevious()) {
                    //    int[] aux;
                    //    aux = movementIterator.previous();
                    //    x = aux[0];
                    //    y = aux[1];
                    //}
                    //Blinky chegou aao destino
                } else {
                    //Logica Movimentacao Normal Blinky
                    switch (direction) {
                        case UP -> {
                            //Cima livre
                            if ((gameData.getIMazeElement(y - 1, x) == null && y > 0) || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                //Esquerda Livre
                                //Cima livre & Esquerda livre
                                if (gameData.getIMazeElement(y, x - 1) == null || gameData.getIMazeElement(y, x - 1).getSymbol() != 'x') {
                                    //Direita Livre
                                    //Cima livre & Esquerda livre & Direita livre
                                    if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                        float randN = rnd.nextFloat();
                                        //Esquerda
                                        if (randN <= 0.33) {
                                            direction = Directions.LEFT;
                                            //auxF = gameData.getIMazeElement(y,x-1);
                                            x--;
                                            //gameData.getMaze().set(y,x+1, auxA);
                                        }
                                        //Cima
                                        else if (randN > 0.33 && randN <= 0.66) {
                                            direction = Directions.UP;
                                            //auxF = gameData.getIMazeElement(y-1,x);
                                            y--;
                                            //gameData.getMaze().set(y+1,x, auxA);
                                        }
                                        //Direita
                                        else {
                                            direction = Directions.RIGHT;
                                            //auxF = gameData.getIMazeElement(y,x+1);
                                            x++;
                                            //gameData.getMaze().set(y,x-1, auxA);
                                        }
                                    }
                                    //Direita Parede
                                    //Cima livre & Esquerda livre & Direita parede
                                    else if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() == 'x') {
                                        //Cima
                                        if (rnd.nextBoolean()) {
                                            direction = Directions.UP;
                                            //auxF = gameData.getIMazeElement(y-1,x);
                                            y--;
                                            //gameData.getMaze().set(y+1,x, auxA);
                                        }
                                        //Esquerda
                                        else {
                                            direction = Directions.LEFT;
                                            //auxF = gameData.getIMazeElement(y,x-1);
                                            x--;
                                            //gameData.getMaze().set(y,x+1, auxA);
                                        }
                                    }
                                }
                                //Esquerda Parede
                                //Cima Livre & Esquerda Parede
                                else if (gameData.getIMazeElement(y, x - 1) == null || gameData.getIMazeElement(y, x - 1).getSymbol() == 'x') {
                                    //Direita Parede
                                    //Cima Livre & Esquerda Parede & Direita Parede
                                    if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() == 'x') {
                                        //auxF = gameData.getIMazeElement(y-1,x);
                                        y--;
                                        //gameData.getMaze().set(y+1,x, auxA);
                                    }
                                    //Direita livre
                                    //Cima livre & Esquerda parede & Direita livre
                                    else if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                        //Cima
                                        if (rnd.nextBoolean()) {
                                            direction = Directions.UP;
                                            //auxF = gameData.getIMazeElement(y-1,x);
                                            y--;
                                            //gameData.getMaze().set(y+1,x, auxA);
                                        }
                                        //Direita
                                        else {
                                            direction = Directions.RIGHT;
                                            //auxF = gameData.getIMazeElement(y,x+1);
                                            x++;
                                            //gameData.getMaze().set(y,x-1, auxA);
                                        }
                                    }
                                }
                            }
                            //Cima Parede
                            else if ((gameData.getIMazeElement(y - 1, x) == null && y > 0) || gameData.getIMazeElement(y - 1, x).getSymbol() == 'x') {
                                //Esquerda Livre
                                //Cima parede & Esquerda livre
                                if (gameData.getIMazeElement(y, x - 1) == null || gameData.getIMazeElement(y, x - 1).getSymbol() != 'x') {
                                    //Direita Livre
                                    //Cima parede & Esquerda livre & Direita livre
                                    if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                        //Esquerda
                                        if (rnd.nextBoolean()) {
                                            direction = Directions.LEFT;
                                            //auxF = gameData.getIMazeElement(y,x-1);
                                            x--;
                                            //gameData.getMaze().set(y,x+1, auxA);
                                        }
                                        //Direita
                                        else {
                                            direction = Directions.RIGHT;
                                            //auxF = gameData.getIMazeElement(y,x+1);
                                            x++;
                                            //gameData.getMaze().set(y,x-1, auxA);
                                        }
                                    }
                                    //Direita Parede
                                    //Cima parede & Esquerda livre & Direita parede
                                    else if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() == 'x') {
                                        direction = Directions.LEFT;
                                        //auxF = gameData.getIMazeElement(y,x-1);
                                        x--;
                                        //gameData.getMaze().set(y,x+1, auxA);
                                    }
                                }
                                //Esquerda Parede
                                //Cima parede & Esquerda Parede
                                else if (gameData.getIMazeElement(y, x - 1) == null || gameData.getIMazeElement(y, x - 1).getSymbol() == 'x') {
                                    //Direita Parede
                                    //Cima parede & Esquerda Parede & Direita Parede
                                    if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() == 'x') {
                                        direction = Directions.DOWN;
                                        //auxF = gameData.getIMazeElement(y+1,x);
                                        y++;
                                        //gameData.getMaze().set(y-1,x, auxA);
                                    }
                                    //Direita livre
                                    //Cima parede & Esquerda parede & Direita livre
                                    else if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                        direction = Directions.RIGHT;
                                        //auxF = gameData.getIMazeElement(y,x+1);
                                        x++;
                                        //gameData.getMaze().set(y,x-1, auxA);
                                    }
                                }
                            }
                            //gameData.getMaze().set(y, x, this);
                            //auxA = auxF;
                        }
                        case DOWN -> {
                            //Baixo livre
                            if ((gameData.getIMazeElement(y + 1, x) == null && y < gameData.getMazeLength() + 1) || gameData.getIMazeElement(y + 1, x).getSymbol() != 'x') {
                                //Esquerda Livre
                                //Baixo livre & Esquerda livre
                                if (gameData.getIMazeElement(y, x - 1) == null || gameData.getIMazeElement(y, x - 1).getSymbol() != 'x') {
                                    //Direita Livre
                                    //Baixo livre & Esquerda livre & Direita livre
                                    if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                        float randN = rnd.nextFloat();
                                        //Esquerda
                                        if (randN <= 0.33) {
                                            direction = Directions.LEFT;
                                            //auxF = gameData.getIMazeElement(y,x-1);
                                            x--;
                                            //gameData.getMaze().set(y,x+1, auxA);
                                        }
                                        //Baixo
                                        else if (randN > 0.33 && randN <= 0.66) {
                                            direction = Directions.DOWN;
                                            //auxF = gameData.getIMazeElement(y+1,x);
                                            y++;
                                            //gameData.getMaze().set(y-1,x, auxA);
                                        }
                                        //Direita
                                        else {
                                            direction = Directions.RIGHT;
                                            //auxF = gameData.getIMazeElement(y,x+1);
                                            x++;
                                            //gameData.getMaze().set(y,x-1, auxA);
                                        }
                                    }
                                    //Direita Parede
                                    //Baixo livre & Esquerda livre & Direita parede
                                    else if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() == 'x') {
                                        //Baixo
                                        if (rnd.nextBoolean()) {
                                            direction = Directions.DOWN;
                                            //auxF = gameData.getIMazeElement(y+1,x);
                                            y++;
                                            //gameData.getMaze().set(y-1,x, auxA);
                                        }
                                        //Esquerda
                                        else {
                                            direction = Directions.LEFT;
                                            //auxF = gameData.getIMazeElement(y,x-1);
                                            x--;
                                            //gameData.getMaze().set(y,x+1, auxA);
                                        }
                                    }
                                }
                                //Esquerda Parede
                                //Baixo Livre & Esquerda Parede
                                else if (gameData.getIMazeElement(y, x - 1) == null || gameData.getIMazeElement(y, x - 1).getSymbol() == 'x') {
                                    //Direita Parede
                                    //Baixo Livre & Esquerda Parede & Direita Parede
                                    if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() == 'x') {
                                        //auxF = gameData.getIMazeElement(y+1,x);
                                        y++;
                                        //gameData.getMaze().set(y-1,x, auxA);
                                    }
                                    //Direita livre
                                    //Baixo livre & Esquerda parede & Direita livre
                                    else if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                        //Baixo
                                        if (rnd.nextBoolean()) {
                                            direction = Directions.DOWN;
                                            //auxF = gameData.getIMazeElement(y+1,x);
                                            y++;
                                            //gameData.getMaze().set(y-1,x, auxA);
                                        }
                                        //Direita
                                        else {
                                            direction = Directions.RIGHT;
                                            //auxF = gameData.getIMazeElement(y,x+1);
                                            x++;
                                            //gameData.getMaze().set(y,x-1, auxA);
                                        }
                                    }
                                }
                            }
                            //Baixo Parede
                            else if ((gameData.getIMazeElement(y + 1, x) == null && y < gameData.getMazeLength() + 1) || gameData.getIMazeElement(y + 1, x).getSymbol() == 'x') {
                                //Esquerda Livre
                                //Baixo parede & Esquerda livre
                                if (gameData.getIMazeElement(y, x - 1) == null || gameData.getIMazeElement(y, x - 1).getSymbol() != 'x') {
                                    //Direita Livre
                                    //Baixo parede & Esquerda livre & Direita livre
                                    if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                        //Esquerda
                                        if (rnd.nextBoolean()) {
                                            direction = Directions.LEFT;
                                            //auxF = gameData.getIMazeElement(y,x-1);
                                            x--;
                                            //gameData.getMaze().set(y,x+1, auxA);
                                        }
                                        //Direita
                                        else {
                                            direction = Directions.RIGHT;
                                            //auxF = gameData.getIMazeElement(y,x+1);
                                            x++;
                                            //gameData.getMaze().set(y,x-1, auxA);
                                        }
                                    }
                                    //Direita Parede
                                    //Baixo parede & Esquerda livre & Direita parede
                                    else if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() == 'x') {
                                        direction = Directions.LEFT;
                                        //auxF = gameData.getIMazeElement(y,x-1);
                                        x--;
                                        //gameData.getMaze().set(y,x+1, auxA);
                                    }
                                }
                                //Esquerda Parede
                                //Baixo parede & Esquerda Parede
                                else if (gameData.getIMazeElement(y, x - 1) == null || gameData.getIMazeElement(y, x - 1).getSymbol() == 'x') {
                                    //Direita Parede
                                    //Baixo parede & Esquerda Parede & Direita Parede
                                    if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() == 'x') {
                                        direction = Directions.UP;
                                        //auxF = gameData.getIMazeElement(y-1,x);
                                        y--;
                                        //gameData.getMaze().set(y+1,x, auxA);
                                    }
                                    //Direita livre
                                    //Baixo parede & Esquerda parede & Direita livre
                                    else if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                        direction = Directions.RIGHT;
                                        //auxF = gameData.getIMazeElement(y,x+1);
                                        x++;
                                        //gameData.getMaze().set(y,x-1, auxA);
                                    }
                                }
                            }

                            //gameData.getMaze().set(y, x, this);
                            //auxA = auxF;
                        }
                        case LEFT -> {
                            //Esquerda livre
                            if ((gameData.getIMazeElement(y, x - 1) == null && x > 0) || gameData.getIMazeElement(y, x - 1).getSymbol() != 'x') {
                                //Baixo Livre
                                //Esquerda livre & Baixo livre
                                if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() != 'x') {
                                    //Cima Livre
                                    //Esquerda livre & Baixo livre & Cima livre
                                    if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                        float randN = rnd.nextFloat();
                                        //Baixo
                                        if (randN <= 0.33) {
                                            direction = Directions.DOWN;
                                            //auxF = gameData.getIMazeElement(y+1,x);
                                            y++;
                                            //gameData.getMaze().set(y-1,x, auxA);
                                        }
                                        //Esquerda
                                        else if (randN > 0.33 && randN <= 0.66) {
                                            direction = Directions.LEFT;
                                            //auxF = gameData.getIMazeElement(y,x-1);
                                            x--;
                                            //gameData.getMaze().set(y,x+1, auxA);
                                        }
                                        //Cima
                                        else {
                                            direction = Directions.UP;
                                            //auxF = gameData.getIMazeElement(y-1,x);
                                            y--;
                                            //gameData.getMaze().set(y+1,x, auxA);
                                        }
                                    }
                                    //Cima Parede
                                    //Esquerda livre & Baixo livre & Cima parede
                                    else if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() == 'x') {
                                        //Esquerda
                                        if (rnd.nextBoolean()) {
                                            direction = Directions.LEFT;
                                            //auxF = gameData.getIMazeElement(y,x-1);
                                            x--;
                                            //gameData.getMaze().set(y,x+1, auxA);
                                        }
                                        //Baixo
                                        else {
                                            direction = Directions.DOWN;
                                            //auxF = gameData.getIMazeElement(y+1,x);
                                            y++;
                                            //gameData.getMaze().set(y-1,x, auxA);
                                        }
                                    }
                                }
                                //Baixo Parede
                                //Esquerda Livre & Baixo Parede
                                else if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() == 'x') {
                                    //Cima Parede
                                    //Esquerda Livre & Baixo Parede & Cima Parede
                                    if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() == 'x') {
                                        //auxF = gameData.getIMazeElement(y,x-1);
                                        x--;
                                        //gameData.getMaze().set(y,x+1, auxA);
                                    }
                                    //Cima livre
                                    //Esquerda livre & Baixo parede & Cima livre
                                    else if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                        //Esquerda
                                        if (rnd.nextBoolean()) {
                                            direction = Directions.LEFT;
                                            //auxF = gameData.getIMazeElement(y,x-1);
                                            x--;
                                            //gameData.getMaze().set(y,x+1, auxA);
                                        }
                                        //Cima
                                        else {
                                            direction = Directions.UP;
                                            //auxF = gameData.getIMazeElement(y-1,x);
                                            y--;
                                            //gameData.getMaze().set(y+1,x, auxA);
                                        }
                                    }
                                }
                            }
                            //Esquerda Parede
                            else if ((gameData.getIMazeElement(y, x - 1) == null && x > 0) || gameData.getIMazeElement(y, x - 1).getSymbol() == 'x') {
                                //Baixo Livre
                                //Esquerda parede & Baixo livre
                                if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() != 'x') {
                                    //Cima Livre
                                    //Esquerda parede & Baixo livre & Cima livre
                                    if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                        //Baixo
                                        if (rnd.nextBoolean()) {
                                            direction = Directions.DOWN;
                                            //auxF = gameData.getIMazeElement(y+1,x);
                                            y++;
                                            //gameData.getMaze().set(y-1,x, auxA);
                                        }
                                        //Cima
                                        else {
                                            direction = Directions.UP;
                                            //auxF = gameData.getIMazeElement(y-1,x);
                                            y--;
                                            //gameData.getMaze().set(y+1,x, auxA);
                                        }
                                    }
                                    //Cima Parede
                                    //Esquerda parede & Baixo livre & Cima parede
                                    else if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() == 'x') {
                                        direction = Directions.DOWN;
                                        //auxF = gameData.getIMazeElement(y+1,x);
                                        y++;
                                        //gameData.getMaze().set(y-1,x, auxA);
                                    }
                                }
                                //Baixo Parede
                                //Esquerda parede & Baixo Parede
                                else if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() == 'x') {
                                    //Cima Parede
                                    //Esquerda parede & Baixo Parede & Cima Parede
                                    if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() == 'x') {
                                        direction = Directions.RIGHT;
                                        //auxF = gameData.getIMazeElement(y,x+1);
                                        x++;
                                        //gameData.getMaze().set(y,x-1, auxA);
                                    }
                                    //Cima livre
                                    //Esquerda parede & Baixo parede & Cima livre
                                    else if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                        direction = Directions.UP;
                                        //auxF = gameData.getIMazeElement(y-1,x);
                                        y--;
                                        //gameData.getMaze().set(y+1,x, auxA);
                                    }
                                }
                            }

                            //gameData.getMaze().set(y, x, this);
                            //auxA = auxF;
                        }
                        case RIGHT -> {
                            //Direita livre
                            if ((gameData.getIMazeElement(y, x + 1) == null && x < gameData.getMazeHeight() - 3) || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                //Baixo Livre
                                //Direita livre & Baixo livre
                                if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() != 'x') {
                                    //Cima Livre
                                    //Direita livre & Baixo livre & Cima livre
                                    if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                        float randN = rnd.nextFloat();
                                        //Baixo
                                        if (randN <= 0.33) {
                                            direction = Directions.DOWN;
                                            //auxF = gameData.getIMazeElement(y+1,x);
                                            y++;
                                            //gameData.getMaze().set(y-1,x, auxA);
                                        }
                                        //Direita
                                        else if (randN > 0.33 && randN <= 0.66) {
                                            direction = Directions.RIGHT;
                                            //auxF = gameData.getIMazeElement(y,x+1);
                                            x++;
                                            //gameData.getMaze().set(y,x-1, auxA);
                                        }
                                        //Cima
                                        else {
                                            direction = Directions.UP;
                                            //auxF = gameData.getIMazeElement(y-1,x);
                                            y--;
                                            //gameData.getMaze().set(y+1,x, auxA);
                                        }
                                    }
                                    //Cima Parede
                                    //Direita livre & Baixo livre & Cima parede
                                    else if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() == 'x') {
                                        //Direita
                                        if (rnd.nextBoolean()) {
                                            direction = Directions.RIGHT;
                                            //auxF = gameData.getIMazeElement(y,x+1);
                                            x++;
                                            //gameData.getMaze().set(y,x-1, auxA);
                                        }
                                        //Baixo
                                        else {
                                            direction = Directions.DOWN;
                                            //auxF = gameData.getIMazeElement(y+1,x);
                                            y++;
                                            //gameData.getMaze().set(y-1,x, auxA);
                                        }
                                    }
                                }
                                //Baixo Parede
                                //Direita Livre & Baixo Parede
                                else if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() == 'x') {
                                    //Cima Parede
                                    //Direita Livre & Baixo Parede & Cima Parede
                                    if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() == 'x') {
                                        //auxF = gameData.getIMazeElement(y,x+1);
                                        x++;
                                        //gameData.getMaze().set(y,x-1, auxA);
                                    }
                                    //Cima livre
                                    //Direita livre & Baixo parede & Cima livre
                                    else if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                        //Direita
                                        if (rnd.nextBoolean()) {
                                            direction = Directions.RIGHT;
                                            //auxF = gameData.getIMazeElement(y,x+1);
                                            x++;
                                            //gameData.getMaze().set(y,x-1, auxA);
                                        }
                                        //Cima
                                        else {
                                            direction = Directions.UP;
                                            //auxF = gameData.getIMazeElement(y-1,x);
                                            y--;
                                            //gameData.getMaze().set(y+1,x, auxA);
                                        }
                                    }
                                }
                            }
                            //Direita Parede
                            else if ((gameData.getIMazeElement(y, x + 1) == null && x < gameData.getMazeHeight() - 3) || gameData.getIMazeElement(y, x + 1).getSymbol() == 'x') {
                                //Baixo Livre
                                //Direita parede & Baixo livre
                                if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() != 'x') {
                                    //Cima Livre
                                    //Direita parede & Baixo livre & Cima livre
                                    if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                        //Baixo
                                        if (rnd.nextBoolean()) {
                                            direction = Directions.DOWN;
                                            //auxF = gameData.getIMazeElement(y+1,x);
                                            y++;
                                            //gameData.getMaze().set(y-1,x, auxA);
                                        }
                                        //Cima
                                        else {
                                            direction = Directions.UP;
                                            //auxF = gameData.getIMazeElement(y-1,x);
                                            y--;
                                            //gameData.getMaze().set(y+1,x, auxA);
                                        }
                                    }
                                    //Cima Parede
                                    //Direita parede & Baixo livre & Cima parede
                                    else if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() == 'x') {
                                        direction = Directions.DOWN;
                                        //auxF = gameData.getIMazeElement(y+1,x);
                                        y++;
                                        //gameData.getMaze().set(y-1,x, auxA);
                                    }
                                }
                                //Baixo Parede
                                //Direita parede & Baixo Parede
                                else if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() == 'x') {
                                    //Cima Parede
                                    //Direita parede & Baixo Parede & Cima Parede
                                    if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() == 'x') {
                                        direction = Directions.LEFT;
                                        //auxF = gameData.getIMazeElement(y,x-1);
                                        x--;
                                        //gameData.getMaze().set(y,x+1, auxA);
                                    }
                                    //Cima livre
                                    //Direita parede & Baixo parede & Cima livre
                                    else if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                        direction = Directions.UP;
                                        //auxF = gameData.getIMazeElement(y-1,x);
                                        y--;
                                        //gameData.getMaze().set(y+1,x, auxA);
                                    }
                                }
                            }

                            //gameData.getMaze().set(y, x, this);
                            //auxA = auxF;
                        }
                    }
                    movementLog.add(new int[]{x, y});
                    this.movementIterator = movementLog.size();
                    //this.movementIterator = movementLog.listIterator(movementLog.size());
                }
            } else
                initTime--;

        }
        gameData.incrementEvolveInstantsGhosts();
    }

    /**
     * Gets Element Symbol
     * @return Symbol of the Element (Blinky)
     */
    @Override
    public char getSymbol() {
        return 'B';
    }

    //Internal Functions


}