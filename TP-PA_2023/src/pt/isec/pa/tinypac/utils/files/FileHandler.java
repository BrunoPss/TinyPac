package pt.isec.pa.tinypac.utils.files;

import java.io.*;
import java.util.Scanner;

public class FileHandler {
    //Internal Data
    Scanner scanner;

    //Constructor


    //Get Methods


    //Set Methods


    //Methods
    public static char[][] readFile(String filePath) {
        //Fechar ficheiro
        String theString;
        int y=0;
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            int xSize = getNColumns(filePath);
            char[][] c = new char[xSize][];

            theString = scanner.nextLine();
            while (scanner.hasNextLine()) {
                c[y] = scanner.nextLine().toCharArray();
                y++;
            }
            return c;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static int getNColumns(String filePath) {
        //Fechar ficheiro
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            return scanner.nextLine().split("").length;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Overrides


    //Internal Functions


}