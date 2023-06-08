package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.Main;

public class MainJFX extends Application {
    //Internal Data
    GameManager gameManager;

    //Constructor


    //Get Methods


    //Set Methods


    //Methods
    private void newStageForTesting(Stage stage, String title) {
        RootPane root = new RootPane(gameManager);
        Scene scene = new Scene(root, 1100, 600);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.show();
    }

    //Overrides
    @Override
    public void init() throws Exception {
        super.init();
        gameManager = Main.gameManager;
    }
    @Override
    public void start(Stage stage) throws Exception {
        newStageForTesting(stage,"Tiny-Pac");
        //newStageForTesting(new Stage(),"Game BW#clone");
    }

    @Override
    public void stop() {
        gameManager.saveTop5();
    }


    //Internal Functions


}