package com.maverick.gui;

import com.maverick.domain.Genre;
import com.maverick.domain.Publisher;
import com.maverick.domain.Author;
import com.maverick.domain.Book;
import com.maverick.oldDAO.entitydao.BookDAO;
import org.apache.commons.lang.math.NumberUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SearchBookFrame extends JDialog implements WindowClosing {

    private BookDAO bookDAO;

    private JTextField titleTextField = new JTextField();
    private JTextField publishingYearTextField = new JTextField();
    private JTextField amountTextField = new JTextField();

    private JTextField authorTextField = new JTextField();
    private JTextField genreTextField = new JTextField();
    private JTextField publisherTextField = new JTextField();

    public SearchBookFrame(Window owner) {

        super(owner, ModalityType.DOCUMENT_MODAL);

        bookDAO = LoginFrame.getDaoFactory().getBookDAO();

        JLabel[] labels = new JLabel[6];
        labels[0] = new JLabel("Автор:");
        labels[1] = new JLabel("Название:");
        labels[2] = new JLabel("Год издательства:");
        labels[3] = new JLabel("Жанр:");
        labels[4] = new JLabel("Издательство:");
        labels[5] = new JLabel("Количество:");


        setTitle("Поиск книги");
        setIconImage(new ImageIcon("images/search.png").getImage());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);

        for (int i = 0; i < labels.length; i++)
            add(labels[i], new GridBagConstraints(0, i, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                    new Insets(18, 10, 2, 2), 0, 0));


        authorTextField.setPreferredSize(new Dimension(200, 30));
        add(authorTextField, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(10, 0, 2, 10), 0, 0));

        genreTextField.setPreferredSize(new Dimension(200, 30));
        add(genreTextField, new GridBagConstraints(1, 3, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(10, 0, 2, 10), 0, 0));

        publisherTextField.setPreferredSize(new Dimension(200, 30));
        add(publisherTextField, new GridBagConstraints(1, 4, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(10, 0, 2, 10), 0, 0));


        titleTextField.setPreferredSize(new Dimension(200, 30));
        add(titleTextField, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(10, 0, 2, 10), 0, 0));

        publishingYearTextField.setPreferredSize(new Dimension(200, 30));
        add(publishingYearTextField, new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(10, 0, 2, 10), 0, 0));


        amountTextField.setPreferredSize(new Dimension(200, 30));
        add(amountTextField, new GridBagConstraints(1, 5, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(10, 0, 2, 10), 0, 0));


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

        Object[] options = {"Да", "Нет"};
        switch (JOptionPane.showOptionDialog(null, "Подтверждете поиск?", "Поиск?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
            case JOptionPane.YES_OPTION:

                Author author = new Author();
                author.setFullName(authorTextField.getText());

                Genre genre = new Genre();
                genre.setName(genreTextField.getText());

                Publisher publisher = new Publisher();
                publisher.setName(publisherTextField.getText());

                Book book = new Book(-1, author, titleTextField.getText(), NumberUtils.toInt(publishingYearTextField.getText(), -1), genre, publisher, (NumberUtils.toInt(amountTextField.getText(), -1)));
                List<Book> books = bookDAO.searchBook(book);

                if (books.isEmpty()) {
                    JOptionPane.showMessageDialog(null, " По вашему запросу не найдено совпадений", "Завершено", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    MainFrame.bookTableModel.addBookData(books);
                    MainFrame.bookTable.updateUI();
                    JOptionPane.showMessageDialog(null, "Найдено совпадений: " + MainFrame.bookTable.getRowCount(), "Завершено", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    setVisible(false);
                }

            case JOptionPane.NO_OPTION:
                break;
        }
    }
}