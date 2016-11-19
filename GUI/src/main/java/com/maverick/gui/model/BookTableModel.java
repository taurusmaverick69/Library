package com.maverick.gui.model;

import com.maverick.domain.Book;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class BookTableModel extends AbstractTableModel {

    private int columnCount;
    private ArrayList<String[]> arrayList;

    public BookTableModel() {
        columnCount = 7;
        arrayList = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.add(new String[getColumnCount()]);
        }
    }

    public void addBookData(List<Book> books) {

        arrayList.clear();
        for (Book book : books) {

            String[] rowTable = {
                    Integer.toString(book.getId()),
                    book.getAuthor().getFullName(),
                    book.getTitle(),
                    Integer.toString(book.getPublishingYear()),
                    book.getGenre().getName(),
                    book.getPublisher().getName(),
                    Integer.toString(book.getAmount())};

            arrayList.add(rowTable);
        }
    }

    public int getRowCount() {
        return arrayList.size();
    }

    public int getColumnCount() {
        return columnCount;
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "#";
            case 1:
                return "Автор";
            case 2:
                return "Название";
            case 3:
                return "Год издательства";
            case 4:
                return "Жанр";
            case 5:
                return "Издательство";
            case 6:
                return "Количество";
        }
        return "";
    }

    public String getValueAt(int rowIndex, int columnIndex) {
        String[] strings = arrayList.get(rowIndex);
        return strings[columnIndex];
    }

}
