package pt.isec.pa.tinypac.ui.gui.resources.presets;

public enum MusicPreset {
    MUSIC_PRESET1, MUSIC_PRESET2, MUSIC_PRESET3;

    @Override
    public String toString() {
        return switch (this) {
            case MUSIC_PRESET1 -> "Music Preset 1";
            case MUSIC_PRESET2 -> "Music Preset 2";
            case MUSIC_PRESET3 -> "Music Preset 3";
        };
    }
}