package pt.isec.pa.tinypac.ui.gui.resources;

import javafx.scene.media.Media;
import pt.isec.pa.tinypac.ui.gui.resources.presets.MusicPreset;

import java.io.File;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Media Manager Class
 * <p>Class that represents the Media Manager</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class MediaManager {
    //Internal Data
    private static List<String> playList = new ArrayList<>();
    private static int currentSong = 0;
    private static Random rand = new Random();
    private static final HashMap<String, Media> medias = new HashMap<String, Media>();

    //Constructor
    private MediaManager() {}

    //Get Methods


    //Set Methods


    //Methods

    /**
     * Loads a Playlist
     * @param musicPreset music preset
     */
    public static void loadPlaylist(MusicPreset musicPreset) {
        if (!playList.isEmpty()) {
            playList.clear();
        }
        File path = new File(Objects.requireNonNull(MediaManager.class.getResource("music/" + musicPreset)).getPath());
        File[] fileList = path.listFiles();
        for (File file : fileList) {
            playList.add(file.getName());
        }
        Collections.shuffle(playList, rand);
    }

    /**
     * Gets the media
     * @param musicPreset music preset
     * @return Media Object
     */
    public static Media getMedia(MusicPreset musicPreset) {
        Media media = medias.get(playList.get(currentSong));

        if (media == null) {
            try {
                media = new Media(Objects.requireNonNull(MediaManager.class.getResource("music/" + musicPreset + "/" + playList.get(currentSong))).toURI().toString());
                medias.put(playList.get(currentSong), media);
            } catch (URISyntaxException e) {
                return null;
            }
        }
        return media;
    }

    /**
     * Plays the next song in the playlist
     * @param musicPreset music preset
     */
    public static void nextSong(MusicPreset musicPreset) {
        if (currentSong < playList.size()-1) {
            currentSong++;
        }
        else {
            System.out.println("LIMIT");
            currentSong = 0;
            loadPlaylist(musicPreset);
        }
    }

    //Overrides


    //Internal Functions


}