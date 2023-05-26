package pt.isec.pa.tinypac.ui.gui.resources.presets;

public enum ColorPreset {
    COLOR_PRESET1, COLOR_PRESET2, COLOR_PRESET3;

    @Override
    public String toString() {
        return switch (this) {
            case COLOR_PRESET1 -> "Color Preset 1";
            case COLOR_PRESET2 -> "Color Preset 2";
            case COLOR_PRESET3 -> "Color Preset 3";
        };
    }
}