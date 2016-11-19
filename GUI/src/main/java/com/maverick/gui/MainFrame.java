package com.maverick.gui;

import com.maverick.domain.Book;
import com.maverick.domain.Librarian;
import com.maverick.gui.model.BookTableModel;
import com.maverick.gui.model.OrderTableModel;
import com.maverick.oldDAO.entitydao.BookDAO;
import com.maverick.patterns.memnto.CareTaker;
import com.maverick.patterns.memnto.Originator;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Comparator;
import java.util.List;

public class MainFrame extends JFrame {

    public static BookTableModel bookTableModel;
    public static JTable bookTable;
    public static OrderTableModel orderTableModel;
    public static JTable orderTable;
    private static JScrollPane bookScrollPane;
    private static JScrollPane orderScrollPane;
    private int selectedRow;
    private int indexState = -1;
    private Originator originator = new Originator();
    private CareTaker careTaker = new CareTaker();
    private BookDAO bookDAO;
    private List<Book> books;
    private Librarian librarian = (Librarian) LoginFrame.librarianComboBox.getSelectedItem();
    private JTabbedPane tabbedPane = new JTabbedPane();

    private JMenuBar menuBar = new JMenuBar();

    private JMenu bookMenu = new JMenu("Книги");
    private JMenuItem addBookMenuItem = new JMenuItem("Добавить");
    private JMenuItem deleteBookMenuItem = new JMenuItem("Удалить");
    private JMenuItem editBookMenuItem = new JMenuItem("Изменить");
    private JMenuItem searchBookMenuItem = new JMenuItem("Поиск");
    private JMenuItem refreshBookMenuItem = new JMenuItem("Сброс");
    private JMenuItem undoBookMenuItem = new JMenuItem("Backward");

    private JMenu orderMenu = new JMenu("Заказы");
    private JMenuItem addOrderMenuItem = new JMenuItem("Добавить");
    private JMenuItem editOrderMenuItem = new JMenuItem("Изменить");
    private JMenuItem searchOrderMenuItem = new JMenuItem("Поиск");
    private JMenuItem refreshOrderMenuItem = new JMenuItem("Сброс");

    private JMenu migrationMenu = new JMenu("Миграция");

    private JMenu mySQLToMongoMenuItem = new JMenu("Из MySQL в MongoDB");
    private JMenuItem mySQLToMongoAuthorMenuItem = new JMenuItem("Авторы");
    private JMenuItem mySQLToMongoBookMenuItem = new JMenuItem("Книги");
    private JMenuItem mySQLToMongoOrderMenuItem = new JMenuItem("Заказы");
    private JMenuItem mySQLToMongoStudentMenuItem = new JMenuItem("Студенты");

    private JMenu mongoToMySQLMenuItem = new JMenu("Из MongoDB в MySQL");
    private JMenuItem mongoToMySQLAuthorMenuItem = new JMenuItem("Авторы");
    private JMenuItem mongoToMySQLBookMenuItem = new JMenuItem("Книги");
    private JMenuItem mongoToMySQLOrderMenuItem = new JMenuItem("Заказы");
    private JMenuItem mongoToMySQLStudentMenuItem = new JMenuItem("Студенты");

    private JMenu userMenu = new JMenu(LoginFrame.librarianComboBox.getSelectedItem().toString());
    private JMenuItem changeUserMenuItem = new JMenuItem("Сменить пользователя");

    public MainFrame() {

        bookDAO = LoginFrame.getDaoFactory().getBookDAO();

        bookTableModel = new BookTableModel();
        bookTable = new JTable(bookTableModel);
        bookScrollPane = new JScrollPane(bookTable);

        books = bookDAO.selectBooks();

        bookTableModel.addBookData(books);


        TableRowSorter<BookTableModel> bookRowSorter = new TableRowSorter<>(bookTableModel);
        bookTable.setRowSorter(bookRowSorter);

        for (int i = 0; i <= 6; i += 3) {

            bookRowSorter.setComparator(i, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    try {
                        Integer integer1 = Integer.parseInt(o1);
                        Integer integer2 = Integer.parseInt(o2);
                        return integer1.compareTo(integer2);
                    } catch (Exception e) {
                        return o1.compareTo(o2);
                    }
                }
            });
        }

        orderTableModel = new OrderTableModel();
        orderTable = new JTable(orderTableModel);
        orderScrollPane = new JScrollPane(orderTable);
        orderTableModel.addOrderData(librarian.getOrders());

        TableRowSorter<OrderTableModel> orderRowSorter = new TableRowSorter<>(orderTableModel);
        orderTable.setRowSorter(orderRowSorter);

        orderRowSorter.setComparator(0, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                try {
                    Integer integer1 = Integer.parseInt(o1);
                    Integer integer2 = Integer.parseInt(o2);
                    return integer1.compareTo(integer2);
                } catch (Exception e) {
                    return o1.compareTo(o2);
                }
            }
        });

        for (int i = 3; i <= 4; i++) {
            orderRowSorter.setComparator(i, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    String[] split1 = o1.split("\\.");
                    String[] split2 = o2.split("\\.");

                    if (split1[2].compareTo(split2[2]) == 0) {
                        if (split1[1].compareTo(split2[1]) == 0) {
                            return split1[0].compareTo(split2[0]);
                        } else return split1[1].compareTo(split2[1]);
                    } else return split1[2].compareTo(split2[2]);
                }
            });
        }


        menuBar.add(bookMenu);
        bookMenu.add(addBookMenuItem);
        bookMenu.add(deleteBookMenuItem);
        bookMenu.add(editBookMenuItem);
        bookMenu.add(searchBookMenuItem);
        bookMenu.add(refreshBookMenuItem);
        bookMenu.add(undoBookMenuItem);


        menuBar.add(orderMenu);
        orderMenu.add(addOrderMenuItem);
        orderMenu.add(editOrderMenuItem);
        orderMenu.add(searchOrderMenuItem);
        orderMenu.add(refreshOrderMenuItem);

        menuBar.add(migrationMenu);
        migrationMenu.add(mySQLToMongoMenuItem);
        mySQLToMongoMenuItem.add(mySQLToMongoAuthorMenuItem);
        mySQLToMongoMenuItem.add(mySQLToMongoBookMenuItem);
        mySQLToMongoMenuItem.add(mySQLToMongoOrderMenuItem);
        mySQLToMongoMenuItem.add(mySQLToMongoStudentMenuItem);

        migrationMenu.add(mongoToMySQLMenuItem);
        mongoToMySQLMenuItem.add(mongoToMySQLAuthorMenuItem);
        mongoToMySQLMenuItem.add(mongoToMySQLBookMenuItem);
        mongoToMySQLMenuItem.add(mongoToMySQLOrderMenuItem);
        mongoToMySQLMenuItem.add(mongoToMySQLStudentMenuItem);

        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(userMenu);
        userMenu.add(changeUserMenuItem);

        setJMenuBar(menuBar);

        editBookMenuItem.setEnabled(false);
        deleteBookMenuItem.setEnabled(false);

        setTitle("Главное окно");
        setIconImage(new ImageIcon("images/bookOnFrame.png").getImage());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);

        bookScrollPane.setPreferredSize(new Dimension(1000, 500));
        bookTable.setPreferredSize(new Dimension(720, 500));
        bookTable.setRowHeight(24);
        bookTable.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        bookTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        bookTable.getTableHeader().setReorderingAllowed(false);
        bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        orderScrollPane.setPreferredSize(new Dimension(1000, 500));
        orderTable.setPreferredSize(new Dimension(720, 500));
        orderTable.setRowHeight(24);
        orderTable.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        orderTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        orderTable.getTableHeader().setReorderingAllowed(false);
        orderTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


        add(tabbedPane, new GridBagConstraints(0, 0, 2, 5, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        tabbedPane.addTab("Книги", new ImageIcon("images/20x20/book.png"), bookScrollPane);
        tabbedPane.addTab("Заказы", new ImageIcon("images/20x20/order.png"), orderScrollPane);

        pack();
        setLocationRelativeTo(null);

        final MainFrame mainFrame = this;

        addBookMenuItem.addActionListener(e -> new AddBookFrame(mainFrame));


        deleteBookMenuItem.addActionListener(e -> {

            switch (JOptionPane.showConfirmDialog(null, "Вы действительно хотите удалить данные записи?", "Подтвердите", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                case JOptionPane.OK_OPTION:

                    for (int i : bookTable.getSelectedRows()) {


                        Book book = new Book();
                        book.setId(Integer.parseInt(bookTable.getValueAt(i, 0).toString()));

                        if (!bookDAO.deleteBook(book)) {
                            JOptionPane.showMessageDialog(null, "Нельзя удалить книгу " +
                                    bookTable.getValueAt(bookTable.getSelectedRow(), 1) +
                                    ' ' +
                                    bookTable.getValueAt(bookTable.getSelectedRow(), 2) +
                                    ",пока есть заказы на неё", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                    }

                    selectedRow = bookTable.getSelectedRow();

                    Book book = books.get(selectedRow);
                    books.remove(selectedRow);

                    originator.setState(book);
                    careTaker.add(originator.saveStateToMemento());

                    indexState++;

                    bookTableModel.addBookData(books);
                    bookTable.updateUI();
                    bookTable.setRowSelectionInterval(0, 0);
                    JOptionPane.showMessageDialog(null, "Удаление успешно", "Выполнено", JOptionPane.INFORMATION_MESSAGE);
                case JOptionPane.CANCEL_OPTION:
                    break;
            }

        });

        editBookMenuItem.addActionListener(e -> {

            Book book = new Book();
            book.setId(Integer.parseInt(bookTable.getValueAt(bookTable.getSelectedRow(), 0).toString()));

            if (bookTable.getSelectedRows().length > 1) {
                JOptionPane.showMessageDialog(null, "Нельзя редактировать более одной строки!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            } else if (bookDAO.isOrdered(book)) {
                JOptionPane.showMessageDialog(null, "Нельзя редактировать книгу, на которую есть заказы", "Ошибка", JOptionPane.ERROR_MESSAGE);
            } else {
                new EditBookFrame(mainFrame);
            }
        });

        searchBookMenuItem.addActionListener(e -> new SearchBookFrame(mainFrame));

        refreshBookMenuItem.addActionListener(e -> {
            switch (JOptionPane.showConfirmDialog(null, "Обновить таблицу?", "Обновить", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                case JOptionPane.OK_OPTION:
                    bookTableModel.addBookData(bookDAO.selectBooks());
                    break;
                case JOptionPane.CANCEL_OPTION:
                    break;
            }
        });

        addOrderMenuItem.addActionListener(e -> new AddOrderFrame(mainFrame));

        editOrderMenuItem.addActionListener(e -> {

            boolean isSelected = false;

            for (int i = 0; i < orderTable.getRowCount(); i++) {
                if (orderTable.isRowSelected(i)) {
                    isSelected = true;
                    break;
                }
            }

            if (!isSelected) {
                JOptionPane.showMessageDialog(null, "Выделите строку для редактирования!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            } else if (orderTable.getSelectedRows().length > 1) {
                JOptionPane.showMessageDialog(null, "Нельзя редактировать более одной строки!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            } else if (orderTable.getValueAt(orderTable.getSelectedRow(), 5).toString().equals("Возвращена")) {
                JOptionPane.showMessageDialog(null, "Нельзя редактировать заказ,  который возвращён", "Ошибка", JOptionPane.ERROR_MESSAGE);
            } else {
                new EditOrderFrame(mainFrame);
            }
        });


        searchOrderMenuItem.addActionListener(e -> new SearchOrderFrame(mainFrame));

        refreshOrderMenuItem.addActionListener(e -> {
            Object[] options = {"Да", "Нет"};
            switch (JOptionPane.showOptionDialog(null, "Обновить таблицу?", "Обновить?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
                case JOptionPane.OK_OPTION:
                    orderTableModel.addOrderData(librarian.getOrders());
                    break;
                case JOptionPane.CANCEL_OPTION:
                    break;
            }
        });

        bookTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                editBookMenuItem.setEnabled(true);
                deleteBookMenuItem.setEnabled(true);
            }

        });


//        mySQLToMongoAuthorMenuItem.addActionListener(e -> {
//
//            Object[] options = { "Да", "Нет" };
//            switch (JOptionPane.showOptionDialog(null, "Действительно перенести авторов из MYSQL в MongoDB?", "Перенести?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
//                case JOptionPane.OK_OPTION:
//                  //  mysqlDao.initMigrateDB();
//                  //  int migrate = LoginFrame.getDaoInstance().migrateAuthors();
//                 //   JOptionPane.showMessageDialog(null, "Миграция завершена\nПеренесено " + migrate + " записей", "Завершено",JOptionPane.INFORMATION_MESSAGE);
//                    break;
//                case JOptionPane.CANCEL_OPTION:
//                    break;
//            }
//        });
//
//        mySQLToMongoBookMenuItem.addActionListener(e -> {
//            Object[] options = { "Да", "Нет" };
//            switch (JOptionPane.showOptionDialog(null, "Действительно перенести книги из MYSQL в MongoDB?", "Перенести?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
//                case JOptionPane.OK_OPTION:
//
//                    int rowCount = bookTable.getRowCount();
//                 //   mysqlDao.initMigrateDB();
//                  //  int migrate = LoginFrame.getDaoInstance().migrateBooks();
//                    bookTableModel.addBookData(LoginFrame.getDaoInstance().selectBooks());
//
//                 //   JOptionPane.showMessageDialog(null, "Миграция завершена\nПеренесено " + (rowCount - migrate) + " записей\n" +
//                 //           "Не перенесено " + migrate + " записей", "Завершено",JOptionPane.INFORMATION_MESSAGE);
//                    break;
//                case JOptionPane.CANCEL_OPTION:
//                    break;
//            }
//
//        });
//
//        mySQLToMongoOrderMenuItem.addActionListener(e -> {
//            Object[] options = { "Да", "Нет" };
//            switch (JOptionPane.showOptionDialog(null, "Действительно перенести заказы из MYSQL в MongoDB?", "Перенести?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
//                case JOptionPane.OK_OPTION:
////                    int migrate = mysqlDao.migrateOrders(librarian);
//                    orderTableModel.addOrderData(LoginFrame.getDaoInstance().selectOrdersByLibrarian(librarian));
//                  //  JOptionPane.showMessageDialog(null, "Миграция завершена\nПеренесено " + migrate + " записей", "Завершено",JOptionPane.INFORMATION_MESSAGE);
//                    break;
//                case JOptionPane.CANCEL_OPTION:
//                    break;
//            }
//
//        });
//
//        mySQLToMongoStudentMenuItem.addActionListener(e -> {
//            Object[] options = { "Да", "Нет" };
//            switch (JOptionPane.showOptionDialog(null, "Действительно перенести студентов из MYSQL в MongoDB?", "Перенести?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
//                case JOptionPane.OK_OPTION:
//                  //  mysqlDao.initMigrateDB();
//                    int migrate = LoginFrame.getDaoInstance().migrateStudents();
//                    JOptionPane.showMessageDialog(null, "Миграция завершена\nПеренесено " + migrate + " записей", "Завершено",JOptionPane.INFORMATION_MESSAGE);
//                    break;
//                case JOptionPane.CANCEL_OPTION:
//                    break;
//            }
//
//        });
//
//
//
//        mongoToMySQLAuthorMenuItem.addActionListener(e -> {
//
//            Object[] options = { "Да", "Нет" };
//            switch (JOptionPane.showOptionDialog(null, "Действительно перенести авторов из MongoDB в MYSQL?", "Перенести?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
//                case JOptionPane.OK_OPTION:
//                    int migrate = mongoDao.migrateAuthors();
//                    JOptionPane.showMessageDialog(null, "Миграция завершена\nПеренесено " + migrate + " записей\n", "Завершено",JOptionPane.INFORMATION_MESSAGE);
//                    break;
//                case JOptionPane.CANCEL_OPTION:
//                    break;
//            }
//
//        });
//
//        mongoToMySQLBookMenuItem.addActionListener(e -> {
//            Object[] options = { "Да", "Нет" };
//            switch (JOptionPane.showOptionDialog(null, "Действительно перенести книги из MongoDB в MYSQL?", "Перенести?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
//                case JOptionPane.OK_OPTION:
//                    int migrate = mongoDao.migrateBooks();
//                    bookTableModel.addBookData(LoginFrame.getDaoInstance().selectBooks());
//                    JOptionPane.showMessageDialog(null, "Миграция завершена\nПеренесено " + migrate + " записей\n", "Завершено",JOptionPane.INFORMATION_MESSAGE);
//                    break;
//                case JOptionPane.CANCEL_OPTION:
//                    break;
//            }
//
//        });
//
//        mongoToMySQLOrderMenuItem.addActionListener(e -> {
//            Object[] options = { "Да", "Нет" };
//            switch (JOptionPane.showOptionDialog(null, "Действительно перенести заказы из MongoDB в MYSQL?", "Перенести?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
//                case JOptionPane.OK_OPTION:
//                    int migrate = mongoDao.migrateOrders(librarian);
//                    orderTableModel.addOrderData(LoginFrame.getDaoInstance().selectOrdersByLibrarian(librarian));
//                    JOptionPane.showMessageDialog(null, "Миграция завершена\nПеренесено " + migrate + " записей\n", "Завершено",JOptionPane.INFORMATION_MESSAGE);
//                    break;
//                case JOptionPane.CANCEL_OPTION:
//                    break;
//            }
//
//        });
//
//        mongoToMySQLStudentMenuItem.addActionListener(e -> {
//
//            Object[] options = { "Да", "Нет" };
//            switch (JOptionPane.showOptionDialog(null, "Действительно перенести студентов из MongoDB в MYSQL?", "Перенести?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
//                case JOptionPane.OK_OPTION:
//                    int migrate = mongoDao.migrateStudents();
//                    JOptionPane.showMessageDialog(null, "Миграция завершена\nПеренесено " + migrate + " записей\n", "Завершено",JOptionPane.INFORMATION_MESSAGE);
//                    break;
//                case JOptionPane.CANCEL_OPTION:
//                    break;
//            }
//               });

        changeUserMenuItem.addActionListener(e -> {
            dispose();
            setVisible(false);
            new LoginFrame();
        });

        undoBookMenuItem.addActionListener(e -> {
            if (indexState == -1) {
                JOptionPane.showMessageDialog(null, "нет состояний для возврата", "Завершено", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            originator.getStateFromMemento(careTaker.get(indexState--));
            books.add(selectedRow, originator.getState());
            bookTableModel.addBookData(books);
            bookTable.updateUI();

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
}
