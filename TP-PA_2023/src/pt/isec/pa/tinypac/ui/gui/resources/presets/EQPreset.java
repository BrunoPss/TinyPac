package pt.isec.pa.tinypac.ui.gui.resources.presets;

public enum EQPreset {
    EQ_PRESET1, EQ_PRESET2, EQ_PRESET3;

    @Override
    public String toString() {
        return switch (this) {
            case EQ_PRESET1 -> "EQ Preset 1";
            case EQ_PRESET2 -> "EQ Preset 2";
            case EQ_PRESET3 -> "EQ Preset 3";
        };
    }
}