package com.maverick;

import com.maverick.domain.Author;
import com.maverick.gui.LoginFrame;
import com.maverick.oldDAO.DAOFactory;
import com.maverick.oldDAO.TypeDAO;
import com.maverick.oldDAO.entitydao.AuthorDAO;
//import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;

import javax.swing.*;
import java.util.List;

public class UIRunner {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
   //     UIManager.setLookAndFeel(SyntheticaPlainLookAndFeel.class.getName());
       // new LoginFrame();

        DAOFactory daoFactory = DAOFactory.getDAOFactory(TypeDAO.Hibernate);

        AuthorDAO authorDAO =
                daoFactory.getAuthorDAO();


        List<Author> all =
                authorDAO.findAll();


        System.out.println(all);

    }
}
