package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.ConfigMenu;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;

public class MainMenuUI extends BorderPane {
    //Internal Data
    GameManager gameManager;
    MenuItem aboutItem, configItem;
    Button btnStart, btnTop5, btnConfig, btnExit;

    //Constructor
    public MainMenuUI(GameManager gameManager) {
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
        //Menu Bar
        MenuBar menuBar = new MenuBar();
        Menu infoMenu = new Menu("Info");
        Menu optionMenu = new Menu("Option");
        aboutItem = new MenuItem("About");
        configItem = new MenuItem("Configuration");
        optionMenu.getItems().add(configItem);
        menuBar.getMenus().add(optionMenu);
        infoMenu.getItems().add(aboutItem);
        menuBar.getMenus().add(infoMenu);

        //Center Zone
        //Pacman Logo
        ImageView logoPacman = new ImageView(ImageManager.getImage("pacmanLogo.png"));
        logoPacman.setFitHeight(200);
        logoPacman.setFitWidth(200);
        logoPacman.setPreserveRatio(true);
        //Start Button
        btnStart = new Button("Iniciar Jogo");
        btnStart.setMinWidth(100);
        //Top 5 Button
        btnTop5 = new Button("Consultar Top 5");
        btnTop5.setMinWidth(100);
        //Configurations Button
        btnConfig = new Button("Configuracoes");
        btnConfig.setMinWidth(100);
        //Exit Button
        btnExit = new Button("Sair");
        btnExit.setMinWidth(100);

        //Footer Informations
        Label info1 = new Label("Bruno Guiomar - 2021137345");
        ImageView logoIsec = new ImageView(ImageManager.getImage("logoIsec.png"));
        logoIsec.setFitHeight(100);
        logoIsec.setFitWidth(100);
        logoIsec.setPreserveRatio(true);
        Label info2 = new Label("DEIS - ISEC - IPC    LEI-CE");

        //Hbox Container
        BorderPane bPane = new BorderPane();

        //Button Group
        VBox btnGroup = new VBox(logoPacman, btnStart, btnTop5, btnConfig, btnExit);
        btnGroup.setAlignment(Pos.CENTER);
        btnGroup.setSpacing(10);

        //Footer Group
        HBox footerGroup = new HBox(info1, logoIsec, info2);
        footerGroup.setAlignment(Pos.BOTTOM_CENTER);
        footerGroup.setSpacing(80);

        bPane.setTop(menuBar);
        bPane.setCenter(btnGroup);
        bPane.setBottom(footerGroup);

        this.setCenter(bPane);
    }
    private void registerHandlers() {
        //Property ChangeListener
        gameManager.addPropertyChangeListener( evt -> { update();});

        //Config Menu Item ActionEvent
        configItem.setOnAction( event -> {
            new ConfigMenu(gameManager);
        });

        //About Menu Item ActionEvent
        aboutItem.setOnAction( event -> {
            //Popup infoPopup = new Popup();
            //CONSTRUIR POPUP
            Label info1 = new Label("Nome: Bruno Dinis Guiomar" +
                                     "\nNumero: 2021137345" +
                                     "\nCurso: LEI-CE" +
                                     "\nAno Letivo: 2022/2023" +
                                     "\nUnidade Curricular: Programacao Avancada" +
                                     "\nInstituicao: IPC" +
                                     "\nFaculdade: ISEC" +
                                     "\nDepartamento: DEIS" +
                                     "\nTRABALHO ACADEMICO");
            HBox hbox = new HBox();
            hbox.getChildren().add(info1);

            Scene scene = new Scene(hbox, 350, 200);

            Stage window = new Stage();
            window.setTitle("About");
            window.setScene(scene);

            window.show();
        });
        //Start Button ActionEvent
        btnStart.setOnAction( event -> {
            gameManager.setMainMenuState(false);
            this.setVisible(false);
        });
        //Top 5 Button ActionEvent
        btnTop5.setOnAction( event -> {

        });
        //Configurations Button ActionEvent
        btnConfig.setOnAction( event -> {
            new ConfigMenu(gameManager);
        });
        //Exit Button ActionEvent
        btnExit.setOnAction( event -> {
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

    }
}