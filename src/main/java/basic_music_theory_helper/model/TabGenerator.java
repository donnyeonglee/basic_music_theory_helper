package basic_music_theory_helper.model;

import java.util.*;
import java.util.stream.Collectors;

public class TabGenerator {

    private final int STRING_COUNT = 6;
    private final int FRET_COUNT = 24;
    private final int SLIDING_WINDOW_SIZE = 5;
    private final int HIGHEST_ROOT_STRING = 3; // 3번 줄까지 근음 탐색
    private final int HIGHEST_ROOT_FRET = 12; // 12번 프렛까지 근음 탐색

    List<List<String>> tabList = new ArrayList<>();
    List<List<String>> chordTonesInTabOrder = new ArrayList<>();

    public String showTuningTypes() {
        String tuningTypes = "";
        int num = 1;
        OpenStrings[] openStrings = OpenStrings.values();
        for (OpenStrings tuningType : openStrings) {
            tuningTypes += num + ": " + tuningType.getTuningName() + " (" + tuningType.getTuningForm() + ")\n";
            num ++;
        }
        return tuningTypes.replaceAll("\n$","");
    }

    public List<List<String>> generateTab(List<String> chordTones, int tuningTypeNum) {
        String root = chordTones.getFirst();
        List<String> openStringNotes = getOpenStringNotes(tuningTypeNum);
        List<List<List<String>>> fretBoard = generateFretBoard(openStringNotes);
        List<List<Integer>> rootPositions = findRootPosition(root, fretBoard);
        List<List<List<Integer>>> candidateChordPositions = findChordTonePositions(chordTones, fretBoard, rootPositions);
        //System.out.println("후보 위치 : " + candidateChordPositions); // 테스트 출력
        List<List<List<Integer>>> allCombinations = new ArrayList<>();
        for (List<List<Integer>> singleChordPosition : candidateChordPositions) {
            List<List<List<Integer>>> singleChordPositionCombinations = buildCombinations(singleChordPosition);
            //System.out.println(singleChordPositionCombinations); // 테스트 출력
            //System.out.println(); // 테스트 출력
            for ( List<List<Integer>> singleCombination : singleChordPositionCombinations) {
                allCombinations.add(singleCombination);
            }
        }
        //System.out.println(allCombinations); // 테스트 출력
        List<List<List<Integer>>> nonRedundantCombinations = allCombinations.stream().distinct().collect(Collectors.toList()); // 리스트 중복 제거
        //System.out.println("리스트 : " + nonRedundantCombinations); // 테스트 출력
        List<List<List<Integer>>> validatedChordPositions = new ArrayList<>();
        for (List<List<Integer>> nonRedundantCombination : nonRedundantCombinations) {
            if (validateCombination(nonRedundantCombination, chordTones, fretBoard)) {
                validatedChordPositions.add(nonRedundantCombination);
                //System.out.println("최종코드폼: " + nonRedundantCombination); // 테스트 출력
            }
        }
        assignChordTonesInTabOrder(validatedChordPositions, fretBoard, chordTones);
        tabList = generateTabList(validatedChordPositions, chordTonesInTabOrder);
        System.out.println("\n" + getTuningType(tuningTypeNum));
        System.out.println("코드 블록 사이즈 : " + SLIDING_WINDOW_SIZE + "프렛");
        System.out.println("근음 위치 : " + STRING_COUNT + " ~ " + HIGHEST_ROOT_STRING + " 번 줄, 0 ~ " + HIGHEST_ROOT_FRET + "프렛");
        return tabList;
    }

    private List<String> getOpenStringNotes(int tuningTypeNum) {
        OpenStrings[] openStrings = OpenStrings.values();
        for (OpenStrings openString : openStrings) {
            if (openString.getTuningTypeNum() == tuningTypeNum) {
                return openString.getOpenStringNotes();
            }
        }
        throw new RuntimeException("[ERROR] 튜닝값 가져오기 오류");
    }

    private List<List<List<String>>> generateFretBoard(List<String> openStringNotes) { // 2차원 리스트 생성
        IntervalCalculator intervalCalculator = new IntervalCalculator();
        List<List<List<String>>> fretBoard = new ArrayList<>();
        for (int i = 0; i < STRING_COUNT; i++) {
            List<List<String>> singleString = new ArrayList<>();
            String openStringNote = openStringNotes.get(i);
            int openStringHalfTonePosition = intervalCalculator.findHalfTonePosition(openStringNote);
            for (int j = 0; j < FRET_COUNT; j ++) {
                List<String> singleFret = new ArrayList<>(); // 이명동음을 포함한 음이름 리스트
                int halfTonePosition = (openStringHalfTonePosition + j - 1) % 12 + 1;
                Pitch[] pitches = Pitch.values();
                for (Pitch pitch : pitches) {
                    if (halfTonePosition == pitch.getHalfTonePosition()) {
                        singleFret.add(pitch.getPitchName());
                    }
                }
                singleString.add(singleFret);
            }
            fretBoard.add(singleString);
        }
        return fretBoard;
    }

    private List<List<Integer>> findRootPosition(String root, List<List<List<String>>> fretBoard) {
        List<List<Integer>> rootPositions = new ArrayList<>();
        for (int i = 0; i < STRING_COUNT - HIGHEST_ROOT_STRING + 1; i++) { // 3, 4, 5, 6번 줄에서 근음 탐색
            for (int j = 0; j < HIGHEST_ROOT_FRET; j ++) { // 근음은 반복되므로 12프렛 이내에서 탐색
                if (fretBoard.get(i).get(j).contains(root)) {
                    rootPositions.add(Arrays.asList(i,j));
                }
            }
        }
        return rootPositions;
    }

    private List<List<List<Integer>>> findChordTonePositions(List<String> chordTones, List<List<List<String>>> fretBoard, List<List<Integer>> rootPositions) {
        List<List<List<Integer>>> candidateChordPositons = new ArrayList<>();
        for (List<Integer> rootPosition : rootPositions) {
            int rootString = rootPosition.getFirst();
            int rootFret = rootPosition.getLast();
            int minFret = rootFret - SLIDING_WINDOW_SIZE + 1;
            if (minFret <0) {
                minFret = 0;
            }
            int maxFret = rootFret + SLIDING_WINDOW_SIZE - 1;
            if (maxFret > FRET_COUNT) {
                maxFret = FRET_COUNT;
            }
            for (int i = minFret; i < maxFret - SLIDING_WINDOW_SIZE + 1; i ++) { // sliding window에 따른 최소 프렛 이동
                List<List<Integer>> candidateChordPositionsForSingleWindow = new ArrayList<>();
                candidateChordPositionsForSingleWindow.add(Arrays.asList(rootString, rootFret));
                for (int k = 0; k < STRING_COUNT; k++) { // 줄 이동
                    for (int j = i; j < i + SLIDING_WINDOW_SIZE; j++) { // sliding window별 탐색
                        for (String chordTone : chordTones) {
                            if (fretBoard.get(k).get(j).contains(chordTone) && k != rootString) { // 근음이 지정되어 있는 현은 제외하고 탐색
                                candidateChordPositionsForSingleWindow.add(Arrays.asList(k, j));
                            }
                        }
                    }
                }
                candidateChordPositons.add(candidateChordPositionsForSingleWindow);

            }
        }
        return candidateChordPositons;
    }

    private List<List<List<Integer>>> buildCombinations(List<List<Integer>> singleChordPosition) {
        List<List<List<Integer>>> strings = new ArrayList<>();
        for (int i = 0; i < STRING_COUNT; i++) {
            strings.add(new ArrayList<>()); // 각 줄 별 리스트 생성
        }

        for (List<Integer> position : singleChordPosition) {
            int stringIndex = position.getFirst();
            strings.get(stringIndex).add(position);
        }

        List<List<List<Integer>>> combinations = new ArrayList<>();
        backtrack(0, strings, new ArrayList<>(), combinations);
        return combinations;
    }

    private void backtrack(
            int stringIdx,
            List<List<List<Integer>>> strings,
            List<List<Integer>> current,
            List<List<List<Integer>>> result
    ) {
        // 6줄 모두 처리 → 현재 선택한 조합을 결과로 추가
        if (stringIdx == 5) {
            // 마지막 줄(5번줄)도 고려해야 함
            if (!strings.get(5).isEmpty()) {
                for (List<Integer> pos : strings.get(5)) {
                    current.add(pos);
                    result.add(new ArrayList<>(current));
                    current.remove(current.size() - 1);
                }
            } else {
                // 5번줄이 없다면 바로 저장
                result.add(new ArrayList<>(current));
            }
            return;
        }

        // 후보가 없는 줄 → 그냥 넘긴다
        if (strings.get(stringIdx).isEmpty()) {
            backtrack(stringIdx + 1, strings, current, result);
            return;
        }

        // 후보가 여러 개라면 각각 선택하여 재귀
        for (List<Integer> pos : strings.get(stringIdx)) {
            current.add(pos);
            backtrack(stringIdx + 1, strings, current, result);
            current.remove(current.size() - 1);
        }
    }

    private boolean validateCombination(List<List<Integer>> nonRedundantCombination, List<String> chordTones, List<List<List<String>>> fretBoard) {
        List<String> checkChordTones = new ArrayList<>(chordTones);
        for (String chordTone : chordTones) {
            for (List<Integer> position : nonRedundantCombination) {
                int stringNum = position.get(0);
                int fretNum = position.get(1);
                List<String> noteForPosition = fretBoard.get(stringNum).get(fretNum);
                if (noteForPosition.contains(chordTone)) {
                    checkChordTones.remove(chordTone);
                }
            }
        }
        if (checkChordTones.isEmpty()) {
            return true;
        }
        return false;
    }

    private void assignChordTonesInTabOrder(List<List<List<Integer>>> validatedChordPositions, List<List<List<String>>> fretBoard, List<String> chordTones) {
        for (List<List<Integer>> validatedChordPosition : validatedChordPositions) {
            List<String> singleChordTonesInOrder = Arrays.asList("", "", "", "", "", "");

            for (List<Integer> position : validatedChordPosition) {
                int stringNum = position.get(0);
                int fretNum = position.get(1);
                for (String chordTone : chordTones) {
                    if (fretBoard.get(stringNum).get(fretNum).contains(chordTone)) {
                        singleChordTonesInOrder.set(stringNum, chordTone);
                    }
                }
            }
            //System.out.println(singleChordTonesInOrder); // 테스트 출력
            chordTonesInTabOrder.add(singleChordTonesInOrder);
        }
    }

    private List<List<String>> generateTabList(List<List<List<Integer>>> chordPositions, List<List<String>> chordTonesInOrder) {
        int chordFormCount = chordPositions.size();
        List<List<String>> tabList = new ArrayList<>();
        for (int i = 0; i < chordFormCount; i ++) {
            List<List<Integer>> chordPosition = chordPositions.get(i);
            List<String> chordTone = chordTonesInOrder.get(i);
            List<String> singleTabList = new ArrayList<>();
            for (int j = STRING_COUNT - 1; j >= 0; j--) {
                String note = chordTone.get(j) + " ".repeat(2 - chordTone.get(j).length());
                String tabPosition = "--";
                for (List<Integer> position : chordPosition) {
                    if (position.getFirst() == j) {
                        tabPosition = position.get(1) + "";
                        if (tabPosition.length() == 1) {
                            tabPosition = "-" + tabPosition;
                        }
                    }
                }
                singleTabList.add(note + "|--" + tabPosition + "---|");
            }
            tabList.add(singleTabList);
        }
        return tabList;
    }

    private String getTuningType(int tuningTypeNum) {
        OpenStrings[] openStrings = OpenStrings.values();
        String tuningName = "";
        String tuningForm = "";
        for (OpenStrings openString : openStrings) {
            if (openString.getTuningTypeNum() == tuningTypeNum) {
                tuningName = openString.getTuningName();
                tuningForm = openString.getTuningForm();
            }
        }
        return ("튜닝 : " + tuningName + " (" + tuningForm + ")");
    }
}
