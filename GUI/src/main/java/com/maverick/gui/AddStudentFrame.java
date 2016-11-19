package com.maverick.gui;

import com.maverick.domain.Group;
import com.maverick.domain.Student;
import com.maverick.oldDAO.entitydao.GroupDAO;
import com.maverick.oldDAO.entitydao.StudentDAO;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

public class AddStudentFrame extends JDialog implements WindowClosing {

    private static JComboBox<Group> groupComboBox = new JComboBox<>();
    private StudentDAO studentDAO;
    private JTextField fullNameTextField = new JTextField();
    private JTextField libraryCardTextField;

    public AddStudentFrame(Window owner) {
        super(owner, ModalityType.DOCUMENT_MODAL);

        GroupDAO groupDAO = LoginFrame.getDaoFactory().getGroupDAO();
        studentDAO = LoginFrame.getDaoFactory().getStudentDAO();

        JLabel[] labels = new JLabel[3];
        labels[0] = new JLabel("ФИО:");
        labels[1] = new JLabel("№ билета:");
        labels[2] = new JLabel("Группа:");

        try {
            libraryCardTextField = new JFormattedTextField(new MaskFormatter("########"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        groupComboBox.removeAllItems();
        for (Group group : groupDAO.selectGroups())
            groupComboBox.addItem(group);

        setTitle("Добавить студента");
        ImageIcon addIcon = new ImageIcon("images/20x20/add.png");
        setIconImage(addIcon.getImage());
        setLayout(new GridBagLayout());
        setResizable(false);

        for (int i = 0; i < labels.length; i++)
            add(labels[i], new GridBagConstraints(0, i, 1, 1, 1.0, 1.0,
                    GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                    new Insets(10, 10, 2, 2), 0, 0));


        fullNameTextField.setPreferredSize(new Dimension(200, 30));
        add(fullNameTextField, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));

        libraryCardTextField.setPreferredSize(new Dimension(200, 30));
        add(libraryCardTextField, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));


        groupComboBox.setPreferredSize(new Dimension(200, 30));
        JButton addGroupButton = new JButton(addIcon);
        addGroupButton.setPreferredSize(new Dimension(30, 30));
        add(groupComboBox, new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));
        add(addGroupButton, new GridBagConstraints(2, 2, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));

        JPanel panel = new JPanel(new FlowLayout());
        JButton okButton = new JButton("Ок");
        panel.add(okButton);
        JButton cancelButton = new JButton("Отмена");
        panel.add(cancelButton);
        add(panel, new GridBagConstraints(0, 3, 3, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));

        pack();
        setLocationRelativeTo(null);

        final AddStudentFrame addStudentFrame = this;


        okButton.addActionListener(e -> saveChanges());

        cancelButton.addActionListener(e -> {
            dispose();
            setVisible(false);
        });

        addGroupButton.addActionListener(e -> new AddGroupFrame(addStudentFrame));


        addWindowListener(getWindowAdapter(this));
        setVisible(true);
    }

    public static JComboBox<Group> getGroupComboBox() {
        return groupComboBox;
    }

    @Override
    public void saveChanges() {
        if (fullNameTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Поле ФИО должно быть заполнено!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (libraryCardTextField.getText().isEmpty() || libraryCardTextField.getText().equals("        ")) {
            JOptionPane.showMessageDialog(null, "Поле № билета должно быть заполнено! (8 значное число)", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object[] options = {"Да", "Нет"};
        switch (JOptionPane.showOptionDialog(null, "Вы действительно хотите добавить студента?", "Добавить?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
            case JOptionPane.YES_OPTION:

                Student student = new Student();
                student.setFullName(fullNameTextField.getText());
                student.setLibraryCard(libraryCardTextField.getText());
                student.setGroup((Group) groupComboBox.getSelectedItem());

                if (!studentDAO.insertStudent(student)) {
                    JOptionPane.showMessageDialog(null, "Введённый Вами студент с таким номер билета уже существует", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ArrayList<Student> students = new ArrayList<>(studentDAO.selectStudents());
                Collections.sort(students, (o1, o2) -> o1.getFullName().compareTo(o2.getFullName()));

                AddOrderFrame.getStudentComboBox().removeAllItems();
                for (Student anotherStudent : students)
                    AddOrderFrame.getStudentComboBox().addItem(anotherStudent);

                JOptionPane.showMessageDialog(null, "Добавление успешно", "Успешно", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                setVisible(false);
                break;

            case JOptionPane.NO_OPTION:
                break;
        }
    }
}
