package basic_music_theory_helper.controller;

import basic_music_theory_helper.model.IntervalCalculator;
import basic_music_theory_helper.view.InputValidator;
import basic_music_theory_helper.view.InputView;

import java.util.List;

public class MainController {
    private final InputView inputView;

    public MainController(InputView inputView) {
        this.inputView = inputView;
        InputValidator inputValidator = new InputValidator();
        int functionNum = inputValidator.validatedFunctionNum();
        if (functionNum == 0) {
            System.out.println("0 입력. 도움말 출력"); // 테스트 출력. 도움말 넣기.
        }
        if (functionNum == 1) {
            System.out.println("1 입력. 음정 계산기 실행"); // 테스트 출력.
            IntervalCalculator intervalCalculator = new IntervalCalculator(inputValidator.validatedPitchNames());
            System.out.println(intervalCalculator.getPitchNames()); // 테스트 출력.
        }
        if (functionNum == 2) {
            System.out.println("2 입력. 코드 구성음 찾기 실행"); // 테스트 출력.
        }

    }
}
