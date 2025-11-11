package basic_music_theory_helper.view;

public class OutputView {

    private final String HELP_MESSAGE = " ===== 기초 음악 이론 도우미 (Basic music theory helper) =====\n\n" +
            "다음의 기능 중 하나를 선택해 사용하실 수 있습니다.\n\n" +
            "음정 계산기: 두 개의 음 이름을 입력하면 두 음 사이의 거리를 출력합니다.\n" +
            "코드 구성음 찾기: 코드 이름을 입력하면 코드의 구성음을 출력합니다.\n\n" +
            "개발자: 이돈녕\n\n" +
            "=========================================================\n";;


    public void helpMessage() {
        System.out.println(HELP_MESSAGE);
    }

    public void intervalCalculatorResult (String interval) {
        System.out.println("두 음 사이의 거리는 " + interval + "입니다.");
    }
}
