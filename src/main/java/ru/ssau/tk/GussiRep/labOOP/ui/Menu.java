package ru.ssau.tk.GussiRep.labOOP.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    JMenu menu = new JMenu("Functions");
    JMenuBar jMenuBar = new JMenuBar();
    final createZeroFunction function = new createZeroFunction("Нулевая функция");

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
        JMenuItem zero = new JMenuItem("Нулевая функция");
        zero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                setName("aaaaaa");
                function.setVisible(true);
            }
        });
        mathFunction.add(zero);
        mathFunction.add(new JMenuItem("Квадратичная функция"));
        mathFunction.add(new JMenuItem("Функция арксинуса"));
        mathFunction.add(new JMenuItem("Функция корня"));
        mathFunction.add(new JMenuItem("Тождественная функция"));

        return mathFunction;
    }


    static class createZeroFunction extends JDialog {
        JMenu menu = new JMenu("Нулевая функция");
        JMenuBar jMenuBar = new JMenuBar();

        public createZeroFunction(String s) {
            setSize(100, 100);
            setVisible(false);
            setResizable(false);
            setLocationRelativeTo(null);
            jMenuBar.add(menu);
            setJMenuBar(jMenuBar);
        }
    }
}
