package pt.isec.pa.tinypac.ui.gui.resources;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import pt.isec.pa.tinypac.ui.gui.resources.presets.MusicPreset;

import javax.print.URIException;
import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.*;

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
    private static void loadPlaylist(MusicPreset musicPreset) {
        File path = new File(Objects.requireNonNull(MediaManager.class.getResource("music/" + musicPreset)).getPath());
        File[] fileList = path.listFiles();
        for (File file : fileList) {
            playList.add(file.getName());
        }
    }
    public static Media getMedia(MusicPreset musicPreset) {
        loadPlaylist(musicPreset);

        Collections.shuffle(playList, rand);
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
    public static void nextSong() {
        currentSong++;
    }

    //Overrides


    //Internal Functions


}