package ru.ssau.tk.GussiRep.labOOP.ui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.Serial;
import java.util.List;

public class TableModel extends AbstractTableModel {

    @Serial
    private static final long serialVersionUID = -6914076134122253408L;
    private static final int X_COLUMN_NUMBER = 0;
    private static final int Y_COLUMN_NUMBER = 1;
    private final List<Double> stringsX;
    private final List<Double> stringsY;
    private int count;
    private JTable function;


    public TableModel(List<Double> stringsX, List<Double> stringsY) {
        this.stringsX = stringsX;
        this.stringsY = stringsY;
        this.count = stringsX.size();
    }

    public void setCount(int size) {
        count = size;
    }

    @Override
    public int getRowCount() {
        return count;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == X_COLUMN_NUMBER) {
            stringsX.set(rowIndex, Double.parseDouble(aValue.toString()));
        }
        if (columnIndex == Y_COLUMN_NUMBER) {
            stringsY.set(rowIndex, Double.parseDouble(aValue.toString()));
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == X_COLUMN_NUMBER) {
            return stringsX.get(rowIndex);
        }
        if (columnIndex == Y_COLUMN_NUMBER) {
            return stringsY.get(rowIndex);
        }

        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == X_COLUMN_NUMBER || columnIndex == Y_COLUMN_NUMBER) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case X_COLUMN_NUMBER -> "Value X";
            case Y_COLUMN_NUMBER -> "Value Y";

            default -> super.getColumnName(column);
        };
    }
        public JTable getFunction() {
            return function;
        }

        public void setFunction(JTable function) {
            this.function = function;
        }
    }
