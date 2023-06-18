package pt.isec.pa.tinypac.model.data.pacman;

import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.entity.Entity;
import pt.isec.pa.tinypac.model.data.entity.EntityType;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.warp.Warp;

/**
 * Pacman Class
 * <p>Class that represents the Pacman Element</p>
 *
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class Pacman extends Entity {
    //Internal Data
    /**
     * Symbol of the Element (Pacman)
     */
    public static final char SYMBOL = 'M';
    private Directions direction;
    private int points;

    //Constructor
    /**
     * Constructor
     * @param gameData Game Data Model
     * @param x Initial Position (x cord.)
     * @param y Initial Position (y cord.)
     */
    public Pacman(Game gameData, int x, int y) {
        super(gameData, x, y);
        this.points = 0;
        this.direction = Directions.LEFT;
    }

    //Get Methods

    /**
     * Gets current points
     * @return current points
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Gets current direction
     * @return current direction
     */
    public Directions getDirection() { return this.direction; };

    //Set Methods

    /**
     * Sets current direction
     * @param direction current direction
     */
    public void setDirection(Directions direction) { this.direction = direction; }

    //Methods


    //Overrides
    /**
     * Method that updates and moves the Ghost
     */
    @Override
    public void move() {
        if (gameData.findValidInstantsPacman().contains(gameData.getEvolveInstantsPacman())) {

            //Detetao Fantasmas
            ghostDetection();

            switch (direction) {
                case UP -> {
                    //Mudar cadeia de if's para switch
                    //Detecao Paredes
                    if ((gameData.getIMazeElement(y - 1, x) == null && y > 0) || gameData.getIMazeElement(y - 1, x).getSymbol() != 'x') {
                        gameData.setIMazeElement(y, x, null);
                        y--;
                    }

                    //Detecao Ball
                    ballDetection();

                    //Detecao Fruit
                    fruitDetection();

                    //Detecao Zona Warp
                    warpDetection();

                    //Detecao Super Ball
                    superBallDetection();
                }
                case DOWN -> {
                    //Detecao Paredes
                    if ((gameData.getIMazeElement(y + 1, x) == null && y < gameData.getMazeLength() + 1) || gameData.getIMazeElement(y + 1, x).getSymbol() != 'x') {
                        gameData.setIMazeElement(y, x, null);
                        y++;
                    }

                    //Detecao Ball
                    ballDetection();

                    //Detecao Fruit
                    fruitDetection();

                    //Detecao Zona Warp
                    warpDetection();

                    //Detecao Super Ball
                    superBallDetection();
                }
                case LEFT -> {
                    //Detecao Paredes
                    if ((gameData.getIMazeElement(y, x - 1) == null && x > 0) || gameData.getIMazeElement(y, x - 1).getSymbol() != 'x') {
                        gameData.setIMazeElement(y, x, null);
                        x--;
                    }

                    //Detecao Ball
                    ballDetection();

                    //Detecao Fruit
                    fruitDetection();

                    //Detecao Zona Warp
                    warpDetection();

                    //Detecao Super Ball
                    superBallDetection();
                }
                case RIGHT -> {
                    //Detecao Paredes
                    if ((gameData.getIMazeElement(y, x + 1) == null && x < gameData.getMazeHeight() - 3) || gameData.getIMazeElement(y, x + 1).getSymbol() != 'x') {
                        gameData.setIMazeElement(y, x, null);
                        x++;
                    }

                    //Detecao Ball
                    ballDetection();

                    //Detecao Fruit
                    fruitDetection();

                    //Detecao Zona Warp
                    warpDetection();

                    //Detecao Super Ball
                    superBallDetection();
                }
            }
            gameData.setIMazeElement(y, x, this);
        }
        gameData.incrementEvolveInstantsPacman();
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
        if (gameData.getIMazeElement(y,x) != null && gameData.getIMazeElement(y,x).getSymbol() == 'W') {
            Element warp = (Element) gameData.getIMazeElement(y,x);
            gameData.setIMazeElement(((Warp) warp).getComplementWarp().getY(), ((Warp) warp).getComplementWarp().getX(), this);
            this.x = ((Warp) warp).getComplementWarp().getX();
            this.y = ((Warp) warp).getComplementWarp().getY();
        }
    }
    private void ballDetection() {
        if (gameData.getIMazeElement(y,x) != null && gameData.getIMazeElement(y,x).getSymbol() == 'o') {
            this.points++;
            gameData.decrementTotalBalls();
        }
    }
    private void superBallDetection() {
        if (gameData.getIMazeElement(y,x) != null && gameData.getIMazeElement(y,x).getSymbol() == 'O') {
            gameData.setSuperBallInactive();
            points += 10;
            gameData.fsmEnchance();
        }
    }
    private void fruitDetection() {
        if (gameData.getIMazeElement(y,x) != null && gameData.getIMazeElement(y,x).getSymbol() == 'F') {
            points += (gameData.getCurrentLevel()+1) * 25;
            gameData.catchFruit();
        }
    }
    private void ghostDetection() {
        if (!gameData.getEnchancedPhase()) {
            if (gameData.getEntityCord(EntityType.BLINKY)[0] == x && gameData.getEntityCord(EntityType.BLINKY)[1] == y
             || gameData.getEntityCord(EntityType.CLYDE)[0] == x && gameData.getEntityCord(EntityType.CLYDE)[1] == y
             || gameData.getEntityCord(EntityType.INKY)[0] == x && gameData.getEntityCord(EntityType.INKY)[1] == y
             || gameData.getEntityCord(EntityType.PINKY)[0] == x && gameData.getEntityCord(EntityType.PINKY)[1] == y) {
                gameData.pacmanDead();
            }
        }
        else {
            if (gameData.getEntityCord(EntityType.BLINKY)[0] == x && gameData.getEntityCord(EntityType.BLINKY)[1] == y) {
                gameData.setEntityActive(EntityType.BLINKY, false);
                points += 50 * gameData.getPacmanGhostsEaten();
                gameData.incPacmanGhostsEaten();
            }
            if (gameData.getEntityCord(EntityType.CLYDE)[0] == x && gameData.getEntityCord(EntityType.CLYDE)[1] == y) {
                gameData.setEntityActive(EntityType.CLYDE, false);
                points += 50 * gameData.getPacmanGhostsEaten();
                gameData.incPacmanGhostsEaten();
            }
            if (gameData.getEntityCord(EntityType.INKY)[0] == x && gameData.getEntityCord(EntityType.INKY)[1] == y) {
                gameData.setEntityActive(EntityType.INKY, false);
                points += 50 * gameData.getPacmanGhostsEaten();
                gameData.incPacmanGhostsEaten();
            }
            if (gameData.getEntityCord(EntityType.PINKY)[0] == x && gameData.getEntityCord(EntityType.PINKY)[1] == y) {
                gameData.setEntityActive(EntityType.PINKY, false);
                points += 50 * gameData.getPacmanGhostsEaten();
                gameData.incPacmanGhostsEaten();
            }
        }
    }
}