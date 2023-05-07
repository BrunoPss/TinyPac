package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Directions;
import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.game.Game;

abstract public class Ghost extends Element implements IGameEngineEvolve {
    //Internal Data
    protected int initTime = 2; //5
    protected Directions direction;
    protected boolean cave = true;

    //Constructor
    public Ghost(Game gameData, int x, int y) {
        super(gameData, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides


    //Internal Functions


}