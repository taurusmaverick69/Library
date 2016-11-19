package com.maverick.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public interface WindowClosing {

    default WindowAdapter getWindowAdapter(Window window) {
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Object[] closingOptions = {"Да", "Нет", "Отмена"};
                switch (JOptionPane.showOptionDialog(e.getWindow(), "Сохранить изменения?", "Сохранить", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, closingOptions, closingOptions[0])) {
                    case JOptionPane.YES_OPTION:
                        saveChanges();
                        break;
                    case JOptionPane.NO_OPTION:
                        window.dispose();
                        window.setVisible(false);
                        break;
                    case JOptionPane.CANCEL_OPTION:
                        break;
                }
            }
        };
    }

    void saveChanges();
}
