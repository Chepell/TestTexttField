package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale locale = new Locale("en", "UK");
        ResourceBundle bundle = ResourceBundle.getBundle("control", locale);

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"), bundle);

        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
//        scene.getStylesheets().add("stylecheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {


        launch(args);
    }
}
