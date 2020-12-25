package ru.ssau.tk.GussiRep.labOOP.ui;

import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.GroupLayout.*;

public class CreateTabulatedFunction extends JDialog {

    JLabel label = new JLabel("Количество точек:");
    JButton button = new JButton("Ввод");
    JTextField textField = new JTextField(5);
    List<String> strings = new ArrayList<>();
    List<String> strings2 = new ArrayList<>();
    AbstractTableModel tableModel = new TableModel(strings, strings2);
    JTable table = new JTable(tableModel);
    JButton addRowButton = new JButton("Создать");
    JScrollPane scrollPane = new JScrollPane(table);
    double i;

    public CreateTabulatedFunction(Menu menu, String s, boolean modal) {
        super(menu, s, modal);
        setLayout(null);
        label.setBounds(20, 170, 110, 40);
        textField.setBounds(140, 170, 120, 40);
        button.setBounds(280, 170, 80, 40);

        add(label);
        add(textField);
        add(button);
        listenerButton();
        listenerRow();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setSize(400, 450);

        setVisible(false);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
                layout.setHorizontalGroup(layout.createParallelGroup(Alignment.CENTER)
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
