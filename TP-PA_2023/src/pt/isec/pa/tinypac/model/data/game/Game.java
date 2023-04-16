package pt.isec.pa.tinypac.model.data.game;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;

public class Game implements IGameEngineEvolve {
    //Internal Data
    int globalTimer;

    //Constructor
    public Game() {
        this.globalTimer = 0;
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        System.out.printf("[%d] %d\n",currentTime,++globalTimer);
        if (globalTimer >= 20) gameEngine.stop();
    }

    //Internal Functions


}