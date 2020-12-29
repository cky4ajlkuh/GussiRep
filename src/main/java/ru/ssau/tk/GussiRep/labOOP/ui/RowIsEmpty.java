package ru.ssau.tk.GussiRep.labOOP.ui;

import javax.swing.*;
import java.io.Serial;

public class RowIsEmpty extends Exception {
    @Serial
    private static final long serialVersionUID = 8210089719489164073L;

    public RowIsEmpty() {
        super();
    }

    public RowIsEmpty(String message) {
        super(message);
        JOptionPane.showMessageDialog(null, "Отсутствую значения в таблице!");

    }
}
