package pt.isec.pa.tinypac.ui.gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.uistates.InitUI;

public class RootPane extends BorderPane {
    //Internal Data
    GameManager gameManager;

    //Constructor
    public RootPane(GameManager gameManager) {
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
        //StackPane Configuration
        StackPane stackPane = new StackPane(
                new InitUI(gameManager)
        );
        //Background Configuration

        //Align Configuration
        this.setCenter(stackPane);
    }
    private void registerHandlers() {

    }
    private void update() {

    }
}