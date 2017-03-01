package com.maverick;

import com.maverick.gui.LoginFrame;

import javax.swing.*;

public class UIRunner {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        //UIManager.setLookAndFeel(SyntheticaPlainLookAndFeel.class.getName());
        new LoginFrame();
    }
}
