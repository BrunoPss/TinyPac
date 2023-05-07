package pt.isec.pa.tinypac.model.data.ghosts;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.game.Game;

public class Clyde extends Ghost {
    //Internal Data


    //Constructor
    public Clyde(Game gameData, int x, int y) {
        super(gameData, x, y);
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

    }

    @Override
    public char getSymbol() {
        return 'C';
    }

    //Internal Functions


}