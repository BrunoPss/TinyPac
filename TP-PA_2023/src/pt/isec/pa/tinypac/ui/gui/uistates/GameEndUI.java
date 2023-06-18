package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.fsm.GameState;

import java.util.Optional;

/**
 * Game End UI Class
 * <p>Class that represents the Game End UI</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class GameEndUI extends BorderPane {
    //Internal Data
    private final GameManager gameManager;
    private Button submitButton, exitBtn;
    private TextField nomeTextField;
    private VBox addTop5Pane;
    private Label levelInfo, scoreInfo, lifesInfo;

    //Constructor
    /**
     * Constructor
     * @param gameManager Game Manager
     */
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
        //Title Box
        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        //Title
        Label title = new Label("Game End");
        title.setAlignment(Pos.CENTER);
        title.setPadding(new Insets(15));
        title.setStyle("-fx-font-size: 30; -fx-font-weight: bold");
        titleBox.getChildren().addAll(title);

        //Info Pane
        VBox infoPane = new VBox();
        infoPane.setSpacing(40);
        infoPane.setAlignment(Pos.CENTER);
        //Info Title
        Label infoTitle = new Label("Game Informations");
        infoTitle.setStyle("-fx-font-size: 20; -fx-font-weight: bold");
        //Info Block
        TilePane infoBlock = new TilePane();
        infoBlock.setHgap(40);
        infoBlock.setVgap(40);
        infoBlock.setAlignment(Pos.CENTER);
        //Level Block
        VBox levelBox = new VBox();
        levelBox.setStyle("-fx-border-width: 1; -fx-border-color: black;");
        levelBox.setAlignment(Pos.CENTER);
        levelBox.setPadding(new Insets(5));
        Label levelLabel = new Label("Level");
        levelInfo = new Label();
        levelBox.getChildren().addAll(levelLabel, levelInfo);

        //Score Block
        VBox scoreBox = new VBox();
        scoreBox.setStyle("-fx-border-width: 1; -fx-border-color: black;");
        scoreBox.setAlignment(Pos.CENTER);
        scoreBox.setPadding(new Insets(5));
        Label scoreLabel = new Label("Score");
        scoreInfo = new Label();
        scoreBox.getChildren().addAll(scoreLabel, scoreInfo);

        //Lifes Block
        VBox lifesBox = new VBox();
        lifesBox.setStyle("-fx-border-width: 1; -fx-border-color: black;");
        lifesBox.setAlignment(Pos.CENTER);
        lifesBox.setPadding(new Insets(5));
        Label lifesLabel = new Label("Vidas");
        lifesInfo = new Label();
        lifesBox.getChildren().addAll(lifesLabel, lifesInfo);

        infoBlock.getChildren().addAll(levelBox, scoreBox, lifesBox);
        infoPane.getChildren().addAll(infoTitle, infoBlock);

        //Top5 Add Pane
        addTop5Pane = new VBox();
        addTop5Pane.setAlignment(Pos.CENTER);
        addTop5Pane.setSpacing(20);
        addTop5Pane.setPadding(new Insets(20));
        addTop5Pane.setStyle("-fx-border-width: 1; -fx-border-color: black");
        Label addTop5Title = new Label("Top 5");
        addTop5Title.setStyle("-fx-font-size: 20; -fx-font-weight: bold");
        Label addTop5SubTitle = new Label("Insira o seu nome");
        //Campo Nome
        HBox nomeField = new HBox();
        nomeField.setSpacing(10);
        nomeField.setAlignment(Pos.CENTER);
        Label nomeLabel = new Label("Nome");
        nomeTextField = new TextField();

        submitButton = new Button("Submeter");

        //Exit Button Box
        VBox exitBtnBox = new VBox();
        exitBtnBox.setAlignment(Pos.CENTER);
        exitBtnBox.setPadding(new Insets(20));
        exitBtnBox.setSpacing(10);
        //Exit Button
        exitBtn = new Button("Exit");
        exitBtn.setAlignment(Pos.CENTER);
        exitBtnBox.getChildren().addAll(exitBtn);

        nomeField.getChildren().addAll(nomeLabel, nomeTextField);
        addTop5Pane.getChildren().addAll(addTop5Title, addTop5SubTitle, nomeField, submitButton);

        this.setTop(titleBox);
        this.setCenter(infoPane);
        this.setRight(addTop5Pane);
        this.setBottom(exitBtnBox);
    }

    private void registerHandlers() {
        //Property Change Listener
        //gameManager.addPropertyChangeListener( evt -> { update();});
        gameManager.addPropertyChangeListener(evt -> Platform.runLater(this::update));

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
        addTop5Pane.setDisable(!gameManager.isTopPlayer());

        //Level Info Update
        levelInfo.setText(Integer.toString(gameManager.getCurrentLevel()+1));

        //Score Info Update
        scoreInfo.setText(Integer.toString(gameManager.getPacmanPoints()));

        //Lifes Info Update
        lifesInfo.setText(Integer.toString(gameManager.getPacmanLives()));

        if (gameManager.getState() != GameState.GAME_ENDSTATE ||
                gameManager.getMainMenuState()) {
            this.setVisible(false);
            return;
        }
        if (!gameManager.getMainMenuState())
            this.setVisible(true);
    }
}