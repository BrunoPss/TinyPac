package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.data.player.Player;
import pt.isec.pa.tinypac.model.fsm.GameState;

public class Top5MenuUI extends BorderPane {
    //Internal Data
    private final GameManager gameManager;
    Button backButton;

    //Constructor
    public Top5MenuUI(GameManager gameManager) {
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
        //Title HBox
        VBox titleBox = new VBox();
        //Title
        Label title = new Label("Top 5");
        title.setPadding(new Insets(15));
        title.setStyle("-fx-font-size: 30; -fx-font-weight: bold");
        titleBox.getChildren().addAll(title);

        //Content Table
        TableView<Player> contentTable = new TableView<>();
        contentTable.setMaxHeight(200);
        contentTable.setMaxWidth(330);
        //Columns
        TableColumn<Player, String> numberColumn = new TableColumn<>("Place");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
        numberColumn.setStyle("-fx-font-size: 15");
        numberColumn.setSortable(false);
        numberColumn.setResizable(false);
        numberColumn.setMinWidth(40);
        TableColumn<Player, String> firstName = new TableColumn<>("User Name");
        firstName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        firstName.setStyle("-fx-font-size: 15");
        firstName.setSortable(false);
        firstName.setMinWidth(150);
        firstName.setResizable(false);
        TableColumn<Player, String> points = new TableColumn<>("Points");
        points.setCellValueFactory(new PropertyValueFactory<>("points"));
        points.setStyle("-fx-font-size: 15");
        points.setSortable(false);
        points.setMinWidth(60);
        points.setResizable(false);

        //Items
        //ObservableList<String> items = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        //Add Data
        contentTable.getColumns().addAll(numberColumn, firstName, points);
        for (Player p : gameManager.getTop5List())
            contentTable.getItems().add(p);

        //Back Button Box
        VBox backButtonBox = new VBox();
        backButtonBox.setAlignment(Pos.CENTER);
        backButtonBox.setPadding(new Insets(20));
        //Back Button
        backButton = new Button("Main Menu");
        backButton.setAlignment(Pos.CENTER);

        backButtonBox.getChildren().addAll(backButton);

        this.setTop(title);
        this.setCenter(contentTable);
        this.setBottom(backButtonBox);
        BorderPane.setAlignment(title, Pos.CENTER);
    }

    private void registerHandlers() {
        //Property Change Listener
        //gameManager.addPropertyChangeListener(GameManager.PROP_ELEMENTS, evt -> { update();});
        gameManager.addPropertyChangeListener(evt -> Platform.runLater(this::update));

        //Back Button ActionEvent
        backButton.setOnAction( event -> {
            gameManager.setTop5MenuState(false);
        });
    }

    private void update() {
        if (!gameManager.getTop5MenuState()) {
            this.setVisible(false);
            return;
        }
        if (gameManager.getTop5MenuState()) {
            this.setVisible(true);
        }
    }
}