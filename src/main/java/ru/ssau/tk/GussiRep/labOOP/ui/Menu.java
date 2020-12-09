package ru.ssau.tk.GussiRep.labOOP.ui;

import javax.swing.*;

public class Menu extends JFrame {
    JMenu menu = new JMenu("Function");
    JMenuBar jMenuBar = new JMenuBar();

    Menu(String s) {
        super(s);
        menu.add(createTabulatedFunction());
        menu.add(createMathFunction());
        jMenuBar.add(menu);
        setJMenuBar(jMenuBar);
        setSize(400, 400);
    }

    private JMenu createTabulatedFunction() {
        JMenu tabulatedFunction = new JMenu("TabulatedFunction");
        tabulatedFunction.add(new JMenuItem("Ввести количество точек"));
        return tabulatedFunction;
    }

    private JMenu createMathFunction() {
        JMenu mathFunction = new JMenu("MathFunction");
        mathFunction.add(new JMenuItem("Квадратичная функция"));
        mathFunction.add(new JMenuItem("Функция арксинуса"));
        mathFunction.add(new JMenuItem("Функция корня"));
        mathFunction.add(new JMenuItem("Тождественная функция"));
        return mathFunction;
    }

}
