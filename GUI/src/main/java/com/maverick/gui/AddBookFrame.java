package com.maverick.gui;

import com.maverick.domain.Author;
import com.maverick.domain.Book;
import com.maverick.domain.Genre;
import com.maverick.domain.Publisher;
import com.maverick.oldDAO.DAOFactory;
import com.maverick.oldDAO.entitydao.AuthorDAO;
import com.maverick.oldDAO.entitydao.BookDAO;
import com.maverick.oldDAO.entitydao.GenreDAO;
import com.maverick.oldDAO.entitydao.PublisherDAO;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddBookFrame extends JDialog implements WindowClosing {

    private static JComboBox<Author> authorComboBox = new JComboBox<>();
    private static JComboBox<Genre> genreComboBox = new JComboBox<>();
    private static JComboBox<Publisher> publisherComboBox = new JComboBox<>();
    private BookDAO bookDAO;
    private JTextField titleTextField = new JTextField();
    private JTextField publishingYearTextField;
    private JTextField amountTextField = new JTextField();

    AddBookFrame(Window owner) {

        super(owner, ModalityType.DOCUMENT_MODAL);

        DAOFactory daoFactory = LoginFrame.getDaoFactory();

        bookDAO = daoFactory.getBookDAO();
        AuthorDAO authorDAO = daoFactory.getAuthorDAO();
        GenreDAO genreDAO = daoFactory.getGenreDAO();
        PublisherDAO publisherDAO = daoFactory.getPublisherDAO();

        JLabel[] labels = new JLabel[6];
        labels[0] = new JLabel("Автор:");
        labels[1] = new JLabel("Название:");
        labels[2] = new JLabel("Год издательства:");
        labels[3] = new JLabel("Жанр:");
        labels[4] = new JLabel("Издательство:");
        labels[5] = new JLabel("Количество:");

        try {
            publishingYearTextField = new JFormattedTextField(new MaskFormatter("####"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ((AbstractDocument) amountTextField.getDocument()).setDocumentFilter(new DocumentFilter() {
            Pattern regEx = Pattern.compile("\\d+");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if (!matcher.matches()) {
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        authorComboBox.removeAllItems();
        authorDAO.findAll().stream().sorted((o1, o2) -> o1.getFullName().compareTo(o2.getFullName())).forEach(author -> authorComboBox.addItem(author));

        genreComboBox.removeAllItems();
        genreDAO.findAll().stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).forEach(genre -> genreComboBox.addItem(genre));

        publisherComboBox.removeAllItems();
        publisherDAO.findAll().stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).forEach(publisher -> publisherComboBox.addItem(publisher));

        setTitle("Добавить книгу");
        ImageIcon addIcon = new ImageIcon("images/20x20/add.png");
        setIconImage(addIcon.getImage());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);

        for (int i = 0; i < labels.length; i++)
            add(labels[i], new GridBagConstraints(0, i, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(10, 10, 2, 2), 0, 0));

        authorComboBox.setPreferredSize(new Dimension(200, 30));
        JButton addAuthorButton = new JButton(addIcon);
        addAuthorButton.setPreferredSize(new Dimension(30, 30));
        add(authorComboBox, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));
        add(addAuthorButton, new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));

        genreComboBox.setPreferredSize(new Dimension(200, 30));
        JButton addGenreButton = new JButton(addIcon);
        addGenreButton.setPreferredSize(new Dimension(30, 30));
        add(genreComboBox, new GridBagConstraints(1, 3, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));
        add(addGenreButton, new GridBagConstraints(2, 3, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));

        publisherComboBox.setPreferredSize(new Dimension(200, 30));
        JButton addPublisherButton = new JButton(addIcon);
        addPublisherButton.setPreferredSize(new Dimension(30, 30));
        add(publisherComboBox, new GridBagConstraints(1, 4, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));
        add(addPublisherButton, new GridBagConstraints(2, 4, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));


        titleTextField.setPreferredSize(new Dimension(200, 30));
        add(titleTextField, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));

        publishingYearTextField.setPreferredSize(new Dimension(200, 30));
        add(publishingYearTextField, new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));


        amountTextField.setPreferredSize(new Dimension(200, 30));
        add(amountTextField, new GridBagConstraints(1, 5, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));


        JPanel panel = new JPanel(new FlowLayout());
        JButton okButton = new JButton("Ок");
        panel.add(okButton);
        JButton cancelButton = new JButton("Отмена");
        panel.add(cancelButton);
        add(panel, new GridBagConstraints(0, 6, 3, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));


        pack();
        setLocationRelativeTo(null);

        okButton.addActionListener(e -> saveChanges());

        cancelButton.addActionListener(e -> {
            dispose();
            setVisible(false);
        });

        addAuthorButton.addActionListener(e -> new AddAuthorFrame(this));

        addGenreButton.addActionListener(e -> new AddGenreFrame(this));

        addPublisherButton.addActionListener(e -> new AddPublisherFrame(this));

        addWindowListener(getWindowAdapter(this));
        setVisible(true);
    }

    public static JComboBox<Author> getAuthorComboBox() {
        return authorComboBox;
    }

    public static JComboBox<Genre> getGenreComboBox() {
        return genreComboBox;
    }

    public static JComboBox<Publisher> getPublisherComboBox() {
        return publisherComboBox;
    }

    @Override
    public void saveChanges() {
        if (titleTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Поле 'Название' должно быть заполнено!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (publishingYearTextField.getText().isEmpty() || publishingYearTextField.getText().equals("    ")) {
            JOptionPane.showMessageDialog(null, "Поле 'Год издательства' должно быть заполнено!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (amountTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Поле 'Количество' должно быть заполнено!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (amountTextField.getText().length() > 9) {
            JOptionPane.showMessageDialog(null, "Поле 'Количество' не более 9 символов!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object[] options = {"Да", "Нет"};
        switch (JOptionPane.showOptionDialog(null, "Действительно добавить книгу?", "Добавить?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
            case JOptionPane.YES_OPTION:

                Book book = new Book();
                book.setAuthor((Author) authorComboBox.getSelectedItem());
                book.setTitle(titleTextField.getText());
                book.setPublishingYear(Integer.parseInt(publishingYearTextField.getText()));
                book.setGenre((Genre) genreComboBox.getSelectedItem());
                book.setPublisher((Publisher) publisherComboBox.getSelectedItem());
                book.setAmount(Integer.parseInt(amountTextField.getText()));

                if (!bookDAO.save(book)) {
                    JOptionPane.showMessageDialog(null, "Данная запись уже существует в БД", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(null, "Добавление успешно", "Успешно", JOptionPane.INFORMATION_MESSAGE);

                List<Book> books = bookDAO.findAll();
                ArrayList<Book> booksList = new ArrayList<>(books);
                // TODO: 19.12.2016 WTF?

                MainFrame.bookTableModel.addBookData(books);
                MainFrame.bookTable.updateUI();

                Collections.sort(booksList, (o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
                AddOrderFrame.getBookComboBox().removeAllItems();
                for (Book anotherBook : books)
                    AddOrderFrame.getBookComboBox().addItem(anotherBook);

                dispose();
                setVisible(false);

            case JOptionPane.NO_OPTION:
                break;
        }
    }
}
