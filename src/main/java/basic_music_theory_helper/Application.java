package basic_music_theory_helper;

import basic_music_theory_helper.controller.MainController;
import basic_music_theory_helper.view.InputValidator;
import basic_music_theory_helper.view.InputView;

public class Application {
    public static void main(String[] args) {

        InputView inputView = new InputView();

        MainController mainController = new MainController(inputView);


    }
}
