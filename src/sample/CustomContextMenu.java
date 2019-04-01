package sample;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Artem Voytenko
 * 21.03.2019
 */

public class CustomContextMenu extends ContextMenu {
    private Locale locale = new Locale("en", "UK");
    private ResourceBundle resources = ResourceBundle.getBundle("control", locale);


    private TextField textField;
    private MenuItem cut;
    private MenuItem paste;
    private MenuItem selectAll;
    private Clipboard systemClipboard;

    CustomContextMenu(TextField textField) {
//        SystemLocale.setLocale(Locale.getDefault());
//        SystemLocale.
//                setBundle(ResourceBundle.getBundle("src.locale.bundle", SystemLocale.locale));

        this.textField = textField;

        MenuItem undo = new MenuItem(resources.getString("menu.undo"));
        MenuItem redo = new MenuItem(resources.getString("menu.redo"));
        cut = new MenuItem(resources.getString("menu.cut"));
        MenuItem copy = new MenuItem(resources.getString("menu.copy"));
        paste = new MenuItem(resources.getString("menu.paste"));
        MenuItem delete = new MenuItem(resources.getString("menu.delete"));
        delete.setStyle("-fx-text-fill: red;");
        SeparatorMenuItem separator = new SeparatorMenuItem();
        selectAll = new MenuItem("Выбрать все");

        cutInit();
        selectAll();

        this.getItems().addAll(selectAll, copy, paste, delete);


//        this.getItems().addAll(selectAll, copy, paste, separator, delete);

//        delete.setStyle("-fx-text-fill: red; -fx-background-color: black;");


//        textField.setOnMouseClicked(mouseEvent -> {
//            systemClipboard = Clipboard.getSystemClipboard();
//            if (systemClipboard.hasString()) {
//                System.out.println("String in clipboard");
//                paste.setVisible(false);
//            }
//        });

//        textField.setOnContextMenuRequested(mouseEvent -> {
//            systemClipboard = Clipboard.getSystemClipboard();
//            if (systemClipboard.hasString()) {
//                System.out.println("String in clipboard");
//                paste.setVisible(true);
//            } else {
//                System.out.println("Clipboard is empty");
//                paste.setVisible(false);
//            }
//        });

//        this.setOnAction(actionEvent -> {
//            systemClipboard = Clipboard.getSystemClipboard();
//            if (systemClipboard.hasString()) {
//                System.out.println("String in clipboard");
//            }
//        });
    }


    CustomContextMenu() {}


    private void selectAll() {
        selectAll.visibleProperty().bind(textField.textProperty().isNotEmpty());
    }

//    private void pasteInit() {
//        Clipboard systemClipboard = Clipboard.getSystemClipboard();
//
//        paste.visibleProperty().bind(this.on);
//
//        ClipboardContent content = new ClipboardContent();
//        content.putString(selectedString);
//        clipboard.setContent(content);
//
//        cut.visibleProperty().bind(textField.selectedTextProperty().isNotEmpty());
//
//        cut.setOnAction(actionEvent -> {
//            String fullString = textField.getText();
//            String selectedString = textField.getSelectedText();
//
//            int cursorIndex = fullString.indexOf(selectedString);
//            String newString = fullString.replace(selectedString, "");
//
//            textField.clear();
//            textField.setText(newString);
//            textField.positionCaret(cursorIndex);
//
//            Clipboard clipboard = Clipboard.getSystemClipboard();
//            ClipboardContent content = new ClipboardContent();
//            content.putString(selectedString);
//            clipboard.setContent(content);
//        });
//    }

    private void cutInit() {
        cut.visibleProperty().bind(textField.selectedTextProperty().isNotEmpty());

        cut.setOnAction(actionEvent -> {
            String fullString = textField.getText();
            String selectedString = textField.getSelectedText();

            int cursorIndex = fullString.indexOf(selectedString);
            String newString = fullString.replace(selectedString, "");

            textField.clear();
            textField.setText(newString);
            textField.positionCaret(cursorIndex);

            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedString);
            clipboard.setContent(content);
        });
    }
}
