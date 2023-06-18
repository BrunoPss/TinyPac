package pt.isec.pa.tinypac.model.data.game;

import org.junit.jupiter.api.Test;
import pt.isec.pa.tinypac.model.data.entity.Entity;
import pt.isec.pa.tinypac.model.data.entity.EntityType;
import pt.isec.pa.tinypac.model.data.pacman.Pacman;
import pt.isec.pa.tinypac.model.fsm.GameContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Game Test Class
 */

public class GameTest {
    /**
     * setEntityActiveTest1
     */
    @Test
    public void setEntityActiveTest1() {
        //Arrange
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);

        //Act
        game.setEntityActive(EntityType.BLINKY, true);
        game.setEntityActive(EntityType.CLYDE, false);
        game.setEntityActive(EntityType.INKY, false);
        game.setEntityActive(EntityType.PINKY, true);
        boolean testVar1 = game.getEntityActive(EntityType.BLINKY);
        boolean testVar2 = game.getEntityActive(EntityType.CLYDE);
        boolean testVar3 = game.getEntityActive(EntityType.INKY);
        boolean testVar4 = game.getEntityActive(EntityType.PINKY);

        //Assert
        assertTrue(testVar1);
        assertFalse(testVar2);
        assertFalse(testVar3);
        assertTrue(testVar4);
    }

    /**
     * setGhostDoorTest1
     */
    @Test
    public void setGhostDoorTest1() {
        //Arrange
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);

        //Act
        game.setGhostDoor(13,25);
        int[] testVar1 = game.getGhostDoor();

        //Assert
        assertArrayEquals(new int[]{13,25}, testVar1);
    }

    /**
     * setEnchancedTimeTest1
     */
    @Test
    public void setEnchancedTimeTest1() {
        //Arrange
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);

        //Act
        game.setEnchancedTimeout(50);
        int testVar1 = game.getEnchancedTimeout();

        //Assert
        assertEquals(50, testVar1);
    }

    /**
     * setEntityTest1
     */
    @Test
    public void setEntityTest1() {
        //Arrange
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);
        Pacman pacman = new Pacman(game, 0, 0);

        //Act
        game.setEntity(EntityType.PACMAN, pacman);
        Entity testVar1 = game.getEntity(EntityType.PACMAN);

        //Assert
        assertNotNull(testVar1);
    }
}