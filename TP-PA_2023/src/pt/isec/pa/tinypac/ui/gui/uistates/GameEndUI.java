package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.fsm.GameState;

public class GameEndUI extends BorderPane {
    //Internal Data
    private final GameManager gameManager;

    //Constructor
    public GameEndUI(GameManager gameManager) {
        this.gameManager = gameManager;

        createViews();
        registerHandlers();
        update();
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides


    //Internal Functions
    private void createViews() {
        this.setCenter(new Label("Game End"));
    }

    private void registerHandlers() {
        //Property Change Listener
        gameManager.addPropertyChangeListener( evt -> { update();});
    }

    private void update() {
        if (gameManager.getState() != GameState.GAME_ENDSTATE ||
                gameManager.getMainMenuState()) {
            this.setVisible(false);
            return;
        }
        if (!gameManager.getMainMenuState())
            this.setVisible(true);
    }
}