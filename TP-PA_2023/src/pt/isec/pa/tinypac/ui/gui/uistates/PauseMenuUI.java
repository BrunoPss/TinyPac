package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.fsm.GameState;

public class PauseMenuUI extends BorderPane {
    //Internal Data
    private final GameManager gameManager;

    //Constructor
    public PauseMenuUI(GameManager gameManager) {
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
        this.setCenter(new Label("PAUSE MENU"));
    }

    private void registerHandlers() {
        //Property Change Listener
        gameManager.addPropertyChangeListener( evt -> { update();});
    }

    private void update() {
        if (gameManager.getState() != GameState.PAUSEDSTATE ||
                gameManager.getMainMenuState()) {
            this.setVisible(false);
            return;
        }
        if (!gameManager.getMainMenuState())
            this.setVisible(true);
    }
}