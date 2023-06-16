package pt.isec.pa.tinypac.model;

import org.junit.jupiter.api.Test;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameState;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameManagerTest {
    @Test
    public void changeNormalStateTest1() {
        //Arrange
        GameManager gameManager = new GameManager();

        //Act
        gameManager.down();
        GameState state = gameManager.getState();

        //Assert
        assertEquals(GameState.NORMALRUNSTATE, state);
    }
    @Test
    public void changeNormalStateTest2() {
        //Arrange
        GameManager gameManager = new GameManager();

        //Act
        gameManager.resumeGame();
        GameState state = gameManager.getState();

        //Assert
        assertEquals(GameState.INITSTATE, state);
    }

    @Test
    public void setVolumeTest1() {
        //Arrange
        GameManager gameManager = new GameManager();
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);

        //Act
        gameManager.setMusicVolume(20);
        int testVolume = gameManager.getMusicVolume();
        int testVolume2 = game.getMusicVolume();

        //Assert
        assertEquals(20, testVolume, testVolume2);
    }

    @Test
    public void setMainMenuStateTest1() {
        //Arrange
        GameManager gameManager = new GameManager();
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);

        //Act
        gameManager.setMainMenuState(true);
        boolean testVar1 = fsm.getMainMenuState();
        boolean testVar2 = game.getMainMenuState();

        //Assert
        assertTrue(testVar1);
        assertTrue(testVar2);
    }
}