package java_practice.koreaClock;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ClockMain extends Application {
    @Override
    public void start(Stage stage) {
        ClockUI clockUI = new ClockUI();
        clockUI.run(stage);
    }

    public static void main(String[] args) {
        launch(args);

    }
}
