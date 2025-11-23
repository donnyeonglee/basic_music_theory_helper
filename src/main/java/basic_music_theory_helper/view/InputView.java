package basic_music_theory_helper.view;

import basic_music_theory_helper.Application;
import basic_music_theory_helper.controller.MainController;
import basic_music_theory_helper.model.ChordTonesFinder;
import basic_music_theory_helper.model.TabGenerator;

import java.util.Scanner;

public class InputView {
    static final String PROMPT_CHOOSE_FUNCTION = "다음 중 원하는 기능의 번호를 입력해주세요.\n0: 도움말\n1: 음정 계산기\n2: 코드 구성음 찾기";
    static final String PROMPT_INTERVAL_CALCULATOR = """
            
            ==== 음정 계산기 =====
            두 개 음을 콤마(,)로 구분하여 입력해주세요.
            첫 번째 음이 기준음입니다.
            음은 알파벳(A-G) + 조표(# or b, 선택 사항) 으로 나타내주세요. (ex. A#,Eb)
                                                                  [뒤로 가기: b]""";
    static final String PROMPT_CHORD_TONES_FINDER = """
            
            ===== 코드 구성음 찾기 =====
            예시를 참고하여 코드 이름을 입력해주세요.
            근음은 알파벳(A-G) + 조표(# or b, 선택 사항) 으로 나타내주세요. (ex A#m7)
                                                                  [코드 예시: e]
                                                                  [뒤로 가기: b]""";
    static final String PROMPT_TAB = "\n타브를 출력하시겠습니까? (Y/N)";
    static final String PROMPT_TUNING_TYPE = "\n다음 중 튜닝 유형에 해당하는 번호를 입력해주세요.";
    static final String PROMPT_EXIT_OR_RESTART_PROGRAM = """
            
            실행이 완료되었습니다.
                                                                [다시 시작: r]
                                                                [종료: Enter]""";

    Scanner scanner = new Scanner(System.in);

    public String chooseFunction() {
        System.out.println(PROMPT_CHOOSE_FUNCTION);
        return scanner.nextLine();
    }

    public String enterPitchNames() {
        System.out.println(PROMPT_INTERVAL_CALCULATOR);
        String inputPitchName = scanner.nextLine().trim();
        return inputPitchName;
    }

    public String enterChordName() {
        ChordTonesFinder chordTonesFinder = new ChordTonesFinder();
        System.out.println(PROMPT_CHORD_TONES_FINDER);
        String inputChordName = scanner.nextLine().trim();
        return inputChordName;
    }

    public String enterWhetherPrintTab() {
        System.out.println(PROMPT_TAB);
        return scanner.nextLine();
    }

    public String enterTuningType() {
        TabGenerator tabGenerator = new TabGenerator();
        System.out.println(PROMPT_TUNING_TYPE);
        System.out.println(tabGenerator.showTuningTypes());
        return scanner.nextLine();
    }

    public boolean restartOrExit() {
        System.out.println(PROMPT_EXIT_OR_RESTART_PROGRAM);
        String inputKey = scanner.nextLine().trim();
        if (inputKey.equals("r")) { // 다시 시작
            return true;
        }
        return false;
    }
}
