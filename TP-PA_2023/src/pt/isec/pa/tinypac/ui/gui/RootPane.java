package pt.isec.pa.tinypac.ui.gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.MediaManager;
import pt.isec.pa.tinypac.ui.gui.uistates.GameEndUI;
import pt.isec.pa.tinypac.ui.gui.uistates.MainGameUI;
import pt.isec.pa.tinypac.ui.gui.uistates.MainMenuUI;
import pt.isec.pa.tinypac.ui.gui.uistates.PauseMenuUI;

import java.net.URISyntaxException;
import java.util.Objects;

public class RootPane extends BorderPane {
    //Internal Data
    GameManager gameManager;
    MediaPlayer mPlayer;

    //Constructor
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
                new MainGameUI(gameManager),
                new PauseMenuUI(gameManager),
                new GameEndUI(gameManager)
        );

        //Audio Player
        mPlayer = null;

        mPlayer = new MediaPlayer(Objects.requireNonNull(MediaManager.getMedia(gameManager.getMusicPreset())));

        //Background Configuration

        //Align Configuration
        this.setCenter(stackPane);
    }
    private void registerHandlers() {
        //Property ChangeListener
        gameManager.addPropertyChangeListener( evt -> { update();});

        //Music End ActionEvent
        mPlayer.setOnEndOfMedia(this::nextSong);
    }
    private void nextSong() {
        System.out.println("END SONG");
        MediaManager.nextSong();
        mPlayer = new MediaPlayer(Objects.requireNonNull(MediaManager.getMedia(gameManager.getMusicPreset())));
        mPlayer.play();
    }
    private void update() {
        //Music Play Update
        if (gameManager.getMusicPlayStatus()) {
            mPlayer.play();
        }
        else {
            mPlayer.pause();
        }
    }
}