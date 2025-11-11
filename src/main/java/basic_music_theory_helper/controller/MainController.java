package basic_music_theory_helper.controller;

import basic_music_theory_helper.model.IntervalCalculator;
import basic_music_theory_helper.view.InputValidator;
import basic_music_theory_helper.view.InputView;
import basic_music_theory_helper.view.OutputView;

import java.util.List;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        InputValidator inputValidator = new InputValidator();
        int functionNum = inputValidator.validatedFunctionNum();
        if (functionNum == 0) {
            while (functionNum == 0) {
                outputView.helpMessage();
                functionNum = inputValidator.validatedFunctionNum();
            }
        }
        if (functionNum == 1) {
            IntervalCalculator intervalCalculator = new IntervalCalculator();
            intervalCalculator.calculate(inputValidator.validatedPitchNames());
        }
        if (functionNum == 2) {
            System.out.println("2 입력. 코드 구성음 찾기 실행"); // 테스트 출력.
        }
    }
}
