package pt.isec.pa.tinypac.ui.gui.uistates;

import com.googlecode.lanterna.gui2.WindowListenerAdapter;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pt.isec.pa.tinypac.model.GameManager;
//NAO SE PODE IMPORTAR COISAS DO MODELO DE DADOS (APAGAR)
import pt.isec.pa.tinypac.model.data.ball.Ball;
import pt.isec.pa.tinypac.model.data.element.ElementType;
import pt.isec.pa.tinypac.model.data.entity.EntityType;
import pt.isec.pa.tinypac.model.data.superBall.SuperBall;
import pt.isec.pa.tinypac.model.data.pacman.Pacman;
import pt.isec.pa.tinypac.model.data.warp.Warp;
import pt.isec.pa.tinypac.model.data.fruit.Fruit;
import pt.isec.pa.tinypac.model.data.ghosts.Inky;
import pt.isec.pa.tinypac.model.data.ghosts.Blinky;
import pt.isec.pa.tinypac.model.data.ghosts.Clyde;
import pt.isec.pa.tinypac.model.data.ghosts.Pinky;
import pt.isec.pa.tinypac.model.data.wall.Wall;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

import java.awt.*;

public class MainGameUI extends BorderPane {
    //Internal Data
    private final GameManager gameManager;
    private final int mazeWidth = 800, mazeHeight = 550;
    private final int cellSize = 18;
    private Canvas maze;
    private GraphicsContext mazeContext;
    private Button arrowLEFT, arrowUP, arrowDOWN, arrowRIGHT, pauseBtn;
    private CheckBox muteButton;
    private Stage popupStage;
    private Button infoPacmanButton, infoBlinkyButton, infoClydeButton, infoInkyButton, infoPinkyButton, infoBallButton, infoWarpButton, infoFruitButton, infoSuperBallButton;
    private Label popupMSG;
    private Label scoreInfo, levelInfo, ballsInfo, livesInfo;
    private ImageView iconFruit, iconSuperBall, iconBlinky, iconInky, iconPinky, iconClyde;

    //Constructor
    public MainGameUI(GameManager gameManager) {
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
        //Main Pane
        SplitPane mainPane = new SplitPane();
        //Maze Panel
        maze = new Canvas(mazeWidth-100,mazeHeight);
        mazeContext = maze.getGraphicsContext2D();
        mazeContext.setFill(Color.BLACK);
        mazeContext.fillRect(0, 0, maze.getWidth(), maze.getHeight());
        //Draw Maze
        drawMaze();

        //Second Panel
        VBox secondPanel = new VBox();
        secondPanel.setAlignment(Pos.TOP_CENTER);
        secondPanel.setSpacing(20);
        //Score Panel
        VBox scorePanel = new VBox();
        scorePanel.setSpacing(10);
        //Icons Panel
        TilePane iconsPanel = new TilePane();
        iconsPanel.setHgap(10);
        iconsPanel.setVgap(10);
        iconsPanel.setAlignment(Pos.BASELINE_CENTER);
        iconsPanel.setStyle("-fx-background-color: #A0A6A6");
        //Title Label
        Label iconsTitle = new Label("Game Elements");
        //Icon 1 (Pacman)
        VBox iconPacmanPanel = new VBox();
        iconPacmanPanel.setAlignment(Pos.CENTER);
        ImageView iconPacman = new ImageView(ImageManager.getImage("pacmanRight.png"));
        iconPacman.setPreserveRatio(true);
        iconPacman.setFitHeight(20);
        iconPacman.setFitWidth(20);
        HBox infoPacmanPanel = new HBox();
        infoPacmanPanel.setAlignment(Pos.CENTER);
        infoPacmanPanel.setPadding(new Insets(5));
        infoPacmanPanel.setSpacing(5);
        Label infoPacman = new Label("Pacman");
        infoPacman.setStyle("-fx-font-size: 10; -fx-text-fill: white");
        infoPacmanButton = new Button("?");
        infoPacmanButton.setStyle("-fx-font-size: 8");
        infoPacmanPanel.getChildren().addAll(infoPacman, infoPacmanButton);
        iconPacmanPanel.getChildren().addAll(iconPacman, infoPacmanPanel);

        //Icon 2 (Blinky)
        VBox iconBlinkyPanel = new VBox();
        iconBlinkyPanel.setAlignment(Pos.CENTER);
        iconBlinky = new ImageView(ImageManager.getImage("blinky.png"));
        iconBlinky.setPreserveRatio(true);
        iconBlinky.setFitHeight(20);
        iconBlinky.setFitWidth(20);
        HBox infoBlinkyPanel = new HBox();
        infoBlinkyPanel.setAlignment(Pos.CENTER);
        infoBlinkyPanel.setPadding(new Insets(5));
        infoBlinkyPanel.setSpacing(5);
        Label infoBlinky = new Label("Blinky");
        infoBlinky.setStyle("-fx-font-size: 10; -fx-text-fill: white");
        infoBlinkyButton = new Button("?");
        infoBlinkyButton.setStyle("-fx-font-size: 8");
        infoBlinkyPanel.getChildren().addAll(infoBlinky, infoBlinkyButton);
        iconBlinkyPanel.getChildren().addAll(iconBlinky, infoBlinkyPanel);

        //Icon 3 (Clyde)
        VBox iconClydePanel = new VBox();
        iconClydePanel.setAlignment(Pos.CENTER);
        iconClyde = new ImageView(ImageManager.getImage("clyde.png"));
        iconClyde.setPreserveRatio(true);
        iconClyde.setFitHeight(20);
        iconClyde.setFitWidth(20);
        HBox infoClydePanel = new HBox();
        infoClydePanel.setAlignment(Pos.CENTER);
        infoClydePanel.setPadding(new Insets(5));
        infoClydePanel.setSpacing(5);
        Label infoClyde = new Label("Clyde");
        infoClyde.setStyle("-fx-font-size: 10; -fx-text-fill: white");
        infoClydeButton = new Button("?");
        infoClydeButton.setStyle("-fx-font-size: 8");
        infoClydePanel.getChildren().addAll(infoClyde, infoClydeButton);
        iconClydePanel.getChildren().addAll(iconClyde, infoClydePanel);

        //Icon 4 (Inky)
        VBox iconInkyPanel = new VBox();
        iconInkyPanel.setAlignment(Pos.CENTER);
        iconInky = new ImageView(ImageManager.getImage("inky.png"));
        iconInky.setPreserveRatio(true);
        iconInky.setFitHeight(20);
        iconInky.setFitWidth(20);
        HBox infoInkyPanel = new HBox();
        infoInkyPanel.setAlignment(Pos.CENTER);
        infoInkyPanel.setPadding(new Insets(5));
        infoInkyPanel.setSpacing(5);
        Label infoInky = new Label("Inky");
        infoInky.setStyle("-fx-font-size: 10; -fx-text-fill: white");
        infoInkyButton = new Button("?");
        infoInkyButton.setStyle("-fx-font-size: 8");
        infoInkyPanel.getChildren().addAll(infoInky, infoInkyButton);
        iconInkyPanel.getChildren().addAll(iconInky, infoInkyPanel);

        //Icon 5 (Pinky)
        VBox iconPinkyPanel = new VBox();
        iconPinkyPanel.setAlignment(Pos.CENTER);
        iconPinky = new ImageView(ImageManager.getImage("pinky.png"));
        iconPinky.setPreserveRatio(true);
        iconPinky.setFitHeight(20);
        iconPinky.setFitWidth(20);
        HBox infoPinkyPanel = new HBox();
        infoPinkyPanel.setAlignment(Pos.CENTER);
        infoPinkyPanel.setPadding(new Insets(5));
        infoPinkyPanel.setSpacing(5);
        Label infoPinky = new Label("Pinky");
        infoPinky.setStyle("-fx-font-size: 10; -fx-text-fill: white");
        infoPinkyButton = new Button("?");
        infoPinkyButton.setStyle("-fx-font-size: 8");
        infoPinkyPanel.getChildren().addAll(infoPinky, infoPinkyButton);
        iconPinkyPanel.getChildren().addAll(iconPinky, infoPinkyPanel);

        //Icon 6 (Ball)
        VBox iconBallPanel = new VBox();
        iconBallPanel.setAlignment(Pos.CENTER);
        ImageView iconBall = new ImageView(ImageManager.getImage("ball.png"));
        iconBall.setPreserveRatio(true);
        iconBall.setFitHeight(20);
        iconBall.setFitWidth(20);
        HBox infoBallPanel = new HBox();
        infoBallPanel.setAlignment(Pos.CENTER);
        infoBallPanel.setPadding(new Insets(5));
        infoBallPanel.setSpacing(5);
        Label infoBall = new Label("Ball");
        infoBall.setStyle("-fx-font-size: 10; -fx-text-fill: white");
        infoBallButton = new Button("?");
        infoBallButton.setStyle("-fx-font-size: 8");
        infoBallPanel.getChildren().addAll(infoBall, infoBallButton);
        iconBallPanel.getChildren().addAll(iconBall, infoBallPanel);

        //Icon 7 (Fruit)
        VBox iconFruitPanel = new VBox();
        iconFruitPanel.setAlignment(Pos.CENTER);
        iconFruit = new ImageView(ImageManager.getImage("fruit.png"));
        iconFruit.setPreserveRatio(true);
        iconFruit.setFitHeight(20);
        iconFruit.setFitWidth(20);
        HBox infoFruitPanel = new HBox();
        infoFruitPanel.setAlignment(Pos.CENTER);
        infoFruitPanel.setPadding(new Insets(5));
        infoFruitPanel.setSpacing(5);
        Label infoFruit = new Label("Fruit");
        infoFruit.setStyle("-fx-font-size: 10; -fx-text-fill: white");
        infoFruitButton = new Button("?");
        infoFruitButton.setStyle("-fx-font-size: 8");
        infoFruitPanel.getChildren().addAll(infoFruit, infoFruitButton);
        iconFruitPanel.getChildren().addAll(iconFruit, infoFruitPanel);

        //Icon 8 (Super Ball)
        VBox iconSuperBallPanel = new VBox();
        iconSuperBallPanel.setAlignment(Pos.CENTER);
        iconSuperBall = new ImageView(ImageManager.getImage("superBall.png"));
        iconSuperBall.setPreserveRatio(true);
        iconSuperBall.setFitHeight(20);
        iconSuperBall.setFitWidth(20);
        HBox infoSuperBallPanel = new HBox();
        infoSuperBallPanel.setAlignment(Pos.CENTER);
        infoSuperBallPanel.setPadding(new Insets(5));
        infoSuperBallPanel.setSpacing(5);
        Label infoSuperBall = new Label("SuperBall");
        infoSuperBall.setStyle("-fx-font-size: 10; -fx-text-fill: white");
        infoSuperBallButton = new Button("?");
        infoSuperBallButton.setStyle("-fx-font-size: 8");
        infoSuperBallPanel.getChildren().addAll(infoSuperBall, infoSuperBallButton);
        iconSuperBallPanel.getChildren().addAll(iconSuperBall, infoSuperBallPanel);

        //Icon 9 (Warp)
        VBox iconWarpPanel = new VBox();
        iconWarpPanel.setAlignment(Pos.CENTER);
        ImageView iconWarp = new ImageView(ImageManager.getImage("warp.png"));
        iconWarp.setPreserveRatio(true);
        iconWarp.setFitHeight(20);
        iconWarp.setFitWidth(20);
        HBox infoWarpPanel = new HBox();
        infoWarpPanel.setAlignment(Pos.CENTER);
        infoWarpPanel.setPadding(new Insets(5));
        infoWarpPanel.setSpacing(5);
        Label infoWarp = new Label("Warp");
        infoWarp.setStyle("-fx-font-size: 10; -fx-text-fill: white");
        infoWarpButton = new Button("?");
        infoWarpButton.setStyle("-fx-font-size: 8");
        infoWarpPanel.getChildren().addAll(infoWarp, infoWarpButton);
        iconWarpPanel.getChildren().addAll(iconWarp, infoWarpPanel);
        //Add Elements
        iconsPanel.getChildren().addAll(iconPacmanPanel, iconBlinkyPanel, iconClydePanel, iconInkyPanel, iconPinkyPanel, iconBallPanel, iconFruitPanel, iconSuperBallPanel, iconWarpPanel);

        //Separator (Horizontal)
        Separator separator3 = new Separator(Orientation.HORIZONTAL);

        //Game Information
        Label gameInfoLabel = new Label("Game Information");
        //Info Pane (Tile Pane)
        TilePane gameInfoPane = new TilePane();
        gameInfoPane.setHgap(40);
        gameInfoPane.setVgap(40);
        gameInfoPane.setAlignment(Pos.CENTER);
        //Block 1 (Score)
        VBox scoreBlock = new VBox();
        scoreBlock.setStyle("-fx-border-width: 1; -fx-border-color: black;");
        scoreBlock.setAlignment(Pos.CENTER);
        scoreBlock.setPadding(new Insets(5));
        Label scoreLabel = new Label("Score");
        scoreInfo = new Label();
        scoreBlock.getChildren().addAll(scoreLabel, scoreInfo);

        //Block 2 (Nivel)
        VBox levelBlock = new VBox();
        levelBlock.setStyle("-fx-border-width: 1; -fx-border-color: black;");
        levelBlock.setAlignment(Pos.CENTER);
        levelBlock.setPadding(new Insets(5));
        Label levelLabel = new Label("Nivel");
        levelInfo = new Label();
        levelBlock.getChildren().addAll(levelLabel, levelInfo);

        //Block 3 (Bolas)
        VBox ballsBlock = new VBox();
        ballsBlock.setStyle("-fx-border-width: 1; -fx-border-color: black;");
        ballsBlock.setAlignment(Pos.CENTER);
        ballsBlock.setPadding(new Insets(5));
        Label ballsLabel = new Label("Bolas");
        ballsInfo = new Label();
        ballsBlock.getChildren().addAll(ballsLabel, ballsInfo);

        //Block 4 (Vidas)
        VBox livesBlock = new VBox();
        livesBlock.setStyle("-fx-border-width: 1; -fx-border-color: black;");
        livesBlock.setAlignment(Pos.CENTER);
        livesBlock.setPadding(new Insets(5));
        Label livesLabel = new Label("Vidas");
        livesInfo = new Label();
        livesBlock.getChildren().addAll(livesLabel, livesInfo);

        //Add Elements
        gameInfoPane.getChildren().addAll(scoreBlock, levelBlock, ballsBlock, livesBlock);

        //Help Popup (Normal Hidden)
        popupStage = new Stage();
        popupStage.initStyle(StageStyle.UNDECORATED);
        BorderPane popupPane = new BorderPane();
        popupPane.setPrefSize(100, 80);
        popupMSG = new Label();
        popupMSG.setWrapText(true);
        popupPane.setCenter(popupMSG);
        popupStage.setScene(new Scene(popupPane));

        //Separator (Horizontal)
        Separator separator1 = new Separator(Orientation.HORIZONTAL);
        //Options Panel
        Label optionsLabel = new Label("Options");
        VBox optionsPanel = new VBox();
        optionsPanel.setSpacing(20);
        //Buttons Panel
        HBox btnsPanel = new HBox();
        btnsPanel.setAlignment(Pos.BASELINE_CENTER);
        //Pause Button
        pauseBtn = new Button("Pause");
        btnsPanel.getChildren().add(pauseBtn);
        //Controls Panel
        HBox controlsPanel = new HBox();
        controlsPanel.setSpacing(20);
        //Virtual Arrows
        GridPane arrowsPane = new GridPane();
        arrowsPane.setHgap(10);
        arrowsPane.setVgap(10);
        //Arrow Images
        ImageView arrowLeftIMG = new ImageView(ImageManager.getImage("arrowLeft.png"));
        ImageView arrowUpIMG = new ImageView(ImageManager.getImage("arrowUp.png"));
        ImageView arrowDownIMG = new ImageView(ImageManager.getImage("arrowDown.png"));
        ImageView arrowRightIMG = new ImageView(ImageManager.getImage("arrowRight.png"));
        arrowLeftIMG.setPreserveRatio(true);
        arrowUpIMG.setPreserveRatio(true);
        arrowDownIMG.setPreserveRatio(true);
        arrowRightIMG.setPreserveRatio(true);
        arrowLeftIMG.setFitHeight(20);
        arrowUpIMG.setFitHeight(20);
        arrowDownIMG.setFitHeight(20);
        arrowRightIMG.setFitHeight(20);
        arrowLeftIMG.setFitWidth(20);
        arrowUpIMG.setFitWidth(20);
        arrowDownIMG.setFitWidth(20);
        arrowRightIMG.setFitWidth(20);
        //Arrow Buttons
        arrowLEFT = new Button();
        arrowUP = new Button();
        arrowDOWN = new Button();
        arrowRIGHT = new Button();
        //Button Creation
        arrowLEFT.setGraphic(arrowLeftIMG);
        arrowUP.setGraphic(arrowUpIMG);
        arrowRIGHT.setGraphic(arrowRightIMG);
        arrowDOWN.setGraphic(arrowDownIMG);
        //Button Position
        arrowsPane.add(arrowLEFT, 0,1);
        arrowsPane.add(arrowUP, 1,0);
        arrowsPane.add(arrowDOWN, 1,2);
        arrowsPane.add(arrowRIGHT, 2,1);
        //Separator
        Separator separator2 = new Separator(Orientation.VERTICAL);
        //Button Controls
        VBox btnControls = new VBox();
        //Mute Button
        muteButton = new CheckBox("Mute");
        btnControls.getChildren().add(muteButton);
        //Group Add
        controlsPanel.getChildren().addAll(arrowsPane, separator2, btnControls);
        scorePanel.getChildren().addAll(iconsTitle, iconsPanel, separator3, gameInfoLabel, gameInfoPane);
        optionsPanel.getChildren().addAll(optionsLabel, btnsPanel, controlsPanel);
        secondPanel.getChildren().addAll(scorePanel,separator1, optionsPanel);
        mainPane.getItems().addAll(maze, secondPanel);
        this.setCenter(mainPane);
    }

    private void registerHandlers() {
        //Property Change Listener
        //gameManager.addPropertyChangeListener(GameManager.PROP_ELEMENTS, evt -> { update();});
        gameManager.addPropertyChangeListener(evt -> Platform.runLater(this::update));

        //Keyboard Key Press ActionEvent
        this.setOnKeyPressed( event -> {
            KeyCode key = event.getCode();
            System.out.println(key);
            switch (key) {
                case W -> gameManager.up();
                case S -> gameManager.down();
                case A -> gameManager.left();
                case D -> gameManager.right();
                case ESCAPE -> gameManager.pauseGame();
            }
        });

        //Hover Help Popup ActionEvent (Pacman)
        infoPacmanButton.setOnMousePressed(evt -> {
            helpPopupShow("Pacman Description");
        });
        infoPacmanButton.setOnMouseReleased(evt -> {
            popupStage.hide();
        });
        //Hover Help Popup ActionEvent (Blinky)
        infoBlinkyButton.setOnMousePressed(evt -> {
            helpPopupShow("Blinky Description");
        });
        infoBlinkyButton.setOnMouseReleased(evt -> {
            popupStage.hide();
        });
        //Hover Help Popup ActionEvent (Clyde)
        infoClydeButton.setOnMousePressed(evt -> {
            helpPopupShow("Clyde Description");
        });
        infoClydeButton.setOnMouseReleased(evt -> {
            popupStage.hide();
        });
        //Hover Help Popup ActionEvent (Inky)
        infoInkyButton.setOnMousePressed(evt -> {
            helpPopupShow("Inky Description");
        });
        infoInkyButton.setOnMouseReleased(evt -> {
            popupStage.hide();
        });
        //Hover Help Popup ActionEvent (Pinky)
        infoPinkyButton.setOnMousePressed(evt -> {
            helpPopupShow("Pinky Description");
        });
        infoPinkyButton.setOnMouseReleased(evt -> {
            popupStage.hide();
        });
        //Hover Help Popup ActionEvent (Ball)
        infoBallButton.setOnMousePressed(evt -> {
            helpPopupShow("Ball Description");
        });
        infoBallButton.setOnMouseReleased(evt -> {
            popupStage.hide();
        });
        //Hover Help Popup ActionEvent (Fruit)
        infoFruitButton.setOnMousePressed(evt -> {
            helpPopupShow("Fruit Description");
        });
        infoFruitButton.setOnMouseReleased(evt -> {
            popupStage.hide();
        });
        //Hover Help Popup ActionEvent (Super Ball)
        infoSuperBallButton.setOnMousePressed(evt -> {
            helpPopupShow("Super Ball Description");
        });
        infoSuperBallButton.setOnMouseReleased(evt -> {
            popupStage.hide();
        });
        //Hover Help Popup ActionEvent (Warp)
        infoWarpButton.setOnMousePressed(evt -> {
            helpPopupShow("Warp Description");
        });
        infoWarpButton.setOnMouseReleased(evt -> {
            popupStage.hide();
        });

        //Virtual Arrows ActionEvent
        arrowLEFT.setOnAction( event -> {
            gameManager.left();
        });
        arrowUP.setOnAction( event -> {
            gameManager.up();
        });
        arrowDOWN.setOnAction( event -> {
            gameManager.down();
        });
        arrowRIGHT.setOnAction( event -> {
            gameManager.right();
        });

        //Pause Button ActionEvent
        pauseBtn.setOnAction( event -> {
            gameManager.pauseGame();
        });

        //Mute Button ActionEvent
        muteButton.setOnAction( event -> {
            gameManager.toogleMute();
        });
    }

    private void update() {
        //Pause Button Update
        pauseBtn.setDisable(gameManager.getState() != GameState.NORMALRUNSTATE);

        //Mute Button Update
        muteButton.setSelected(gameManager.getMuted());

        //Maze Update
        drawMaze();

        //Element Icons Update
        ColorAdjust removeColor = new ColorAdjust();
        removeColor.setBrightness(-1);
        //Fruit Icon
        if (!gameManager.getElementActive(ElementType.FRUIT))
            iconFruit.setEffect(removeColor);
        else
            iconFruit.setEffect(null);
        //Super Ball Icon
        if (!gameManager.getElementActive(ElementType.SUPER_BALL))
            iconSuperBall.setEffect(removeColor);
        else
            iconSuperBall.setEffect(null);
        //Blinky Icon
        if (!gameManager.getEntityActive(EntityType.BLINKY))
            iconBlinky.setEffect(removeColor);
        else
            iconBlinky.setEffect(null);
        //Inky Icon
        if (!gameManager.getEntityActive(EntityType.INKY))
            iconInky.setEffect(removeColor);
        else
            iconInky.setEffect(null);
        //Pinky Icon
        if (!gameManager.getEntityActive(EntityType.PINKY))
            iconPinky.setEffect(removeColor);
        else
            iconPinky.setEffect(null);
        //Clyde Icon
        if (!gameManager.getEntityActive(EntityType.CLYDE))
            iconClyde.setEffect(removeColor);
        else
            iconClyde.setEffect(null);

        //Game Information Update
        //Score Update
        scoreInfo.setText(Integer.toString(gameManager.getPacmanPoints()));
        //Level Update
        levelInfo.setText(Integer.toString(gameManager.getCurrentLevel()));
        //Balls Remaining Update
        ballsInfo.setText(Integer.toString(gameManager.getTotalBalls()));
        //Pacman Lives Update
        livesInfo.setText(Integer.toString(gameManager.getPacmanLives()));

        //State Change Update
        if ((gameManager.getState() != GameState.INITSTATE &&
                gameManager.getState() != GameState.NORMALRUNSTATE &&
                gameManager.getState() != GameState.PAUSEDSTATE) ||
                gameManager.getMainMenuState()) {
            this.setVisible(false);
            return;
        }
        if (!gameManager.getMainMenuState()) {
            this.setVisible(true);
        }
    }

    private void drawMaze() {
        mazeContext.clearRect(0, 0, maze.getWidth(), maze.getHeight());
        for (int y=0; y < gameManager.getMazeHeight(); y++) {
            for (int x=0; x < gameManager.getMazeLength(); x++) {
                Image icon = null;
                int[] iconSize = new int[2];
                int[] iconPos = new int[2];
                Color color = switch(gameManager.getMazeElement(x,y)) {
                    case Pacman.SYMBOL -> {
                        switch (gameManager.getPacmanDirection()) {
                            case RIGHT -> icon = ImageManager.getImage("pacmanRight.png");
                            case LEFT -> icon = ImageManager.getImage("pacmanLeft.png");
                            case UP -> icon = ImageManager.getImage("pacmanUp.png");
                            case DOWN -> icon = ImageManager.getImage("pacmanDown.png");
                        }
                        iconSize = new int[]{15, 15};
                        iconPos = new int[]{2, 2};
                        yield Color.BLACK;
                    }
                    case Wall.SYMBOL -> Color.BLUEVIOLET;
                    case Ball.SYMBOL -> {
                        icon = ImageManager.getImage("ball.png");
                        iconSize = new int[]{8, 8};
                        iconPos = new int[]{5, 5};
                        yield Color.BLACK;
                    }
                    case SuperBall.SYMBOL -> {
                        icon = ImageManager.getImage("superBall.png");
                        iconSize = new int[]{19, 19};
                        iconPos = new int[]{0, 0};
                        yield Color.BLACK;
                    }
                    case Fruit.SYMBOL -> {
                        icon = ImageManager.getImage("fruit.png");
                        iconSize = new int[]{15, 15};
                        iconPos = new int[]{3, 1};
                        yield Color.BLACK;
                    }
                    case Warp.SYMBOL -> {
                        icon = ImageManager.getImage("warp.png");
                        iconSize = new int[]{10, 15};
                        iconPos = new int[]{4, 2};
                        yield Color.BLACK;
                    }
                    //case Blinky.SYMBOL -> {
                    //    icon = ImageManager.getImage("blinky.png");
                    //    iconSize = new int[]{15, 15};
                    //    iconPos = new int[]{3, 1};
                    //    yield Color.BLACK;
                    //}
                    //case Clyde.SYMBOL -> {
                    //    icon = ImageManager.getImage("clyde.png");
                    //    iconSize = new int[]{15, 15};
                    //    iconPos = new int[]{3, 1};
                    //    yield Color.BLACK;
                    //}
                    //case Inky.SYMBOL -> {
                    //    icon = ImageManager.getImage("inky.png");
                    //    iconSize = new int[]{15, 15};
                    //    iconPos = new int[]{3, 1};
                    //    yield Color.BLACK;
                    //}
                    //case Pinky.SYMBOL -> {
                    //    icon = ImageManager.getImage("pinky.png");
                    //    iconSize = new int[]{15, 15};
                    //    iconPos = new int[]{3, 1};
                    //    yield Color.BLACK;
                    //}
                    //case ' ' -> Color.WHITE; //Normal Empty Path
                    default -> Color.BLACK;
                };

                mazeContext.setFill(color);
                mazeContext.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                mazeContext.drawImage(icon, x * cellSize + iconPos[0], y * cellSize + iconPos[1], iconSize[0], iconSize[1]);
                drawGhosts();
            }
        }
    }

    private void drawGhosts() {
        //Draw Blinky
        mazeContext.drawImage(ImageManager.getImage("blinky.png"), gameManager.getEntityCord(EntityType.BLINKY)[0] * cellSize + 3, gameManager.getEntityCord(EntityType.BLINKY)[1] * cellSize + 1, 15, 15);

        //Draw Pinky
        mazeContext.drawImage(ImageManager.getImage("pinky.png"), gameManager.getEntityCord(EntityType.PINKY)[0] * cellSize + 3, gameManager.getEntityCord(EntityType.PINKY)[1] * cellSize + 1, 15, 15);

        //Draw Inky
        mazeContext.drawImage(ImageManager.getImage("inky.png"), gameManager.getEntityCord(EntityType.INKY)[0] * cellSize + 3, gameManager.getEntityCord(EntityType.INKY)[1] * cellSize + 1, 15, 15);

        //Draw Clyde
        mazeContext.drawImage(ImageManager.getImage("clyde.png"), gameManager.getEntityCord(EntityType.CLYDE)[0] * cellSize + 3, gameManager.getEntityCord(EntityType.CLYDE)[1] * cellSize + 1, 15, 15);
    }

    //Hover Help Popup ActionEven
    private void helpPopupShow(String desc) {
        popupStage.setX(MouseInfo.getPointerInfo().getLocation().getX());
        popupStage.setY(MouseInfo.getPointerInfo().getLocation().getY());
        popupMSG.setText(desc);
        popupStage.show();
    }
}