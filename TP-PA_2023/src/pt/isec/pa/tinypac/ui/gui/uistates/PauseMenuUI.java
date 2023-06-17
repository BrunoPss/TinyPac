package pt.isec.pa.tinypac.ui.gui.uistates;

import java.util.Optional;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.ui.gui.ConfigMenu;

public class PauseMenuUI extends BorderPane {
    //Internal Data
    private final GameManager gameManager;
    private Button resumeBtn, saveBtn, configBtn, exitBtn;

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
        //Main Window Configuration
        //this.setOpacity(0.5);
        //this.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));

        //Filter Panel
        VBox filterPanel = new VBox();
        filterPanel.setOpacity(0.5);
        filterPanel.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));

        //Menu Panel
        VBox menuPanel = new VBox();
        menuPanel.setMinWidth(300);
        menuPanel.setMaxWidth(300);
        menuPanel.setMinHeight(450);
        menuPanel.setMaxHeight(450);
        menuPanel.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        menuPanel.setAlignment(Pos.CENTER);
        menuPanel.setSpacing(10);

        //Resume Button
        resumeBtn = new Button("Retomar");
        //Save Button
        saveBtn = new Button("Salvar");
        //Configurations Button
        configBtn = new Button("Configuracoes");
        //Exit Button
        exitBtn = new Button("Terminar Jogo");

        //Group Add
        menuPanel.getChildren().addAll(resumeBtn, saveBtn, configBtn, exitBtn);
        this.setCenter(new StackPane(filterPanel, menuPanel));
    }

    private void registerHandlers() {
        //Property Change Listener
        gameManager.addPropertyChangeListener(evt -> Platform.runLater(this::update));

        //Resume Button ActionEvent
        resumeBtn.setOnAction( event -> {
            gameManager.resumeGame();
        });

        //Save Button ActionEvent
        saveBtn.setOnAction( event -> {
            gameManager.saveGame();
        });

        //Configuration Button ActionEvent
        configBtn.setOnAction( event -> {
            new ConfigMenu(gameManager);
        });

        //Exit Button ActionEvent
        exitBtn.setOnAction( event -> {
            Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
            exitAlert.setTitle("Confirm Exit");
            exitAlert.setHeaderText("Deseja sair do Jogo ?");
            exitAlert.setContentText("Todo o progresso nao salvo ira ser perdido!");
            Optional<ButtonType> opt = exitAlert.showAndWait();
            if (opt.get() == ButtonType.OK) {
                gameManager.exitGame();
            }
        });
    }

    private void update() {
        if (gameManager.getState() != GameState.PAUSEDSTATE ||
                gameManager.getMainMenuState() ||
                gameManager.getTop5MenuState()) {
            this.setVisible(false);
            return;
        }
        if (!gameManager.getMainMenuState())
            this.setVisible(true);
    }
}