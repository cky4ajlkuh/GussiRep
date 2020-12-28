package ru.ssau.tk.GussiRep.labOOP.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ru.ssau.tk.GussiRep.labOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.TabulatedFunctionFactory;

public class Menu extends JFrame {
    JMenu menu = new JMenu("Функции");
    JMenuBar jMenuBar = new JMenuBar();
    JMenu menuSettings = new JMenu("Настройки");

    private final CreateZeroFunction function = new CreateZeroFunction();
    private final CreatePowFunction function1 = new CreatePowFunction();
    private final CreateSqrFunction function2 = new CreateSqrFunction();
    private final CreateASinFunction function3 = new CreateASinFunction();
    private final CreateConstantFunction function4 = new CreateConstantFunction();
    private final JDialog createTabulatedFunction = new CreateTabulatedFunction(this, "Создание функции", true);
    private final JDialog operationsWithFunctions = new OperationsWithFunctions(this, "Операции", true);
    private final CreateTabulatedFunctionForMath createTabulatedFunctionForMath = new CreateTabulatedFunctionForMath();
    public static TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();

    private final JRadioButtonMenuItem array = new JRadioButtonMenuItem("Массив");
    private final JRadioButtonMenuItem linkedList = new JRadioButtonMenuItem("Связный список");

    Menu(String s) {
        super(s);
        draw();
        JButton button = new JButton("Что-то..");
        setLayout(null);
        button.setBounds(280, 190, 100, 30);
        add(button);
        button.addActionListener(e -> {
            if (e.getSource() == button) {
                QR qr = new QR(this, "QR-код", true);
                qr.setVisible(true);
            }
        });
        menu.add(createTabulatedFunction());
        menu.add(createMathFunction());
        jMenuBar.add(menu);
        setJMenuBar(jMenuBar);
        jMenuBar.add(createOperations());
        jMenuBar.add(setSettings());

        setSize(400, 400);

    }

    public void draw() {
        Image image = new ImageIcon("обложка.png").getImage();
        setContentPane(new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(image, 0, 0, null);
            }
        });
    }

    private JMenu createOperations() {
        JMenu menuOperations = new JMenu("Операции");
        JMenuItem jMenuItem = new JMenuItem("Приступить к операциям");
        jMenuItem.addActionListener(e -> operationsWithFunctions.setVisible(true));
        menuOperations.add(jMenuItem);
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
