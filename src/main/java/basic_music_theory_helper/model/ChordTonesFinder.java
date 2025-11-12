package basic_music_theory_helper.model;

import java.util.List;

public class ChordTonesFinder {
    String chordName;

    public String exampleNames() {
        String exampleChordNames = "";
        String type = "";
        Chord[] chords = Chord.values();
        for (Chord chord : chords) {
            if (!type.equals(chord.getType())) {
                exampleChordNames = exampleChordNames + "\n===== " + chord.getType() + " =====\n";
            }
            type = chord.getType();
            exampleChordNames = exampleChordNames +
                    chord.getChordName() + " :" + generateChordNames(chord.getSuffixList()) + "\n";
        }
        return exampleChordNames;
    }

    public void validateChordName(String inputChordName) {
        this.chordName = inputChordName;
    }

    private String generateChordNames(List<String> suffixList) {
        String chordNames = "";
        for (String suffix : suffixList) {
            chordNames = chordNames + " C" + suffix + ",";
        }
        return chordNames.replaceAll(",$", ""); // 마지막 구분자 제거
    }

    public String getChordName() {
        return chordName;
    }
}
