package ru.ssau.tk.GussiRep.labOOP.ui;

import javax.swing.*;

public class MainWindow {
    public static void main(String[] args) {
        Menu menu = new Menu("Menu Function");
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setResizable(false);
        menu.setLocationRelativeTo(null);
    }
}
