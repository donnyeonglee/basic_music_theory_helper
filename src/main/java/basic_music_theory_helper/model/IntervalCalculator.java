package basic_music_theory_helper.model;

import basic_music_theory_helper.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalCalculator {
    static final int PROPER_PITCH_COUNT = 2;
    static final int HEPTATONIC_COUNT = 7;
    static final int HALF_TONE_COUNT = 12;

    List<String> pitchNames = new ArrayList<String>();

    OutputView outputView = new OutputView();

    public void validatePitchNames(String inputPitchNames) {
        String separator = ",";
        pitchNames = Arrays.asList(inputPitchNames.split(separator));
        validateInputCount(pitchNames);
        for (String pitchName : pitchNames) {
            validateSinglePitchName(pitchName.trim());
        }
    }

    public String calculate(List<String> inputPitchNames) {
        String firstNote = inputPitchNames.get(0).trim();
        String secondNote = inputPitchNames.get(1).trim();
        int degree = findHeptatonicPosition(secondNote) - findHeptatonicPosition(firstNote) + 1;
        int halfToneDistance = findHalfTonePosition(secondNote) - findHalfTonePosition(firstNote); // 반음 개수
        if (degree <= 0) {
            degree += HEPTATONIC_COUNT;
        }
        if (halfToneDistance <= 0) {
            halfToneDistance += HALF_TONE_COUNT;
        }
        //System.out.println(firstNote + "와 " + secondNote + "사이의 거리는 " + degree + "도, 반음거리는 " + halfToneDistance); // 테스트 출력
        return findInterval(degree, halfToneDistance);
    }

    private void validateSinglePitchName(String pitchName) {
        if (!pitchName.matches("[A-G]{1}+[#♭]?")) { // {1}: 정확히 1번 반복. ?: 앞의 문자가 한 번 나오거나 나오지 않음.
            throw new IllegalArgumentException("[ERROR] 음이름을 알파벳(A-G), 혹은 알파벳(A-G)과 #, ♭의 조합으로 입력해주세요.\n");
        }
    }

    private void validateInputCount(List<String> pitchNames) {
        if (pitchNames.size() != PROPER_PITCH_COUNT) {
            throw new IllegalArgumentException("[ERROR] 두 개의 음을 입력해주세요.\n");
        }
    }

    private int findHeptatonicPosition(String note) {
        Pitch[] pitches = Pitch.values();
        for (Pitch pitch : pitches) {
            if (note.equals(pitch.getPitchName())) {
                return pitch.getHeptaTonicPosition();
            }
        }
        throw new RuntimeException("[ERROR] 도수 계산 오류");
    }

    public int findHalfTonePosition(String note) {
        Pitch[] pitches = Pitch.values();
        for (Pitch pitch : pitches) {
            if (note.equals(pitch.getPitchName())) {
                return pitch.getHalfTonePosition();
            }
        }
        throw new RuntimeException("[ERROR] 반음거리 계산 오류");
    }

    private String findInterval(int degree, int halfToneDistance) {
        Interval[] intervals = Interval.values();
        //System.out.println("degree : " + degree + " halfToneDistance : " + halfToneDistance); // 테스트 출력
        for (Interval interval : intervals) {
            if (degree == interval.getDegree() && halfToneDistance == interval.getHalfToneDistance()) {
                return interval.getKoreanIntervalName();
            }
        }
        throw new RuntimeException("[ERROR] 음정 계산 오류");
    }

    public List<String> getPitchNames() {
        return pitchNames;
    }
}
