package pt.isec.pa.tinypac.ui.gui.resources.presets;

import javafx.scene.media.AudioEqualizer;
import javafx.scene.media.EqualizerBand;

public enum EQPreset {
    NONE, ACOUSTIC, ELECTRONIC, LATIN, PIANO, POP, ROCK, BASS_BOOSTER, HIGH_BOOSTER;

    public static void loadPreset(EQPreset preset, AudioEqualizer equalizer) {
        switch (preset) {
            case NONE -> {
                equalizer.setEnabled(false);
                for (EqualizerBand band : equalizer.getBands()) {
                    band.setGain(0);
                }
            }
            case ACOUSTIC -> {
                equalizer.setEnabled(true);
                setEQValues(new int[]{5,5,4,1,2,2,4,5,4,2}, equalizer);
            }
            case ELECTRONIC -> {
                equalizer.setEnabled(true);
                setEQValues(new int[]{5,4,2,0,-2,2,1,2,4,5}, equalizer);
            }
            case LATIN -> {
                equalizer.setEnabled(true);
                setEQValues(new int[]{5,3,0,0,-2,-2,-2,0,3,5}, equalizer);
            }
            case PIANO -> {
                equalizer.setEnabled(true);
                setEQValues(new int[]{3,2,0,2,3,1,4,5,3,4}, equalizer);
            }
            case POP -> {
                equalizer.setEnabled(true);
                setEQValues(new int[]{-2,-1,0,2,4,4,2,0,-1,-2}, equalizer);
            }
            case ROCK -> {
                equalizer.setEnabled(true);
                setEQValues(new int[]{5,4,3,2,-1,-2,1,2,4,5}, equalizer);
            }
            case BASS_BOOSTER -> {
                equalizer.setEnabled(true);
                setEQValues(new int[]{6,8,7,6,4,0,0,0,0,0}, equalizer);
            }
            case HIGH_BOOSTER -> {
                equalizer.setEnabled(true);
                setEQValues(new int[]{0,0,0,0,0,6,8,9,8,6}, equalizer);
            }
        }
    }

    //Overrides
    @Override
    public String toString() {
        return switch (this) {
            case NONE -> "None";
            case ACOUSTIC -> "Acoustic";
            case ELECTRONIC -> "Electronic";
            case LATIN -> "Latin";
            case PIANO -> "Piano";
            case POP -> "Pop";
            case ROCK -> "Rock";
            case BASS_BOOSTER -> "Bass Booster";
            case HIGH_BOOSTER -> "High Booster";
        };
    }

    //Private Functions
    private static void setEQValues(int[] values, AudioEqualizer equalizer) {
        equalizer.getBands().get(0).setGain(values[0]);
        equalizer.getBands().get(1).setGain(values[1]);
        equalizer.getBands().get(2).setGain(values[2]);
        equalizer.getBands().get(3).setGain(values[3]);
        equalizer.getBands().get(4).setGain(values[4]);
        equalizer.getBands().get(5).setGain(values[5]);
        equalizer.getBands().get(6).setGain(values[6]);
        equalizer.getBands().get(7).setGain(values[7]);
        equalizer.getBands().get(8).setGain(values[8]);
        equalizer.getBands().get(9).setGain(values[9]);
    }
}