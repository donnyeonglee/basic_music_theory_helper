package basic_music_theory_helper.model;

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

    public String findChordTonesFromName(String validatedInputChordName) {
        this.chordName = validatedInputChordName;
        String suffix = chordName.replaceAll("^[A-G]{1}+[#♭]?", "");
        String root = chordName.replace(suffix, "");
        String halfTones = "-1";
        Chord[] chords = Chord.values();
        for (Chord chord : chords) {
            if (chord.getSuffixList().contains(suffix)) {
                halfTones = chord.getHalfTones();
                break;
            }
        }
        if (halfTones.equals("-1")) {
            throw new RuntimeException("[ERROR] 반음거리 가져오기 오류");
        }
        return findChordTonesFromRootAndHalfTones(root, halfTones);
    }

    private String generateChordNames(List<String> suffixList) {
        String chordNames = "";
        for (String suffix : suffixList) {
            chordNames = chordNames + " C" + suffix + ",";
        }
        return chordNames.replaceAll(",$", ""); // 마지막 구분자 제거
    }

    private String findChordTonesFromRootAndHalfTones(String root, String halfTones) {
        String intervalAndPitches = "";
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
            intervalAndPitches += findPitchFromHalfTonePosition(root, halfTonePosition);
        }
        return intervalAndPitches;
    }

    private String findPitchFromHalfTonePosition(String root, int halfTonePosition) {
        String intervalAndPitch = "";
        IntervalCalculator intervalCalculator = new IntervalCalculator();
        Pitch[] pitches = Pitch.values();
        for (Pitch pitch : pitches) {
            if (halfTonePosition == pitch.getHalfTonePosition()) {
                String singleChordTone = pitch.getPitchName();
                String interval = intervalCalculator.calculate(Arrays.asList(root, singleChordTone)).replace("완전1도", "근음");
                intervalAndPitch += interval + " : " + singleChordTone + "\n";
                break;
            }
        }
        return intervalAndPitch;
    }

    public String getChordName() {
        return chordName;
    }
}
