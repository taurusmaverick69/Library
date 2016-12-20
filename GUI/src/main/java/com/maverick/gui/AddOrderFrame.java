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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class AddOrderFrame extends JDialog implements WindowClosing {

    private static JComboBox<Student> studentComboBox = new JComboBox<>();
    private static JComboBox<Book> bookComboBox = new JComboBox<>();
    private BookDAO bookDAO;
    private OrderDAO orderDAO;
    private JDateChooser startDateChooser = new JDateChooser();
    private JDateChooser finishDateChooser = new JDateChooser();

    AddOrderFrame(Window owner) {

        super(owner, ModalityType.DOCUMENT_MODAL);

        StudentDAO studentDAO = LoginFrame.getDaoFactory().getStudentDAO();
        bookDAO = LoginFrame.getDaoFactory().getBookDAO();
        orderDAO = LoginFrame.getDaoFactory().getOrderDAO();

        JLabel[] labels = new JLabel[4];
        labels[0] = new JLabel("Студент:");
        labels[1] = new JLabel("Книга:");
        labels[2] = new JLabel("Дата оформления:");
        labels[3] = new JLabel("Дата возврата:");

        ArrayList<Student> students = new ArrayList<>(studentDAO.findAll());
        Collections.sort(students, (o1, o2) -> o1.getFullName().compareTo(o2.getFullName()));
        studentComboBox.removeAllItems();
        for (Student student : students)
            studentComboBox.addItem(student);

        ArrayList<Book> books = new ArrayList<>(bookDAO.findAll());
        Collections.sort(books, (o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
        bookComboBox.removeAllItems();
        for (Book book : books)
            bookComboBox.addItem(book);

        setTitle("Добавить заказ");
        ImageIcon addIcon = new ImageIcon("images/20x20/add.png");
        setIconImage(addIcon.getImage());
        setLayout(new GridBagLayout());
        setResizable(false);

        for (int i = 0; i < labels.length; i++)
            add(labels[i], new GridBagConstraints(0, i, 1, 1, 1.0, 1.0,
                    GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
                    new Insets(10, 10, 2, 2), 0, 0));

        studentComboBox.setPreferredSize(new Dimension(200, 30));
        JButton addStudentButton = new JButton(addIcon);
        addStudentButton.setPreferredSize(new Dimension(30, 30));
        add(studentComboBox, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));
        add(addStudentButton, new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));

        bookComboBox.setPreferredSize(new Dimension(200, 30));
        JButton addBookButton = new JButton(addIcon);
        addBookButton.setPreferredSize(new Dimension(30, 30));
        add(bookComboBox, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));
        add(addBookButton, new GridBagConstraints(2, 1, 1, 1, 1.0, 1.0,
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

        JPanel panel = new JPanel(new FlowLayout());
        JButton okButton = new JButton("Ок");
        panel.add(okButton);
        JButton cancelButton = new JButton("Отмена");
        panel.add(cancelButton);
        add(panel, new GridBagConstraints(0, 4, 3, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        ((JTextFieldDateEditor) startDateChooser.getDateEditor()).setEditable(false);
        ((JTextFieldDateEditor) finishDateChooser.getDateEditor()).setEditable(false);

        startDateChooser.setMinSelectableDate(new Date());
        finishDateChooser.setMinSelectableDate(new Date());

        pack();
        setLocationRelativeTo(null);

        okButton.addActionListener(e -> saveChanges());

        cancelButton.addActionListener(e -> {
            dispose();
            setVisible(false);
        });

        addStudentButton.addActionListener(e -> new AddStudentFrame(this));
        addBookButton.addActionListener(e -> new AddBookFrame(this));

        addWindowListener(getWindowAdapter(this));
        setVisible(true);
    }

    public static JComboBox<Student> getStudentComboBox() {
        return studentComboBox;
    }

    public static JComboBox<Book> getBookComboBox() {
        return bookComboBox;
    }

    @Override
    public void saveChanges() {

        Date startDate = startDateChooser.getDate();
        Date finishDate = finishDateChooser.getDate();

        if (bookDAO.selectAmount((Book) bookComboBox.getSelectedItem()) == 0) {
            JOptionPane.showMessageDialog(null, "Все экземпляры нужной Вам книги отсутствуют в библиотеке", "Отсутствует", JOptionPane.WARNING_MESSAGE);
            return;
        }
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
        switch (JOptionPane.showOptionDialog(null, "Добавить заказ?", "Добавить?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
            case JOptionPane.YES_OPTION:

                Librarian librarian = LoginFrame.getLoggedLibrarian();
                int id = Integer.parseInt(MainFrame.orderTable.getValueAt(MainFrame.orderTable.getRowCount() - 1, 0).toString());

                Order order = new Order();
                order.setId(++id);
                order.setStudent((Student) studentComboBox.getSelectedItem());
                order.setBook((Book) bookComboBox.getSelectedItem());
                order.setLibrarian(librarian);
                order.setStartDate(startDate);
                order.setFinishDate(finishDate);
                order.setStatus("Не возвращена");

                orderDAO.save(order);
                librarian.getOrders().add(order);

                MainFrame.orderTableModel.addOrderData(librarian.getOrders());
                MainFrame.orderTable.updateUI();
                MainFrame.bookTableModel.addBookData(bookDAO.findAll());
                MainFrame.bookTable.updateUI();
                JOptionPane.showMessageDialog(null, "Добавление успешно", "Добавлено", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                setVisible(false);

            case JOptionPane.NO_OPTION:
                break;
        }
    }
}