package basic_music_theory_helper.view;

import basic_music_theory_helper.model.IntervalCalculator;

import java.util.List;

public class InputValidator {
    static final int  MIN_FUNCTION_NUM = 0;
    static final int MAX_FUNCTION_NUM = 2;

    InputView inputView = new InputView();

    public int validatedFunctionNum() {
        int functionNum = -1;
        while (true) {
            String inputFunctionNum = inputView.chooseFunction();
            try {
                functionNum = StringToInteger(inputFunctionNum.trim());
                validateRange(functionNum, MIN_FUNCTION_NUM, MAX_FUNCTION_NUM);
                return functionNum;
            } catch (Exception e) {
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int StringToInteger(String inNum) {
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
}
