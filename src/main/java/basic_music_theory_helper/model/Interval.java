package basic_music_theory_helper.model;

import java.util.Arrays;
import java.util.List;

public enum Interval {

    dd1(1, 10, "dd1", Arrays.asList("겹감1도")),
    d1(1, 11, "d1", Arrays.asList("감1도")),
    P1(1, 12, "P1", Arrays.asList("완전1도")),
    A1(1, 1, "A1", Arrays.asList("증1도")),
    AA1(1, 2, "AA1", Arrays.asList("겹증1도")),
    dd2(2, 11, "dd2", Arrays.asList("겹감2도")),
    d2(2, 12, "d2", Arrays.asList("감2도")),
    m2(2, 1, "m2", Arrays.asList("단2도", "단9도")),
    M2(2, 2, "M2", Arrays.asList("장2도", "장9도")),
    A2(2, 3, "A2", Arrays.asList("증2도")),
    AA2(2, 4, "AA2", Arrays.asList("겹증2도")),
    dd3(3, 1, "dd3", Arrays.asList("겹감3도")),
    d3(3, 2, "d3", Arrays.asList("감3도")),
    m3(3, 3, "m3", Arrays.asList("단3도")),
    M3(3, 4, "M3", Arrays.asList("장3도")),
    A3(3, 5, "A3", Arrays.asList("증3도")),
    AA3(3, 6, "AA3", Arrays.asList("겹증3도")),
    dd4(4, 3, "dd4", Arrays.asList("겹감4도")),
    d4(4, 4, "d4", Arrays.asList("감4도")),
    P4(4, 5, "P4", Arrays.asList("완전4도", "완전11도")),
    A4(4, 6, "A4", Arrays.asList("증4도")),
    AA4(4, 7, "AA4", Arrays.asList("겹증4도")),
    dd5(5, 5, "dd5", Arrays.asList("겹감5도")),
    d5(5, 6, "d5", Arrays.asList("감5도")),
    P5(5, 7, "P5", Arrays.asList("완전5도")),
    A5(5, 8, "A5", Arrays.asList("증5도")),
    AA5(5, 9, "AA5", Arrays.asList("겹증5도")),
    dd6(6, 6, "dd6", Arrays.asList("겹감6도")),
    d6(6, 7, "d6", Arrays.asList("감6도")),
    m6(6, 8, "m6", Arrays.asList("단6도")),
    M6(6, 9, "M6", Arrays.asList("장6도", "장13도")),
    A6(6, 10, "A6", Arrays.asList("증6도")),
    AA6(6, 11, "AA6", Arrays.asList("겹증6도")),
    dd7(7, 8, "dd7", Arrays.asList("겹감7도")),
    d7(7, 9, "d7", Arrays.asList("감7도")),
    m7(7, 10, "m7", Arrays.asList("단7도")),
    M7(7, 11, "M7", Arrays.asList("장7도")),
    A7(7, 12, "A7", Arrays.asList("증7도")),
    AA7(7, 1, "AA7", Arrays.asList("겹증7도"));

    private final int degree;
    private final int halfToneDistance;
    private final String englishIntervalName;
    private final List<String> koreanIntervalNames;

    Interval(int degree, int halfToneDistance, String englishIntervalName, List<String> koreanIntervalNames) {
        this.degree = degree;
        this.halfToneDistance = halfToneDistance;
        this.englishIntervalName = englishIntervalName;
        this.koreanIntervalNames = koreanIntervalNames;
    }

    public int getDegree() {
        return degree;
    }

    public int getHalfToneDistance() {
        return halfToneDistance;
    }

    public String getEnglishIntervalName() {
        return englishIntervalName;
    }

    public List<String> getKoreanIntervalNames() {
        return koreanIntervalNames;
    }
}
