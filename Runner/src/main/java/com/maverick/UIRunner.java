package com.maverick;

import com.maverick.gui.LoginFrame;
import com.maverick.oldDAO.dbmsentitydao.hibernate.HibernateBookDAO;
import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;

import javax.swing.*;

public class UIRunner {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
//        UIManager.setLookAndFeel(SyntheticaPlainLookAndFeel.class.getName());
//        new LoginFrame();


        HibernateBookDAO hibernateBookDAO = new HibernateBookDAO();

        long i = hibernateBookDAO.sumAmount();

        System.out.println("i = " + i);
    }
}
