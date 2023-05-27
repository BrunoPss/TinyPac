package pt.isec.pa.tinypac.ui.gui.resources.presets;

public enum MusicPreset {
    LOFI, CALM, ELECTRONIC;

    @Override
    public String toString() {
        return switch (this) {
            case LOFI -> "LoFi";
            case CALM -> "Calm";
            case ELECTRONIC -> "Electronic";
        };
    }
}