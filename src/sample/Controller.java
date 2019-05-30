package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MyTextField textField;

    @FXML
    private TextField textField1;

    @FXML
    private Button btn;

    @FXML
    private Label label;

    @FXML
    private Button transitionBtn;


    @FXML
    void initialize() throws IOException {
        Locale locale = new Locale("en", "UK");
        ResourceBundle bundle = ResourceBundle.getBundle("control", locale);


        Tooltip tooltip = new Tooltip("Circle button");
        Tooltip.install(transitionBtn, tooltip);

        // поджключение нужного css класса к элементу
        transitionBtn.getStyleClass().add("circle");
//        transitionBtn.getStyleClass().add("transition-button");
//        transitionBtn.setText(null);


        label.setText(bundle.getString("ui.label"));
        btn.setOnAction(actionEvent -> label.setText(textField.getText()));


        textField1.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.isContentChange()) {
                String newText = change.getControlNewText();
                if (!newText.matches("^\\d{0,4}$")) {
                    return null;
                }
            }
            return change;
        }));
    }
}
