package com.tei.view.menu;

import com.tei.model.SudokuConstants;
import com.tei.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpMenu extends JMenu {

    private final MainView mainView;
    private JMenuItem about;

    public HelpMenu(String name, MainView mainView) {
        super(name);
        this.mainView = mainView;
        init();
    }

    private void init() {
        about = new JMenuItem("About");
        this.about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.pushRoute(SudokuConstants.ABOUT_PANEL);
            }
        });


        this.add(about);
    }
}
