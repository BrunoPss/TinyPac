package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.entity.EntityType;
import pt.isec.pa.tinypac.model.data.game.Game;

import java.util.Random;

/**
 * Clyde Class
 * <p>Class that represents the Clyde Ghost Element</p>
 *
 * @ author Bruno Guiomar
 * @ version 1.0.0
 */

public class Clyde extends Ghost {
    //Internal Data
    public static final char SYMBOL = 'C';
    private int initTime = 6;
    public static boolean ACTIVE = false;
    private boolean pacmanSawn = false;
    private corridor pacmanCorridor;
    private Directions pacmanDirection;
    private Random rnd;

    private enum corridor {
        HORIZONTAL, VERTICAL
    }

    //Constructor
    public Clyde(Game gameData, int x, int y) {
        super(gameData, x, y);

        this.direction = Directions.UP;
        this.rnd = new Random();
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public void move() {
        if (gameData.getEntityCord(EntityType.PACMAN)[0] == x || gameData.getEntityCord(EntityType.PACMAN)[1] == y) {
            pacmanSawn = true;

            if (gameData.getEntityCord(EntityType.PACMAN)[1] == y) {
                pacmanCorridor = corridor.HORIZONTAL;
                if (gameData.getEntityCord(EntityType.PACMAN)[0] < x) {
                    pacmanDirection = Directions.LEFT;
                }
                else {
                    pacmanDirection = Directions.RIGHT;
                }
            }
            else if (gameData.getEntityCord(EntityType.PACMAN)[0] == x) {
                pacmanCorridor = corridor.VERTICAL;
                if (gameData.getEntityCord(EntityType.PACMAN)[1] < y) {
                    pacmanDirection = Directions.UP;
                }
                else {
                    pacmanDirection = Directions.DOWN;
                }
            }
        }
        else {
            pacmanSawn = false;
        }
        System.out.println("Corrido: " + pacmanCorridor + " directin: " + pacmanDirection);
        if (initTime == 0) {
            ACTIVE = true;

            if (cave) {
                x = gameData.getGhostDoor()[0];
                y = gameData.getGhostDoor()[1];
                cave = false;
            }
            switch (direction) {
                case UP -> {
                    //Cima livre
                    if ((gameData.getMaze().get(y-1, x) == null && y > 0) || gameData.getMaze().get(y-1, x).getSymbol() != 'x') {
                        //Esquerda Livre
                        //Cima livre & Esquerda livre
                        if (gameData.getMaze().get(y, x-1) == null || gameData.getMaze().get(y, x-1).getSymbol() != 'x') {
                            //Direita Livre
                            //Cima livre & Esquerda livre & Direita livre
                            if (gameData.getMaze().get(y, x+1) == null || gameData.getMaze().get(y, x+1).getSymbol() != 'x') {
                                if (pacmanSawn) {
                                    direction = pacmanDirection;
                                    if (direction == Directions.UP)
                                        y--;
                                    else if (direction == Directions.RIGHT)
                                        x++;
                                    else if (direction == Directions.LEFT)
                                        x--;
                                }
                                else {
                                    float randN = rnd.nextFloat();
                                    //Esquerda
                                    if (randN <= 0.33) {
                                        direction = Directions.LEFT;
                                        //auxF = gameData.getMaze().get(y,x-1);
                                        x--;
                                        //gameData.getMaze().set(y,x+1, auxA);
                                    }
                                    //Cima
                                    else if (randN > 0.33 && randN <= 0.66) {
                                        direction = Directions.UP;
                                        //auxF = gameData.getMaze().get(y-1,x);
                                        y--;
                                        //gameData.getMaze().set(y+1,x, auxA);
                                    }
                                    //Direita
                                    else {
                                        direction = Directions.RIGHT;
                                        //auxF = gameData.getMaze().get(y,x+1);
                                        x++;
                                        //gameData.getMaze().set(y,x-1, auxA);
                                    }
                                }
                            }
                            //Direita Parede
                            //Cima livre & Esquerda livre & Direita parede
                            else if (gameData.getMaze().get(y, x+1) == null || gameData.getMaze().get(y, x+1).getSymbol() == 'x') {
                                if (pacmanSawn) {
                                    direction = pacmanDirection;
                                    if (direction == Directions.UP)
                                        y--;
                                    else if (direction == Directions.LEFT)
                                        x--;
                                }
                                else {
                                    //Cima
                                    if (rnd.nextBoolean()) {
                                        direction = Directions.UP;
                                        //auxF = gameData.getMaze().get(y-1,x);
                                        y--;
                                        //gameData.getMaze().set(y+1,x, auxA);
                                    }
                                    //Esquerda
                                    else {
                                        direction = Directions.LEFT;
                                        //auxF = gameData.getMaze().get(y,x-1);
                                        x--;
                                        //gameData.getMaze().set(y,x+1, auxA);
                                    }
                                }
                            }
                        }
                        //Esquerda Parede
                        //Cima Livre & Esquerda Parede
                        else if (gameData.getMaze().get(y, x-1) == null || gameData.getMaze().get(y, x-1).getSymbol() == 'x') {
                            //Direita Parede
                            //Cima Livre & Esquerda Parede & Direita Parede
                            if (gameData.getMaze().get(y, x+1) == null || gameData.getMaze().get(y, x+1).getSymbol() == 'x') {
                                //auxF = gameData.getMaze().get(y-1,x);
                                y--;
                                //gameData.getMaze().set(y+1,x, auxA);
                            }
                            //Direita livre
                            //Cima livre & Esquerda parede & Direita livre
                            else if (gameData.getMaze().get(y, x+1) == null || gameData.getMaze().get(y, x+1).getSymbol() != 'x') {
                                if (pacmanSawn) {
                                    direction = pacmanDirection;
                                    if (direction == Directions.UP)
                                        y--;
                                    else if (direction == Directions.RIGHT)
                                        x++;
                                }
                                else {
                                    //Cima
                                    if (rnd.nextBoolean()) {
                                        direction = Directions.UP;
                                        //auxF = gameData.getMaze().get(y-1,x);
                                        y--;
                                        //gameData.getMaze().set(y+1,x, auxA);
                                    }
                                    //Direita
                                    else {
                                        direction = Directions.RIGHT;
                                        //auxF = gameData.getMaze().get(y,x+1);
                                        x++;
                                        //gameData.getMaze().set(y,x-1, auxA);
                                    }
                                }
                            }
                        }
                    }
                    //Cima Parede
                    else if ((gameData.getMaze().get(y-1, x) == null && y > 0) || gameData.getMaze().get(y-1, x).getSymbol() == 'x') {
                        //Esquerda Livre
                        //Cima parede & Esquerda livre
                        if (gameData.getMaze().get(y, x-1) == null || gameData.getMaze().get(y, x-1).getSymbol() != 'x') {
                            //Direita Livre
                            //Cima parede & Esquerda livre & Direita livre
                            if (gameData.getMaze().get(y, x+1) == null || gameData.getMaze().get(y, x+1).getSymbol() != 'x') {
                                if (pacmanSawn) {
                                    direction = pacmanDirection;
                                    if (direction == Directions.LEFT)
                                        x--;
                                    else if (direction == Directions.RIGHT)
                                        x++;
                                }
                                else {
                                    //Esquerda
                                    if (rnd.nextBoolean()) {
                                        direction = Directions.LEFT;
                                        //auxF = gameData.getMaze().get(y,x-1);
                                        x--;
                                        //gameData.getMaze().set(y,x+1, auxA);
                                    }
                                    //Direita
                                    else {
                                        direction = Directions.RIGHT;
                                        //auxF = gameData.getMaze().get(y,x+1);
                                        x++;
                                        //gameData.getMaze().set(y,x-1, auxA);
                                    }
                                }
                            }
                            //Direita Parede
                            //Cima parede & Esquerda livre & Direita parede
                            else if (gameData.getMaze().get(y, x+1) == null || gameData.getMaze().get(y, x+1).getSymbol() == 'x') {
                                direction = Directions.LEFT;
                                //auxF = gameData.getMaze().get(y,x-1);
                                x--;
                                //gameData.getMaze().set(y,x+1, auxA);
                            }
                        }
                        //Esquerda Parede
                        //Cima parede & Esquerda Parede
                        else if (gameData.getMaze().get(y, x-1) == null || gameData.getMaze().get(y, x-1).getSymbol() == 'x') {
                            //Direita Parede
                            //Cima parede & Esquerda Parede & Direita Parede
                            if (gameData.getMaze().get(y, x+1) == null || gameData.getMaze().get(y, x+1).getSymbol() == 'x') {
                                direction = Directions.DOWN;
                                //auxF = gameData.getMaze().get(y+1,x);
                                y++;
                                //gameData.getMaze().set(y-1,x, auxA);
                            }
                            //Direita livre
                            //Cima parede & Esquerda parede & Direita livre
                            else if (gameData.getMaze().get(y, x+1) == null || gameData.getMaze().get(y, x+1).getSymbol() != 'x') {
                                direction = Directions.RIGHT;
                                //auxF = gameData.getMaze().get(y,x+1);
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
                    if ((gameData.getMaze().get(y+1, x) == null && y < gameData.getMaze().getMaze().length - 1) || gameData.getMaze().get(y-1, x).getSymbol() != 'x') {
                        //Esquerda Livre
                        //Baixo livre & Esquerda livre
                        if (gameData.getMaze().get(y, x-1) == null || gameData.getMaze().get(y, x-1).getSymbol() != 'x') {
                            //Direita Livre
                            //Baixo livre & Esquerda livre & Direita livre
                            if (gameData.getMaze().get(y, x+1) == null || gameData.getMaze().get(y, x+1).getSymbol() != 'x') {
                                if (pacmanSawn) {
                                    direction = pacmanDirection;
                                    if (direction == Directions.DOWN)
                                        y++;
                                    else if (direction == Directions.LEFT)
                                        x--;
                                    else if (direction == Directions.RIGHT)
                                        x++;
                                }
                                else {
                                    float randN = rnd.nextFloat();
                                    //Esquerda
                                    if (randN <= 0.33) {
                                        direction = Directions.LEFT;
                                        //auxF = gameData.getMaze().get(y,x-1);
                                        x--;
                                        //gameData.getMaze().set(y,x+1, auxA);
                                    }
                                    //Baixo
                                    else if (randN > 0.33 && randN <= 0.66) {
                                        direction = Directions.DOWN;
                                        //auxF = gameData.getMaze().get(y+1,x);
                                        y++;
                                        //gameData.getMaze().set(y-1,x, auxA);
                                    }
                                    //Direita
                                    else {
                                        direction = Directions.RIGHT;
                                        //auxF = gameData.getMaze().get(y,x+1);
                                        x++;
                                        //gameData.getMaze().set(y,x-1, auxA);
                                    }
                                }
                            }
                            //Direita Parede
                            //Baixo livre & Esquerda livre & Direita parede
                            else if (gameData.getMaze().get(y, x+1) == null || gameData.getMaze().get(y, x+1).getSymbol() == 'x') {
                                if (pacmanSawn) {
                                    direction = pacmanDirection;
                                    if (direction == Directions.DOWN)
                                        y++;
                                    else if (direction == Directions.LEFT)
                                        x--;
                                }
                                else {
                                    //Baixo
                                    if (rnd.nextBoolean()) {
                                        direction = Directions.DOWN;
                                        //auxF = gameData.getMaze().get(y+1,x);
                                        y++;
                                        //gameData.getMaze().set(y-1,x, auxA);
                                    }
                                    //Esquerda
                                    else {
                                        direction = Directions.LEFT;
                                        //auxF = gameData.getMaze().get(y,x-1);
                                        x--;
                                        //gameData.getMaze().set(y,x+1, auxA);
                                    }
                                }
                            }
                        }
                        //Esquerda Parede
                        //Baixo Livre & Esquerda Parede
                        else if (gameData.getMaze().get(y, x-1) == null || gameData.getMaze().get(y, x-1).getSymbol() == 'x') {
                            //Direita Parede
                            //Baixo Livre & Esquerda Parede & Direita Parede
                            if (gameData.getMaze().get(y, x+1) == null || gameData.getMaze().get(y, x+1).getSymbol() == 'x') {
                                //auxF = gameData.getMaze().get(y+1,x);
                                y++;
                                //gameData.getMaze().set(y-1,x, auxA);
                            }
                            //Direita livre
                            //Baixo livre & Esquerda parede & Direita livre
                            else if (gameData.getMaze().get(y, x+1) == null || gameData.getMaze().get(y, x+1).getSymbol() != 'x') {
                                if (pacmanSawn) {
                                    direction = pacmanDirection;
                                    if (direction == Directions.DOWN)
                                        y++;
                                    else if (direction == Directions.RIGHT)
                                        x++;
                                }
                                else {
                                    //Baixo
                                    if (rnd.nextBoolean()) {
                                        direction = Directions.DOWN;
                                        //auxF = gameData.getMaze().get(y+1,x);
                                        y++;
                                        //gameData.getMaze().set(y-1,x, auxA);
                                    }
                                    //Direita
                                    else {
                                        direction = Directions.RIGHT;
                                        //auxF = gameData.getMaze().get(y,x+1);
                                        x++;
                                        //gameData.getMaze().set(y,x-1, auxA);
                                    }
                                }
                            }
                        }
                    }
                    //Baixo Parede
                    else if ((gameData.getMaze().get(y+1, x) == null && y < gameData.getMaze().getMaze().length - 1) || gameData.getMaze().get(y+1, x).getSymbol() == 'x') {
                        //Esquerda Livre
                        //Baixo parede & Esquerda livre
                        if (gameData.getMaze().get(y, x-1) == null || gameData.getMaze().get(y, x-1).getSymbol() != 'x') {
                            //Direita Livre
                            //Baixo parede & Esquerda livre & Direita livre
                            if (gameData.getMaze().get(y, x+1) == null || gameData.getMaze().get(y, x+1).getSymbol() != 'x') {
                                if (pacmanSawn) {
                                    direction = pacmanDirection;
                                    if (direction == Directions.LEFT)
                                        x--;
                                    else if (direction == Directions.RIGHT)
                                        x++;
                                }
                                else {
                                    //Esquerda
                                    if (rnd.nextBoolean()) {
                                        direction = Directions.LEFT;
                                        //auxF = gameData.getMaze().get(y,x-1);
                                        x--;
                                        //gameData.getMaze().set(y,x+1, auxA);
                                    }
                                    //Direita
                                    else {
                                        direction = Directions.RIGHT;
                                        //auxF = gameData.getMaze().get(y,x+1);
                                        x++;
                                        //gameData.getMaze().set(y,x-1, auxA);
                                    }
                                }
                            }
                            //Direita Parede
                            //Baixo parede & Esquerda livre & Direita parede
                            else if (gameData.getMaze().get(y, x+1) == null || gameData.getMaze().get(y, x+1).getSymbol() == 'x') {
                                direction = Directions.LEFT;
                                //auxF = gameData.getMaze().get(y,x-1);
                                x--;
                                //gameData.getMaze().set(y,x+1, auxA);
                            }
                        }
                        //Esquerda Parede
                        //Baixo parede & Esquerda Parede
                        else if (gameData.getMaze().get(y, x-1) == null || gameData.getMaze().get(y, x-1).getSymbol() == 'x') {
                            //Direita Parede
                            //Baixo parede & Esquerda Parede & Direita Parede
                            if (gameData.getMaze().get(y, x+1) == null || gameData.getMaze().get(y, x+1).getSymbol() == 'x') {
                                direction = Directions.UP;
                                //auxF = gameData.getMaze().get(y-1,x);
                                y--;
                                //gameData.getMaze().set(y+1,x, auxA);
                            }
                            //Direita livre
                            //Baixo parede & Esquerda parede & Direita livre
                            else if (gameData.getMaze().get(y, x+1) == null || gameData.getMaze().get(y, x+1).getSymbol() != 'x') {
                                direction = Directions.RIGHT;
                                //auxF = gameData.getMaze().get(y,x+1);
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
                    if ((gameData.getMaze().get(y, x-1) == null && x > 0) || gameData.getMaze().get(y, x-1).getSymbol() != 'x') {
                        //Baixo Livre
                        //Esquerda livre & Baixo livre
                        if (gameData.getMaze().get(y+1, x) == null || gameData.getMaze().get(y+1, x).getSymbol() != 'x') {
                            //Cima Livre
                            //Esquerda livre & Baixo livre & Cima livre
                            if (gameData.getMaze().get(y-1, x) == null || gameData.getMaze().get(y-1, x).getSymbol() != 'x') {
                                if (pacmanSawn) {
                                    direction = pacmanDirection;
                                    if (direction == Directions.DOWN)
                                        y++;
                                    else if (direction == Directions.UP)
                                        y--;
                                    else if (direction == Directions.LEFT)
                                        x--;
                                }
                                else {
                                    float randN = rnd.nextFloat();
                                    //Baixo
                                    if (randN <= 0.33) {
                                        direction = Directions.DOWN;
                                        //auxF = gameData.getMaze().get(y+1,x);
                                        y++;
                                        //gameData.getMaze().set(y-1,x, auxA);
                                    }
                                    //Esquerda
                                    else if (randN > 0.33 && randN <= 0.66) {
                                        direction = Directions.LEFT;
                                        //auxF = gameData.getMaze().get(y,x-1);
                                        x--;
                                        //gameData.getMaze().set(y,x+1, auxA);
                                    }
                                    //Cima
                                    else {
                                        direction = Directions.UP;
                                        //auxF = gameData.getMaze().get(y-1,x);
                                        y--;
                                        //gameData.getMaze().set(y+1,x, auxA);
                                    }
                                }
                            }
                            //Cima Parede
                            //Esquerda livre & Baixo livre & Cima parede
                            else if (gameData.getMaze().get(y-1, x) == null || gameData.getMaze().get(y-1, x).getSymbol() == 'x') {
                                if (pacmanSawn) {
                                    direction = pacmanDirection;
                                    if (direction == Directions.DOWN)
                                        y++;
                                    else if (direction == Directions.LEFT)
                                        x--;
                                }
                                else {
                                    //Esquerda
                                    if (rnd.nextBoolean()) {
                                        direction = Directions.LEFT;
                                        //auxF = gameData.getMaze().get(y,x-1);
                                        x--;
                                        //gameData.getMaze().set(y,x+1, auxA);
                                    }
                                    //Baixo
                                    else {
                                        direction = Directions.DOWN;
                                        //auxF = gameData.getMaze().get(y+1,x);
                                        y++;
                                        //gameData.getMaze().set(y-1,x, auxA);
                                    }
                                }
                            }
                        }
                        //Baixo Parede
                        //Esquerda Livre & Baixo Parede
                        else if (gameData.getMaze().get(y+1, x) == null || gameData.getMaze().get(y+1, x).getSymbol() == 'x') {
                            //Cima Parede
                            //Esquerda Livre & Baixo Parede & Cima Parede
                            if (gameData.getMaze().get(y-1, x) == null || gameData.getMaze().get(y-1, x).getSymbol() == 'x') {
                                //auxF = gameData.getMaze().get(y,x-1);
                                x--;
                                //gameData.getMaze().set(y,x+1, auxA);
                            }
                            //Cima livre
                            //Esquerda livre & Baixo parede & Cima livre
                            else if (gameData.getMaze().get(y-1, x) == null || gameData.getMaze().get(y-1, x).getSymbol() != 'x') {
                                if (pacmanSawn) {
                                    direction = pacmanDirection;
                                    if (direction == Directions.UP)
                                        y--;
                                    else if (direction == Directions.LEFT)
                                        x--;
                                }
                                else {
                                    //Esquerda
                                    if (rnd.nextBoolean()) {
                                        direction = Directions.LEFT;
                                        //auxF = gameData.getMaze().get(y,x-1);
                                        x--;
                                        //gameData.getMaze().set(y,x+1, auxA);
                                    }
                                    //Cima
                                    else {
                                        direction = Directions.UP;
                                        //auxF = gameData.getMaze().get(y-1,x);
                                        y--;
                                        //gameData.getMaze().set(y+1,x, auxA);
                                    }
                                }
                            }
                        }
                    }
                    //Esquerda Parede
                    else if ((gameData.getMaze().get(y, x-1) == null && x > 0) || gameData.getMaze().get(y, x-1).getSymbol() == 'x') {
                        //Baixo Livre
                        //Esquerda parede & Baixo livre
                        if (gameData.getMaze().get(y+1, x) == null || gameData.getMaze().get(y+1, x).getSymbol() != 'x') {
                            //Cima Livre
                            //Esquerda parede & Baixo livre & Cima livre
                            if (gameData.getMaze().get(y-1, x) == null || gameData.getMaze().get(y-1, x).getSymbol() != 'x') {
                                if (pacmanSawn) {
                                    direction = pacmanDirection;
                                    if (direction == Directions.DOWN)
                                        y++;
                                    else if (direction == Directions.UP)
                                        y--;
                                }
                                else {
                                    //Baixo
                                    if (rnd.nextBoolean()) {
                                        direction = Directions.DOWN;
                                        //auxF = gameData.getMaze().get(y+1,x);
                                        y++;
                                        //gameData.getMaze().set(y-1,x, auxA);
                                    }
                                    //Cima
                                    else {
                                        direction = Directions.UP;
                                        //auxF = gameData.getMaze().get(y-1,x);
                                        y--;
                                        //gameData.getMaze().set(y+1,x, auxA);
                                    }
                                }
                            }
                            //Cima Parede
                            //Esquerda parede & Baixo livre & Cima parede
                            else if (gameData.getMaze().get(y-1, x) == null || gameData.getMaze().get(y-1, x).getSymbol() == 'x') {
                                direction = Directions.DOWN;
                                //auxF = gameData.getMaze().get(y+1,x);
                                y++;
                                //gameData.getMaze().set(y-1,x, auxA);
                            }
                        }
                        //Baixo Parede
                        //Esquerda parede & Baixo Parede
                        else if (gameData.getMaze().get(y+1, x) == null || gameData.getMaze().get(y+1, x).getSymbol() == 'x') {
                            //Cima Parede
                            //Esquerda parede & Baixo Parede & Cima Parede
                            if (gameData.getMaze().get(y-1, x) == null || gameData.getMaze().get(y-1, x).getSymbol() == 'x') {
                                direction = Directions.RIGHT;
                                //auxF = gameData.getMaze().get(y,x+1);
                                x++;
                                //gameData.getMaze().set(y,x-1, auxA);
                            }
                            //Cima livre
                            //Esquerda parede & Baixo parede & Cima livre
                            else if (gameData.getMaze().get(y-1, x) == null || gameData.getMaze().get(y-1, x).getSymbol() != 'x') {
                                direction = Directions.UP;
                                //auxF = gameData.getMaze().get(y-1,x);
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
                    if ((gameData.getMaze().get(y, x+1) == null && x < gameData.getMaze().getMaze()[0].length - 1) || gameData.getMaze().get(y, x+1).getSymbol() != 'x') {
                        //Baixo Livre
                        //Direita livre & Baixo livre
                        if (gameData.getMaze().get(y+1, x) == null || gameData.getMaze().get(y+1, x).getSymbol() != 'x') {
                            //Cima Livre
                            //Direita livre & Baixo livre & Cima livre
                            if (gameData.getMaze().get(y-1, x) == null || gameData.getMaze().get(y-1, x).getSymbol() != 'x') {
                                if (pacmanSawn) {
                                    direction = pacmanDirection;
                                    if (direction == Directions.DOWN)
                                        y++;
                                    else if (direction == Directions.UP)
                                        y--;
                                    else if (direction == Directions.RIGHT)
                                        x++;
                                }
                                else {
                                    float randN = rnd.nextFloat();
                                    //Baixo
                                    if (randN <= 0.33) {
                                        direction = Directions.DOWN;
                                        //auxF = gameData.getMaze().get(y+1,x);
                                        y++;
                                        //gameData.getMaze().set(y-1,x, auxA);
                                    }
                                    //Direita
                                    else if (randN > 0.33 && randN <= 0.66) {
                                        direction = Directions.RIGHT;
                                        //auxF = gameData.getMaze().get(y,x+1);
                                        x++;
                                        //gameData.getMaze().set(y,x-1, auxA);
                                    }
                                    //Cima
                                    else {
                                        direction = Directions.UP;
                                        //auxF = gameData.getMaze().get(y-1,x);
                                        y--;
                                        //gameData.getMaze().set(y+1,x, auxA);
                                    }
                                }
                            }
                            //Cima Parede
                            //Direita livre & Baixo livre & Cima parede
                            else if (gameData.getMaze().get(y-1, x) == null || gameData.getMaze().get(y-1, x).getSymbol() == 'x') {
                                if (pacmanSawn) {
                                    direction = pacmanDirection;
                                    if (direction == Directions.DOWN)
                                        y++;
                                    else if (direction == Directions.RIGHT)
                                        x++;
                                }
                                else {
                                    //Direita
                                    if (rnd.nextBoolean()) {
                                        direction = Directions.RIGHT;
                                        //auxF = gameData.getMaze().get(y,x+1);
                                        x++;
                                        //gameData.getMaze().set(y,x-1, auxA);
                                    }
                                    //Baixo
                                    else {
                                        direction = Directions.DOWN;
                                        //auxF = gameData.getMaze().get(y+1,x);
                                        y++;
                                        //gameData.getMaze().set(y-1,x, auxA);
                                    }
                                }
                            }
                        }
                        //Baixo Parede
                        //Direita Livre & Baixo Parede
                        else if (gameData.getMaze().get(y+1, x) == null || gameData.getMaze().get(y+1, x).getSymbol() == 'x') {
                            //Cima Parede
                            //Direita Livre & Baixo Parede & Cima Parede
                            if (gameData.getMaze().get(y-1, x) == null || gameData.getMaze().get(y-1, x).getSymbol() == 'x') {
                                //auxF = gameData.getMaze().get(y,x+1);
                                x++;
                                //gameData.getMaze().set(y,x-1, auxA);
                            }
                            //Cima livre
                            //Direita livre & Baixo parede & Cima livre
                            else if (gameData.getMaze().get(y-1, x) == null || gameData.getMaze().get(y-1, x).getSymbol() != 'x') {
                                if (pacmanSawn) {
                                    direction = pacmanDirection;
                                    if (direction == Directions.UP)
                                        y--;
                                    else if (direction == Directions.RIGHT)
                                        x++;
                                }
                                else {
                                    //Direita
                                    if (rnd.nextBoolean()) {
                                        direction = Directions.RIGHT;
                                        //auxF = gameData.getMaze().get(y,x+1);
                                        x++;
                                        //gameData.getMaze().set(y,x-1, auxA);
                                    }
                                    //Cima
                                    else {
                                        direction = Directions.UP;
                                        //auxF = gameData.getMaze().get(y-1,x);
                                        y--;
                                        //gameData.getMaze().set(y+1,x, auxA);
                                    }
                                }
                            }
                        }
                    }
                    //Direita Parede
                    else if ((gameData.getMaze().get(y, x+1) == null && x < gameData.getMaze().getMaze()[0].length - 1) || gameData.getMaze().get(y, x+1).getSymbol() == 'x') {
                        //Baixo Livre
                        //Direita parede & Baixo livre
                        if (gameData.getMaze().get(y+1, x) == null || gameData.getMaze().get(y+1, x).getSymbol() != 'x') {
                            //Cima Livre
                            //Direita parede & Baixo livre & Cima livre
                            if (gameData.getMaze().get(y-1, x) == null || gameData.getMaze().get(y-1, x).getSymbol() != 'x') {
                                if (pacmanSawn) {
                                    direction = pacmanDirection;
                                    if (direction == Directions.DOWN)
                                        y++;
                                    else if (direction == Directions.UP)
                                        y--;
                                }
                                else {
                                    //Baixo
                                    if (rnd.nextBoolean()) {
                                        direction = Directions.DOWN;
                                        //auxF = gameData.getMaze().get(y+1,x);
                                        y++;
                                        //gameData.getMaze().set(y-1,x, auxA);
                                    }
                                    //Cima
                                    else {
                                        direction = Directions.UP;
                                        //auxF = gameData.getMaze().get(y-1,x);
                                        y--;
                                        //gameData.getMaze().set(y+1,x, auxA);
                                    }
                                }
                            }
                            //Cima Parede
                            //Direita parede & Baixo livre & Cima parede
                            else if (gameData.getMaze().get(y-1, x) == null || gameData.getMaze().get(y-1, x).getSymbol() == 'x') {
                                direction = Directions.DOWN;
                                //auxF = gameData.getMaze().get(y+1,x);
                                y++;
                                //gameData.getMaze().set(y-1,x, auxA);
                            }
                        }
                        //Baixo Parede
                        //Direita parede & Baixo Parede
                        else if (gameData.getMaze().get(y+1, x) == null || gameData.getMaze().get(y+1, x).getSymbol() == 'x') {
                            //Cima Parede
                            //Direita parede & Baixo Parede & Cima Parede
                            if (gameData.getMaze().get(y-1, x) == null || gameData.getMaze().get(y-1, x).getSymbol() == 'x') {
                                direction = Directions.LEFT;
                                //auxF = gameData.getMaze().get(y,x-1);
                                x--;
                                //gameData.getMaze().set(y,x+1, auxA);
                            }
                            //Cima livre
                            //Direita parede & Baixo parede & Cima livre
                            else if (gameData.getMaze().get(y-1, x) == null || gameData.getMaze().get(y-1, x).getSymbol() != 'x') {
                                direction = Directions.UP;
                                //auxF = gameData.getMaze().get(y-1,x);
                                y--;
                                //gameData.getMaze().set(y+1,x, auxA);
                            }
                        }
                    }

                    //gameData.getMaze().set(y, x, this);
                    //auxA = auxF;
                }
            }
        }
        else
            initTime--;
    }

    @Override
    public char getSymbol() {
        return 'C';
    }

    //Internal Functions


}