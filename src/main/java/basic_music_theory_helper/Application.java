package basic_music_theory_helper;

//import basic_music_theory_helper.view.InputView;
import basic_music_theory_helper.view.InputValidator;

public class Application {
    public static void main(String[] args) {

        InputValidator inputValidator = new InputValidator();

        int functionNum = inputValidator.validatedFunctionNum();
        System.out.println("입력한 기능 번호 : " + functionNum); // 테스트 출력
    }
}
