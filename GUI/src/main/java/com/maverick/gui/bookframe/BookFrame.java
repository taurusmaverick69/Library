package com.maverick.gui.bookframe;

import com.maverick.domain.Author;
import com.maverick.domain.Genre;
import com.maverick.domain.Publisher;
import com.maverick.gui.WindowClosing;

import javax.swing.*;
import java.awt.*;

public interface BookFrame extends WindowClosing {

    JComboBox<Author> authorComboBox = new JComboBox<>();
    JComboBox<Genre> genreComboBox = new JComboBox<>();
    JComboBox<Publisher> publisherComboBox = new JComboBox<>();

    JTextField titleTextField = new JTextField();
    JTextField publishingYearTextField = new JFormattedTextField();
    JTextField amountTextField = new JTextField();

    ImageIcon addIcon = new ImageIcon("images/20x20/add.png");
    JButton addPublisherButton = new JButton(addIcon);
    JButton addAuthorButton = new JButton(addIcon);
    JButton addGenreButton = new JButton(addIcon);

    JPanel panel = new JPanel(new FlowLayout());
    JButton okButton = new JButton("Ок");
    JButton cancelButton = new JButton("Отмена");

    JLabel[] labels = {
            new JLabel("Автор:"),
            new JLabel("Название:"),
            new JLabel("Год издательства:"),
            new JLabel("Жанр:"),
            new JLabel("Издательство:"),
            new JLabel("Количество:")
    };

    default void clearFields() {
        authorComboBox.removeAllItems();
        genreComboBox.removeAllItems();
        publisherComboBox.removeAllItems();
        titleTextField.setText("");
        publishingYearTextField.setText("");
        amountTextField.setText("");
    }

    default void setFieldsSize() {
        authorComboBox.setPreferredSize(new Dimension(200, 30));
        genreComboBox.setPreferredSize(new Dimension(200, 30));
        publisherComboBox.setPreferredSize(new Dimension(200, 30));

        titleTextField.setPreferredSize(new Dimension(200, 30));
        publishingYearTextField.setPreferredSize(new Dimension(200, 30));
        amountTextField.setPreferredSize(new Dimension(200, 30));

        addPublisherButton.setPreferredSize(new Dimension(30, 30));
        addAuthorButton.setPreferredSize(new Dimension(30, 30));
        addGenreButton.setPreferredSize(new Dimension(30, 30));
    }

    default boolean isInputCorrect() {
        if (titleTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Поле 'Название' должно быть заполнено!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (publishingYearTextField.getText().isEmpty() || publishingYearTextField.getText().equals("    ")) {
            JOptionPane.showMessageDialog(null, "Поле 'Год издательства' должно быть заполнено!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (amountTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Поле 'Количество' должно быть заполнено!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (amountTextField.getText().length() > 9) {
            JOptionPane.showMessageDialog(null, "Поле 'Количество' не более 9 символов!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}
