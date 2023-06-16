package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.fsm.GameState;

import java.util.Optional;

public class GameEndUI extends BorderPane {
    //Internal Data
    private final GameManager gameManager;
    private Button submitButton, exitBtn;
    TextField nomeTextField;
    VBox addTop5Pane;

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
        //Info Pane
        VBox infoPane = new VBox();
        infoPane.setAlignment(Pos.CENTER);
        infoPane.setSpacing(20);
        infoPane.setPadding(new Insets(20));
        Label info1 = new Label("Info 1");
        Label info2 = new Label("Info 2");
        Label info3 = new Label("Info 3");
        infoPane.getChildren().addAll(info1, info2, info3);

        //Top5 Add Pane
        addTop5Pane = new VBox();
        addTop5Pane.setAlignment(Pos.CENTER);
        addTop5Pane.setSpacing(20);
        addTop5Pane.setPadding(new Insets(20));
        Label addTop5Title = new Label("Parabens");
        Label addTop5SubTitle = new Label("Entrou no Top 5");
        //Campo Nome
        HBox nomeField = new HBox();
        nomeField.setSpacing(10);
        nomeField.setAlignment(Pos.CENTER);
        Label nomeLabel = new Label("Nome");
        nomeTextField = new TextField();
        submitButton = new Button("Submeter");

        //Exit Button
        exitBtn = new Button("Exit");

        nomeField.getChildren().addAll(nomeLabel, nomeTextField, submitButton);
        addTop5Pane.getChildren().addAll(addTop5Title, addTop5SubTitle, nomeField);

        this.setLeft(infoPane);
        this.setRight(addTop5Pane);
        this.setBottom(exitBtn);
    }

    private void registerHandlers() {
        //Property Change Listener
        gameManager.addPropertyChangeListener( evt -> { update();});

        //Submit Button ActionEvent
        submitButton.setOnAction( event -> {
            gameManager.insertPlayerTop5(nomeTextField.getText());
        });

        //Exit Button ActionEvent
        exitBtn.setOnAction( event -> {
            Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
            exitAlert.setTitle("Confirm Exit");
            exitAlert.setHeaderText("Deseja sair do programa ?");
            Optional<ButtonType> opt = exitAlert.showAndWait();
            if (opt.get() == ButtonType.OK) {
                Platform.exit();
            }
        });
    }

    private void update() {
        //Top Player Update
        addTop5Pane.setVisible(gameManager.isTopPlayer());


        if (gameManager.getState() != GameState.GAME_ENDSTATE ||
                gameManager.getMainMenuState()) {
            this.setVisible(false);
            return;
        }
        if (!gameManager.getMainMenuState())
            this.setVisible(true);
    }
}