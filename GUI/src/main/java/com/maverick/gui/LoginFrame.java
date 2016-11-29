package com.maverick.gui;


import com.maverick.domain.Librarian;
import com.maverick.oldDAO.DAOFactory;
import com.maverick.oldDAO.TypeDAO;
import com.maverick.oldDAO.entitydao.LibrarianDAO;
import org.apache.commons.codec.digest.DigestUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginFrame extends JFrame {

    public static JComboBox<Librarian> librarianComboBox = new JComboBox<>();
    private static DAOFactory daoFactory;
    private LibrarianDAO librarianDAO;
    private JComboBox<TypeDAO> daoComboBox = new JComboBox<>(TypeDAO.values());
    private JPasswordField passwordField = new JPasswordField();

    public LoginFrame() {

        daoFactory = DAOFactory.getDAOFactory(TypeDAO.MySQL);
        librarianDAO = daoFactory.getLibrarianDAO();

        JLabel[] labels = new JLabel[3];
        labels[0] = new JLabel("Библиотекарь:");
        labels[1] = new JLabel("Пароль:");
        labels[2] = new JLabel("Выбирите СУБД (DAO):");

        librarianComboBox.removeAllItems();
        for (Librarian librarian : librarianDAO.selectLibrarians()) {
            librarianComboBox.addItem(librarian);
        }

        setTitle("Добро пожаловать");
        setIconImage(new ImageIcon("images/login.png").getImage());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);

        for (int i = 0; i < labels.length; i++)
            add(labels[i], new GridBagConstraints(0, i, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(18, 10, 2, 2), 0, 0));

        librarianComboBox.setPreferredSize(new Dimension(200, 30));
        add(librarianComboBox, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(13, 10, 2, 10), 0, 0));

        passwordField.setPreferredSize(new Dimension(200, 30));
        add(passwordField, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(10, 10, 2, 10), 0, 0));

        daoComboBox.setPreferredSize(new Dimension(200, 30));
        add(daoComboBox, new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(10, 10, 2, 10), 0, 0));


        JPanel panel = new JPanel(new FlowLayout());
        JButton loginButton = new JButton("Войти");
        panel.add(loginButton);
        //panel.add(registrationButton);

        add(panel, new GridBagConstraints(0, 3, 2, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(10, 10, 2, 2), 0, 0));

        pack();
        setLocationRelativeTo(null);

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    saveChanges();
            }
        });


        loginButton.addActionListener(e -> saveChanges());

        JButton registrationButton = new JButton("Регистрация");
        registrationButton.addActionListener(e -> {
            new RegistrationFrame();
        });

        daoComboBox.addActionListener(e -> {

            switch ((TypeDAO) daoComboBox.getSelectedItem()) {

                case MySQL:
                    daoFactory = DAOFactory.getDAOFactory(TypeDAO.MySQL);
                    break;
                case MongoDB:
                    daoFactory = DAOFactory.getDAOFactory(TypeDAO.MongoDB);
                    break;
                case Hibernate:
                    daoFactory = DAOFactory.getDAOFactory(TypeDAO.Hibernate);
                    break;
            }

            librarianDAO = daoFactory.getLibrarianDAO();

            try {
                librarianComboBox.removeAllItems();
                for (Librarian librarian : librarianDAO.selectLibrarians())
                    librarianComboBox.addItem(librarian);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, "Не запущен сервер данной СУБД, будет возвращёна СУБД MySQL", "Ошибка", JOptionPane.ERROR_MESSAGE);
                daoComboBox.setSelectedItem(TypeDAO.MySQL);
                daoFactory = DAOFactory.getDAOFactory(TypeDAO.MySQL);
                librarianComboBox.removeAllItems();
                for (Librarian librarian : librarianDAO.selectLibrarians())
                    librarianComboBox.addItem(librarian);

            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Object[] options = {"Да", "Нет"};
                switch (JOptionPane.showOptionDialog(e.getWindow(), "Закрыть программу?", "Подтверждение", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {

                    case JOptionPane.YES_OPTION:
                        dispose();
                        e.getWindow().setVisible(false);
                        System.exit(0);
                        break;
                    case JOptionPane.NO_OPTION:
                        break;
                }
            }
        });

        setVisible(true);
    }

    public static DAOFactory getDaoFactory() {
        return daoFactory;
    }

    public void saveChanges() {
        if (passwordField.getPassword().length == 0)
            JOptionPane.showMessageDialog(null, "Введите пароль", "Предупреждение", JOptionPane.WARNING_MESSAGE);

        else if (passwordField.getPassword().length < 6)
            JOptionPane.showMessageDialog(null, "Пароль более 6 символов", "Предупреждение", JOptionPane.WARNING_MESSAGE);

        else {
            Librarian librarian = (Librarian) librarianComboBox.getSelectedItem();

            if (!librarian.getPassword().equals(DigestUtils.md5Hex(String.valueOf(passwordField.getPassword()))))
                JOptionPane.showMessageDialog(null, "Неверный пароль", "Неверно", JOptionPane.ERROR_MESSAGE);

            else {
                dispose();
                setVisible(false);
                new MainFrame();
            }
        }
    }
}
