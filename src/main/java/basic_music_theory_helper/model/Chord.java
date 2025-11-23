package basic_music_theory_helper.model;

import java.util.Arrays;
import java.util.List;

public enum Chord {
    MAJOR_TRIAD(Arrays.asList("", "M", "maj"), "메이저 코드", "3화음", "047", Arrays.asList("근음", "장3도", "완전5도")),
    MINOR_TRIAD(Arrays.asList("m", "min", "-"), "마이너 코드", "3화음", "037", Arrays.asList("근음", "단3도", "완전5도")),
    AUGMENTED_TRIAD(Arrays.asList("aug", "+"), "오그먼트 코드", "3화음", "048", Arrays.asList("근음", "장3도", "증5도")),
    DIMINISHED_TRIAD(Arrays.asList("dim"), "디미니쉬 코드", "3화음", "036", Arrays.asList("근음", "단3도", "감5도")),
    DOMINANT_SEVENTH(Arrays.asList("7", "Mm7", "majm7", "majb7"), "도미넌트 세븐스 코드", "7th 코드", "047X", Arrays.asList("근음", "장3도", "완전5도", "단7도")),
    MAJOR_SEVENTH(Arrays.asList("M7", "Ma7", "maj7"), "메이저 세븐스 코드", "7th 코드", "047N", Arrays.asList("근음", "장3도", "완전5도", "장7도")),
    MINOR_MAJOR_SEVENTH(Arrays.asList("mM7", "-M7", "minmaj7"), "마이너 메이저 세븐스 코드", "7th 코드", "037N", Arrays.asList("근음", "단3도", "완전5도", "장7도")),
    MINOR_SEVENTH(Arrays.asList("m7", "-7", "min7"), "마이너 세븐스 코드", "7th 코드", "037X", Arrays.asList("근음", "단3도", "완전5도", "단7도")),
    AUGMENTED_MAJOR_SEVENTH(Arrays.asList("+M7", "augmaj7", "augM7"), "오그먼트 메이저 세븐스 코드", "7th 코드", "048N", Arrays.asList("근음", "장3도", "증5도", "장7도")),
    AUGMENTED_SEVENTH(Arrays.asList("+7", "aug7"), "오그먼트 세븐스 코드", "7th 코드", "048X", Arrays.asList("근음", "장3도", "증5도", "단7도")),
    HALF_DIMINISHED_SEVENTH(Arrays.asList("m7dim5"), "하프 디미니쉬 세븐스 코드", "7th 코드", "036X", Arrays.asList("근음", "단3도", "감5도", "단7도")),
    DIMINISHED_SEVENTH(Arrays.asList("dim7"), "디미니쉬 세븐스 코드", "7th 코드", "0369", Arrays.asList("근음", "단3도", "감5도", "감7도")),
    DOMINANT_SEVENTH_FLAT_FIVE(Arrays.asList("7dim5"), "도미넌트 세븐스 플랫 파이브 코드", "7th 코드", "046X", Arrays.asList("근음", "장3도", "감5도", "단7도")),
    MAJOR_NINTH(Arrays.asList("M9", "maj9"), "메이저 나인스 코드", "9th 코드", "047N2", Arrays.asList("근음", "장3도", "완전5도", "장7도", "장9도")),
    DOMINANT_NINTH(Arrays.asList("9"), "도미넌트 나인스 코드", "9th 코드", "047X2", Arrays.asList("근음", "장3도", "완전5도", "단7도", "장9도")),
    DOMINANT_MINOR_NINTH(Arrays.asList("7b9"), "도미넌트 마이너 나인스 코드", "9th 코드", "047X1", Arrays.asList("근음", "장3도", "완전5도", "단7도", "단9도")),
    MINOR_MAJOR_NINTH(Arrays.asList("mM9", "-M9", "minmaj9"), "마이너 메이저 나인스 코드", "9th 코드", "037N2", Arrays.asList("근음", "단3도", "완전5도", "장7도", "장9도")),
    MINOR_NINTH(Arrays.asList("m9", "-9", "min9"), "마이너 나인스 코드", "9th 코드", "037X2", Arrays.asList("근음", "단3도", "완전5도", "단7도", "장9도")),
    AUGMENTED_MAJOR_NINTH(Arrays.asList("+M9", "augmaj9"), "오그먼트 메이저 나인스 코드", "9th 코드", "048N2", Arrays.asList("근음", "장3도", "증5도", "장7도", "장9도")),
    AUGMENTED_DOMINANT_NINTH(Arrays.asList("+9", "aug9"), "오그먼트 도미넌트 나인스 코드", "9th 코드", "048X2", Arrays.asList("근음", "장3도", "증5도", "단7도", "장9도")),
    HALF_DIMINISHED_NINTH(Arrays.asList("m9b5", "m7b5(9)"), "하프 디미니쉬 나인스 코드", "9th 코드", "036X2", Arrays.asList("근음", "단3도", "감5도", "단7도", "장9도")),
    HALF_DIMINISHED_MINOR_NINTH(Arrays.asList("m7b5b9"), "하프 디미니쉬 마이너 나인스 코드", "9th 코드", "036X1", Arrays.asList("근음", "단3도", "감5도", "단7도", "단9도")),
    DIMINISHED_NINTH(Arrays.asList("dim9"), "디미니쉬 나인스 코드", "9th 코드", "03692", Arrays.asList("근음", "단3도", "감5도", "감7도", "장9도")),
    DIMINISHED_MINOR_NINTH(Arrays.asList("dimb9"), "디미니쉬 마이너 나인스 코드", "9th 코드", "03691", Arrays.asList("근음", "단3도", "감5도", "감7도", "단9도")),
    ELEVENTH(Arrays.asList("11"), "일레븐스 코드", "11th 코드", "047X25", Arrays.asList("근음", "장3도", "완전5도", "단7도", "장9도", "완전11도")),
    MAJOR_ELEVENTH(Arrays.asList("M11", "maj11"), "메이저 일레븐스 코드", "11th 코드", "047N25", Arrays.asList("근음", "장3도", "완전5도", "장7도", "장9도", "완전 11도")),
    MINOR_MAJOR_ELEVENTH(Arrays.asList("mM11", "-M11", "minmaj11"), "마이너 메이저 일레븐스 코드", "11th 코드", "037N25", Arrays.asList("근음", "단3도", "완전5도", "장7도", "장9도", "완전11도")),
    MINOR_ELEVENTH(Arrays.asList("m11", "-11", "min11"), "마이너 일레븐스 코드", "11th 코드", "037X25", Arrays.asList("근음", "단3도", "완전5도", "단7도", "장9도", "완전11도")),
    AUGMENTED_MAJOR_ELEVENTH(Arrays.asList("+M11", "augmaj11"), "오그먼트 메이저 일레븐스 코드", "11th 코드", "048N25", Arrays.asList("근음", "장3도", "증5도", "장7도", "장9도", "완전11도")),
    AUGMENTED_ELEVENTH(Arrays.asList("+11", "aug11"), "오그먼트 일레븐스 코드", "11th 코드", "048X25", Arrays.asList("근음", "장3도", "증5도", "단7도", "장9도", "완전11도")),
    HALF_DIMINISHED_ELEVENTH(Arrays.asList("m11b5", "m7b5(11)"), "하프 디미니쉬 일레븐스 코드", "11th 코드", "036X25", Arrays.asList("근음", "단3도", "감5도", "단7도", "장9도", "완전11도")),
    DIMINISHED_ELEVENTH(Arrays.asList("dim11"), "디미니쉬 일레븐스 코드", "11th 코드", "036925", Arrays.asList("근음", "단3도", "감5도", "감7도", "장9도", "완전11도")),
    MAJOR_THIRTEENTH(Arrays.asList("M13", "maj13"), "메이저 서틴스 코드", "13th 코드", "047N259", Arrays.asList("근음", "장3도", "완전5도", "장7도", "장9도", "완전 11도", "장13도")),
    THIRTEENTH(Arrays.asList("13"), "서틴스 코드", "13th 코드", "047X259", Arrays.asList("근음", "장3도", "완전5도", "단7도", "장9도", "완전11도", "장13도")),
    MINOR_MAJOR_THIRTEENTH(Arrays.asList("mM13", "-M13", "minmaj13"), "마이너 메이저 서틴스 코드", "13th 코드", "037N259", Arrays.asList("근음", "단3도", "완전5도", "장7도", "장9도", "완전11도", "장13도")),
    MINOR_THIRTEENTH(Arrays.asList("m13", "-13", "min13"), "마이너 서틴스 코드", "13th 코드", "037X259", Arrays.asList("근음", "단3도", "완전5도", "단7도", "장9도", "완전11도", "장13도")),
    AUGMENTED_MAJOR_THIRTEENTH(Arrays.asList("+M13", "augmaj13"), "오그먼트 메이저 서틴스 코드", "13th 코드", "048N259", Arrays.asList("근음", "장3도", "증5도", "장7도", "장9도", "완전11도", "장13도")),
    AUGMENTED_THIRTEENTH(Arrays.asList("+13", "aug13"), "오그먼트 서틴스 코드", "13th 코드", "048X259", Arrays.asList("근음", "장3도", "증5도", "단7도", "장9도", "완전11도", "장13도")),
    HALF_DIMINISHED_THIRTEENTH(Arrays.asList("m13b5", "m7b5(13)"), "하프 디미니쉬 서틴스 코드", "13th 코드", "036X259", Arrays.asList("근음", "단3도", "감5도", "단7도", "장9도", "완전11도", "장13도")),
    MAJOR_SIXTH(Arrays.asList("6", "M6", "maj6"), "메이저 식스 코드", "6th 코드", "0479", Arrays.asList("근음", "장3도", "완전5도", "장6도")),
    MINOR_SIXTH(Arrays.asList("m6", "mM6"), "마이너 식스 코드", "6th 코드", "0379", Arrays.asList("근음", "단3도", "완전5도", "장6도")),
    MINOR_FLAT_SIXTH(Arrays.asList("mb6"), "마이너 플랫 식스 코드", "6th 코드", "0378", Arrays.asList("근음", "단3도", "완전5도", "단6도")),
    ADD_SECOND(Arrays.asList("add2"), "애드 투 코드", "add 코드", "0247", Arrays.asList("근음", "장2도", "장3도", "완전5도")),
    ADD_FOURTH(Arrays.asList("add4"), "애드 포 코드", "add 코드", "0457", Arrays.asList("근음", "장3도", "완전4도", "완전5도")),
    MINOR_ADD_SECOND(Arrays.asList("madd2", "minadd2"), "마이너 애드 투 코드", "add 코드", "0237", Arrays.asList("근음", "장2도", "단3도", "완전5도")),
    MINOR_ADD_FOURTH(Arrays.asList("madd4", "minadd4"), "마이너 애드 포 코드", "add 코드", "0357", Arrays.asList("근음", "단3도", "완전4도", "완전5도")),
    SUSPENDED_SECOND(Arrays.asList("sus2"), "서스 투 코드", "sus 코드", "027", Arrays.asList("근음", "장2도", "완전5도")),
    DOMINANT_SEVENTH_SUSPENDED_SECOND(Arrays.asList("7sus2"), "도미넌트 세븐스 서스 투 코드", "sus 코드", "027X", Arrays.asList("근음", "장2도", "완전5도", "단7도")),
    SUSPENDED_FOURTH(Arrays.asList("sus4", "sus"), "서스 포 코드", "sus 코드", "057", Arrays.asList("근음", "완전4도", "완전5도")),
    DOMINANT_SEVENTH_SUSPENDED_FOURTH(Arrays.asList("7sus4"), "도미넌트 세븐스 서스 포 코드", "sus 코드", "057X", Arrays.asList("근음", "완전4도", "완전5도", "단7도")),
    DOMINANT_NINTH_SUSPENDED_FOURTH(Arrays.asList("9sus4"), "도미넌트 나인스 서스 포 코드", "sus 코드", "057X2", Arrays.asList("근음", "완전4도", "완전5도", "단7도", "장9도"));

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
