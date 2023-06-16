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
        gameData.setEnchancedTimeout(15);
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
        ((Pacman) gameData.getEntity(EntityType.PACMAN)).setDirection(Directions.UP);
        return true;
    }
    @Override
    public boolean down() {
        ((Pacman) gameData.getEntity(EntityType.PACMAN)).setDirection(Directions.DOWN);
        return true;
    }
    @Override
    public boolean left() {
        ((Pacman) gameData.getEntity(EntityType.PACMAN)).setDirection(Directions.LEFT);
        return true;
    }
    @Override
    public boolean right() {
        ((Pacman) gameData.getEntity(EntityType.PACMAN)).setDirection(Directions.RIGHT);
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
    public GameState getState() {
        return GameState.SUPERPACMANSTATE;
    }

    //Internal Functions


}