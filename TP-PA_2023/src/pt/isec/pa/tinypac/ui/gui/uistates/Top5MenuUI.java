package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.data.player.Player;
import pt.isec.pa.tinypac.model.fsm.GameState;

public class Top5MenuUI extends BorderPane {
    //Internal Data
    private final GameManager gameManager;

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
        //Title
        Label title = new Label("Top 5");

        //Content Table
        TableView<Player> contentTable = new TableView<>();
        contentTable.setMaxHeight(300);
        contentTable.setMaxWidth(500);
        //Columns
        TableColumn<Player, String> numberColumn = new TableColumn<>("Place");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
        TableColumn<Player, String> firstName = new TableColumn<>("User Name");
        firstName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        TableColumn<Player, String> points = new TableColumn<>("Points");
        points.setCellValueFactory(new PropertyValueFactory<>("points"));

        //Items
        //ObservableList<String> items = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        //Add Data
        contentTable.getColumns().addAll(numberColumn, firstName, points);
        for (Player p : gameManager.getTop5List())
            contentTable.getItems().add(p);

        this.setTop(title);
        this.setCenter(contentTable);
    }

    private void registerHandlers() {
        //Property Change Listener
        //gameManager.addPropertyChangeListener(GameManager.PROP_ELEMENTS, evt -> { update();});
        gameManager.addPropertyChangeListener(evt -> Platform.runLater(this::update));
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