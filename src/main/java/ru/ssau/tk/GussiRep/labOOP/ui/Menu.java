package ru.ssau.tk.GussiRep.labOOP.ui;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ru.ssau.tk.GussiRep.labOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.TabulatedFunctionFactory;

public class Menu extends JFrame {
    JMenu menu = new JMenu("Функции");
    JMenuBar jMenuBar = new JMenuBar();
    JMenu menuSettings = new JMenu("Настройки");
    JMenu menuOperations = new JMenu("Операции");
    final CreateZeroFunction function = new CreateZeroFunction();
    final CreatePowFunction function1 = new CreatePowFunction();
    final CreateSqrFunction function2 = new CreateSqrFunction();
    final CreateASinFunction function3 = new CreateASinFunction();
    final CreateConstantFunction function4 = new CreateConstantFunction();
    JDialog createTabulatedFunction = new CreateTabulatedFunction(this, "Создание функции", true);
    JDialog operationsWithFunctions = new OperationsWithFunctions(this, "Операции",true);
    final CreateTabulatedFunctionForMath createTabulatedFunctionForMath = new CreateTabulatedFunctionForMath();
    public static TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();

    JRadioButtonMenuItem array = new JRadioButtonMenuItem("Массив");
    JRadioButtonMenuItem linkedList = new JRadioButtonMenuItem("Связный список");

    Menu(String s) {
        super(s);
        menu.add(createTabulatedFunction());
        menu.add(createMathFunction());
        jMenuBar.add(menu);
        setJMenuBar(jMenuBar);
        jMenuBar.add(createOperations());
        jMenuBar.add(setSettings());
        setSize(400, 400);
    }

    private JMenu createOperations() {
        menuOperations.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                operationsWithFunctions.setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
        return menuOperations;
    }

    private JMenu createTabulatedFunction() {
        JMenu tabulatedFunction = new JMenu("Табулированная функция");
        JMenuItem tabFunction = new JMenuItem("Ввести количество точек");
        tabFunction.addActionListener(event -> createTabulatedFunction.setVisible(true));
        tabulatedFunction.add(tabFunction);
        return tabulatedFunction;
    }

    private JMenu setSettings() {
        array.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    factory = new ArrayTabulatedFunctionFactory();
                    linkedList.setEnabled(false);
                }
            }
        });
        array.addActionListener(e -> {
            if (!array.isSelected()) {
                linkedList.setEnabled(true);
            }
        });
        linkedList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    factory = new LinkedListTabulatedFunctionFactory();
                    array.setEnabled(false);
                }
            }
        });
        linkedList.addActionListener(e -> {
            if (!linkedList.isSelected()) {
                array.setEnabled(true);
            }
        });
        menuSettings.add(array);
        menuSettings.add(linkedList);

        return menuSettings;
    }

    private JMenu createMathFunction() {
        JMenu mathFunction = new JMenu("MathFunction");

        JMenuItem zero = new JMenuItem("Нулевая функция");
        zero.addActionListener(event -> function.setVisible(true));
        mathFunction.add(zero);
        JMenuItem aSin = new JMenuItem("Арксинус-функция");
        aSin.addActionListener(event -> function3.setVisible(true));
        mathFunction.add(aSin);
        JMenuItem parabola = new JMenuItem("Квадратичная функция");
        parabola.addActionListener(event -> function2.setVisible(true));
        mathFunction.add(parabola);
        JMenuItem pow = new JMenuItem("х в степени х функция");
        pow.addActionListener(event -> function1.setVisible(true));
        mathFunction.add(pow);
        JMenuItem constant = new JMenuItem("Тождественная функция");
        constant.addActionListener(event -> function4.setVisible(true));
        mathFunction.add(constant);
        JMenuItem tabulated = new JMenuItem("Табулированная функция");
        tabulated.addActionListener(event -> createTabulatedFunctionForMath.setVisible(true));

        return mathFunction;
    }

    static class CreateZeroFunction extends JDialog {
        JMenu menu = new JMenu("Нулевая функция");
        JMenuBar jMenuBar = new JMenuBar();

        public CreateZeroFunction() {
            setSize(400, 400);
            setVisible(false);
            setResizable(false);
            setLocationRelativeTo(null);
            jMenuBar.add(menu);
            setJMenuBar(jMenuBar);
        }
    }

    static class CreateASinFunction extends JDialog {
        JMenu menu = new JMenu("Арксинус-функция");
        JMenuBar jMenuBar = new JMenuBar();

        public CreateASinFunction() {
            setSize(400, 400);
            setVisible(false);
            setResizable(false);
            setLocationRelativeTo(null);
            jMenuBar.add(menu);
            setJMenuBar(jMenuBar);
        }
    }

    static class CreateSqrFunction extends JDialog {
        JMenu menu = new JMenu("Парабола функция");
        JMenuBar jMenuBar = new JMenuBar();

        public CreateSqrFunction() {
            setSize(400, 400);
            setVisible(false);
            setResizable(false);
            setLocationRelativeTo(null);
            jMenuBar.add(menu);
            setJMenuBar(jMenuBar);
        }
    }

    static class CreatePowFunction extends JDialog {
        JMenu menu = new JMenu("х в степени х функция");
        JMenuBar jMenuBar = new JMenuBar();

        public CreatePowFunction() {
            setSize(400, 400);
            setVisible(false);
            setResizable(false);
            setLocationRelativeTo(null);
            jMenuBar.add(menu);
            setJMenuBar(jMenuBar);
        }
    }

    static class CreateConstantFunction extends JDialog {
        JMenu menu = new JMenu("Тождественная функция");
        JMenuBar jMenuBar = new JMenuBar();

        public CreateConstantFunction() {
            setSize(400, 400);
            setVisible(false);
            setResizable(false);
            setLocationRelativeTo(null);
            jMenuBar.add(menu);
            setJMenuBar(jMenuBar);
        }
    }

    static class CreateTabulatedFunctionForMath extends JDialog {
        JMenu menu = new JMenu("Тождественная функция");
        JMenuBar jMenuBar = new JMenuBar();

        public CreateTabulatedFunctionForMath() {
            setSize(400, 400);
            setVisible(false);
            setResizable(false);
            setLocationRelativeTo(null);
            jMenuBar.add(menu);
            setJMenuBar(jMenuBar);
        }
    }

}
