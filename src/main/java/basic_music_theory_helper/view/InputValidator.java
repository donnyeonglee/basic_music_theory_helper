package basic_music_theory_helper.view;

public class InputValidator {
    static final int  MIN_FUNCTION_NUM = 0;
    static final int MAX_FUNCTION_NUM = 2;

    InputView inputView = new InputView();
    String inputFunctionNum = inputView.chooseFunction();

    public int validatedFunctionNum() {
        int functionNum = -1;
        while (true) {
            try {
                functionNum = StringToInteger(inputFunctionNum.trim());
                validateRange(functionNum, MIN_FUNCTION_NUM, MAX_FUNCTION_NUM);
                return functionNum;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                inputFunctionNum = inputView.chooseFunction();
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
