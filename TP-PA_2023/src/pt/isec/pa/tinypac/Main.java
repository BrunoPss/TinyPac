package pt.isec.pa.tinypac;

import javafx.application.Application;
import javafx.application.Platform;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.curses.CursesGameUI;
import pt.isec.pa.tinypac.ui.gui.MainJFX;
import pt.isec.pa.tinypac.ui.text.TextGameUI;
import pt.isec.pa.tinypac.model.fsm.GameContext;

import java.io.IOException;

/**
 * Main Class
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class Main {
    /**
     * Game Manager
     */
    public static GameManager gameManager;
    static {
        IGameEngine gameEngine = new GameEngine();
        gameManager = new GameManager();
        gameEngine.registerClient((g,t) -> gameManager.update());
        gameEngine.start(100);
    }

    /**
     * Main Function
     * @param args Arguments
     */
    public static void main(String[] args) {
        //IGameEngine gameEngine = new GameEngine();
        //GameContext fsm = new GameContext(gameEngine);
        //CursesGameUI cursesUI = new CursesGameUI(fsm);
        //TextGameUI ui = new TextGameUI(fsm, cursesUI);
        //gameEngine.registerClient(cursesUI);
        //ui.initMenu();

        Application.launch(MainJFX.class, args);
    }
}