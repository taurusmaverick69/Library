package com.maverick.gui;

import com.maverick.domain.Publisher;
import com.maverick.oldDAO.entitydao.PublisherDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddPublisherFrame extends JDialog implements WindowClosing {

    private PublisherDAO publisherDAO;
    private JTextField nameTextField = new JTextField();

    public AddPublisherFrame(Window owner) {

        super(owner, ModalityType.DOCUMENT_MODAL);
        publisherDAO = LoginFrame.getDaoFactory().getPublisherDAO();

        setTitle("Добавить издательство");
        ImageIcon addIcon = new ImageIcon("images/20x20/add.png");
        setIconImage(addIcon.getImage());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);


        JLabel nameLabel = new JLabel("Имя издательства:");
        add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(10, 2, 2, 2), 0, 0));

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

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Object[] closingOptions = {"Да", "Нет", "Отмена"};
                switch (JOptionPane.showOptionDialog(e.getWindow(), "Сохранить изменения?", "Сохранить", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, closingOptions, closingOptions[0])) {

                    case JOptionPane.YES_OPTION:
                        saveChanges();
                        break;

                    case JOptionPane.NO_OPTION:
                        dispose();
                        setVisible(false);
                        break;

                    case JOptionPane.CANCEL_OPTION:
                        break;
                }
            }
        });
        setVisible(true);
    }

    @Override
    public void saveChanges() {

        if (nameTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Поле Издательство должно быть заполнено!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object[] options = {"Да", "Нет"};
        switch (JOptionPane.showOptionDialog(null, "Вы хотите добавить издательство?", "Добавить?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
            case JOptionPane.YES_OPTION:

                Publisher existedPublisher = new Publisher();
                existedPublisher.setName(nameTextField.getText());

                if (!publisherDAO.insertPublisher(existedPublisher)) {
                    JOptionPane.showMessageDialog(null, "Введённоё Вами издательство уже существует", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                AddBookFrame.getPublisherComboBox().removeAllItems();
                for (Publisher publisher : publisherDAO.selectPublishers())
                    AddBookFrame.getPublisherComboBox().addItem(publisher);

                JOptionPane.showMessageDialog(null, "Добавление успешно", "Успешно", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                setVisible(false);

            case JOptionPane.NO_OPTION:
                break;
        }
    }

}