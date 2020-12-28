package ru.ssau.tk.GussiRep.labOOP.exceptions;

import javax.swing.*;

public class ArrayIsNotSortedException extends RuntimeException {
    private static final long serialVersionUID = -529930192912911674L;

    public ArrayIsNotSortedException() {
        super();
    }

    public ArrayIsNotSortedException(String message) {
        super(message);
        JOptionPane.showMessageDialog(null, "Массив знечений не отсортирован!");
    }
}
