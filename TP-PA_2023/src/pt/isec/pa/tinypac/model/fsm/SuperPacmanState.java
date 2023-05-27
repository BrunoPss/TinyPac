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
        System.out.println("SUPER");
        gameData.setEnchancedTimeout(15);
        gameData.setEnchancedPhase(true);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
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
    public boolean disableEnhancedPacman() {
        gameData.setEnchancedPhase(false);
        changeState(new NormalRunState(context, gameData));
        return true;
    }
    @Override
    public boolean pauseGame() {
        changeState(new PausedState(context, gameData));
        context.gameEngine.pause();
        return true;
    }
    @Override
    public GameState getState() {
        return GameState.NORMALRUNSTATE;
    }

    //Internal Functions


}