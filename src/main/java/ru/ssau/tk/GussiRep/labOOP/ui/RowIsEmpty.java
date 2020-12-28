package ru.ssau.tk.GussiRep.labOOP.ui;

import javax.swing.*;

public class RowIsEmpty extends Exception {
    public RowIsEmpty() {

    }

    public RowIsEmpty(String message) {
        super(message);
        JOptionPane.showMessageDialog(null, "Отсутствую значения в таблице!");
    }
}
