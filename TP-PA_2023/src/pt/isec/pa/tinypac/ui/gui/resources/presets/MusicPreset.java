package pt.isec.pa.tinypac.ui.gui.resources.presets;

/**
 * Music Preset Enumeration
 * <p>Enumeration that contains all Music Presets</p>
 *
 * @author Bruno Guiomar
 * @version 1.0.0
 */

public enum MusicPreset {
    /**
     * LOFI
     */
    LOFI,
    /**
     * CALM
     */
    CALM,
    /**
     * ELECTRONIC
     */
    ELECTRONIC;

    @Override
    public String toString() {
        return switch (this) {
            case LOFI -> "LoFi";
            case CALM -> "Calm";
            case ELECTRONIC -> "Electronic";
        };
    }
}