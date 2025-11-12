package basic_music_theory_helper.model;

public enum Pitch {

    C("C", 1, 1),
    C_SHARP("C#", 1, 2),
    D("D", 2, 3),
    D_SHARP("D#", 2, 4),
    E("E", 3, 5),
    F("F", 4, 6),
    F_SHARP("F#", 4, 7),
    G("G", 5, 8),
    G_SHARP("G#", 5, 9),
    A("A", 6, 10),
    A_SHARP("A#", 6, 11),
    B("B", 7, 12),
    C_FLAT("C♭", 1, 12),
    D_FLAT("D♭", 2, 2),
    E_FLAT("E♭", 3, 4),
    E_SHARP("E#", 3, 6),
    F_FLAT("F♭", 4, 5),
    G_FLAT("G♭", 5, 7),
    A_FLAT("A♭", 6, 9),
    B_FLAT("B♭", 7, 11),
    B_SHARP("B#", 7, 1);

    /*
    C_FLAT("C♭", 1, 12),
    C("C", 1, 1),
    C_SHARP("C#", 1, 2),
    D_FLAT("D♭", 2, 2),
    D("D", 2, 3),
    D_SHARP("D#", 2, 4),
    E_FLAT("E♭", 3, 4),
    E("E", 3, 5),
    E_SHARP("E#", 3, 6),
    F_FLAT("F♭", 4, 5),
    F("F", 4, 6),
    F_SHARP("F#", 4, 7),
    G_FLAT("G♭", 5, 7),
    G("G", 5, 8),
    G_SHARP("G#", 5, 9),
    A_FLAT("A♭", 6, 9),
    A("A", 6, 10),
    A_SHARP("A#", 6, 11),
    B_FLAT("B♭", 7, 11),
    B("B", 7, 12),
    B_SHARP("B#", 7, 1);
     */

    private final String pitchName;
    private final int heptaTonicPosition;
    private final int halfTonePosition;

    Pitch(String pitchName, int heptaTonicPosition, int halfTonePosition) {
        this.pitchName = pitchName;
        this.heptaTonicPosition = heptaTonicPosition;
        this.halfTonePosition = halfTonePosition;
    }

    public String getPitchName() {
        return pitchName;
    }

    public int getHeptaTonicPosition() {
        return heptaTonicPosition;
    }

    public int getHalfTonePosition() {
        return halfTonePosition;
    }
}
