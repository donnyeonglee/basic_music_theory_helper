package basic_music_theory_helper.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChordTonesFinder {

    static final int HALF_TONE_COUNT = 12;

    public String exampleNames() { // 예시 코드명 생성
        String exampleChordNames = "\n======================= 코드 이름 예시 (근음 : C) =======================";
        String type = "";
        String indent = "        ";
        Chord[] chords = Chord.values();
        for (Chord chord : chords) {
            if (!type.equals(chord.getType())) {
                exampleChordNames = exampleChordNames + "\n" + indent + "=== " + chord.getType() + " ===\n";
            }
            type = chord.getType();
            exampleChordNames = exampleChordNames + indent + "- " +
                    chord.getChordName() + " :" + generateChordNames(chord.getSuffixList()) + "\n";
        }
        return exampleChordNames.replaceAll("\n$", "") + "\n=================================================================";
    }

    public String validatedChordName(String inputChordName) { // 올바른 코드명 입력인지 검증
        String inputChordSuffix = inputChordName.trim().replaceAll("^[A-G]{1}+[#♭]?", "");
        String root = inputChordName.replace(inputChordSuffix, "");
        Chord[] chords = Chord.values();
        for (Chord chord : chords) {
            List<String> suffixList = chord.getSuffixList();
            if (suffixList.contains(inputChordSuffix) && !root.isEmpty()) {
                return inputChordName.trim();
            }
        }
        throw new IllegalArgumentException("[ERROR] 잘못된 코드 이름입니다.");
    }

    public List<List<String>> findChordTonesFromName(String validatedInputChordName) { // 코드명에서 구성음 찾기
        String suffix = validatedInputChordName.replaceAll("^[A-G]{1}+[#♭]?", "");
        String root = validatedInputChordName.replace(suffix, "");
        return findChordTonesFromRootAndSuffix(root, suffix);
    }

    private List<List<String>> findChordTonesFromRootAndSuffix(String root, String suffix) {
        Chord[] chords = Chord.values();
        for (Chord chord : chords) { // 코드 명에 맞는 반음계 구성과 음정을 가져옴
            if (chord.getSuffixList().contains(suffix)) {
                String halfTones = chord.getHalfTones();
                List<String> intervalNames = chord.getIntervalNames();
                return findChordTonesFromRootAndHalfTones(root, halfTones, intervalNames);
            }
        }
        throw new RuntimeException("[ERROR] 반음거리 가져오기 오류");
    }

    private String generateChordNames(List<String> suffixList) {
        String chordNames = "";
        for (String suffix : suffixList) {
            chordNames = chordNames + " C" + suffix + ",";
        }
        return chordNames.replaceAll(",$", ""); // 마지막 구분자 제거
    }

    private List<List<String>> findChordTonesFromRootAndHalfTones(String root, String halfTones, List<String> intervalNames) {
        int rootPosition = getHalfTonePositionFromPitchName(root);
        return findChordTonesFromRootPositionAndHalfTones(root, rootPosition, halfTones, intervalNames);
    }

    private int getHalfTonePositionFromPitchName(String root) {
        Pitch[] pitches = Pitch.values(); // 근음의 반음 위치 계산
        for (Pitch pitch : pitches) {
            if (root.equals(pitch.getPitchName())) {
                return pitch.getHalfTonePosition();
            }
        }
        throw new RuntimeException("[ERROR] 근음 탐색 오류");
    }

    private List<List<String>> findChordTonesFromRootPositionAndHalfTones(String root, int rootPosition, String halfTones, List<String> intervalNames) {
        List<String> chordTones = new ArrayList<>();
        chordTones.add(root); // 구성음에 근음 추가. 근음이 natural note가 아닌 경우 그대로 출력
        for (int num = 0; num < halfTones.length(); num++) {
            int halfToneDistanceFromRoot = Integer.parseInt(halfTones.substring(num, num + 1).replace("X", "10").replace("N", "11")); // 각 구성음과 근음의 반음 거리
            int halfTonePosition = ((rootPosition + halfToneDistanceFromRoot - 1) % HALF_TONE_COUNT) + 1; // 각 구성음의 반음 위치
            String intervalName = intervalNames.get(num);
            if (num != 0) {
                chordTones.add(findPitchFromHalfTonePosition(root, halfTonePosition, intervalName)); // 근음 이외의 구성음 추가
            }
        }
        return Arrays.asList(intervalNames, chordTones);
    }

    private String findPitchFromHalfTonePosition(String root, int halfTonePosition, String intervalName) {
        List<String> enharmonicChordTone = new ArrayList<>(); // 이명동음 음이름 리스트
        Pitch[] pitches = Pitch.values();
        for (Pitch pitch : pitches) {
            if (halfTonePosition == pitch.getHalfTonePosition()) {
                enharmonicChordTone.add(pitch.getPitchName());
            }
        }
        return chordToneFromEnharmonicPitches(root, enharmonicChordTone, intervalName);
    }

    private String chordToneFromEnharmonicPitches(String root, List<String> enharmonicChordTone, String intervalName) {
        String chordTone = "";
        String natural = getNaturalNote(enharmonicChordTone); // 이명동음 1순위: 조표가 붙지 않은 이름
        if (!natural.isEmpty()) {
            chordTone = natural;
            return chordTone;
        }
        String chordIntervalNote = getChordIntervalNote(root, enharmonicChordTone, intervalName); // 이명동음 2순위: 코드 구성음의 음정 이름과 근음과의 음정이 동일한 음이름
        if (!chordIntervalNote.isEmpty()) {
            chordTone = chordIntervalNote;
            return chordTone;
        }
        chordTone = getAllNotes(enharmonicChordTone); // 이명동음 3순위: 이외의 경우
        return chordTone;
    }

    private String getNaturalNote(List<String> enharmonicChordTone) {
        String naturalNote = "";
        for (String note : enharmonicChordTone) {
            if (note.matches("^[A-G]$")) {
                naturalNote = note;
            }
        }
        return naturalNote;
    }

    private String getChordIntervalNote(String root, List<String> enharmonicChordTone, String intervalName) {
        String intervalNameForEachNote = "";
        IntervalCalculator intervalCalculator = new IntervalCalculator();
        for (String note : enharmonicChordTone) {
            List<String> noteNames = Arrays.asList(root, note);
            intervalNameForEachNote = intervalCalculator.calculate(noteNames);
            if (intervalNameForEachNote.equals(intervalName)) {
                return note;
            }
        }
        return "";
    }

    private String getAllNotes(List<String> enharmonicChordTone) {
        String allNotes = "";
        for (String note : enharmonicChordTone) {
            allNotes += allNotes + note + "=";
        }
        return allNotes.replaceAll("=$", "");
    }
}
