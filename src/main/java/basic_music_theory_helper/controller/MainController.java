package basic_music_theory_helper.controller;

import basic_music_theory_helper.model.ChordTonesFinder;
import basic_music_theory_helper.model.IntervalCalculator;
import basic_music_theory_helper.model.TabGenerator;
import basic_music_theory_helper.view.InputValidator;
import basic_music_theory_helper.view.InputView;
import basic_music_theory_helper.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class MainController {
    InputValidator inputValidator = new InputValidator();
    OutputView outputView = new OutputView();
    InputView inputView = new InputView();
    private boolean runComplete;

    public MainController() {
        boolean running = true;
        while (running) {
            runComplete = false;
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
            if (runComplete) {
                running = inputView.restartOrExit();
                System.out.println();
            }
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
        List<String> pitchNames = inputValidator.validatedPitchNames();
        if (pitchNames.equals(Arrays.asList("back"))) {
            System.out.println();
            return;
        }
        List<String> interval = intervalCalculator.calculate(pitchNames);
        outputView.intervalCalculatorResult(interval);
        runComplete = true;
    }

    private void runChordTonesFinder() {
        ChordTonesFinder chordTonesFinder = new ChordTonesFinder();
        String chordName = inputValidator.validatedChordName();
        while (chordName.equals("back") || chordName.equals("showExample")) {
            if (chordName.equals("back")) {
                System.out.println();
                return;
            }
            if (chordName.equals("showExample")) {
                System.out.println(chordTonesFinder.exampleNames());
                chordName = inputValidator.validatedChordName();
            }
        }
        List<List<String>> intervalNameAndChordTones = chordTonesFinder.findChordTonesFromName(chordName);
        outputView.chordTonesFinderResult(intervalNameAndChordTones);
        runComplete = true;
        if (inputValidator.validateWhetherPrintTab().equals("Y")) {
            TabGenerator tabGenerator = new TabGenerator();
            List<String> chordTones = intervalNameAndChordTones.get(1);
            int tuningTypeNum = inputValidator.validatedTuningType();
            List<List<String>> tabList = tabGenerator.generateTab(chordName, chordTones, tuningTypeNum);
            outputView.tabGeneratorResult(tabList);
        }
    }
}
