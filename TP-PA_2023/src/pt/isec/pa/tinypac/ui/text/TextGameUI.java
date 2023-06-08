package pt.isec.pa.tinypac.ui.text;

import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.ui.curses.CursesGameUI;
import pt.isec.pa.tinypac.utils.PAInput;

import java.io.IOException;

public class TextGameUI {
    //Internal Data
    GameContext fsm;
    CursesGameUI cursesUI;
    boolean finish = false;

    //Constructor
    public TextGameUI(GameContext fsm, CursesGameUI cursesUI) {
        this.fsm = fsm;
        this.cursesUI = cursesUI;
    }

    //Get Methods


    //Set Methods


    //Methods
    public void initMenu() throws IOException {
        int op;
        do {
            op = PAInput.chooseOption("Tiny-PAc", "Iniciar Jogo", "Consultar Top 5", "Sair");
            switch (op) {
                case 1 -> gameStart();
                case 2 -> topFive();
            }
        } while (op > 3);
    }

    //Overrides


    //Internal Functions
    private void gameStart() throws IOException {
        cursesUI.show();
        while (!finish) {
            switch (fsm.getState()) {
                case INITSTATE -> initStateUI();
                case NORMALRUNSTATE -> normalRunStateUI();
                case PAUSEDSTATE -> pausedStateUI();
                case GAME_ENDSTATE -> gameEndUI();
            }
        }
    }
    private void topFive() {

    }

    private void initStateUI() {
        System.out.println("Init State");
        switch (PAInput.chooseOption("Tiny-PAc", "up", "down", "left", "right", "Quit")) {
            //case 1 -> fsm.up();
            //case 2 -> fsm.down();
            //case 3 -> fsm.left();
            //case 4 -> fsm.right();
            case 5 -> {finish = true;}
        }
    }
    private void normalRunStateUI() {
        System.out.println("Normal Run State");
        //System.out.println("Pacman -> Current Direction: " + fsm.getPacmanDirection()
        //+ "\r\nCurrent Position: " + fsm.getPacmanPosition()
        //+ "\r\nMazeX: " + fsm.getMaze()[0].length + ", MazeY: " + fsm.getMaze().length);
        switch (PAInput.chooseOption("Tiny-PAc", "up", "down", "left", "right", "Pause", "Show Maze", "Quit")) {
            //case 1 -> fsm.up();
            //case 2 -> fsm.down();
            //case 3 -> fsm.left();
            //case 4 -> fsm.right();
            //case 5 -> fsm.pauseGame();
            //case 6 -> showMaze();
            case 7 -> {finish = true;}
        }
    }
    private void showMaze() {
        /*
        for (int i=0; i < fsm.getMaze().length; i++) {
            System.out.print("|");
            for (int ii=0; ii < (fsm.getMaze()[0].length * 2); ii++) {
                if (fsm.getMaze()[i][ii/2] != ' ' && ii % 2 == 0)
                    System.out.print(fsm.getMaze()[i][ii/2]);
                else if (ii % 2 == 0)
                    System.out.print("_");
                else
                    System.out.print(" ");
            }
            System.out.print("|\r\n");
        }
         */
    }
    private void pausedStateUI() {
        System.out.println("Paused State");
        switch (PAInput.chooseOption("Tiny-PAc", "resumeGame()", "Quit")) {
            case 1 -> fsm.resumeGame();
            case 2 -> fsm.exitGame();
        }
    }
    private void gameEndUI() {
        System.out.println("Game End State");
        if (PAInput.chooseOption("Tiny-PAc", "Quit") == 1) {
            finish = true;
        }
    }
}