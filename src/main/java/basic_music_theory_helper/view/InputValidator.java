package basic_music_theory_helper.view;

import basic_music_theory_helper.model.ChordTonesFinder;
import basic_music_theory_helper.model.IntervalCalculator;

import java.util.Arrays;
import java.util.List;

public class InputValidator {
    static final int  MIN_FUNCTION_NUM = 0;
    static final int MAX_FUNCTION_NUM = 2;
    static final int MIN_TUNING_TYPE_NUM = 1;
    static final int MAX_TUNING_TYPE_NUM = 4;

    InputView inputView = new InputView();

    public int validatedFunctionNum() {
        int functionNum = -1;
        while (true) {
            String inputFunctionNum = inputView.chooseFunction();
            try {
                functionNum = stringToInteger(inputFunctionNum.trim());
                validateRange(functionNum, MIN_FUNCTION_NUM, MAX_FUNCTION_NUM);
                return functionNum;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<String> validatedPitchNames() {
        while (true) {
            try {
                IntervalCalculator intervalCalculator = new IntervalCalculator();
                intervalCalculator.validatePitchNames(inputView.enterPitchNames());
                return intervalCalculator.getPitchNames();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String validatedChordName() {
        while (true) {
            try {
                ChordTonesFinder chordTonesFinder = new ChordTonesFinder();
                chordTonesFinder.validateChordName(inputView.enterChordName());
                return chordTonesFinder.getChordName();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String validateWhetherPrintTab() {
        while (true) {
            try {
                return checkYesOrNo(inputView.enterWhetherPrintTab());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int validatedTuningType() {
        while (true) {
            String inputTuningTypeNum = inputView.enterTuningType();
            try {
                int tuningTypeNum = stringToInteger(inputTuningTypeNum.trim());
                validateRange(tuningTypeNum, MIN_TUNING_TYPE_NUM, MAX_TUNING_TYPE_NUM);
                return tuningTypeNum;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int stringToInteger(String inNum) {
        int outNum = -1;
        try {
            outNum = Integer.parseInt(inNum);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 정수를 입력해주세요.");
        }
        return outNum;
    }

    private void validateRange(int num, int minNum, int maxNum) {
        if (num < minNum || num > maxNum) {
            throw new IllegalArgumentException("[ERROR] 범위를 벗어나는 입력입니다.");
        }
    }

    private String checkYesOrNo(String sign) {
        if (sign.equals("Y")) {
            return "Y";
        }
        if (sign.equals("N")) {
            return "N";
        }
        throw new IllegalArgumentException("[ERROR] Y 또는 N을 입결해 주세요.");
    }
}
