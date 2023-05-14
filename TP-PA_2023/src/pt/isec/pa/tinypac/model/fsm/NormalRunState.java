package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.game.Game;

public class NormalRunState extends GameStateAdapter {
    //Internal Data


    //Constructor
    NormalRunState(GameContext context, Game gameData) {
        super(context, gameData);
        System.out.println("NORMAL");
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public boolean up() {
        gameData.getPacman().setDirection(Directions.UP);
        changeState(new NormalRunState(context, gameData));
        return true;
    }
    @Override
    public boolean down() {
        gameData.getPacman().setDirection(Directions.DOWN);
        changeState(new NormalRunState(context, gameData));
        return true;
    }
    @Override
    public boolean left() {
        gameData.getPacman().setDirection(Directions.LEFT);
        changeState(new NormalRunState(context, gameData));
        return true;
    }
    @Override
    public boolean right() {
        gameData.getPacman().setDirection(Directions.RIGHT);
        changeState(new NormalRunState(context, gameData));
        return true;
    }
    @Override
    public boolean enhancedPacman() {
        changeState(new SuperPacmanState(context, gameData));
        return true;
    }
    @Override
    public boolean pauseGame() {
        changeState(new PausedState(context, gameData));
        context.gameEngine.pause();
        return true;
    }
    @Override
    public boolean restart() {
        context.gameEngine.stop();
        changeState(new InitState(context, gameData));
        return true;
    }
    @Override
    public boolean endGame() {
        changeState(new GameEnd(context, gameData));
        return true;
    }
    @Override
    public GameState getState() {
        return GameState.NORMALRUNSTATE;
    }

    //Internal Functions


}