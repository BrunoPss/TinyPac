package pt.isec.pa.tinypac.model.data.maze;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.element.ElementFactory;
import pt.isec.pa.tinypac.model.data.element.ElementType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MazeManager {
    //Internal Data
    Scanner scanner;

    //Constructor


    //Get Methods


    //Set Methods


    //Methods
    public static void loadLevel(Maze maze, String filePath) {
        File file = new File(filePath);
        int x=0, y=0;

        try (FileReader fr = new FileReader(file))
        {
            int content;
            while ((content = fr.read()) != -1) {
                //System.out.print((char) content);
                switch ((char) content) {
                    case 'o' -> maze.set(y,x, ElementFactory.createElement(ElementType.BALL));
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

    //Overrides


    //Internal Functions


}