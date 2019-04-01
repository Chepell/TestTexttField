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
    private TextField textField;

    @FXML
    private Button btn;

    @FXML
    private Label label;

    private Clipboard systemClipboard;

    @FXML
    void initialize() throws IOException {
        Locale locale = new Locale("en", "UK");
        ResourceBundle bundle = ResourceBundle.getBundle("control", locale);

        label.setText(bundle.getString("ui.label"));
        btn.setOnAction(actionEvent -> label.setText(textField.getText()));


        // todo в констукторе наследника textField

        CustomContextMenu customContextMenu = new CustomContextMenu(textField);
        CustomContextMenu customContextMenuEmpty = new CustomContextMenu();

        textField.setContextMenu(customContextMenuEmpty);


        textField.setOnMouseClicked(actionEvent -> {
            systemClipboard = Clipboard.getSystemClipboard();
            if (!textField.getText().isEmpty() || systemClipboard.hasString()) {
                textField.setContextMenu(customContextMenu);
            } else {
                textField.setContextMenu(customContextMenuEmpty);
            }
        });

        // todo и сделать поле для паттерна и геттер для него. и если в геттер вызван то регулрка проверяет ввод


    }
}
