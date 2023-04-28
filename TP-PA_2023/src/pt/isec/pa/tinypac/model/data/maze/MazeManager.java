package pt.isec.pa.tinypac.model.data.maze;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.data.Pacman.Pacman;
import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.element.ElementFactory;
import pt.isec.pa.tinypac.model.data.element.ElementType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class MazeManager {
    //Internal Data
    Scanner scanner;

    //Constructor


    //Get Methods


    //Set Methods


    //Methods
    public static Dictionary<ElementType, Element> loadLevel(Maze maze, IGameEngine gameEngine, String filePath) {
        File file = new File(filePath);
        int x=0, y=0;
        Dictionary<ElementType, Element> movingElements = new Hashtable<>();

        try (FileReader fr = new FileReader(file))
        {
            int content;
            while ((content = fr.read()) != -1) {
                //System.out.print((char) content);
                switch ((char) content) {
                    case 'x' -> maze.set(y,x, ElementFactory.createElement(ElementType.WALL, maze, x, y));
                    case 'W' -> maze.set(y,x, ElementFactory.createElement(ElementType.WARP, maze, x, y));
                    case 'o' -> maze.set(y,x, ElementFactory.createElement(ElementType.BALL, maze, x, y));
                    case 'F' -> maze.set(y,x, ElementFactory.createElement(ElementType.FRUIT, maze, x, y));
                    case 'M' -> {
                        movingElements.put(ElementType.PACMAN, ElementFactory.createElement(ElementType.PACMAN, maze, x, y));
                        maze.set(y,x, movingElements.get(ElementType.PACMAN));
                    }
                    case 'O' -> maze.set(y,x, ElementFactory.createElement(ElementType.SUPER_BALL, maze, x, y));
                    case 'Y' -> maze.set(y,x, ElementFactory.createElement(ElementType.PORTAL, maze, x, y));
                    case 'y' -> maze.set(y,x, ElementFactory.createElement(ElementType.CAVE, maze, x, y));
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
        return movingElements;
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