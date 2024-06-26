package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Platform;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.presets.ColorPreset;
import pt.isec.pa.tinypac.ui.gui.resources.presets.EQPreset;
import pt.isec.pa.tinypac.ui.gui.resources.presets.MusicPreset;

/**
 * Config Menu UI Class
 * <p>Class that represents the Config Menu UI</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class ConfigMenu extends VBox {
    //Internal Data
    private GameManager gameManager;
    private TabPane tPane;
    private ChoiceBox<ColorPreset> colorThemeSelector;
    private ChoiceBox<EQPreset> soundEqSelector;
    private ChoiceBox<MusicPreset> musicThemeSelector;
    private Slider volumeSlider;
    private Label volumeValue;
    private CheckBox muteButton;

    //Constructor
    /**
     * Constructor
     * @param gameManager Game Manager
     */
    public ConfigMenu(GameManager gameManager) {
        this.gameManager = gameManager;

        createViews();
        registerHandlers();
        update();

        Scene scene = new Scene(this, 500, 300);
        Stage window = new Stage();
        window.setTitle("Configurations");
        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }

    //Get Methods


    //Set Methods


    //Methods


    //Overrides


    //Internal Functions
    private void createViews() {
        //Tab Pane
        tPane = new TabPane();
        Tab appearenceTab = new Tab("Appearence");
        Tab soundTab = new Tab("Sound");
        tPane.getTabs().add(appearenceTab);
        tPane.getTabs().add(soundTab);
        tPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        this.getChildren().add(tPane);

        //Appearence Tab Content
        GridPane gPane = new GridPane();
        HBox themeGroup = new HBox();
        themeGroup.setSpacing(10);
        gPane.setVgap(20);
        gPane.setHgap(20);
        Label choiceInfo1 = new Label("Main Color Theme");
        colorThemeSelector = new ChoiceBox<>();
        for (ColorPreset cPreset : ColorPreset.values())
            colorThemeSelector.getItems().add(cPreset);
        colorThemeSelector.setValue(gameManager.getMainColorPreset());
        themeGroup.getChildren().addAll(choiceInfo1, colorThemeSelector);
        gPane.add(themeGroup, 1, 1);
        appearenceTab.setContent(gPane);

        //Sound Tab Content
        GridPane soundConfig = new GridPane();
        soundConfig.setVgap(20);
        soundConfig.setHgap(20);
        //Volume & Mute
        VBox volumeGroup = new VBox();
        volumeGroup.setSpacing(10);
        //Volume
        HBox volumeBox = new HBox();
        Label volumeLabel = new Label("Music Volume");
        volumeSlider = new Slider(0, 20, gameManager.getMusicVolume());
        volumeValue = new Label(Integer.toString(gameManager.getMusicVolume()));
        volumeBox.setSpacing(10);
        //Mute Button
        muteButton = new CheckBox("Mute");
        muteButton.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        //Group Add
        volumeBox.getChildren().addAll(volumeLabel, volumeSlider, volumeValue);
        volumeGroup.getChildren().addAll(volumeBox, muteButton);

        //EQ & Theme
        VBox eqThemeGroup = new VBox();
        eqThemeGroup.setSpacing(10);
        //EQ
        HBox eqBox = new HBox();
        Label eqLabel = new Label("Sound EQ Preset");
        soundEqSelector = new ChoiceBox<>();
        for (EQPreset eqPreset : EQPreset.values())
            soundEqSelector.getItems().add(eqPreset);
        soundEqSelector.setValue(gameManager.getMainEQPreset());
        eqBox.getChildren().addAll(eqLabel, soundEqSelector);
        eqBox.setSpacing(10);
        //Music Theme
        HBox musicThemeBox = new HBox();
        Label musicThemeLabel = new Label("Music Preset");
        musicThemeSelector = new ChoiceBox<>();
        for (MusicPreset mPreset : MusicPreset.values())
            musicThemeSelector.getItems().add(mPreset);
        musicThemeSelector.setValue(gameManager.getMusicPreset());
        musicThemeBox.getChildren().addAll(musicThemeLabel, musicThemeSelector);
        musicThemeBox.setSpacing(10);
        //Group Add
        eqThemeGroup.getChildren().addAll(eqBox, musicThemeBox);

        soundConfig.add(volumeGroup, 1, 1);
        soundConfig.add(eqThemeGroup, 1, 3);
        soundTab.setContent(soundConfig);
    }

    private void registerHandlers() {
        //Property Change Listener
        //gameManager.addPropertyChangeListener( evt -> { update();});
        gameManager.addPropertyChangeListener(evt -> Platform.runLater(this::update));

        //Color Theme ActionEvent
        colorThemeSelector.setOnAction(event -> {
            gameManager.setMainColorPreset(colorThemeSelector.getValue());
        });

        //Music Volume Slider Drag ActionEvent
        volumeSlider.setOnMouseDragged(event -> {
            gameManager.setMusicVolume((int) volumeSlider.getValue());
        });
        //Music Volume Slider Release ActionEvent
        volumeSlider.setOnMouseReleased(event -> {
            gameManager.setMusicVolume((int) volumeSlider.getValue());
        });

        //Music Mute ActionEvent
        muteButton.setOnAction(event -> {
            gameManager.toogleMute();
        });

        //Music Eq Preset Selector ActionEvent
        soundEqSelector.setOnAction(event -> {
            gameManager.setMainEQPreset(soundEqSelector.getValue());
        });

        //Music Theme Selector ActionEvent
        musicThemeSelector.setOnAction(event -> {
            gameManager.setMusicPreset(musicThemeSelector.getValue());
        });
    }

    private void update() {
        //Music Volume Label Update
        //Comparacao feita pelo pcs {if (!(volumeValue.getText().equals(Integer.toString(gameManager.getMusicVolume()))))}
        volumeSlider.setValue(gameManager.getMusicVolume());
        volumeSlider.setDisable(gameManager.getMuted());
        volumeValue.setText(Integer.toString(gameManager.getMusicVolume()));
        //Mute Button Update
        muteButton.setSelected(gameManager.getMuted());

        //Color Theme Update
        colorThemeSelector.setValue(gameManager.getMainColorPreset());
        //EQ Preset Update
        soundEqSelector.setValue(gameManager.getMainEQPreset());
        //Music Theme Update
        musicThemeSelector.setValue(gameManager.getMusicPreset());
    }
}