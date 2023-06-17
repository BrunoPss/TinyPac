package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.entity.Directions;
import pt.isec.pa.tinypac.model.data.entity.EntityType;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.pacman.Pacman;

public class SuperPacmanState extends GameStateAdapter {
    //Internal Data


    //Constructor
    SuperPacmanState(GameContext context, Game gameData) {
        super(context, gameData);
        System.out.println("SUPER PACMAN STATE");
        gameData.setEnchancedTimeout(20 - gameData.getCurrentLevel());
        gameData.setEnchancedPhase(true);
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
        return true;
    }
    @Override
    public boolean down() {
        gameData.setPacmanDirection(Directions.DOWN);
        return true;
    }
    @Override
    public boolean left() {
        gameData.setPacmanDirection(Directions.LEFT);
        return true;
    }
    @Override
    public boolean right() {
        gameData.setPacmanDirection(Directions.RIGHT);
        return true;
    }
    @Override
    public boolean disableEnhancedPacman() {
        gameData.setEnchancedPhase(false);
        changeState(new NormalRunState(context, gameData));
        return true;
    }
    @Override
    public boolean pauseGame() {
        changeState(new PausedState(context, gameData, GameState.SUPERPACMANSTATE));
        //context.gameEngine.pause();
        return true;
    }
    @Override
    public boolean endGame() {
        changeState(new GameEndState(context, gameData));
        return true;
    }

    @Override
    public GameState getState() {
        return GameState.SUPERPACMANSTATE;
    }

    //Internal Functions


}