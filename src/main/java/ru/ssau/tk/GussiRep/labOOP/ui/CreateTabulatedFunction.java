package ru.ssau.tk.GussiRep.labOOP.ui;

import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreateTabulatedFunction extends JDialog {

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

            TabulatedFunction function = Menu.factory.create(x, y);
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
