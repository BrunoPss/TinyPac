package pt.isec.pa.tinypac.model.data.pacman;

import org.junit.jupiter.api.Test;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.entity.Entity;
import pt.isec.pa.tinypac.model.data.entity.EntityType;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * PacmanTest Class
 */

public class PacmanTest {
    /**
     * pacmanDirectionsTest1
     */
    @Test
    public void pacmanDirectionsTest1() {
        //Arrange
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);
        Pacman pacman = new Pacman(game, 0, 0);

        //Act
        pacman.setDirection(Directions.RIGHT);
        Directions testVar1 = pacman.getDirection();

        pacman.setDirection(Directions.LEFT);
        Directions testVar2 = pacman.getDirection();

        pacman.setDirection(Directions.DOWN);
        Directions testVar3 = pacman.getDirection();

        pacman.setDirection(Directions.UP);
        Directions testVar4 = pacman.getDirection();

        //Assert
        assertEquals(Directions.RIGHT, testVar1);
        assertEquals(Directions.LEFT, testVar2);
        assertEquals(Directions.DOWN, testVar3);
        assertEquals(Directions.UP, testVar4);
    }

    /**
     * pacmanPointsTest1
     */
    @Test
    public void pacmanPointsTest1() {
        //Arrange
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);
        Pacman pacman = new Pacman(game, 0, 0);

        //Act
        int testVar1 = pacman.getPoints();
        int testVar2 = fsm.getPacmanPoints();

        //Assert
        assertEquals(0, testVar1, testVar2);
    }

    /**
     * pacmanSymbolTest
     */
    @Test
    public void pacmanSymbolTest() {
        //Arrange
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);
        Pacman pacman = new Pacman(game, 0, 0);

        //Act
        char testVar1 = pacman.getSymbol();

        //Assert
        assertEquals('M', testVar1);
    }

    /**
     * pacmanCordsTest
     */
    @Test
    public void pacmanCordsTest() {
        //Arrange
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);
        Pacman pacman = new Pacman(game, 5, 8);

        //Act
        int[] testVar1 = new int[]{pacman.getX(), pacman.getY()};

        //Assert
        assertEquals(5, testVar1[0]);
        assertEquals(8, testVar1[1]);
    }
}