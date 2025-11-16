package basic_music_theory_helper.model;

import java.util.Arrays;
import java.util.List;

public enum Chord {
    MAJOR_TRIAD(Arrays.asList("", "M", "maj"), "메이저 코드", "3화음", "047", Arrays.asList("완전1도", "장3도", "완전5도")),
    MINOR_TRIAD(Arrays.asList("m", "min", "-"), "마이너 코드", "3화음", "037", Arrays.asList("완전1도", "단3도", "완전5도")),
    AUGMENTED_TRIAD(Arrays.asList("aug", "+"), "오그먼트 코드", "3화음", "048", Arrays.asList("완전1도", "장3도", "증5도")),
    DIMINISHED_TRIAD(Arrays.asList("dim"), "디미니쉬 코드", "3화음", "036", Arrays.asList("완전1도", "단3도", "감5도")),
    DOMINANT_SEVENTH(Arrays.asList("7", "Mm7", "majm7", "maj♭7"), "도미넌트 세븐스 코드", "7th 코드", "047X", Arrays.asList("완전1도", "장3도", "완전5도", "단7도")),
    MAJOR_SEVENTH(Arrays.asList("M7", "Ma7", "maj7"), "메이저 세븐스 코드", "7th 코드", "047N", Arrays.asList("완전1도", "장3도", "완전5도", "장7도")),
    MINOR_MAJOR_SEVENTH(Arrays.asList("mM7", "-M7", "minmaj7"), "마이너 메이저 세븐스 코드", "7th 코드", "037N", Arrays.asList("완전1도", "단3도", "완전5도", "장7도")),
    MINOR_SEVENTH(Arrays.asList("m7", "-7", "min7"), "마이너 세븐스 코드", "7th 코드", "037X", Arrays.asList("완전1도", "단3도", "완전5도", "단7도")),
    AUGMENTED_MAJOR_SEVENTH(Arrays.asList("+M7", "augmaj7", "augM7"), "오그먼트 메이저 세븐스 코드", "7th 코드", "048N", Arrays.asList("완전1도", "장3도", "증5도", "장7도")),
    AUGMENTED_SEVENTH(Arrays.asList("+7", "aug7"), "오그먼트 세븐스 코드", "7th 코드", "048X", Arrays.asList("완전1도", "장3도", "증5도", "단7도")),
    HALF_DIMINISHED_SEVENTH(Arrays.asList("m7dim5"), "하프 디미니쉬 세븐스 코드", "7th 코드", "036X", Arrays.asList("완전1도", "단3도", "감5도", "단7도")),
    DIMINISHED_SEVENTH(Arrays.asList("dim7"), "디미니쉬 세븐스 코드", "7th 코드", "0369", Arrays.asList("완전1도", "단3도", "감5도", "감7도")),
    DOMINANT_SEVENTH_FLAT_FIVE(Arrays.asList("7dim5"), "도미넌트 세븐스 플랫 파이브 코드", "7th 코드", "046X", Arrays.asList("완전1도", "장3도", "감5도", "단7도"));
    /*
    MAJOR_TRIAD(Arrays.asList("", "M", "maj"), "메이저 코드", "3화음", "047"),
    MINOR_TRIAD(Arrays.asList("m", "min", "-"), "마이너 코드", "3화음", "037"),
    AUGMENTED_TRIAD(Arrays.asList("aug", "+"), "오그먼트 코드", "3화음", "048"),
    DIMINISHED_TRIAD(Arrays.asList("dim"), "디미니쉬 코드", "3화음", "036"),
    DOMINANT_SEVENTH(Arrays.asList("7", "Mm7", "majm7", "maj♭7"), "도미넌트 세븐스 코드", "7th 코드", "047X"),
    MAJOR_SEVENTH(Arrays.asList("M7", "Ma7", "maj7"), "메이저 세븐스 코드", "7th 코드", "047N"),
    MINOR_MAJOR_SEVENTH(Arrays.asList("mM7", "-M7", "minmaj7"), "마이너 메이저 세븐스 코드", "7th 코드", "037N"),
    MINOR_SEVENTH(Arrays.asList("m7", "-7", "min7"), "마이너 세븐스 코드", "7th 코드", "037X"),
    AUGMENTED_MAJOR_SEVENTH(Arrays.asList("+M7", "augmaj7", "augM7"), "오그먼트 메이저 세븐스 코드", "7th 코드", "048N"),
    AUGMENTED_SEVENTH(Arrays.asList("+7", "aug7"), "오그먼트 세븐스 코드", "7th 코드", "048X"),
    HALF_DIMINISHED_SEVENTH(Arrays.asList("m7dim5"), "하프 디미니쉬 세븐스 코드", "7th 코드", "036X"),
    DIMINISHED_SEVENTH(Arrays.asList("dim7"), "디미니쉬 세븐스 코드", "7th 코드", "0369"),
    DOMINANT_SEVENTH_FLAT_FIVE(Arrays.asList("7dim5"), "도미넌트 세븐스 플랫 파이브 코드", "7th 코드", "046X");

     */

    private final List<String> suffixList;
    private final String chordName;
    private final String type;
    private final String halfTones;
    private final List<String> intervalNames;

    Chord(List<String> suffixList, String chordName, String type, String halfTones, List<String> intervalNames) {
        this.suffixList = suffixList;
        this.chordName = chordName;
        this.type = type;
        this.halfTones = halfTones;
        this.intervalNames = intervalNames;
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

    public List<String> getIntervalNames() {
        return intervalNames;
    }
}
