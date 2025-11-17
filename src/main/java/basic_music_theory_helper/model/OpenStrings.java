package basic_music_theory_helper.model;

import java.util.Arrays;
import java.util.List;

public enum OpenStrings {
    STANDARD_TUNING("Standard Tuning", "E-A-D-G-B-E", Arrays.asList("E", "A", "D", "G", "B", "E")),
    HALF_STEP_DOWN_TUNING("Half-Step Down Tuning", "E♭-A♭-D♭-G♭-B♭-E♭", Arrays.asList("E♭", "A♭", "D♭", "G♭", "B♭", "E♭")),
    DROP_D_TUNING("Drop D Tuning", "D-A-D-G-B-E", Arrays.asList("D", "A", "D", "G", "B", "E")),
    DROP_CSHARP_TUNING("Drop C# Tuning", "C#-A♭-D♭-G♭-B♭-E♭", Arrays.asList("C#", "A♭", "D♭", "G♭", "B♭", "E♭"));

    private final String tuningName;
    private final String tuningForm;
    private final List<String> openStringNotes;

    OpenStrings(String tuningName, String tuningForm, List<String> openStringNotes) {
        this.tuningName = tuningName;
        this.tuningForm = tuningForm;
        this.openStringNotes = openStringNotes;
    }

    public String getTuningName() {
        return tuningName;
    }

    public String getTuningForm() {
        return tuningForm;
    }

    public List<String> getOpenStringNotes() {
        return openStringNotes;
    }
}
