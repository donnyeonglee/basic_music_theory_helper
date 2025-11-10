package basic_music_theory_helper.view;

import java.util.Scanner;

public class InputView {
    static final String PROMPT_CHOOSE_FUNCTION = "다음 중 원하는 기능의 번호를 입력해주세요.\n0: 도움말\n1: 음정 계산기\n2: 코드 구성음 찾기";
    static final String PROMPT_INTERVAL_CALCULATOR = "두 개 음을 콤마(,)로 구분하여 입력해주세요.\n첫 번째 음이 기준음입니다.\n" +
            "음은 알파벳 + 조표(♯ or ♭, 선택 사항) 으로 나타내주세요. (ex. A#,E♭)";

    Scanner scanner = new Scanner(System.in);

    public String chooseFunction() {
        System.out.println(PROMPT_CHOOSE_FUNCTION);
        String inputFunctionNum = scanner.nextLine();
        return inputFunctionNum;
    }
}
