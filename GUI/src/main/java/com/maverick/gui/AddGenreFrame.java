package com.maverick.gui;

import com.maverick.domain.Genre;
import com.maverick.gui.bookframe.AddBookFrame;
import com.maverick.oldDAO.entitydao.GenreDAO;

import javax.swing.*;
import java.awt.*;

public class AddGenreFrame extends JDialog implements WindowClosing {

    private GenreDAO genreDAO;
    private JTextField nameTextField = new JTextField();

    public AddGenreFrame(Window owner) {

        super(owner, ModalityType.DOCUMENT_MODAL);
        genreDAO = LoginFrame.getDaoFactory().getGenreDAO();

        setTitle("Добавить жанр");
        ImageIcon addIcon = new ImageIcon("images/20x20/add.png");
        setIconImage(addIcon.getImage());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);

        JLabel nameLabel = new JLabel("Имя жанра:");
        add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(10, 10, 2, 2), 0, 0));

        nameTextField.setPreferredSize(new Dimension(200, 30));
        add(nameTextField, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        JPanel panel = new JPanel(new FlowLayout());
        JButton okButton = new JButton("Ок");
        panel.add(okButton);
        JButton cancelButton = new JButton("Отмена");
        panel.add(cancelButton);
        add(panel, new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

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

        if (nameTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Поле Жанр должно быть заполнено!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object[] options = {"Да", "Нет"};
        switch (JOptionPane.showOptionDialog(null, "Вы хотите добавить жанр?", "Добавить?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
            case JOptionPane.YES_OPTION:

                Genre existedGenre = new Genre();
                existedGenre.setName(nameTextField.getText());

                if (!genreDAO.save(existedGenre)) {
                    JOptionPane.showMessageDialog(null, "Введённый Вами жанр уже существует", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }

//                AddBookFrame.getGenreComboBox().removeAllItems();
//                for (Genre genre : genreDAO.findAll())
//                    AddBookFrame.getGenreComboBox().addItem(genre);

                JOptionPane.showMessageDialog(null, "Добавление успешно", "Успешно", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                setVisible(false);

            case JOptionPane.NO_OPTION:
                break;
        }
    }

}
