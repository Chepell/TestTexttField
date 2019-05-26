package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;

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
    private MenuItem selectAll;
    private MenuItem copy;
    private MenuItem paste;
    private SeparatorMenuItem separator;
    private MenuItem delete;

    CustomContextMenu(TextField textField) {
        this.textField = textField;

//        SystemLocale.setLocale(Locale.getDefault());
//        SystemLocale.
//                setBundle(ResourceBundle.getBundle("src.locale.bundle", SystemLocale.locale));

        selectAll = new MenuItem("Выбрать все");
        copy = new MenuItem(resources.getString("menu.copy"));
        paste = new MenuItem(resources.getString("menu.paste"));
        separator = new SeparatorMenuItem();
        delete = new MenuItem(resources.getString("menu.delete"));
        delete.setStyle("-fx-text-fill: red;");

        selectAllInit();
        copyInit();
        pasteInit();
        separatorInit();
        deleteInit();

        getItems().addAll(selectAll, copy, paste, separator, delete);
    }

    private void selectAllInit() {
        selectAll.visibleProperty().bind(textField.textProperty().isNotEqualTo(textField.selectedTextProperty()));
        selectAll.setOnAction(actionEvent -> textField.selectAll());
    }

    private void copyInit() {
        copy.visibleProperty().bind(textField.selectedTextProperty().isNotEmpty());
        copy.setOnAction(actionEvent -> textField.copy());
    }

    private void pasteInit() {
        BooleanBinding clipBind =
                Bindings.createBooleanBinding(() ->
                        Clipboard.getSystemClipboard().hasString() && !Clipboard.getSystemClipboard().getString().isEmpty());
        paste.visibleProperty().bind(clipBind);
        paste.setOnAction(actionEvent -> textField.paste());
    }

    private void separatorInit() {
        separator.visibleProperty().bind(delete.visibleProperty().and(paste.visibleProperty()));
    }

    private void deleteInit() {
        delete.visibleProperty().bind(textField.selectedTextProperty().isNotEmpty());
        delete.setOnAction(actionEvent -> textField.cut());
    }
}
