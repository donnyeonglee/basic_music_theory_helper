package basic_music_theory_helper;

import basic_music_theory_helper.controller.MainController;
import basic_music_theory_helper.view.InputView;
import basic_music_theory_helper.view.OutputView;


public class Application {
    public static void main(String[] args) {

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        MainController mainController = new MainController(inputView, outputView);
    }
}
