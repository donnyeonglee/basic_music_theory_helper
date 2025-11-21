package basic_music_theory_helper.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TabGenerator {

    private final int STRING_COUNT = 6; // 기타 줄 수
    private final int FRET_COUNT = 24; // 기타 프렛 수
    private final int SLIDING_WINDOW_SIZE = 5; // 코드 블록 범위 (프렛)
    private final int HIGHEST_ROOT_STRING = 3; // 근음을 탐색하는 가장 높은 음의 줄
    private final int HIGHEST_ROOT_FRET = 12; // 근음을 탐색하는 가장 높은 음의 프렛

    List<List<String>> tabList = new ArrayList<>();
    List<List<String>> chordTonesInTabOrder = new ArrayList<>();

    public String showTuningTypes() {
        String tuningTypes = "";
        int num = 1;
        OpenStrings[] openStrings = OpenStrings.values();
        for (OpenStrings tuningType : openStrings) {
            tuningTypes += num + ": " + tuningType.getTuningName() + " (" + tuningType.getTuningForm() + ")\n";
            num++;
        }
        return tuningTypes.replaceAll("\n$", "");
    }

    public List<List<String>> generateTab(String chordName, List<String> chordTones, int tuningTypeNum) {
        String root = chordTones.getFirst();
        List<String> openStringNotes = getOpenStringNotes(tuningTypeNum);
        List<List<List<String>>> fretBoard = generateFretBoard(openStringNotes);
        List<List<Integer>> rootPositions = findRootPosition(root, fretBoard);
        List<List<List<Integer>>> candidateChordPositions = findChordTonePositions(chordTones, fretBoard, rootPositions);
        List<List<List<Integer>>> allCombinations = buildAllCombinations(candidateChordPositions); // 모든 조합 생성
        List<List<List<Integer>>> nonRedundantCombinations = allCombinations.stream().distinct().collect(Collectors.toList()); // 리스트 중복 제거
        List<List<List<Integer>>> validatedChordPositions = validatedCombinations(nonRedundantCombinations, chordTones, fretBoard);
        assignChordTonesInTabOrder(validatedChordPositions, fretBoard, chordTones);
        tabList = generateTabList(validatedChordPositions, chordTonesInTabOrder);
        System.out.println("\n코드 : " + chordName + "\n" + getTuningInformation(tuningTypeNum) + "\n코드 블록 사이즈 : " + SLIDING_WINDOW_SIZE + "프렛");
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
            for (int j = 0; j < FRET_COUNT; j++) {
                List<String> singleFret = generateSingleFret(openStringHalfTonePosition, i, j);
                singleString.add(singleFret);
            }
            fretBoard.add(singleString);
        }
        return fretBoard;
    }

    private List<String> generateSingleFret(int openStringHalfTonePosition, int i, int j) {
        List<String> singleFret = new ArrayList<>(); // 이명동음을 포함한 음이름 리스트
        int halfTonePosition = (openStringHalfTonePosition + j - 1) % 12 + 1;
        Pitch[] pitches = Pitch.values();
        for (Pitch pitch : pitches) {
            if (halfTonePosition == pitch.getHalfTonePosition()) {
                singleFret.add(pitch.getPitchName());
            }
        }
        return singleFret;
    }

    private List<List<Integer>> findRootPosition(String root, List<List<List<String>>> fretBoard) {
        List<List<Integer>> rootPositions = new ArrayList<>();
        for (int i = 0; i < STRING_COUNT - HIGHEST_ROOT_STRING + 1; i++) { // 3, 4, 5, 6번 줄에서 근음 탐색
            for (int j = 0; j < HIGHEST_ROOT_FRET; j++) { // 근음은 반복되므로 12프렛 이내에서 탐색
                if (fretBoard.get(i).get(j).contains(root)) {
                    rootPositions.add(Arrays.asList(i, j));
                }
            }
        }
        return rootPositions;
    }

    //모든 후보 코드 포지션 생성
    private List<List<List<Integer>>> findChordTonePositions(List<String> chordTones, List<List<List<String>>> fretBoard, List<List<Integer>> rootPositions) {
        List<List<List<Integer>>> candidateChordPositons = new ArrayList<>();
        for (List<Integer> rootPosition : rootPositions) {
            int rootString = rootPosition.getFirst();
            int rootFret = rootPosition.getLast();
            int minFret = Math.max(rootFret - SLIDING_WINDOW_SIZE + 1, 0);
            int maxFret = Math.min(rootFret + SLIDING_WINDOW_SIZE - 1, FRET_COUNT);
            for (int startFret = minFret; startFret < maxFret - SLIDING_WINDOW_SIZE + 1; startFret++) { // sliding window에 따른 프렛 이동
                List<List<Integer>> candidateChordPositionsForSingleWindow = generateCandidateChordPositionsForSingleWindow(startFret, rootString, rootFret, chordTones, fretBoard);
                candidateChordPositons.add(candidateChordPositionsForSingleWindow);
            }
        }
        return candidateChordPositons;
    }

    // 단일 블록의 코드 포지션 생성
    private List<List<Integer>> generateCandidateChordPositionsForSingleWindow(int startFret, int rootString, int rootFret, List<String> chordTones, List<List<List<String>>> fretBoard) {
        List<List<Integer>> candidateChordPositionsForSingleWindow = new ArrayList<>();
        candidateChordPositionsForSingleWindow.add(Arrays.asList(rootString, rootFret));
        for (int j = 0; j < STRING_COUNT; j++) { // 줄 이동
            for (int k = startFret; k < startFret + SLIDING_WINDOW_SIZE; k++) { // sliding window별 탐색
                for (String chordTone : chordTones) {
                    if (fretBoard.get(j).get(k).contains(chordTone) && j != rootString) { // 근음이 지정되어 있는 현은 제외하고 탐색
                        candidateChordPositionsForSingleWindow.add(Arrays.asList(j, k));
                    }
                }
            }
        }
        return candidateChordPositionsForSingleWindow;
    }

    private List<List<List<Integer>>> buildAllCombinations(List<List<List<Integer>>> candidateChordPositions) {
        List<List<List<Integer>>> allCombinations = new ArrayList<>();
        for (List<List<Integer>> singleChordPosition : candidateChordPositions) {
            List<List<List<Integer>>> singleChordPositionCombinations = buildSingleBlockCombinations(singleChordPosition);
            for (List<List<Integer>> singleCombination : singleChordPositionCombinations) {
                allCombinations.add(singleCombination);
            }
        }
        return allCombinations;
    }

    private List<List<List<Integer>>> buildSingleBlockCombinations(List<List<Integer>> singleChordPosition) {
        List<List<List<Integer>>> strings = new ArrayList<>();
        for (int i = 0; i < STRING_COUNT; i++) {
            strings.add(new ArrayList<>()); // 각 줄 별 리스트 생성
        }
        for (List<Integer> position : singleChordPosition) {
            int stringIndex = position.getFirst();
            strings.get(stringIndex).add(position); // 각 줄 별 프렛 추가
        }
        List<List<List<Integer>>> combinations = new ArrayList<>();
        backtrack(0, strings, new ArrayList<>(), combinations); // 해당 블록에서 모든 조합 생성
        return combinations;
    }

    private void backtrack(int stringIdx, List<List<List<Integer>>> strings, List<List<Integer>> current,
                           List<List<List<Integer>>> result) { // 해당 블록에서 모든 조합 생성
        if (stringIdx == STRING_COUNT - 1) { // 6줄 모두 처리 시 현재 선택한 조합을 결과로 추가
            if (!strings.get(STRING_COUNT - 1).isEmpty()) { // 마지막 줄도 고려해야 함
                for (List<Integer> pos : strings.get(STRING_COUNT - 1)) {
                    current.add(pos);
                    result.add(new ArrayList<>(current));
                    current.remove(current.size() - 1);
                }
            } else {
                result.add(new ArrayList<>(current)); // 5번줄이 없다면 바로 저장
            }
            return;
        }
        if (strings.get(stringIdx).isEmpty()) { // 후보가 없는 줄 → 그냥 넘긴다
            backtrack(stringIdx + 1, strings, current, result);
            return;
        }
        for (List<Integer> pos : strings.get(stringIdx)) { // 후보가 여러 개라면 각각 선택하여 재귀
            current.add(pos);
            backtrack(stringIdx + 1, strings, current, result);
            current.remove(current.size() - 1);
        }
    }

    // 전체 조합 중 모든 코드 구성음을 가진 조합 반환
    private List<List<List<Integer>>> validatedCombinations(List<List<List<Integer>>> nonRedundantCombinations,
                                                            List<String> chordTones, List<List<List<String>>> fretBoard) {
        List<List<List<Integer>>> validatedChordPositions = new ArrayList<>();
        for (List<List<Integer>> nonRedundantCombination : nonRedundantCombinations) {
            if (validateCombination(nonRedundantCombination, chordTones, fretBoard)) {
                validatedChordPositions.add(nonRedundantCombination);
            }
        }
        return validatedChordPositions;
    }

    // 해당 조합이 모든 코드 구성음을 가지고 있는지 검증
    private boolean validateCombination(List<List<Integer>> nonRedundantCombination, List<String> chordTones,
                                        List<List<List<String>>> fretBoard) {
        List<String> unconfirmedChordTones = new ArrayList<>(chordTones);
        for (String chordTone : chordTones) {
            unconfirmedChordTones = confirmChordTone(chordTone, unconfirmedChordTones, nonRedundantCombination, fretBoard);
        }
        if (unconfirmedChordTones.isEmpty()) {
            return true;
        }
        return false;
    }

    // 해당 코드톤이 조합에 존재하면 리스트에서 삭제
    private List<String> confirmChordTone(String chordTone, List<String> unconfirmedChordTones,
                                          List<List<Integer>> nonRedundantCombination, List<List<List<String>>> fretBoard) {
        for (List<Integer> position : nonRedundantCombination) {
            int stringNum = position.get(0);
            int fretNum = position.get(1);
            List<String> noteForPosition = fretBoard.get(stringNum).get(fretNum);
            if (noteForPosition.contains(chordTone)) {
                unconfirmedChordTones.remove(chordTone);
            }
        }
        return unconfirmedChordTones;
    }

    // 타브에서 보여지는 순서대로 구성음 리스트 생성
    private void assignChordTonesInTabOrder(List<List<List<Integer>>> validatedChordPositions, List<List<List<String>>> fretBoard, List<String> chordTones) {
        for (List<List<Integer>> validatedChordPosition : validatedChordPositions) { // 모든 코드 조합 각각에 대해 실행
            List<String> singleChordTonesInOrder = Arrays.asList("", "", "", "", "", "");
            for (List<Integer> position : validatedChordPosition) { // 코드 포지션 내 각각의 자리에 대해 실행
                int stringNum = position.get(0);
                int fretNum = position.get(1);
                singleChordTonesInOrder = insertChordTones(stringNum, fretNum, fretBoard, chordTones, singleChordTonesInOrder);

            }
            chordTonesInTabOrder.add(singleChordTonesInOrder);
        }
    }

    // 각 구성음을 순서에 맞게 리스트에 삽입
    private List<String> insertChordTones(int stringNum, int fretNum, List<List<List<String>>> fretBoard, List<String> chordTones, List<String> singleChordTonesInOrder) {
        for (String chordTone : chordTones) {
            if (fretBoard.get(stringNum).get(fretNum).contains(chordTone)) {
                singleChordTonesInOrder.set(stringNum, chordTone);
            }
        }
        return singleChordTonesInOrder;
    }

    // 가능한 모든 타브를 가지는 리스트 생성
    private List<List<String>> generateTabList(List<List<List<Integer>>> chordPositions, List<List<String>> chordTonesInOrder) {
        int chordFormCount = chordPositions.size();
        List<List<String>> tabList = new ArrayList<>();
        for (int i = 0; i < chordFormCount; i++) {
            List<List<Integer>> chordPosition = chordPositions.get(i);
            List<String> chordTone = chordTonesInOrder.get(i);
            List<String> singleTabList = new ArrayList<>();
            for (int stringNum = STRING_COUNT - 1; stringNum >= 0; stringNum--) {
                String singleTabLine = tabForSingleString(chordTone, chordPosition, stringNum);
                singleTabList.add(singleTabLine);
            }
            tabList.add(singleTabList);
        }
        return tabList;
    }

    private String tabForSingleString(List<String> chordTone, List<List<Integer>> chordPosition, int stringNum) {
        String note = chordTone.get(stringNum) + " ".repeat(2 - chordTone.get(stringNum).length()); // 음이름
        String tabPosition = "--";
        for (List<Integer> position : chordPosition) {
            if (position.getFirst() == stringNum) {
                tabPosition = String.format("%2s", position.get(1) + "").replace(' ', '-'); // 2자리수가 되도록 '-' 채우기
            }
        }
        return note + "|--" + tabPosition + "---|"; // 해당 줄의 타브
    }

    // 튜닝 유형 번호로부터 튜닝 정보 가져오기
    private String getTuningInformation(int tuningTypeNum) {
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
