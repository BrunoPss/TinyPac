package pt.isec.pa.tinypac.gameengine;

public interface IGameEngineEvolve {
    /**
     * Evolve
     * @param gameEngine Game Engine
     * @param currentTime Current Time
     */
    void evolve(IGameEngine gameEngine, long currentTime);
}