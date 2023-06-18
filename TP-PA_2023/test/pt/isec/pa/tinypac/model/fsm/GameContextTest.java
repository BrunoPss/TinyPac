package pt.isec.pa.tinypac.model.fsm;

import org.junit.jupiter.api.Test;
import pt.isec.pa.tinypac.model.data.game.Game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GameContextTest {
    @Test
    public void changePauseStateTest1() {
        //Arrange
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);
        fsm.changeState(new PausedState(fsm, game, GameState.NORMALRUNSTATE));

        //Act
        fsm.resumeGame();
        GameState state = fsm.getState();

        //Assert
        assertEquals(GameState.NORMALRUNSTATE, state);
    }
    @Test
    public void changePauseStateTest2() {
        //Arrange
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);
        fsm.changeState(new PausedState(fsm, game, GameState.SUPERPACMANSTATE));

        //Act
        fsm.resumeGame();
        GameState state = fsm.getState();

        //Assert
        assertEquals(GameState.SUPERPACMANSTATE, state);
    }

    @Test
    public void changeInitStateTest1() {
        //Arrange
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);
        fsm.changeState(new InitState(fsm, game));

        //Act
        fsm.enhancedPacman();
        GameState state = fsm.getState();

        //Assert
        assertEquals(GameState.INITSTATE, state);
    }
    @Test
    public void changeInitStateTest2() {
        //Arrange
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);
        fsm.changeState(new InitState(fsm, game));

        //Act
        fsm.right();
        GameState state = fsm.getState();

        //Assert
        assertEquals(GameState.NORMALRUNSTATE, state);
    }

    @Test
    public void changeNormalStateTest1() {
        //Arrange
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);
        fsm.changeState(new NormalRunState(fsm, game));

        //Act
        fsm.restart();
        GameState state = fsm.getState();

        //Assert
        assertEquals(GameState.INITSTATE, state);
    }
    @Test
    public void changeNormalStateTest2() {
        //Arrange
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);
        fsm.changeState(new NormalRunState(fsm, game));

        //Act
        fsm.endGame();
        GameState state = fsm.getState();

        //Assert
        assertEquals(GameState.GAME_ENDSTATE, state);
    }

    @Test
    public void changeEndStateTest1() {
        //Arrange
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);
        fsm.changeState(new GameEndState(fsm, game));

        //Act
        fsm.up();
        GameState state = fsm.getState();

        //Assert
        assertEquals(GameState.GAME_ENDSTATE, state);
    }
    @Test
    public void changeEndStateTest2() {
        //Arrange
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);
        fsm.changeState(new GameEndState(fsm, game));

        //Act
        boolean testVar = fsm.up();

        //Assert
        assertFalse(testVar);
    }

    @Test
    public void changeSuperStateTest1() {
        //Arrange
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);
        fsm.changeState(new SuperPacmanState(fsm, game));

        //Act
        fsm.disableEnhancedPacman();
        GameState state = fsm.getState();

        //Assert
        assertEquals(GameState.NORMALRUNSTATE, state);
    }

    @Test
    public void changeSuperStateTest2() {
        //Arrange
        GameContext fsm = new GameContext();
        Game game = new Game(fsm);
        fsm.changeState(new SuperPacmanState(fsm, game));

        //Act
        boolean testVar = fsm.enhancedPacman();

        //Assert
        assertFalse(testVar);
    }
}