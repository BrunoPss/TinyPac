package pt.isec.pa.tinypac.model.data.maze;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.element.ElementFactory;
import pt.isec.pa.tinypac.model.data.element.ElementType;
import pt.isec.pa.tinypac.model.data.entity.EntityFactory;
import pt.isec.pa.tinypac.model.data.entity.EntityType;
import pt.isec.pa.tinypac.model.data.fruit.Fruit;
import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.warp.Warp;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

public class MazeManager {
    //Internal Data


    //Constructor


    //Get Methods


    //Set Methods


    //Methods
    public static void loadLevel(Game gameData, String filePath) {
        File file = new File(filePath);
        int x=0, y=0;
        int f = 0;
        Element auxWarp = null;

        try (FileReader fr = new FileReader(file))
        {
            int content;
            while ((content = fr.read()) != -1) {
                switch ((char) content) {
                    case 'x' -> gameData.getMaze().set(y,x, ElementFactory.createElement(ElementType.WALL, gameData, x, y));
                    case 'W' -> {
                        if (auxWarp == null)
                            auxWarp = ElementFactory.createElement(ElementType.WARP, gameData, x, y);
                        else {
                            Element aux2Warp = ElementFactory.createElement(ElementType.WARP, gameData, x, y);
                            ((Warp) aux2Warp).setComplementWarp(auxWarp);
                            ((Warp) auxWarp).setComplementWarp(aux2Warp);
                            auxWarp = aux2Warp;
                        }
                        gameData.getMaze().set(y,x, auxWarp);
                    }
                    case 'y' -> {
                        switch (f) {
                            case 1 -> {
                                //DESCOMENTAR
                                gameData.setEntity(EntityType.BLINKY, EntityFactory.createEntity(EntityType.BLINKY, gameData, x, y));
                                gameData.getMaze().set(y,x, gameData.getEntity(EntityType.BLINKY));
                            }
                            case 2 -> {
                                //gameData.setEntity(EntityType.CLYDE, EntityFactory.createEntity(EntityType.CLYDE, gameData, x, y));
                                //gameData.getMaze().set(y,x, gameData.getEntity(EntityType.CLYDE));
                            }
                            case 3 -> {
                                //gameData.setEntity(EntityType.INKY, EntityFactory.createEntity(EntityType.INKY, gameData, x, y));
                                //gameData.getMaze().set(y,x, gameData.getEntity(EntityType.INKY));
                            }
                            case 4 -> {
                                gameData.setEntity(EntityType.PINKY, EntityFactory.createEntity(EntityType.PINKY, gameData, x, y));
                                gameData.getMaze().set(y,x, gameData.getEntity(EntityType.PINKY));
                            }
                        }
                        f++;
                        gameData.getMaze().set(y,x, ElementFactory.createElement(ElementType.WALL, gameData, x, y));
                        //System.out.println("WALL: " + x + " " + y);
                        //System.out.println(gameData.getMazeElement(x,y));
                    }
                    case 'Y' -> {
                        gameData.setGhostDoor(x,y);
                        gameData.getMaze().set(y,x, ElementFactory.createElement(ElementType.WALL, gameData, x, y));
                    }
                    case 'o' -> {
                        gameData.getMaze().set(y,x, ElementFactory.createElement(ElementType.BALL, gameData, x, y));
                        gameData.incrementTotalBalls();
                    }
                    case 'F' -> {
                        Element fruit = ElementFactory.createElement(ElementType.FRUIT, gameData, x, y);
                        gameData.setFruit(fruit);
                        gameData.getMaze().set(y,x, fruit);
                    }
                    case 'M' -> {
                        gameData.setEntity(EntityType.PACMAN, EntityFactory.createEntity(EntityType.PACMAN, gameData, x, y));
                        gameData.getMaze().set(y,x, gameData.getEntity(EntityType.PACMAN));
                    }
                    case 'O' -> gameData.getMaze().set(y,x, ElementFactory.createElement(ElementType.SUPER_BALL, gameData, x, y));
                    //case 'Y' -> maze.set(y,x, ElementFactory.createElement(ElementType.PORTAL, maze, x, y));
                    //case 'y' -> maze.set(y,x, ElementFactory.createElement(ElementType.CAVE, maze, x, y));
                }
                //Condicao apenas com \n porque na mudanca de linha do ficheiro contem \n\r
                if ((char) content == '\n') {
                    y++;
                    x=0;
                }
                else
                    x++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getXSize(String filePath) {
        int x=0;
        int content;
        File file = new File(filePath);
        try (FileReader fr = new FileReader(file)) {
            while ((content = fr.read()) != -1 && (char) content != '\n') {
                x++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return x-1;
    }
    public static int getYSize(String filePath) {
        int y=0;
        int content;
        File file = new File(filePath);
        try (FileReader fr = new FileReader(file)) {
            while ((content = fr.read()) != -1) {
                if ((char) content == '\n')
                    y++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return y+1;
    }

    //Overrides


    //Internal Functions


}