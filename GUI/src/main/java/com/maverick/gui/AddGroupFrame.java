package com.maverick.gui;

import com.maverick.domain.Group;
import com.maverick.oldDAO.entitydao.GroupDAO;

import javax.swing.*;
import java.awt.*;

public class AddGroupFrame extends JDialog implements WindowClosing {

    private GroupDAO groupDAO;
    private JTextField nameTextField = new JTextField();

    public AddGroupFrame(Window owner) {

        super(owner, ModalityType.DOCUMENT_MODAL);
        groupDAO = LoginFrame.getDaoFactory().getGroupDAO();

        setTitle("Добавить группу");
        ImageIcon addIcon = new ImageIcon("images/20x20/add.png");
        setIconImage(addIcon.getImage());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);

        JLabel nameLabel = new JLabel("Группа:");
        add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, 2, 2, 2), 0, 0));

        nameTextField.setPreferredSize(new Dimension(200, 30));
        add(nameTextField, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(15, 2, 2, 2), 0, 0));


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
            JOptionPane.showMessageDialog(null, "Поле Группа должно быть заполнено!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object[] options = {"Да", "Нет"};
        switch (JOptionPane.showOptionDialog(null, "Вы хотите добавить группу?", "Добавить?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
            case JOptionPane.YES_OPTION:

                Group existedGroup = new Group();
                existedGroup.setName(nameTextField.getText());

                if (!groupDAO.insertGroup(existedGroup)) {
                    JOptionPane.showMessageDialog(null, "Введённая Вами группа уже существует", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                AddStudentFrame.getGroupComboBox().removeAllItems();
                groupDAO.selectGroups().forEach(group -> AddStudentFrame.getGroupComboBox().addItem(group));

                JOptionPane.showMessageDialog(null, "Добавление успешно", "Успешно", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                setVisible(false);
            case JOptionPane.NO_OPTION:
                break;
        }
    }
}
