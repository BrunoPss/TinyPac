package pt.isec.pa.tinypac.ui.gui.resources;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.HashMap;

/**
 * Image Manager Class
 * <p>Class that represents the Image Manager</p>
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public class ImageManager {
    //Internal Data
    private static final HashMap<String, Image> images = new HashMap<String, Image>();

    //Constructor
    private ImageManager() {}

    //Get Methods


    //Set Methods


    //Methods

    /**
     * Gets an Image
     * @param filename file name
     * @return Image object
     */
    public static Image getImage(String filename) {
        Image image = images.get(filename);
        if (image == null)
            try (InputStream is = ImageManager.class.getResourceAsStream("images/" + filename)) {
                image = new Image(is);
                images.put(filename, image);
            } catch (Exception e) {
                return null;
            }
        return image;
    }
    public static Image getExternalImage(String filename) {
        Image image = images.get(filename);
        if (image == null)
            try {
                image = new Image(filename);
                images.put(filename, image);
            } catch (Exception e) {
                return null;
            }
        return image;
    }
    public static void purgeImage(String filename) { images.remove(filename); }

    //Overrides


    //Internal Functions


}