package handler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class LabelResizeHandler implements ComponentListener {

    private JLabel usernameLabel;

    public LabelResizeHandler(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        JPanel panel = (JPanel) e.getSource();
        usernameLabel.setBorder(new EmptyBorder(20, (int) (panel.getWidth() * 0.8), 20, 20));
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 26));
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
