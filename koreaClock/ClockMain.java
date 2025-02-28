package java_practice.koreaClock;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ClockMain extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("JavaFX 설치 성공!");
        Scene scene = new Scene(label, 300, 100);
        stage.setScene(scene);
        stage.setTitle("테스트");
        stage.show();
    }

    public static void main(String[] args) {
//        launch(args);

    }
}
