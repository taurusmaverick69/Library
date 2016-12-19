package com.maverick.gui;


import com.maverick.domain.Librarian;
import com.maverick.oldDAO.entitydao.LibrarianDAO;

import javax.swing.*;
import java.awt.*;

public class RegistrationFrame extends JDialog implements WindowClosing {

    private LibrarianDAO librarianDAO;

    private JTextField fullNameTextField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JPasswordField confirmPasswordField = new JPasswordField();

    RegistrationFrame() {

        librarianDAO = LoginFrame.getDaoFactory().getLibrarianDAO();

        JLabel[] labels = new JLabel[3];
        labels[0] = new JLabel("ФИО:");
        labels[1] = new JLabel("Пароль:");
        labels[2] = new JLabel("Подтвердите пароль");

        setTitle("Регистрация библиотекаря");
        setIconImage(new ImageIcon("images/registration.png").getImage());
        setLayout(new GridBagLayout());
        setResizable(false);

        for (int i = 0; i < labels.length; i++)
            add(labels[i], new GridBagConstraints(0, i, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(18, 10, 2, 2), 0, 0));

        fullNameTextField.setPreferredSize(new Dimension(200, 30));
        add(fullNameTextField, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(10, 10, 2, 2), 0, 0));

        passwordField.setPreferredSize(new Dimension(200, 30));
        add(passwordField, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(10, 10, 2, 2), 0, 0));


        confirmPasswordField.setPreferredSize(new Dimension(200, 30));
        add(confirmPasswordField, new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(10, 10, 2, 2), 0, 0));

        JPanel panel = new JPanel(new FlowLayout());
        JButton okButton = new JButton("Ок");
        panel.add(okButton);
        JButton cancelButton = new JButton("Отмена");
        panel.add(cancelButton);

        add(panel, new GridBagConstraints(0, 3, 2, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(10, 10, 2, 2), 0, 0));

        pack();
        setLocationRelativeTo(null);

        okButton.addActionListener(e -> saveChanges());

        cancelButton.addActionListener(e -> {
            dispose();
            setVisible(false);
        });

        addWindowListener(getWindowAdapter(this));
        setVisible(true);
    }

    @Override
    public void saveChanges() {
        if (fullNameTextField.getText().isEmpty() || new String(passwordField.getPassword()).isEmpty() || new String(confirmPasswordField.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Все поля должны быть заполнены!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (!new String(passwordField.getPassword()).equals(new String(confirmPasswordField.getPassword()))) {
            JOptionPane.showMessageDialog(null, "Пароли должны совпадать!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (passwordField.getPassword().length <= 6) {
            JOptionPane.showMessageDialog(null, "Пароль должен быть более 6 символов!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        }


        Object[] options = {"Да", "Нет"};
        switch (JOptionPane.showOptionDialog(null, "Подтверждаете регистрацию?", "Регистрировать?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
            case JOptionPane.NO_OPTION:
                return;

            case JOptionPane.YES_OPTION:

                Librarian librarian = new Librarian();
                librarian.setFullName(fullNameTextField.getText());
                librarian.setPassword(new String(passwordField.getPassword()));

                librarianDAO.save(librarian);
                JOptionPane.showMessageDialog(null, "Добавление успешно!", "Успешно", JOptionPane.INFORMATION_MESSAGE);

                LoginFrame.librarianComboBox.removeAllItems();
                for (Librarian anotherLibrarian : librarianDAO.findAllWithOrders())
                    LoginFrame.librarianComboBox.addItem(anotherLibrarian);
                dispose();
                setVisible(false);
        }
    }
}
