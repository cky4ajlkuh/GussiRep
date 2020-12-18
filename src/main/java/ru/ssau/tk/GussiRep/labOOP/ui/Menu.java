package ru.ssau.tk.GussiRep.labOOP.ui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.TabulatedFunctionFactory;

public class Menu extends JFrame {
    JMenu menu = new JMenu("Functions");
    JMenuBar jMenuBar = new JMenuBar();
    JMenu menuSettings = new JMenu("Настройки");
    final CreateZeroFunction function = new CreateZeroFunction();
    final CreatePowFunction function1 = new CreatePowFunction();
    final CreateSqrFunction function2 = new CreateSqrFunction();
    final CreateASinFunction function3 = new CreateASinFunction();
    final CreateConstantFunction function4 = new CreateConstantFunction();
    final CreateTabulatedFunction createTabulatedFunction = new CreateTabulatedFunction();
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
        menuSettings.add(setSettings());
        jMenuBar.add(menuSettings);
        setSize(400, 400);
    }

    private JMenu createTabulatedFunction() {
        JMenu tabulatedFunction = new JMenu("TabulatedFunction");
        JMenuItem tabFunction = new JMenuItem("Ввести количество точек");
        tabFunction.addActionListener(event -> createTabulatedFunction.setVisible(true));
        tabulatedFunction.add(tabFunction);
        return tabulatedFunction;
    }

    private JMenu setSettings() {
        JMenu settings = new JMenu("настройки реализации");
        array.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    factory = new ArrayTabulatedFunctionFactory();
                }
            }
        });

        linkedList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    factory = new LinkedListTabulatedFunctionFactory();
                }
            }
        });
        settings.add(array);
        settings.add(linkedList);

        return settings;
    }

    static class CreateTabulatedFunction extends JDialog {

        JMenu menu = new JMenu("Табулированная функция");
        JMenuBar jMenuBar = new JMenuBar();
        JLabel label = new JLabel("Введите количество точек: ");
        JButton button = new JButton("Ввод");
        JTextField textField = new JTextField(5);
        List<String> strings = new ArrayList<>();
        List<String> strings2 = new ArrayList<>();
        AbstractTableModel tableModel = new TableModel(strings, strings2);
        JTable table = new JTable(tableModel);
        JButton addRowButton = new JButton("Создать");
        JLabel labelNorth = new JLabel("");
        JLabel labelSouth = new JLabel("");
        JScrollPane scrollPane = new JScrollPane(table);
        double i;

        public CreateTabulatedFunction() {
            add(labelSouth, BorderLayout.SOUTH);
            add(labelNorth, BorderLayout.NORTH);
            add(label, BorderLayout.WEST);
            add(textField);
            add(button, BorderLayout.EAST);
            labelSouth.setPreferredSize(new Dimension(100, 175));
            labelNorth.setPreferredSize(new Dimension(100, 125));
            listenerButton();
            listenerRow();
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            setSize(400, 400);
            setVisible(false);
            setResizable(false);
            setLocationRelativeTo(null);
            jMenuBar.add(menu);
            setJMenuBar(jMenuBar);
        }

        public void listenerButton() {
            button.addActionListener(e -> {
                i = Double.parseDouble(textField.getText());
                if (e.getSource() == button) {
                    checkTextAndNull();
                    GroupLayout layout = new GroupLayout(getContentPane());
                    getContentPane().setLayout(layout);
                    layout.setAutoCreateGaps(true);
                    layout.setAutoCreateContainerGaps(true);
                    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(scrollPane)
                            .addComponent(addRowButton)
                    );
                    layout.setVerticalGroup(layout.createSequentialGroup()
                            .addComponent(scrollPane)
                            .addComponent(addRowButton)
                    );
                    button.setVisible(false);
                    textField.setVisible(false);
                    label.setVisible(false);
                }
                for (int j = 0; j < i; j++) {
                    strings.add("");
                    strings2.add("");
                    tableModel.fireTableDataChanged();
                }
            });
        }

        public void listenerRow() {
            addRowButton.addActionListener(e -> {
                double[] x = new double[tableModel.getRowCount()];
                double[] y = new double[tableModel.getRowCount()];

                setVisible(false);
                button.setVisible(true);
                textField.setVisible(true);
                label.setVisible(true);
                addRowButton.setVisible(false);
                scrollPane.setVisible(false);
                textField.setText(null);

                for (int j = 0; j < tableModel.getRowCount(); j++) {
                    x[j] = Double.parseDouble(tableModel.getValueAt(j, 0).toString());
                    y[j] = Double.parseDouble(tableModel.getValueAt(j, 1).toString());
                }

                TabulatedFunction function = factory.create(x, y);
                System.out.println(function.toString());
            });
        }

        private void checkTextAndNull() {
            if (i <= 0) {
                textField.setText(null);
                JOptionPane.showMessageDialog(null, "Кол-во точек не может быть отрицательным!");
                throw new ArithmeticException();
            }
        }

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
