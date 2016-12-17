package com.maverick.gui;

import com.maverick.domain.Book;
import com.maverick.domain.Order;
import com.maverick.domain.Student;
import com.maverick.oldDAO.entitydao.OrderDAO;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SearchOrderFrame extends JDialog implements WindowClosing {

    private OrderDAO orderDAO;

    private JTextField studentTextField = new JTextField();
    private JTextField bookTextField = new JTextField();
    private JDateChooser startDateChooser = new JDateChooser();
    private JDateChooser finishDateChooser = new JDateChooser();
    private JComboBox<String> statusComboBox = new JComboBox<>();

    public SearchOrderFrame(Window owner) {

        super(owner, ModalityType.DOCUMENT_MODAL);

        orderDAO = LoginFrame.getDaoFactory().getOrderDAO();

        JLabel[] labels = new JLabel[5];
        labels[0] = new JLabel("Студент:");
        labels[1] = new JLabel("Книга:");
        labels[2] = new JLabel("Дата оформления:");
        labels[3] = new JLabel("Дата возврата:");
        labels[4] = new JLabel("Статус:");

        statusComboBox.addItem("Возвращена");
        statusComboBox.addItem("Не возвращена");
        statusComboBox.addItem("");

        setTitle("Поиск заказа");
        setIconImage(new ImageIcon("images/search.png").getImage());
        setLayout(new GridBagLayout());
        setResizable(false);


        for (int i = 0; i < labels.length; i++)
            add(labels[i], new GridBagConstraints(0, i, 1, 1, 1.0, 1.0,
                    GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                    new Insets(10, 10, 2, 2), 0, 0));

        studentTextField.setPreferredSize(new Dimension(200, 30));
        add(studentTextField, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));

        bookTextField.setPreferredSize(new Dimension(200, 30));
        add(bookTextField, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));

        startDateChooser.setPreferredSize(new Dimension(200, 30));
        add(startDateChooser, new GridBagConstraints(1, 2, 2, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));


        finishDateChooser.setPreferredSize(new Dimension(200, 30));
        add(finishDateChooser, new GridBagConstraints(1, 3, 2, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));


        statusComboBox.setPreferredSize(new Dimension(200, 30));
        add(statusComboBox, new GridBagConstraints(1, 4, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));

        JPanel panel = new JPanel(new FlowLayout());
        JButton okButton = new JButton("Ок");
        panel.add(okButton);
        JButton cancelButton = new JButton("Отмена");
        panel.add(cancelButton);
        add(panel, new GridBagConstraints(0, 5, 3, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        ((JTextFieldDateEditor) startDateChooser.getDateEditor()).setEditable(false);
        ((JTextFieldDateEditor) finishDateChooser.getDateEditor()).setEditable(false);

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

        String startDateText = ((JTextField) startDateChooser.getDateEditor().getUiComponent()).getText();
        String finishDateText = ((JTextField) finishDateChooser.getDateEditor().getUiComponent()).getText();

        Object[] options = {"Да", "Нет"};
        switch (JOptionPane.showOptionDialog(null, "Подтверждете поиск?", "Поиск?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
            case JOptionPane.YES_OPTION:

                Student student = new Student();
                student.setFullName(studentTextField.getText());
                Book book = new Book();
                book.setTitle(bookTextField.getText());

//
//                Order order = new Order(
//                        -1,
//                        student,
//                        book,
//                        (Librarian) LoginFrame.librarianComboBox.getSelectedItem(),
//                        startDateText,
//                        finishDateText,
//                        statusComboBox.getSelectedItem().toString()
//                );

                Order order = new Order();


//                if (orders.isEmpty()) {
//                    JOptionPane.showMessageDialog(null, " По вашему запросу не найдено совпадений", "Завершено", JOptionPane.INFORMATION_MESSAGE);
//                } else {
//                    // MainFrame.orderTableModel.addOrderData(orders);
//                    MainFrame.orderTable.updateUI();
//                    JOptionPane.showMessageDialog(null, "Найдено совпадений: " + MainFrame.orderTable.getRowCount(), "Завершено", JOptionPane.INFORMATION_MESSAGE);
//                    dispose();
//                    setVisible(false);
//                }

            case JOptionPane.NO_OPTION:
                break;
        }
    }

}
