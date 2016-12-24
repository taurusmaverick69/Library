package com.maverick.gui.bookframe;

import com.maverick.domain.Author;
import com.maverick.domain.Genre;
import com.maverick.domain.Publisher;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface BookFrame {
    JComboBox<Author> authorComboBox = new JComboBox<>();
    JComboBox<Genre> genreComboBox = new JComboBox<>();
    JComboBox<Publisher> publisherComboBox = new JComboBox<>();

    JTextField titleTextField = new JTextField();
    JTextField publishingYearTextField = new JFormattedTextField();
    JTextField amountTextField = new JTextField();

    JLabel[] labels = new JLabel[6];

    default void clearFields() {
        authorComboBox.removeAllItems();
        genreComboBox.removeAllItems();
        publisherComboBox.removeAllItems();

        titleTextField.setText("");
        publishingYearTextField.setText("");
        amountTextField.setText("");
    }

    default void initAndPlaceStatic() {

        labels[0] = new JLabel("Автор:");
        labels[1] = new JLabel("Название:");
        labels[2] = new JLabel("Год издательства:");
        labels[3] = new JLabel("Жанр:");
        labels[4] = new JLabel("Издательство:");
        labels[5] = new JLabel("Количество:");

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
    }
}
