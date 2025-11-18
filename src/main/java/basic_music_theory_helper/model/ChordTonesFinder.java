package basic_music_theory_helper.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChordTonesFinder {
    String chordName;

    public String exampleNames() {
        String exampleChordNames = "";
        String type = "";
        Chord[] chords = Chord.values();
        for (Chord chord : chords) {
            if (!type.equals(chord.getType())) {
                exampleChordNames = exampleChordNames + "\n===== " + chord.getType() + " =====\n";
            }
            type = chord.getType();
            exampleChordNames = exampleChordNames +
                    chord.getChordName() + " :" + generateChordNames(chord.getSuffixList()) + "\n";
        }
        return exampleChordNames;
    }

    public void validateChordName(String inputChordName) {
        String inputChordSuffix = inputChordName.trim().replaceAll("^[A-G]{1}+[#♭]?", "");
        //System.out.println(inputChordSuffix);  // 테스트 출력
        Chord[] chords = Chord.values();
        for (Chord chord : chords) {
            List<String> suffixList = chord.getSuffixList();
            if (suffixList.contains(inputChordSuffix)) {
                //System.out.println("올바른 코드 이름입니다."); // 테스트 출력
                chordName = inputChordName.trim();
                return;
            }
        }
        throw new IllegalArgumentException("[ERROR] 잘못된 코드 이름입니다.");
    }

    public List<List<String>> findChordTonesFromName(String validatedInputChordName) {
        this.chordName = validatedInputChordName;
        String suffix = chordName.replaceAll("^[A-G]{1}+[#♭]?", "");
        String root = chordName.replace(suffix, "");
        String halfTones = "-1";
        List<String> intervalNames = new ArrayList<>();
        Chord[] chords = Chord.values();
        for (Chord chord : chords) {
            if (chord.getSuffixList().contains(suffix)) {
                halfTones = chord.getHalfTones();
                intervalNames = chord.getIntervalNames();
                break;
            }
        }
        if (halfTones.equals("-1") || intervalNames.isEmpty()) {
            throw new RuntimeException("[ERROR] 반음거리 가져오기 오류");
        }
        return findChordTonesFromRootAndHalfTones(root, halfTones, intervalNames);
    }

    private String generateChordNames(List<String> suffixList) {
        String chordNames = "";
        for (String suffix : suffixList) {
            chordNames = chordNames + " C" + suffix + ",";
        }
        return chordNames.replaceAll(",$", ""); // 마지막 구분자 제거
    }

    private List<List<String>> findChordTonesFromRootAndHalfTones(String root, String halfTones, List<String> intervalNames) {
        List<String> chordTones = new ArrayList<>();
        int rootPosition = -1;
        Pitch[] pitches = Pitch.values();
        for (Pitch pitch : pitches) {
            if (root.equals(pitch.getPitchName())) {
                rootPosition = pitch.getHalfTonePosition();
            }
        }
        if (rootPosition == -1) {
            throw new RuntimeException("[ERROR] 근음 탐색 오류");
        }
        for (int num = 0; num < halfTones.length(); num ++) {
            int halfToneDistanceFromRoot = Integer.parseInt(halfTones.substring(num,num + 1).replace("X", "10").replace("N", "11"));
            int halfTonePosition = rootPosition + halfToneDistanceFromRoot;
            if (halfTonePosition > 12) {
                halfTonePosition -= 12;
            }
            String intervalName = intervalNames.get(num);
            if (num == 0) {
                chordTones.add(root);
            }
            if (num != 0) {
                chordTones.add(findPitchFromHalfTonePosition(root, halfTonePosition, intervalName));
            }
        }
        if (intervalNames.size() != chordTones.size()) {
            throw new RuntimeException("[ERROR] 구성음 탐색 오류");
        }
        return Arrays.asList(intervalNames, chordTones);
    }

    private String findPitchFromHalfTonePosition(String root, int halfTonePosition, String intervalName) {
        String chordTone = "";
        List<String> enharmonicChordTone = new ArrayList<>(); // 이명동음 음이름 리스트
        Pitch[] pitches = Pitch.values();
        for (Pitch pitch : pitches) {
            if (halfTonePosition == pitch.getHalfTonePosition()) {
                enharmonicChordTone.add(pitch.getPitchName());
            }
        }
        // 이명동음 처리. 1순위: 조표가 붙지 않은 음이름. 2순위: 코드 구성음의 음정 이름과 같은 음이름. 3순위: 이외의 경우
        String natural = getNaturalNote(enharmonicChordTone); // 1순위
        if (!natural.isEmpty()) {
            chordTone = natural;
            return chordTone;
        }
        String chordIntervalNote = getChordIntervalNote(root, enharmonicChordTone, intervalName); // 2순위
        if (!chordIntervalNote.isEmpty()) {
            chordTone = chordIntervalNote;
            return chordTone;
        }
        chordTone = getAllNotes(enharmonicChordTone); // 3순위

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

    public String getChordName() {
        return chordName;
    }
}
