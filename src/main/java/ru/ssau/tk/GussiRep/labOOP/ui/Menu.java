package ru.ssau.tk.GussiRep.labOOP.ui;

import javax.swing.*;

public class Menu extends JFrame {
    JMenu menu = new JMenu("Function");
    JMenuBar jMenuBar = new JMenuBar();

    Menu(String s) {
        super(s);
        menu.add(new JMenuItem("TabulatedFunction"));
        menu.add(new JMenuItem("MathFunction"));
        jMenuBar.add(menu);
        setJMenuBar(jMenuBar);
        setSize(400, 400);
    }

}
