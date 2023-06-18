package pt.isec.pa.tinypac.model.data.element;

import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.ball.Ball;
import pt.isec.pa.tinypac.model.data.cave.Cave;
import pt.isec.pa.tinypac.model.data.fruit.Fruit;
import pt.isec.pa.tinypac.model.data.portal.Portal;
import pt.isec.pa.tinypac.model.data.superBall.SuperBall;
import pt.isec.pa.tinypac.model.data.wall.Wall;
import pt.isec.pa.tinypac.model.data.warp.Warp;

/**
 * Element Factory Class
 * <p>Class Factory that creates new Elements</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class ElementFactory {
    //Internal Data


    //Constructor


    //Get Methods


    //Set Methods


    //Methods
    /**
     * Creates a new Element according to the type
     * @param type Element type
     * @param gameData Game Data Model
     * @param x Initial Position (x cord.)
     * @param y Initial Position (y cord.)
     * @return New concrete Element
     */
    public static Element createElement(ElementType type, Game gameData, int x, int y) {
        return switch (type) {
            case WALL -> new Wall(gameData);
            case WARP -> new Warp(gameData, x, y);
            case BALL -> new Ball(gameData);
            case FRUIT -> new Fruit(gameData, x, y);
            case SUPER_BALL -> new SuperBall(gameData);
            case PORTAL -> new Portal(gameData);
            case CAVE -> new Cave(gameData);
        };
    }

    //Overrides


    //Internal Functions


}