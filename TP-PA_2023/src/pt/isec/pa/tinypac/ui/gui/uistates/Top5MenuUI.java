package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.fsm.GameState;

public class Top5MenuUI extends BorderPane {
    //Internal Data
    private final GameManager gameManager;

    //Constructor
    public Top5MenuUI(GameManager gameManager) {
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
        this.setCenter(new Label("Top 5"));
    }

    private void registerHandlers() {
        //Property Change Listener
        //gameManager.addPropertyChangeListener(GameManager.PROP_ELEMENTS, evt -> { update();});
        gameManager.addPropertyChangeListener(evt -> Platform.runLater(this::update));
    }

    private void update() {
        if (!gameManager.getTop5MenuState()) {
            this.setVisible(false);
            return;
        }
        if (gameManager.getTop5MenuState()) {
            this.setVisible(true);
        }
    }
}