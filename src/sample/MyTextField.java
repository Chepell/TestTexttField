package sample;


import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;

/**
 * Artem Voytenko
 * 24.05.2019
 */

public class MyTextField extends TextField {

    public MyTextField() {
        CustomContextMenu customContextMenu = new CustomContextMenu(this);
        setContextMenu(customContextMenu);
    }

    @Override
    public void selectAll() {
        super.selectAll();
        System.out.println("Выделить всё");
    }

    @Override
    public void cut() {
        super.cut();
//        String selectedText = getSelectedText();
//        String newText = getText().replaceFirst(selectedText, "");
//        setText(newText);
//        positionCaret(newText.length());
        Clipboard.getSystemClipboard().clear();
        System.out.println("Вырезать");
    }

    @Override
    public void paste() {
        super.paste();
        System.out.println("Вставить");
    }


}
