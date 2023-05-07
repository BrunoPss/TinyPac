package pt.isec.pa.tinypac.model.fsm;
import pt.isec.pa.tinypac.model.data.Directions;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.element.ElementType;
import pt.isec.pa.tinypac.model.data.maze.MazeManager;

import java.util.Dictionary;

public class InitState extends GameStateAdapter {
    //Internal Data
    Dictionary<ElementType, Element> entities;

    //Constructor
    InitState(GameContext context, Game gameData) {
        super(context, gameData);
        gameData.initMaze();
        entities = MazeManager.loadLevel(gameData, "C:\\Projects\\TP-PA_2023\\TP-PA_2023\\src\\pt\\isec\\pa\\tinypac\\model\\data\\levels\\level01.txt");
        gameData.setPacman(entities.get(ElementType.PACMAN));
        gameData.setBlinky(entities.get(ElementType.BLINKY));
        gameData.setClyde(entities.get(ElementType.CLYDE));
        gameData.setInky(entities.get(ElementType.INKY));
        gameData.setPinky(entities.get(ElementType.PINKY));

        gameData.registerEntity(ElementType.PACMAN);
        gameData.registerEntity(ElementType.BLINKY);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public boolean up() {
        gameData.getPacman().setDirection(Directions.UP);
        changeState(new NormalRunState(context, gameData));
        gameData.getGameEngine().start(1000);
        return true;
    }
    @Override
    public boolean down() {
        gameData.getPacman().setDirection(Directions.DOWN);
        changeState(new NormalRunState(context, gameData));
        gameData.getGameEngine().start(1000);
        return true;
    }
    @Override
    public boolean left() {
        gameData.getPacman().setDirection(Directions.LEFT);
        changeState(new NormalRunState(context, gameData));
        gameData.getGameEngine().start(1000);
        return true;
    }
    @Override
    public boolean right() {
        gameData.getPacman().setDirection(Directions.RIGHT);
        changeState(new NormalRunState(context, gameData));
        gameData.getGameEngine().start(1000);
        return true;
    }
    @Override
    public GameState getState() {
        return GameState.INITSTATE;
    }

    //Internal Functions


}