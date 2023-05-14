package pt.isec.pa.tinypac.model.data.entity;

import pt.isec.pa.tinypac.model.data.ball.Ball;
import pt.isec.pa.tinypac.model.data.cave.Cave;
import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.element.ElementType;
import pt.isec.pa.tinypac.model.data.fruit.Fruit;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.ghosts.Blinky;
import pt.isec.pa.tinypac.model.data.ghosts.Clyde;
import pt.isec.pa.tinypac.model.data.ghosts.Inky;
import pt.isec.pa.tinypac.model.data.ghosts.Pinky;
import pt.isec.pa.tinypac.model.data.pacman.Pacman;
import pt.isec.pa.tinypac.model.data.portal.Portal;
import pt.isec.pa.tinypac.model.data.superBall.SuperBall;
import pt.isec.pa.tinypac.model.data.wall.Wall;
import pt.isec.pa.tinypac.model.data.warp.Warp;

public class EntityFactory {
    //Internal Data


    //Constructor


    //Get Methods


    //Set Methods


    //Methods
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