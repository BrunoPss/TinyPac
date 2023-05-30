package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.entity.EntityType;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.pacman.Pacman;

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
        gameData.checkEnchancedMode();
        gameData.checkLevelChange();
        gameData.checkGameEnd();
        gameData.evolveEntities();
        //System.out.println("Normal Run State Update");
        return true;
    }

    @Override
    public boolean up() {
        ((Pacman) gameData.getEntity(EntityType.PACMAN)).setDirection(Directions.UP);
        changeState(new NormalRunState(context, gameData));
        return true;
    }
    @Override
    public boolean down() {
        ((Pacman) gameData.getEntity(EntityType.PACMAN)).setDirection(Directions.DOWN);
        changeState(new NormalRunState(context, gameData));
        return true;
    }
    @Override
    public boolean left() {
        ((Pacman) gameData.getEntity(EntityType.PACMAN)).setDirection(Directions.LEFT);
        changeState(new NormalRunState(context, gameData));
        return true;
    }
    @Override
    public boolean right() {
        ((Pacman) gameData.getEntity(EntityType.PACMAN)).setDirection(Directions.RIGHT);
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
        changeState(new GameEndState(context, gameData));
        return true;
    }
    @Override
    public GameState getState() {
        return GameState.NORMALRUNSTATE;
    }

    //Internal Functions


}