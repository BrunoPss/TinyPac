package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.maze.IMazeElement;
import pt.isec.pa.tinypac.model.data.maze.MazeManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Inky Class
 * <p>Class that represents the Inky Ghost Element</p>
 *
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class Inky extends Ghost {
    //Internal Data
    /**
     * Symbol of the Element (Blinky)
     */
    public static final char SYMBOL = 'I';
    private int initTime = 8;
    /**
     * State of the Element
     */
    public static boolean ACTIVE = false;
    private Corners objectiveCorner;
    private int[] objectiveCords;
    private Random rnd;
    private IMazeElement auxF;
    private IMazeElement auxA;
    private int failedAttempts;
    private ArrayList<int[]> movementLog;
    private int movementIterator = 0;

    //Constructor
    /**
     * Constructor
     * @param gameData Game Data Model
     * @param x Initial Position (x cord.)
     * @param y Initial Position (y cord.)
     */
    public Inky(Game gameData, int x, int y) {
        super(gameData, x, y);
        this.direction = Directions.UP;
        this.objectiveCorner = Corners.BOTTOM_RIGHT;
        this.objectiveCords = new int[] {MazeManager.getXSize(gameData.getCurrentLevelFilePath()), 0};
        this.rnd = new Random();
        this.failedAttempts = 0;
        this.movementLog = new ArrayList<>();

        this.auxF = null;
        this.auxA = gameData.getIMazeElement(y,x);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    /**
     * Method that updates and moves the Ghost
     */
    @Override
    public void move() {
        if (gameData.findValidInstantsGhosts().contains(gameData.getEvolveInstantsGhosts())) {

            if (initTime == 0) {
                if (cave) {
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

                    // if (movementIterator.hasPrevious()) {
                    //     int[] aux;
                    //     aux = movementIterator.previous();
                    //     x = aux[0];
                    //     y = aux[1];
                    //}
                } else {
                    switch (objectiveCorner) {
                        case UPPER_RIGHT -> {
                            //Movimentos -> UP & RIGHT
                            //Objective Change (With 15 attempts the objective changes because it reaches a realtive hill)
                            if (Math.sqrt(Math.pow(objectiveCords[0] - x, 2) + Math.pow(objectiveCords[1] - y, 2)) <= 0.15 * (MazeManager.getXSize(gameData.getCurrentLevelFilePath()))
                                    || failedAttempts >= 10) {
                                System.out.println("CHANGE");
                                objectiveCorner = Corners.UPPER_LEFT;
                                failedAttempts = 0;
                            }

                            //Distancia Diagonal
                            //UP
                            double distance1 = Math.sqrt(Math.pow(objectiveCords[0] - x, 2) + Math.pow(objectiveCords[1] - (y - 1), 2));
                            //RIGHT
                            double distance2 = Math.sqrt(Math.pow(objectiveCords[0] - (x + 1), 2) + Math.pow(objectiveCords[1] - y, 2));
                            //System.out.println("UP: " + distance1 + "\nRIGHT: " + distance2);

                            //Direction UP
                            if (distance1 < distance2) {
                                //Cima livre
                                if ((gameData.getIMazeElement(y - 1, x) == null && y > 0) || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                    direction = Directions.UP;
                                    //auxF = gameData.getIMazeElement(y-1, x);
                                    y--;
                                    //gameData.getMaze().set(y+1, x, auxA);
                                }
                                //Cima parede
                                else if ((gameData.getIMazeElement(y - 1, x) == null && y > 0) || gameData.getIMazeElement(y - 1, x).getSymbol() == 'x') {
                                    //Direita Livre
                                    if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                        direction = Directions.RIGHT;
                                        //auxF = gameData.getIMazeElement(y, x+1);
                                        x++;
                                        //gameData.getMaze().set(y, x-1, auxA);
                                    } else if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() == 'x') {
                                        //Esquerda
                                        if (rnd.nextBoolean()) {
                                            if ((gameData.getIMazeElement(y, x - 1) == null && x > 0) || gameData.getIMazeElement(y, x - 1).getSymbol() != 'x') {
                                                direction = Directions.LEFT;
                                                //auxF = gameData.getIMazeElement(y, x-1);
                                                x--;
                                                //gameData.getMaze().set(y, x+1, auxA);

                                                failedAttempts++;
                                            }
                                        }
                                        //Baixo
                                        else {
                                            if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() != 'x') {
                                                direction = Directions.DOWN;
                                                //auxF = gameData.getIMazeElement(y+1, x);
                                                y++;
                                                //gameData.getMaze().set(y-1, x, auxA);

                                                failedAttempts++;
                                            }
                                        }
                                    }
                                }
                            }
                            //Direction RIGHT
                            else if (distance1 > distance2) {
                                //Direita livre
                                if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                    direction = Directions.RIGHT;
                                    //auxF = gameData.getIMazeElement(y, x+1);
                                    x++;
                                    //gameData.getMaze().set(y, x-1, auxA);
                                }
                                //Direita parede
                                else if ((gameData.getIMazeElement(y, x + 1) == null && y > 0) || gameData.getIMazeElement(y, x + 1).getSymbol() == 'x') {
                                    //Cima Livre
                                    if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                        direction = Directions.UP;
                                        //auxF = gameData.getIMazeElement(y-1, x);
                                        y--;
                                        //gameData.getMaze().set(y+1, x, auxA);
                                    } else if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() == 'x') {
                                        //Esquerda
                                        if (rnd.nextBoolean()) {
                                            if ((gameData.getIMazeElement(y, x - 1) == null && x > 0) || gameData.getIMazeElement(y, x - 1).getSymbol() != 'x') {
                                                direction = Directions.LEFT;
                                                //auxF = gameData.getIMazeElement(y, x-1);
                                                x--;
                                                //gameData.getMaze().set(y, x+1, auxA);

                                                failedAttempts++;
                                            }
                                        }
                                        //Baixo
                                        else {
                                            if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() != 'x') {
                                                direction = Directions.DOWN;
                                                //auxF = gameData.getIMazeElement(y+1, x);
                                                y++;
                                                //gameData.getMaze().set(y-1, x, auxA);

                                                failedAttempts++;
                                            }
                                        }
                                    }
                                }
                            }
                            //Random Direction
                            else {
                                //Esquerda
                                if (rnd.nextBoolean()) {
                                    if (gameData.getIMazeElement(y, x - 1) == null || gameData.getIMazeElement(y, x - 1).getSymbol() != 'x') {
                                        direction = Directions.LEFT;
                                        //auxF = gameData.getIMazeElement(y, x-1);
                                        x--;
                                        //gameData.getMaze().set(y, x+1, auxA);

                                        failedAttempts++;
                                    }
                                }
                                //Baixo
                                else {
                                    if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() != 'x') {
                                        direction = Directions.DOWN;
                                        //auxF = gameData.getIMazeElement(y+1, x);
                                        y++;
                                        //gameData.getMaze().set(y-1, x, auxA);

                                        failedAttempts++;
                                    }
                                }
                            }

                            //gameData.getMaze().set(y, x, this);
                            //auxA = auxF;
                        }
                        case BOTTOM_RIGHT -> {
                            //Movimentos -> DOWN & RIGHT
                            //Objective Change (With 15 attempts the objective changes because it reaches a realtive hill)
                            if (Math.sqrt(Math.pow(objectiveCords[0] - x, 2) + Math.pow(objectiveCords[1] - y, 2)) <= 0.15 * (MazeManager.getXSize(gameData.getCurrentLevelFilePath()))
                                    || failedAttempts >= 10) {
                                System.out.println("CHANGE");
                                objectiveCorner = Corners.BOTTOM_LEFT;
                                failedAttempts = 0;
                            }

                            //Distancia Diagonal
                            //DOWN
                            double distance1 = Math.sqrt(Math.pow(objectiveCords[0] - x, 2) + Math.pow(objectiveCords[1] - (y + 1), 2));
                            //RIGHT
                            double distance2 = Math.sqrt(Math.pow(objectiveCords[0] - (x + 1), 2) + Math.pow(objectiveCords[1] - y, 2));
                            //System.out.println("DOWN: " + distance1 + "\nRIGHT: " + distance2);

                            //Direction DOWN
                            if (distance1 < distance2) {
                                //Baixo livre
                                if ((gameData.getIMazeElement(y + 1, x) == null) || gameData.getIMazeElement(y + 1, x).getSymbol() != 'x') {
                                    direction = Directions.DOWN;
                                    //auxF = gameData.getIMazeElement(y+1, x);
                                    y++;
                                    //gameData.getMaze().set(y-1, x, auxA);
                                }
                                //Baixo parede
                                else if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() == 'x') {
                                    //Direita Livre
                                    if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                        direction = Directions.RIGHT;
                                        //auxF = gameData.getIMazeElement(y, x+1);
                                        x++;
                                        //gameData.getMaze().set(y, x-1, auxA);
                                    } else if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() == 'x') {
                                        //Esquerda
                                        if (rnd.nextBoolean()) {
                                            if ((gameData.getIMazeElement(y, x - 1) == null && x > 0) || gameData.getIMazeElement(y, x - 1).getSymbol() != 'x') {
                                                direction = Directions.LEFT;
                                                //auxF = gameData.getIMazeElement(y, x-1);
                                                x--;
                                                //gameData.getMaze().set(y, x+1, auxA);

                                                failedAttempts++;
                                            }
                                        }
                                        //Cima
                                        else {
                                            if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                                direction = Directions.UP;
                                                //auxF = gameData.getIMazeElement(y-1, x);
                                                y--;
                                                //gameData.getMaze().set(y+1, x, auxA);

                                                failedAttempts++;
                                            }
                                        }
                                    }
                                }
                            }
                            //Direction RIGHT
                            else if (distance1 > distance2) {
                                //Direita livre
                                if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                    direction = Directions.RIGHT;
                                    //auxF = gameData.getIMazeElement(y, x+1);
                                    x++;
                                    //gameData.getMaze().set(y, x-1, auxA);
                                }
                                //Direita parede
                                else if ((gameData.getIMazeElement(y, x + 1) == null && y > 0) || gameData.getIMazeElement(y, x + 1).getSymbol() == 'x') {
                                    //Baixo Livre
                                    if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() != 'x') {
                                        direction = Directions.DOWN;
                                        //auxF = gameData.getIMazeElement(y+1, x);
                                        y++;
                                        //gameData.getMaze().set(y-1, x, auxA);
                                    }
                                    //Baixo parede
                                    else if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() == 'x') {
                                        //Esquerda
                                        if (rnd.nextBoolean()) {
                                            if ((gameData.getIMazeElement(y, x - 1) == null && x > 0) || gameData.getIMazeElement(y, x - 1).getSymbol() != 'x') {
                                                direction = Directions.LEFT;
                                                //auxF = gameData.getIMazeElement(y, x-1);
                                                x--;
                                                //gameData.getMaze().set(y, x+1, auxA);

                                                failedAttempts++;
                                            }
                                        }
                                        //Cima
                                        else {
                                            if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                                direction = Directions.UP;
                                                //auxF = gameData.getIMazeElement(y-1, x);
                                                y--;
                                                //gameData.getMaze().set(y+1, x, auxA);

                                                failedAttempts++;
                                            }
                                        }
                                    }
                                }
                            }
                            //Random Direction
                            else {
                                //Cima
                                if (rnd.nextBoolean()) {
                                    if ((gameData.getIMazeElement(y - 1, x) == null && y > 0) || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                        direction = Directions.UP;
                                        //auxF = gameData.getIMazeElement(y - 1, x);
                                        y--;
                                        //gameData.getMaze().set(y + 1, x, auxA);

                                        failedAttempts++;
                                    }
                                }
                                //Esquerda
                                else {
                                    if (gameData.getIMazeElement(y, x - 1) == null || gameData.getIMazeElement(y, x - 1).getSymbol() != 'x') {
                                        direction = Directions.LEFT;
                                        //auxF = gameData.getIMazeElement(y, x-1);
                                        x--;
                                        //gameData.getMaze().set(y, x+1, auxA);

                                        failedAttempts++;
                                    }
                                }
                            }

                            //gameData.getMaze().set(y, x, this);
                            //auxA = auxF;
                        }
                        case UPPER_LEFT -> {
                            //Movimentos -> UP & LEFT
                            //Objective Change (With 15 attempts the objective changes because it reaches a realtive hill)
                            if (Math.sqrt(Math.pow(objectiveCords[0] - x, 2) + Math.pow(objectiveCords[1] - y, 2)) <= 0.15 * (MazeManager.getXSize(gameData.getCurrentLevelFilePath()))
                                    || failedAttempts >= 10) {
                                System.out.println("CHANGE");
                                objectiveCorner = Corners.BOTTOM_RIGHT;
                                failedAttempts = 0;
                            }

                            //Distancia Diagonal
                            //UP
                            double distance1 = Math.sqrt(Math.pow(objectiveCords[0] - x, 2) + Math.pow(objectiveCords[1] - (y - 1), 2));
                            //LEFT
                            double distance2 = Math.sqrt(Math.pow(objectiveCords[0] - (x - 1), 2) + Math.pow(objectiveCords[1] - y, 2));
                            //System.out.println("UP: " + distance1 + "\nLEFT: " + distance2);

                            //Direction UP
                            if (distance1 < distance2) {
                                //Cima livre
                                if ((gameData.getIMazeElement(y - 1, x) == null && y > 0) || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                    direction = Directions.UP;
                                    //auxF = gameData.getIMazeElement(y-1, x);
                                    y--;
                                    //gameData.getMaze().set(y+1, x, auxA);
                                }
                                //Cima parede
                                else if ((gameData.getIMazeElement(y - 1, x) == null && y > 0) || gameData.getIMazeElement(y - 1, x).getSymbol() == 'x') {
                                    //Esquerda livre
                                    if (gameData.getIMazeElement(y, x - 1) == null || gameData.getIMazeElement(y, x - 1).getSymbol() != 'x') {
                                        direction = Directions.LEFT;
                                        //auxF = gameData.getIMazeElement(y, x-1);
                                        x--;
                                        //gameData.getMaze().set(y, x+1, auxA);
                                    }
                                    //Esquerda parede
                                    else if (gameData.getIMazeElement(y, x - 1) == null || gameData.getIMazeElement(y, x - 1).getSymbol() == 'x') {
                                        //Direita
                                        if (rnd.nextBoolean()) {
                                            if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                                direction = Directions.RIGHT;
                                                //auxF = gameData.getIMazeElement(y, x+1);
                                                x++;
                                                //gameData.getMaze().set(y, x-1, auxA);

                                                failedAttempts++;
                                            }
                                        }
                                        //Baixo
                                        else {
                                            if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() != 'x') {
                                                direction = Directions.DOWN;
                                                //auxF = gameData.getIMazeElement(y+1, x);
                                                y++;
                                                //gameData.getMaze().set(y-1, x, auxA);

                                                failedAttempts++;
                                            }
                                        }
                                    }
                                }
                            }
                            //Direction LEFT
                            else if (distance1 > distance2) {
                                //Esquerda livre
                                if (gameData.getIMazeElement(y, x - 1) == null || gameData.getIMazeElement(y, x - 1).getSymbol() != 'x') {
                                    direction = Directions.LEFT;
                                    //auxF = gameData.getIMazeElement(y, x-1);
                                    x--;
                                    //gameData.getMaze().set(y, x+1, auxA);
                                }
                                //Esquerda parede
                                else if ((gameData.getIMazeElement(y, x - 1) == null && x > 0) || gameData.getIMazeElement(y, x - 1).getSymbol() == 'x') {
                                    //Cima Livre
                                    if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                        direction = Directions.UP;
                                        //auxF = gameData.getIMazeElement(y-1, x);
                                        y--;
                                        //gameData.getMaze().set(y+1, x, auxA);
                                    }
                                    //Cima parede
                                    else if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() == 'x') {
                                        //Direita
                                        if (rnd.nextBoolean()) {
                                            if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                                direction = Directions.RIGHT;
                                                //auxF = gameData.getIMazeElement(y, x+1);
                                                x++;
                                                //gameData.getMaze().set(y, x-1, auxA);

                                                failedAttempts++;
                                            }
                                        }
                                        //Baixo
                                        else {
                                            if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() != 'x') {
                                                direction = Directions.DOWN;
                                                //auxF = gameData.getIMazeElement(y+1, x);
                                                y++;
                                                //gameData.getMaze().set(y-1, x, auxA);

                                                failedAttempts++;
                                            }
                                        }
                                    }
                                }
                            }
                            //Random Direction
                            else {
                                //Direita
                                if (rnd.nextBoolean()) {
                                    if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                        direction = Directions.RIGHT;
                                        //auxF = gameData.getIMazeElement(y, x+1);
                                        x++;
                                        //gameData.getMaze().set(y, x-1, auxA);

                                        failedAttempts++;
                                    }
                                }
                                //Baixo
                                else {
                                    if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() != 'x') {
                                        direction = Directions.DOWN;
                                        //auxF = gameData.getIMazeElement(y+1, x);
                                        y++;
                                        //gameData.getMaze().set(y-1, x, auxA);

                                        failedAttempts++;
                                    }
                                }
                            }

                            //gameData.getMaze().set(y, x, this);
                            //auxA = auxF;
                        }
                        case BOTTOM_LEFT -> {
                            //Movimentos -> DOWN & LEFT
                            //Objective Change (With 15 attempts the objective changes because it reaches a realtive hill)
                            if (Math.sqrt(Math.pow(objectiveCords[0] - x, 2) + Math.pow(objectiveCords[1] - y, 2)) <= 0.15 * (MazeManager.getXSize(gameData.getCurrentLevelFilePath()))
                                    || failedAttempts >= 10) {
                                System.out.println("CHANGE");
                                objectiveCorner = Corners.UPPER_RIGHT;
                                failedAttempts = 0;
                            }

                            //Distancia Diagonal
                            //DOWN
                            double distance1 = Math.sqrt(Math.pow(objectiveCords[0] - x, 2) + Math.pow(objectiveCords[1] - (y + 1), 2));
                            //LEFT
                            double distance2 = Math.sqrt(Math.pow(objectiveCords[0] - (x - 1), 2) + Math.pow(objectiveCords[1] - y, 2));
                            //System.out.println("DOWN: " + distance1 + "\nLEFT: " + distance2);

                            //Direction DOWN
                            if (distance1 < distance2) {
                                //Baixo livre
                                if ((gameData.getIMazeElement(y + 1, x) == null) || gameData.getIMazeElement(y + 1, x).getSymbol() != 'x') {
                                    direction = Directions.DOWN;
                                    //auxF = gameData.getIMazeElement(y+1, x);
                                    y++;
                                    //gameData.getMaze().set(y-1, x, auxA);
                                }
                                //Baixo parede
                                else if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() == 'x') {
                                    //Esquerda Livre
                                    if (gameData.getIMazeElement(y, x - 1) == null || gameData.getIMazeElement(y, x - 1).getSymbol() != 'x') {
                                        direction = Directions.LEFT;
                                        //auxF = gameData.getIMazeElement(y, x-1);
                                        x--;
                                        //gameData.getMaze().set(y, x+1, auxA);
                                    }
                                    //Esquerda parede
                                    else if (gameData.getIMazeElement(y, x - 1) == null || gameData.getIMazeElement(y, x - 1).getSymbol() == 'x') {
                                        //Direita
                                        if (rnd.nextBoolean()) {
                                            if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                                direction = Directions.RIGHT;
                                                //auxF = gameData.getIMazeElement(y, x+1);
                                                x++;
                                                //gameData.getMaze().set(y, x-1, auxA);

                                                failedAttempts++;
                                            }
                                        }
                                        //Cima
                                        else {
                                            if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                                direction = Directions.UP;
                                                //auxF = gameData.getIMazeElement(y-1, x);
                                                y--;
                                                //gameData.getMaze().set(y+1, x, auxA);

                                                failedAttempts++;
                                            }
                                        }
                                    }
                                }
                            }
                            //Direction LEFT
                            else if (distance1 > distance2) {
                                //Esquerda livre
                                if (gameData.getIMazeElement(y, x - 1) == null || gameData.getIMazeElement(y, x - 1).getSymbol() != 'x') {
                                    direction = Directions.LEFT;
                                    //auxF = gameData.getIMazeElement(y, x-1);
                                    x--;
                                    //gameData.getMaze().set(y, x+1, auxA);
                                }
                                //Esquerda parede
                                else if ((gameData.getIMazeElement(y, x - 1) == null && x > 0) || gameData.getIMazeElement(y, x - 1).getSymbol() == 'x') {
                                    //Baixo Livre
                                    if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() != 'x') {
                                        direction = Directions.DOWN;
                                        //auxF = gameData.getIMazeElement(y+1, x);
                                        y++;
                                        //gameData.getMaze().set(y-1, x, auxA);
                                    }
                                    //Baixo parede
                                    else if (gameData.getIMazeElement(y + 1, x) == null || gameData.getIMazeElement(y + 1, x).getSymbol() == 'x') {
                                        //Direita
                                        if (rnd.nextBoolean()) {
                                            if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                                direction = Directions.RIGHT;
                                                //auxF = gameData.getIMazeElement(y, x+1);
                                                x++;
                                                //gameData.getMaze().set(y, x-1, auxA);

                                                failedAttempts++;
                                            }
                                        }
                                        //Cima
                                        else {
                                            if (gameData.getIMazeElement(y - 1, x) == null || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                                direction = Directions.UP;
                                                //auxF = gameData.getIMazeElement(y-1, x);
                                                y--;
                                                //gameData.getMaze().set(y+1, x, auxA);

                                                failedAttempts++;
                                            }
                                        }
                                    }
                                }
                            }
                            //Random Direction
                            else {
                                //Cima
                                if (rnd.nextBoolean()) {
                                    if ((gameData.getIMazeElement(y - 1, x) == null && y > 0) || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                                        direction = Directions.UP;
                                        //auxF = gameData.getIMazeElement(y - 1, x);
                                        y--;
                                        //gameData.getMaze().set(y + 1, x, auxA);

                                        failedAttempts++;
                                    }
                                }
                                //Direia
                                else {
                                    if (gameData.getIMazeElement(y, x + 1) == null || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                                        direction = Directions.RIGHT;
                                        //auxF = gameData.getIMazeElement(y, x+1);
                                        x++;
                                        //gameData.getMaze().set(y, x-1, auxA);

                                        failedAttempts++;
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
    }

    /**
     * Gets Element Symbol
     * @return Symbol of the Element (Inky)
     */
    @Override
    public char getSymbol() {
        return 'I';
    }

    //Internal Functions


}