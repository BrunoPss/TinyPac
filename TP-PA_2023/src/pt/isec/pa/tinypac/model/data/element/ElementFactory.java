package pt.isec.pa.tinypac.model.data.element;

import pt.isec.pa.tinypac.model.data.Pacman.Pacman;
import pt.isec.pa.tinypac.model.data.ball.Ball;
import pt.isec.pa.tinypac.model.data.cave.Cave;
import pt.isec.pa.tinypac.model.data.fruit.Fruit;
import pt.isec.pa.tinypac.model.data.maze.Maze;
import pt.isec.pa.tinypac.model.data.portal.Portal;
import pt.isec.pa.tinypac.model.data.superBall.SuperBall;
import pt.isec.pa.tinypac.model.data.wall.Wall;
import pt.isec.pa.tinypac.model.data.warp.Warp;

public class ElementFactory {
    //Internal Data


    //Constructor


    //Get Methods


    //Set Methods


    //Methods
    public static Element createElement(ElementType type, Maze maze, int x, int y) {
        return switch (type) {
            case WALL -> new Wall(maze, x, y);
            case WARP -> new Warp(maze, x, y);
            case BALL -> new Ball(maze, x, y);
            case FRUIT -> new Fruit(maze, x, y);
            case PACMAN -> new Pacman(maze, x, y);
            case SUPER_BALL -> new SuperBall(maze, x, y);
            case PORTAL -> new Portal(maze, x, y);
            case CAVE -> new Cave(maze, x, y);
        };
    }

    //Overrides


    //Internal Functions


}