package ru.ssau.tk.GussiRep.labOOP.ui.Exceptions;

import javax.swing.*;

public class TablesIsEmpty extends Exception {
    public TablesIsEmpty(){
        super();
        JOptionPane.showMessageDialog(null, "Нельзя оперировать пустыми функциями!");
    }
}
