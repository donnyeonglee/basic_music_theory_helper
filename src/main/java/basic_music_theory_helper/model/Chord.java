package basic_music_theory_helper.model;

import java.util.Arrays;
import java.util.List;

public enum Chord {
    MAJOR_TRIAD(Arrays.asList("", "M", "maj"), "메이저 코드", "3화음", "047"),
    MINOR_TRIAD(Arrays.asList("m", "min", "-"), "마이너 코드", "3화음", "037"),
    AUGMENTED_TRIAD(Arrays.asList("aug", "+"), "오그먼트 코드", "3화음", "048"),
    DIMINISHED_TRIAD(Arrays.asList("dim"), "디미니쉬 코드", "3화음", "036"),
    DOMINANT_SEVENTH(Arrays.asList("7", "Mm7", "majm7", "maj♭7"), "도미넌트 세븐스 코드", "7th 코드", "047X"),
    MAJOR_SEVENTH(Arrays.asList("M7", "Ma7", "maj7"), "메이저 세븐스 코드", "7th 코드", "047N"),
    MINOR_MAJOR_SEVENTH(Arrays.asList("mM7", "-M7", "minmaj7"), "마이너 메이저 세븐스 코드", "7th 코드", "037N"),
    MINOR_SEVENTH(Arrays.asList("m7", "-7", "min7"), "마이너 세븐스 코드", "7th 코드", "037X"),
    AUGMENTED_MAJOR_SEVENTH(Arrays.asList("+M7", "augmaj7", "augM7" ), "오그먼트 메이저 세븐스 코드", "7th 코드", "048N"),
    AUGMENTED_SEVENTH(Arrays.asList("+7", "aug7"), "오그먼트 세븐스 코드", "7th 코드", "048X"),
    HALF_DIMINISHED_SEVENTH(Arrays.asList("m7dim5"), "하프 디미니쉬 세븐스 코드", "7th 코드", "036X"),
    DIMINISHED_SEVENTH(Arrays.asList("dim7"), "디미니쉬 세븐스 코드", "7th 코드", "0369"),
    DOMINANT_SEVENTH_FLAT_FIVE(Arrays.asList("7dim5"), "도미넌트 세븐스 플랫 파이브 코드", "7th 코드", "046X");

    private final List<String> suffixList;
    private final String chordName;
    private final String type;
    private final String halfTones;

    Chord(List<String> suffixList, String chordName, String type, String halfTones) {
        this.suffixList = suffixList;
        this.chordName = chordName;
        this.type = type;
        this.halfTones = halfTones;
    }

    public List<String> getSuffixList() {
        return suffixList;
    }

    public String getChordName() {
        return chordName;
    }

    public String getType() {
        return type;
    }

    public String getHalfTones() {
        return halfTones;
    }
}
