package pt.isec.pa.tinypac;
import pt.isec.pa.tinypac.ui.text.GameUI;
import pt.isec.pa.tinypac.model.fsm.GameContext;

public class Main {
    public static void main(String[] args) {
        GameContext fsm = new GameContext();
        GameUI ui = new GameUI(fsm);
        ui.initMenu();
    }
}