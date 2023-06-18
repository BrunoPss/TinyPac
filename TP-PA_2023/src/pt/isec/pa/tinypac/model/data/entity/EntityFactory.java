package pt.isec.pa.tinypac.model.data.entity;

import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.ghosts.Blinky;
import pt.isec.pa.tinypac.model.data.ghosts.Clyde;
import pt.isec.pa.tinypac.model.data.ghosts.Inky;
import pt.isec.pa.tinypac.model.data.ghosts.Pinky;
import pt.isec.pa.tinypac.model.data.pacman.Pacman;

/**
 * Entity Factory Class
 * <p>Class Factory that creates new Entities</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class EntityFactory {
    //Internal Data


    //Constructor


    //Get Methods


    //Set Methods


    //Methods
    /**
     * Creates a new Entity according to the type
     * @param type Entity type
     * @param gameData Game Data Model
     * @param x Initial Position (x cord.)
     * @param y Initial Position (y cord.)
     * @return New concrete Entity
     */
    public static Entity createEntity(EntityType type, Game gameData, int x, int y) {
        return switch (type) {
            case PACMAN -> new Pacman(gameData, x, y);
            case BLINKY -> new Blinky(gameData, x, y);
            case CLYDE -> new Clyde(gameData, x, y);
            case INKY -> new Inky(gameData, x, y);
            case PINKY -> new Pinky(gameData, x, y);
        };
    }

    //Overrides


    //Internal Functions


}