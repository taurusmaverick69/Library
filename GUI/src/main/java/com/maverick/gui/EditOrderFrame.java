package com.maverick.gui;

import com.maverick.domain.Book;
import com.maverick.domain.Librarian;
import com.maverick.domain.Order;
import com.maverick.domain.Student;
import com.maverick.oldDAO.entitydao.BookDAO;
import com.maverick.oldDAO.entitydao.OrderDAO;
import com.maverick.oldDAO.entitydao.StudentDAO;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EditOrderFrame extends JDialog implements WindowClosing {

    private BookDAO bookDAO;
    private OrderDAO orderDAO;

    private JComboBox<Student> studentComboBox = new JComboBox<>();
    private JComboBox<Book> bookComboBox = new JComboBox<>();
    private JComboBox<String> statusComboBox = new JComboBox<>();

    private JDateChooser startDateChooser = new JDateChooser();
    private JDateChooser finishDateChooser = new JDateChooser();

    public EditOrderFrame(Window owner) {

        super(owner, ModalityType.DOCUMENT_MODAL);

        StudentDAO studentDAO = LoginFrame.getDaoFactory().getStudentDAO();
        bookDAO = LoginFrame.getDaoFactory().getBookDAO();
        orderDAO = LoginFrame.getDaoFactory().getOrderDAO();

        JLabel[] labels = new JLabel[5];
        labels[0] = new JLabel("Студент:");
        labels[1] = new JLabel("Книга:");
        labels[2] = new JLabel("Дата оформления:");
        labels[3] = new JLabel("Дата возврата:");
        labels[4] = new JLabel("Статус:");


        studentComboBox.removeAllItems();
        for (Student student : studentDAO.selectStudents())
            studentComboBox.addItem(student);

        bookComboBox.removeAllItems();
        for (Book book : bookDAO.selectBooks())
            bookComboBox.addItem(book);

        statusComboBox.addItem("Возвращена");
        statusComboBox.addItem("Не возвращена");

        studentComboBox.setSelectedItem(MainFrame.orderTable.getValueAt(MainFrame.orderTable.getSelectedRow(), 1));
        bookComboBox.setSelectedItem(MainFrame.orderTable.getValueAt(MainFrame.orderTable.getSelectedRow(), 2));


        try {
            Date startDate = new SimpleDateFormat("dd.MM.yyyy").parse(MainFrame.orderTable.getValueAt(MainFrame.orderTable.getSelectedRow(), 3).toString());
            Date finishDate = new SimpleDateFormat("dd.MM.yyyy").parse(MainFrame.orderTable.getValueAt(MainFrame.orderTable.getSelectedRow(), 4).toString());
            startDateChooser.setDate(startDate);
            finishDateChooser.setDate(finishDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        statusComboBox.setSelectedItem(MainFrame.orderTable.getValueAt(MainFrame.orderTable.getSelectedRow(), 5));

        setTitle("Редактировать заказ");
        ImageIcon editIcon = new ImageIcon("images/20x20/edit.png");
        setIconImage(editIcon.getImage());
        setLayout(new GridBagLayout());
        setResizable(false);

        for (int i = 0; i < labels.length; i++)
            add(labels[i], new GridBagConstraints(0, i, 1, 1, 1.0, 1.0,
                    GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                    new Insets(10, 10, 2, 2), 0, 0));


        studentComboBox.setPreferredSize(new Dimension(200, 30));
        add(studentComboBox, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));


        bookComboBox.setPreferredSize(new Dimension(200, 30));
        add(bookComboBox, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0,
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

        Date startDate = startDateChooser.getDate();
        Date finishDate = finishDateChooser.getDate();

        if (startDate == null) {
            JOptionPane.showMessageDialog(null, "Ввдеите дату оформления!", "Проверьте дату", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (finishDate == null) {
            JOptionPane.showMessageDialog(null, "Ввдеите дату возврата!", "Проверьте дату", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (startDate.after(finishDate)) {
            JOptionPane.showMessageDialog(null, "Дата возврата не может быть раньше даты оформления!", "Проверьте дату", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object[] options = {"Да", "Нет"};
        switch (JOptionPane.showOptionDialog(null, "Редактировать заказ?", "Редактировать?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
            case JOptionPane.YES_OPTION:


//                Librarian librarian = (Librarian) LoginFrame.librarianComboBox.getSelectedItem();
//
//                Order order = Order.newBuilder()
//                        .setStudent((Student) studentComboBox.getSelectedItem())
//                        .setBook((Book) bookComboBox.getSelectedItem())
//                        .setStartDate(startDate)
//                        .setFinishDate(finishDate)
//                        .setStatus("Не возвращена")
//                        .build();
//
//                orderDAO.insertOrder(order);
//                librarian.getOrders().add(order);

                Librarian librarian = (Librarian) LoginFrame.librarianComboBox.getSelectedItem();


                Order order = new Order(
                        Integer.parseInt(MainFrame.orderTable.getValueAt(MainFrame.orderTable.getSelectedRow(), 0).toString()),
                        (Student) studentComboBox.getSelectedItem(),
                        (Book) bookComboBox.getSelectedItem(),
                        startDate,
                        finishDate,
                        statusComboBox.getSelectedItem().toString());


                orderDAO.updateOrder(order);

                List<Order> orders = librarian.getOrders();
                orders.set(MainFrame.orderTable.getSelectedRow(), order);

                MainFrame.orderTableModel.addOrderData(orders);
                MainFrame.orderTable.updateUI();
                MainFrame.bookTableModel.addBookData(bookDAO.selectBooks());
                MainFrame.bookTable.updateUI();
                JOptionPane.showMessageDialog(null, "Редактирование успешно", "Редактировано", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                setVisible(false);

            case JOptionPane.NO_OPTION:
                break;
        }
    }
}
