package pt.isec.pa.tinypac.ui.gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.uistates.MainGameUI;
import pt.isec.pa.tinypac.ui.gui.uistates.MainMenuUI;
import pt.isec.pa.tinypac.ui.gui.uistates.PauseMenuUI;

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
                new MainMenuUI(gameManager),
                new MainGameUI(gameManager),
                new PauseMenuUI(gameManager)
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