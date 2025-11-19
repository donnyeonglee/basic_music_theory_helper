package basic_music_theory_helper.controller;

import basic_music_theory_helper.model.ChordTonesFinder;
import basic_music_theory_helper.model.IntervalCalculator;
import basic_music_theory_helper.model.TabGenerator;
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
            List<List<String>> intervalNameAndChordTones = chordTonesFinder.findChordTonesFromName(inputValidator.validatedChordName());
            outputView.chordTonesFinderResult(intervalNameAndChordTones);
            if (inputValidator.validateWhetherPrintTab().equals("Y")) {
                TabGenerator tabGenerator = new TabGenerator();
                List<String> chordTones = intervalNameAndChordTones.get(1);
                int tuningTypeNum = inputValidator.validatedTuningType();
                List<List<String>> tabList = tabGenerator.generateTab(chordTones, tuningTypeNum);
                // System.out.println(tabList); 테스트 출력
                outputView.tabGeneratorResult(tabList);
            }
        }
    }
}
