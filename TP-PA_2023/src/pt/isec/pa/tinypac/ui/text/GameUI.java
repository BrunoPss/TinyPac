package pt.isec.pa.tinypac.ui.text;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.utils.PAInput;

import java.util.Arrays;

public class GameUI {
    //Internal Data
    GameContext fsm;
    boolean finish = false;

    //Constructor
    public GameUI(GameContext fsm) {
        this.fsm = fsm;
    }

    //Get Methods


    //Set Methods


    //Methods
    public void initMenu() {
        int op;
        do {
            op = PAInput.chooseOption("Tiny-PAc", "Iniciar Jogo", "Consultar Top 5", "Sair");
            switch (op) {
                case 1 -> gameStart();
                case 2 -> topFive();
            }
        } while (op >= 3);
    }

    //Overrides


    //Internal Functions
    private void gameStart() {
        while (!finish) {
            switch (fsm.getState()) {
                case INITSTATE -> initStateUI();
                case NORMALRUNSTATE -> normalRunStateUI();
                case PAUSEDSTATE -> pausedStateUI();
            }
        }
    }
    private void topFive() {

    }

    private void initStateUI() {
        System.out.println("Init State");
        switch (PAInput.chooseOption("Tiny-PAc", "up", "down", "left", "right", "Quit")) {
            case 1 -> fsm.up();
            case 2 -> fsm.down();
            case 3 -> fsm.left();
            case 4 -> fsm.right();
            case 5 -> {finish = true;}
        }
    }
    private void normalRunStateUI() {
        System.out.println("Normal Run State");
        System.out.println("Pacman -> Current Direction: " + fsm.getPacmanDirection()
        + "\r\nCurrent Position: " + fsm.getPacmanPosition());
        switch (PAInput.chooseOption("Tiny-PAc", "up", "down", "left", "right", "Pause", "Show Maze", "Quit")) {
            case 1 -> fsm.up();
            case 2 -> fsm.down();
            case 3 -> fsm.left();
            case 4 -> fsm.right();
            case 5 -> fsm.pauseGame();
            case 6 -> {

                for (int i=0; i < 10; i++) {
                    for (int ii=0; ii < 10; ii++) {
                        System.out.print(fsm.getMaze()[i][ii]);
                    }
                    System.out.print("\r\n");
                }
            }
            case 7 -> {finish = true;}
        }
    }
    private void pausedStateUI() {
        System.out.println("Paused State");
        switch (PAInput.chooseOption("Tiny-PAc", "resumeGame()", "Quit")) {
            case 1 -> fsm.resumeGame();
            case 2 -> {finish = true;}
        }
    }
}