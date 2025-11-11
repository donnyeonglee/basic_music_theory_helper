package basic_music_theory_helper.model;

public enum Interval {
    dd1(1, 10, "dd1", "겹감1도"),
    d1(1, 11, "d1", "감1도"),
    P1(1, 0, "P1", "완전1도"),
    A1(1, 1, "A1", "증1도"),
    AA1(1, 2, "AA1", "겹증1도"),
    dd2(2, 11, "dd2", "겹감2도"),
    d2(2, 0, "d2", "감2도"),
    m2(2, 1, "m2", "단2도"),
    M2(2, 2, "M2", "장2도"),
    A2(2, 3, "A2", "증2도"),
    AA2(2, 4, "AA2", "겹증2도"),
    dd3(3, 1, "dd3", "겹감3도"),
    d3(3, 2, "d3", "감3도"),
    m3(3, 3, "m3", "단3도"),
    M3(3, 4, "M3", "장3도"),
    A3(3, 5, "A3", "증3도"),
    AA3(3, 6, "AA3", "겹증3도"),
    dd4(4, 3, "dd4", "겹감4도"),
    d4(4, 4, "d4", "감4도"),
    P4(4, 5, "P4", "완전4도"),
    A4(4, 6, "A4", "증4도"),
    AA4(4, 7, "AA4", "겹증4도"),
    dd5(5, 5, "dd5", "겹감5도"),
    d5(5, 6, "d5", "감5도"),
    P5(5, 7, "P5", "완전5도"),
    A5(5, 8, "A5", "증5도"),
    AA5(5, 9, "AA5", "겹증5도"),
    dd6(6, 6, "dd6", "겹감6도"),
    d6(6, 7, "d6", "감6도"),
    m6(6, 8, "m6", "단6도"),
    M6(6, 9, "M6", "장6도"),
    A6(6, 10, "A6", "증6도"),
    AA6(6, 11, "AA6", "겹증6도"),
    dd7(7, 8, "dd7", "겹감7도"),
    d7(7, 9, "d7", "감7도"),
    m7(7, 10, "m7", "단7도"),
    M7(7, 11, "M7", "장7도"),
    A7(7, 12, "A7", "증7도"),
    AA7(7, 1, "AA7", "겹증7도");

    private final int degree;
    private final int halfToneDistance;
    private final String englishIntervalName;
    private final String koreanIntervalName;

    Interval(int degree, int halfToneDistance, String englishIntervalName, String koreanIntervalName) {
        this.degree = degree;
        this.halfToneDistance = halfToneDistance;
        this.englishIntervalName = englishIntervalName;
        this.koreanIntervalName = koreanIntervalName;
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

    public String getKoreanIntervalName() {
        return koreanIntervalName;
    }
}
