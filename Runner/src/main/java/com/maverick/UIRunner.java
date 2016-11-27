package com.maverick;

import com.maverick.gui.LoginFrame;

import javax.swing.*;

//import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;

public class UIRunner {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        //  UIManager.setLookAndFeel(SyntheticaPlainLookAndFeel.class.getName());
        new LoginFrame();
    }
}
