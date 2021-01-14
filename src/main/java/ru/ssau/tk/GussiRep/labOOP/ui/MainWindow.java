package ru.ssau.tk.GussiRep.labOOP.ui;

import ru.ssau.tk.GussiRep.labOOP.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;

public class MainWindow {
    public static TabulatedFunctionFactory factory;

    public static void setFactory(TabulatedFunctionFactory factory) {
        MainWindow.factory = factory;
    }

    public static void main(String[] args) {
        Menu menu = new Menu("Меню");
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setResizable(false);
        menu.setLocationRelativeTo(null);
    }
}
