package pt.isec.pa.tinypac.ui.text;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.utils.PAInput;

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
                case PACMANFREESTATE -> pacmanFreeStateUI();
                case NORMALRUNSTATE -> normalRunStateUI();
                case SUPERPACMANSTATE -> superPacmanStateUI();
                case PAUSEDSTATE -> pausedStateUI();
                case GAMEENDSTATE -> gameEndStateUI();
            }
        }
    }
    private void topFive() {

    }

    private void initStateUI() {
        System.out.println("Init State");
        switch (PAInput.chooseOption("Tiny-PAc", "actionKey()", "Quit")) {
            case 1 -> fsm.actionkey();
            case 2 -> {finish = true;}
        }
    }
    private void pacmanFreeStateUI() {
        System.out.println("Pacman Free State");
        switch (PAInput.chooseOption("Tiny-PAc", "pacmanFreeTimeOut()", "Quit")) {
            case 1 -> fsm.pacmanFreeTimeOut();
            case 2 -> {finish = true;}
        }
    }
    private void normalRunStateUI() {
        System.out.println("Normal Run State");
        switch (PAInput.chooseOption("Tiny-PAc", "enhancedPacman()", "pauseGame()", "endGame()", "Quit")) {
            case 1 -> fsm.enhancedPacman();
            case 2 -> fsm.pauseGame();
            case 3 -> fsm.endGame();
            case 4 -> {finish = true;}
        }
    }
    private void superPacmanStateUI() {
        System.out.println("Super Pacman State");
        switch (PAInput.chooseOption("Tiny-PAc", "disableEnhancedPacman()", "Quit")) {
            case 1 -> fsm.disableEnhancedPacman();
            case 2 -> {finish = true;}
        }
    }
    private void pausedStateUI() {
        System.out.println("Paused State");
        switch (PAInput.chooseOption("Tiny-PAc", "resumeGame()", "Quit")) {
            case 1 -> fsm.resumeGame();
            case 2 -> {finish = true;}
        }
    }
    private void gameEndStateUI() {
        System.out.println("Game End State");
        finish = true;
    }
}