package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.entity.EntityType;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.pacman.Pacman;

import java.util.ArrayList;
import java.util.List;

public class NormalRunState extends GameStateAdapter {
    //Internal Data


    //Constructor
    NormalRunState(GameContext context, Game gameData) {
        super(context, gameData);
        System.out.println("NORMAL RUN STATE");
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public boolean update() {
        //if (gameData.findValidInstants().contains(gameData.getEvolveInstants())) {
            gameData.checkEnchancedMode();
            gameData.checkLevelChange();
            //gameData.checkGameEnd();
            gameData.evolveEntities();
        //}
        //gameData.incrementEvolveInstants();
        return true;
    }

    @Override
    public boolean up() {
        gameData.setPacmanDirection(Directions.UP);
        //changeState(new NormalRunState(context, gameData));
        return true;
    }
    @Override
    public boolean down() {
        gameData.setPacmanDirection(Directions.DOWN);
        //changeState(new NormalRunState(context, gameData));
        return true;
    }
    @Override
    public boolean left() {
        gameData.setPacmanDirection(Directions.LEFT);
        //changeState(new NormalRunState(context, gameData));
        return true;
    }
    @Override
    public boolean right() {
        gameData.setPacmanDirection(Directions.RIGHT);
        //changeState(new NormalRunState(context, gameData));
        return true;
    }
    @Override
    public boolean enhancedPacman() {
        changeState(new SuperPacmanState(context, gameData));
        return true;
    }
    @Override
    public boolean pauseGame() {
        changeState(new PausedState(context, gameData, GameState.NORMALRUNSTATE));
        //context.gameEngine.pause();
        return true;
    }
    @Override
    public boolean restart() {
        //context.gameEngine.stop();
        changeState(new InitState(context, gameData));
        return true;
    }
    @Override
    public boolean endGame() {
        changeState(new GameEndState(context, gameData));
        return true;
    }
    @Override
    public GameState getState() {
        return GameState.NORMALRUNSTATE;
    }

    //Internal Functions

}