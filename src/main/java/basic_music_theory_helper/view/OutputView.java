package basic_music_theory_helper.view;

import java.util.List;

public class OutputView {

    private final String HELP_MESSAGE = " ===== 기초 음악 이론 도우미 (Basic music theory helper) =====\n\n" +
            "다음의 기능 중 하나를 선택해 사용하실 수 있습니다.\n\n" +
            "음정 계산기: 두 개의 음 이름을 입력하면 두 음 사이의 거리를 출력합니다.\n" +
            "코드 구성음 찾기: 코드 이름을 입력하면 코드의 구성음을 출력합니다.\n\n" +
            "개발자: 이돈녕\n\n" +
            "=========================================================\n";
    private final int STRING_COUNT = 6;
    private final int TAB_COUNT_PER_LINE = 15;

    public void helpMessage() {
        System.out.println(HELP_MESSAGE);
    }

    public void intervalCalculatorResult(String interval) {
        System.out.println("두 음 사이의 거리는 " + interval + "입니다.");
    }

    public void chordTonesFinderResult(List<List<String>> intervalNameAndChordTones) {
        List<String> intervalNames = intervalNameAndChordTones.get(0);
        List<String> chordTones = intervalNameAndChordTones.get(1);
        System.out.println();
        for (int num = 0; num < intervalNames.size(); num++) {
            String intervalName = intervalNames.get(num);
            String chordTone = chordTones.get(num);
            System.out.println(intervalName + " : " + chordTone);
        }
    }

    public void tabGeneratorResult(List<List<String>> tabList) {
        int chordFormCount = tabList.size();
        int chunks = (int) Math.ceil((double) chordFormCount / TAB_COUNT_PER_LINE);
        // System.out.println("코드폼 개수 : " + chordFormCount + " chunk 수 : " + chunks); // 테스트 출력
        for (int i = 0; i < chunks; i ++) {
            int start = i * TAB_COUNT_PER_LINE;
            int end = (i + 1) * TAB_COUNT_PER_LINE;
            if (end > chordFormCount) {
                end = chordFormCount;
            }
            List<List<String>> chunkedTabList = tabList.subList(start, end);
            printChunkedTabList(chunkedTabList);
        }

    }

    private void printChunkedTabList(List<List<String>> chunkedTabList) {
        for (int i = 0; i < STRING_COUNT; i++) {
            String singleLine = "";
            for (List<String> singleTab : chunkedTabList) {
                singleLine += singleTab.get(i) + "     ";
            }
            System.out.println(singleLine); // 각 줄에 해당하는 타브 출력
        }
        System.out.println(); // 라인 구분
    }
}
