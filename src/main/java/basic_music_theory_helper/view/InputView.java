package basic_music_theory_helper.view;

import basic_music_theory_helper.model.ChordTonesFinder;

import java.util.Scanner;

public class InputView {
    static final String PROMPT_CHOOSE_FUNCTION = "다음 중 원하는 기능의 번호를 입력해주세요.\n0: 도움말\n1: 음정 계산기\n2: 코드 구성음 찾기";
    static final String PROMPT_INTERVAL_CALCULATOR =
            "\n===== 음정 계산기 =====\n" +
                    "두 개 음을 콤마(,)로 구분하여 입력해주세요.\n첫 번째 음이 기준음입니다.\n" +
                    "음은 알파벳(A-G) + 조표(♯ or ♭, 선택 사항) 으로 나타내주세요. (ex. A#,E♭)";
    static final String PROMPT_CHORD_TONES_FINDER = """
            
            ===== 코드 구성음 찾기 =====
            다음 예시의 형식으로 코드 이름을 입력해주세요.
            예시의 근음은 C 입니다.
            근음은 알파벳(A-G) + 조표(♯ or ♭, 선택 사항) 으로 나타내주세요.""";
    String chordNameExample;

    Scanner scanner = new Scanner(System.in);

    public String chooseFunction() {
        System.out.println(PROMPT_CHOOSE_FUNCTION);
        return scanner.nextLine();
    }

    public String enterPitchNames() {
        System.out.println(PROMPT_INTERVAL_CALCULATOR);
        return scanner.nextLine();
    }

    public String enterChordName() {
        ChordTonesFinder chordTonesFinder = new ChordTonesFinder();
        System.out.println(PROMPT_CHORD_TONES_FINDER);
        System.out.println(chordTonesFinder.exampleNames());
        return scanner.nextLine();
    }

}
