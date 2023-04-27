package pt.isec.pa.tinypac;

import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.ui.curses.CursesGameUI;
import pt.isec.pa.tinypac.ui.text.TextGameUI;
import pt.isec.pa.tinypac.model.fsm.GameContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        IGameEngine gameEngine = new GameEngine();
        GameContext fsm = new GameContext(gameEngine);
        CursesGameUI cursesUI = new CursesGameUI(fsm);
        TextGameUI ui = new TextGameUI(fsm, cursesUI);
        gameEngine.registerClient(cursesUI);
        ui.initMenu();
    }
}