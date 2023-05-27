package pt.isec.pa.tinypac.ui.gui.uistates;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.data.ball.Ball;
import pt.isec.pa.tinypac.model.data.pacman.Pacman;
import pt.isec.pa.tinypac.model.data.wall.Wall;
import pt.isec.pa.tinypac.model.fsm.GameState;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

public class MainGameUI extends BorderPane {
    //Internal Data
    private final GameManager gameManager;
    Button arrowLEFT, arrowUP, arrowDOWN, arrowRIGHT, pauseBtn;
    CheckBox muteButton;

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
        //Maze Dimensions
        int mazeWidth = 800;
        int mazeHeight = 550;
        int cellSize = 15;

        //Main Pane
        SplitPane mainPane = new SplitPane();
        //Maze Panel
        Canvas maze = new Canvas(mazeWidth,mazeHeight);
        GraphicsContext mazeContext = maze.getGraphicsContext2D();
        mazeContext.setFill(Color.BLACK);
        mazeContext.fillRect(0, 0, maze.getWidth(), maze.getHeight());
        Group group = new Group(maze);
        //Draw Maze
        for (int y=0; y < gameManager.getMazeHeight(); y++) {
            for (int x=0; x < gameManager.getMazeLength(); x++) {
                //System.out.println("ELEMT: " + gameManager.getMazeElement(14,12) + x + " " + y);
                Color color = switch(gameManager.getMazeElement(x,y)) {
                    case Pacman.SYMBOL -> Color.YELLOW;
                    case Wall.SYMBOL -> Color.BLUE;
                    case Ball.SYMBOL -> Color.LIGHTYELLOW;
                    default -> Color.BLACK;
                };

                mazeContext.setFill(color);
                mazeContext.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }

        //Second Panel
        VBox secondPanel = new VBox();
        secondPanel.setAlignment(Pos.BOTTOM_CENTER);
        secondPanel.setSpacing(20);
        //Score Panel
        VBox scorePanel = new VBox();
        Label scoreLabel = new Label("Score");
        //...
        //Separator
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
        scorePanel.getChildren().addAll(scoreLabel);
        optionsPanel.getChildren().addAll(optionsLabel, btnsPanel, controlsPanel);
        secondPanel.getChildren().addAll(scorePanel,separator1, optionsPanel);
        mainPane.getItems().addAll(maze, secondPanel);
        this.setCenter(mainPane);
    }

    private void registerHandlers() {
        //Property Change Listener
        gameManager.addPropertyChangeListener( evt -> { update();});

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
}