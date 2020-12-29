package handler;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextFieldKeyHandler implements KeyListener {

    private JFormattedTextField textField;

    public TextFieldKeyHandler(JFormattedTextField textField) {
        this.textField = textField;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Replace textField text when pressing keys 1-9 or numpad 1-9
        if ((e.getKeyCode() >= KeyEvent.VK_1 && e.getKeyCode() <= KeyEvent.VK_9)
                || (e.getKeyCode() >= KeyEvent.VK_NUMPAD1 && e.getKeyCode() <= KeyEvent.VK_NUMPAD9)) {
            textField.setText(String.valueOf(e.getKeyChar()));
        }
    }
}
