package ru.ssau.tk.GussiRep.labOOP.ui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu extends JFrame {
    JMenu menu = new JMenu("Functions");
    JMenuBar jMenuBar = new JMenuBar();
    final CreateZeroFunction function = new CreateZeroFunction();
    final CreatePowFunction function1 = new CreatePowFunction();
    final CreateSqrFunction function2 = new CreateSqrFunction();
    final CreateASinFunction function3 = new CreateASinFunction();
    final CreateConstantFunction function4 = new CreateConstantFunction();
    final CreateTabulatedFunction createTabulatedFunction = new CreateTabulatedFunction();
    final TabulatedFunction tabulatedFunction = new TabulatedFunction();

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
        JMenuItem tabFunction = new JMenuItem("Ввести количество точек");
        tabFunction.addActionListener(event -> createTabulatedFunction.setVisible(true));
        tabulatedFunction.add(tabFunction);
        return tabulatedFunction;
    }

    static class CreateTabulatedFunction extends JDialog {
        JMenu menu = new JMenu("Табулированная функция");
        JMenuBar jMenuBar = new JMenuBar();
        JLabel label = new JLabel("Введите количество точек: ");
        JButton button = new JButton("Ввод");
        JTextField textField = new JTextField(5);
        List<String> strings = new ArrayList<>();
        AbstractTableModel tableModel = new TableModel(strings);
        JTable table = new JTable(tableModel);
        JButton addRowButton = new JButton("Создать");
        JButton removeRowButton = new JButton("Remove row");
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
            listenerRemoveRow();

            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            setSize(400, 400);
            setVisible(false);
            setResizable(false);
            setLocationRelativeTo(null);
            jMenuBar.add(menu);
            setJMenuBar(jMenuBar);

        }

        public void listenerButton() {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
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
                }
            });
        }

        public void listenerRow() {
            addRowButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    button.setVisible(true);
                    textField.setVisible(true);
                    label.setVisible(true);
                    addRowButton.setVisible(false);
                    scrollPane.setVisible(false);
                    textField.setText(null);
                }
            });
        }

        public void listenerRemoveRow() {
            removeRowButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        strings.remove(selectedRow);
                        tableModel.fireTableDataChanged();
                    }
                }
            });
        }

        private void checkTextAndNull() {
            if(i <= 0) {
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
        tabulated.addActionListener(event -> tabulatedFunction.setVisible(true));

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

    static class TabulatedFunction extends JDialog {
        JMenu menu = new JMenu("Тождественная функция");
        JMenuBar jMenuBar = new JMenuBar();

        public TabulatedFunction() {
            setSize(400, 400);
            setVisible(false);
            setResizable(false);
            setLocationRelativeTo(null);
            jMenuBar.add(menu);
            setJMenuBar(jMenuBar);
        }
    }

}
