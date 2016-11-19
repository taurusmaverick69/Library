package com.maverick.gui;

import com.maverick.domain.Author;
import com.maverick.oldDAO.entitydao.AuthorDAO;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class AddAuthorFrame extends JDialog implements WindowClosing {

    private AuthorDAO authorDAO;

    private JTextField fullNameTextField = new JTextField();
    private JFormattedTextField yearsOfLifeTextfield;

    public AddAuthorFrame(Window owner) {

        super(owner, ModalityType.DOCUMENT_MODAL);

        authorDAO = LoginFrame.getDaoFactory().getAuthorDAO();

        try {
            yearsOfLifeTextfield = new JFormattedTextField(new MaskFormatter("####-####"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JLabel[] labels = new JLabel[2];
        labels[0] = new JLabel("ФИО:");
        labels[1] = new JLabel("Годы жизни:");

        setTitle("Добавить автора");
        ImageIcon addIcon = new ImageIcon("images/20x20/add.png");
        setIconImage(addIcon.getImage());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);

        for (int i = 0; i < labels.length; i++) {
            add(labels[i], new GridBagConstraints(0, i, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(10, 10, 2, 2), 0, 0));
        }

        fullNameTextField.setPreferredSize(new Dimension(200, 30));
        add(fullNameTextField, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));


        yearsOfLifeTextfield.setPreferredSize(new Dimension(200, 30));
        add(yearsOfLifeTextfield, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));

        JPanel panel = new JPanel(new FlowLayout());
        JButton okButton = new JButton("Ок");
        panel.add(okButton);
        JButton cancelButton = new JButton("Отмена");
        panel.add(cancelButton);
        add(panel, new GridBagConstraints(0, 2, 2, 1, 1.0, 1.0,
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
        if (fullNameTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Поле ФИО должно быть заполнено!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object[] options = {"Да", "Нет"};
        switch (JOptionPane.showOptionDialog(null, "Вы действительно хотите добавить автора?", "Добавить?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
            case JOptionPane.YES_OPTION:

                Author existedAuthor = new Author();
                existedAuthor.setFullName(fullNameTextField.getText());

                if (!authorDAO.insertAuthor(existedAuthor)) {
                    JOptionPane.showMessageDialog(null, "Введённый Вами автор уже существует", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                AddBookFrame.getAuthorComboBox().removeAllItems();
                for (Author author : authorDAO.selectAuthors())
                    AddBookFrame.getAuthorComboBox().addItem(author);


                JOptionPane.showMessageDialog(null, "Добавление успешно", "Успешно", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                setVisible(false);
                break;

            case JOptionPane.NO_OPTION:
                break;
        }

    }

}