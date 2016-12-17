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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditBookFrame extends JDialog implements WindowClosing {

    private BookDAO bookDAO;

    private JTextField titleTextField = new JTextField();
    private JTextField publishingYearTextField;
    private JTextField amountTextField = new JTextField();

    private JComboBox<Author> authorComboBox = new JComboBox<>();
    private JComboBox<Genre> genreComboBox = new JComboBox<>();
    private JComboBox<Publisher> publisherComboBox = new JComboBox<>();

    public EditBookFrame(Window owner) {

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
        for (Author author : authorDAO.findAll())
            authorComboBox.addItem(author);

        genreComboBox.removeAllItems();
        for (Genre genre : genreDAO.findAll())
            genreComboBox.addItem(genre);

        publisherComboBox.removeAllItems();
        for (Publisher publisher : publisherDAO.findAll())
            publisherComboBox.addItem(publisher);

        authorComboBox.setSelectedItem(MainFrame.bookTable.getValueAt(MainFrame.bookTable.getSelectedRow(), 1));
        genreComboBox.setSelectedItem(MainFrame.bookTable.getValueAt(MainFrame.bookTable.getSelectedRow(), 4));
        publisherComboBox.setSelectedItem(MainFrame.bookTable.getValueAt(MainFrame.bookTable.getSelectedRow(), 5));

        titleTextField.setText(MainFrame.bookTable.getValueAt(MainFrame.bookTable.getSelectedRow(), 2).toString());
        publishingYearTextField.setText(MainFrame.bookTable.getValueAt(MainFrame.bookTable.getSelectedRow(), 3).toString());
        amountTextField.setText(MainFrame.bookTable.getValueAt(MainFrame.bookTable.getSelectedRow(), 6).toString());

        setTitle("Редактировать книгу");
        ImageIcon editIcon = new ImageIcon("images/20x20/edit.png");
        setIconImage(editIcon.getImage());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);

        for (int i = 0; i < labels.length; i++)
            add(labels[i], new GridBagConstraints(0, i, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(10, 10, 2, 2), 0, 0));


        authorComboBox.setPreferredSize(new Dimension(200, 30));
        add(authorComboBox, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));

        genreComboBox.setPreferredSize(new Dimension(200, 30));
        add(genreComboBox, new GridBagConstraints(1, 3, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 2), 0, 0));

        publisherComboBox.setPreferredSize(new Dimension(200, 30));
        add(publisherComboBox, new GridBagConstraints(1, 4, 1, 1, 1.0, 1.0,
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

        addWindowListener(getWindowAdapter(this));
        setVisible(true);
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
        switch (JOptionPane.showOptionDialog(null, "Сохранить изменения?", "Добавить?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
            case JOptionPane.OK_OPTION:
                Book book = new Book(
                        Integer.parseInt(MainFrame.bookTable.getValueAt(MainFrame.bookTable.getSelectedRow(), 0).toString()),
                        (Author) authorComboBox.getSelectedItem(),
                        titleTextField.getText(),
                        Integer.parseInt(publishingYearTextField.getText()),
                        (Genre) genreComboBox.getSelectedItem(),
                        (Publisher) publisherComboBox.getSelectedItem(),
                        Integer.parseInt(amountTextField.getText())
                );
                bookDAO.update(book);
                MainFrame.bookTableModel.addBookData(bookDAO.findAll());
                MainFrame.bookTable.updateUI();
                JOptionPane.showMessageDialog(null, "Редактирование успешно", "Успешно", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                setVisible(false);

            case JOptionPane.NO_OPTION:
                break;
        }
    }
}
