package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.data.ball.Ball;
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

public class MainGameUI extends BorderPane {
    //Internal Data
    private final GameManager gameManager;
    private final int mazeWidth = 800, mazeHeight = 550;
    private final int cellSize = 18;
    private Canvas maze;
    private GraphicsContext mazeContext;
    private Button arrowLEFT, arrowUP, arrowDOWN, arrowRIGHT, pauseBtn;
    private CheckBox muteButton;

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
        maze = new Canvas(mazeWidth,mazeHeight);
        mazeContext = maze.getGraphicsContext2D();
        mazeContext.setFill(Color.BLACK);
        mazeContext.fillRect(0, 0, maze.getWidth(), maze.getHeight());
        Group group = new Group(maze);
        //Draw Maze
        drawMaze();

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
        System.out.println("update");
        //Pause Button Update
        pauseBtn.setDisable(gameManager.getState() != GameState.NORMALRUNSTATE);

        //Mute Button Update
        muteButton.setSelected(gameManager.getMuted());

        //Maze Update
        drawMaze();

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
                        icon = ImageManager.getImage("fruit2.png");
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
                    case Blinky.SYMBOL -> {
                        icon = ImageManager.getImage("blinky.png");
                        iconSize = new int[]{15, 15};
                        iconPos = new int[]{3, 1};
                        yield Color.BLACK;
                    }
                    case Clyde.SYMBOL -> {
                        icon = ImageManager.getImage("");
                        iconSize = new int[]{15, 15};
                        iconPos = new int[]{3, 1};
                        yield Color.BLACK;
                    }
                    case Inky.SYMBOL -> {
                        icon = ImageManager.getImage("inky.png");
                        iconSize = new int[]{15, 15};
                        iconPos = new int[]{3, 1};
                        yield Color.BLUEVIOLET;
                    }
                    case Pinky.SYMBOL -> {
                        icon = ImageManager.getImage("pinky.png");
                        iconSize = new int[]{15, 15};
                        iconPos = new int[]{3, 1};
                        yield Color.PINK;
                    }
                    //case ' ' -> Color.WHITE; //Normal Empty Path
                    default -> Color.BLACK;
                };

                mazeContext.setFill(color);
                mazeContext.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                mazeContext.drawImage(icon, x * cellSize + iconPos[0], y * cellSize + iconPos[1], iconSize[0], iconSize[1]);
            }
        }
    }
}