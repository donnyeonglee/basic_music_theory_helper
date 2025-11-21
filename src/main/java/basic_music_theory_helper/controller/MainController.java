package basic_music_theory_helper.controller;

import basic_music_theory_helper.model.ChordTonesFinder;
import basic_music_theory_helper.model.IntervalCalculator;
import basic_music_theory_helper.model.TabGenerator;
import basic_music_theory_helper.view.InputValidator;
import basic_music_theory_helper.view.OutputView;

import java.util.List;

public class MainController {
    InputValidator inputValidator = new InputValidator();
    OutputView outputView = new OutputView();

    public MainController() {
        int functionNum = inputValidator.validatedFunctionNum();
        if (functionNum == 0) {
            functionNum = reEnteredFunctionNum();
        }
        if (functionNum == 1) {
            runIntervalCalculator();
        }
        if (functionNum == 2) {
            runChordTonesFinder();
        }
    }

    private int reEnteredFunctionNum() {
        int functionNum = 0;
        while (functionNum == 0) {
            outputView.helpMessage();
            functionNum = inputValidator.validatedFunctionNum();
        }
        return functionNum;
    }

    private void runIntervalCalculator() {
        IntervalCalculator intervalCalculator = new IntervalCalculator();
        String interval = intervalCalculator.calculate(inputValidator.validatedPitchNames());
        outputView.intervalCalculatorResult(interval);
    }

    private void runChordTonesFinder() {
        ChordTonesFinder chordTonesFinder = new ChordTonesFinder();
        List<List<String>> intervalNameAndChordTones = chordTonesFinder.findChordTonesFromName(inputValidator.validatedChordName());
        outputView.chordTonesFinderResult(intervalNameAndChordTones);
        if (inputValidator.validateWhetherPrintTab().equals("Y")) {
            TabGenerator tabGenerator = new TabGenerator();
            List<String> chordTones = intervalNameAndChordTones.get(1);
            int tuningTypeNum = inputValidator.validatedTuningType();
            List<List<String>> tabList = tabGenerator.generateTab(chordTones, tuningTypeNum);
            outputView.tabGeneratorResult(tabList);
            return;
        }
        System.out.println("\n프로그램을 종료합니다.");
    }
}
