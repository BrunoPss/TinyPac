package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioEqualizer;
import javafx.scene.media.MediaPlayer;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.MediaManager;
import pt.isec.pa.tinypac.ui.gui.resources.presets.EQPreset;
import pt.isec.pa.tinypac.ui.gui.uistates.*;

import java.net.URISyntaxException;
import java.util.Objects;

/**
 * Root Pane Class
 * <p>Class that represents the Root Pane</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class RootPane extends BorderPane {
    //Internal Data
    GameManager gameManager;
    MediaPlayer mPlayer;
    AudioEqualizer equalizer;

    //Constructor
    /**
     * Constructor
     * @param gameManager Game Manager
     */
    public RootPane(GameManager gameManager) {
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
        //StackPane Configuration
        StackPane stackPane = new StackPane(
                new MainMenuUI(gameManager),
                new Top5MenuUI(gameManager),
                new MainGameUI(gameManager),
                new PauseMenuUI(gameManager),
                new GameEndUI(gameManager)
        );

        //Audio Player
        mPlayer = null;
        MediaManager.loadPlaylist(gameManager.getMusicPreset());
        mPlayer = new MediaPlayer(Objects.requireNonNull(MediaManager.getMedia(gameManager.getMusicPreset())));
        equalizer = mPlayer.getAudioEqualizer();
        mPlayer.play();
        //Background Configuration

        //Align Configuration
        this.setCenter(stackPane);
    }
    private void registerHandlers() {
        //Property ChangeListener
        //gameManager.addPropertyChangeListener( evt -> { update();});
        gameManager.addPropertyChangeListener(evt -> Platform.runLater(this::update));

        //Music End ActionEvent
        mPlayer.setOnEndOfMedia( this::nextSong );
    }
    private void nextSong() {
        MediaManager.nextSong(gameManager.getMusicPreset());
        mPlayer = new MediaPlayer(Objects.requireNonNull(MediaManager.getMedia(gameManager.getMusicPreset())));
        equalizer = mPlayer.getAudioEqualizer();
        mPlayer.setOnEndOfMedia(this::nextSong);
        mPlayer.setVolume((double) gameManager.getMusicVolume() / 20);
        mPlayer.setMute(gameManager.getMuted());
        EQPreset.loadPreset(gameManager.getMainEQPreset(), equalizer);
        mPlayer.play();
    }
    private void update() {
        //Music Preset Update
        StringBuilder currentMediaSource = new StringBuilder(mPlayer.getMedia().getSource());
        StringBuilder mediaFolder = new StringBuilder(currentMediaSource.substring(currentMediaSource.lastIndexOf("/", currentMediaSource.lastIndexOf("/") - 1) + 1, currentMediaSource.lastIndexOf("/")));
        if (!mediaFolder.toString().equals(gameManager.getMusicPreset().toString())) {
            mPlayer.stop();
            MediaManager.loadPlaylist(gameManager.getMusicPreset());
            mPlayer = new MediaPlayer(Objects.requireNonNull(MediaManager.getMedia(gameManager.getMusicPreset())));
            equalizer = mPlayer.getAudioEqualizer();
            mPlayer.play();
        }

        //Volume Update
        mPlayer.setVolume((double) gameManager.getMusicVolume() / 20);

        //Mute Update
        mPlayer.setMute(gameManager.getMuted());

        //EQ Preset Update
        EQPreset.loadPreset(gameManager.getMainEQPreset(), equalizer);

    }
}