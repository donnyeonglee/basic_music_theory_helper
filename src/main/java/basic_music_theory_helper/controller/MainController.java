package basic_music_theory_helper.controller;

import basic_music_theory_helper.model.ChordTonesFinder;
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
            String interval = intervalCalculator.calculate(inputValidator.validatedPitchNames());
            outputView.intervalCalculatorResult(interval);
        }
        if (functionNum == 2) {
            ChordTonesFinder chordTonesFinder = new ChordTonesFinder();
            String chordTones = chordTonesFinder.findChordTonesFromName(inputValidator.validatedChordName());
            System.out.println(chordTones);
            if (inputValidator.validateWhetherPrintTab().equals("Y")) {
                System.out.println("타브 출력을 실행합니다."); // 테스트 출력
            }
        }
    }
}
