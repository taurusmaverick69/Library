package com.maverick.gui.model;

import com.maverick.domain.Order;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderTableModel extends AbstractTableModel {

    private int columnCount;
    private ArrayList<String[]> arrayList;

    public OrderTableModel() {
        columnCount = 6;
        arrayList = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.add(new String[getColumnCount()]);
        }
    }

    public void addOrderData(List<Order> orders) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        arrayList.clear();

        for (Order order : orders) {

            String[] rowTable = {
                    Integer.toString(order.getId()),
                    order.getStudent().getFullName(),
                    order.getBook().getTitle(),
                    formatter.format(order.getStartDate()),
                    formatter.format(order.getFinishDate()),
                    order.getStatus()
            };

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
                return "ФИО студента";
            case 2:
                return "Книга";
            case 3:
                return "Дата оформления";
            case 4:
                return "Дата возврата";
            case 5:
                return "Статус";
        }
        return "";
    }

    public String getValueAt(int rowIndex, int columnIndex) {
        String[] strings = arrayList.get(rowIndex);
        return strings[columnIndex];
    }

}

