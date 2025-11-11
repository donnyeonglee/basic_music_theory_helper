package basic_music_theory_helper.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalCalculator {
    static final int PROPER_PITCH_COUNT = 2;
    private final String inputPitchNames;

    List<String> pitchNames = new ArrayList<String>();

    public IntervalCalculator(String inputPitchNames) {
        this.inputPitchNames = inputPitchNames;
        validatePitchNames(inputPitchNames);
    }

    private void validatePitchNames(String inputPitchNames) {
        String separator = ",";
        pitchNames = Arrays.asList(inputPitchNames.split(separator));
        validateInputCount(pitchNames);
        for (String pitchName : pitchNames) {
            validateSinglePitchName(pitchName.trim());
        }
    }

    private void validateSinglePitchName(String pitchName) {
        if (!pitchName.matches("[A-G]{1}+[#♭]?")) { // {1}: 정확히 1번 반복. ?: 앞의 문자가 한 번 나오거나 나오지 않음.
            throw new IllegalArgumentException("[ERROR] 음이름을 알파벳(A-G), 혹은 알파벳(A-G)과 #, ♭의 조합으로 입력해주세요.");
        }
    }

    private void validateInputCount(List<String> pitchNames) {
        if (pitchNames.size() != PROPER_PITCH_COUNT) {
            throw new IllegalArgumentException("[ERROR] 두 개의 음을 입력해주세요.");
        }
    }

    public String getInputPitchNames() {
        return inputPitchNames;
    }

    public List<String> getPitchNames() {
        return pitchNames;
    }

}
