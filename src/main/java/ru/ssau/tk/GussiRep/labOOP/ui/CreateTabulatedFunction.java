package ru.ssau.tk.GussiRep.labOOP.ui;

import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.ui.Exceptions.RowIsEmpty;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.GroupLayout.*;

public class CreateTabulatedFunction extends JDialog {

    private final JLabel label = new JLabel("Количество точек:");
    private final JButton button = new JButton("Ввод");
    private final JTextField textField = new JTextField(5);
    private final List<Double> strings = new ArrayList<>();
    private final List<Double> strings2 = new ArrayList<>();
    TableModel tableModel = new TableModel(strings, strings2);
    JTable table = new JTable(tableModel);
    JButton addRowButton = new JButton("Создать");
    JScrollPane scrollPane = new JScrollPane(table);
    TabulatedFunction function;
    private int i;

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

    public CreateTabulatedFunction(OperationsWithFunctions menu, String s, boolean modal) {
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
            try {
                i = Integer.parseInt(textField.getText());
                strings2.clear();
                strings.clear();
                if (i >= 2) {
                    if (e.getSource() == button) {
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

                        tableModel.setCount(i);
                        scrollPane.setVisible(true);
                        addRowButton.setVisible(true);
                        button.setVisible(false);
                        textField.setVisible(false);
                        label.setVisible(false);
                    }
                    for (int j = 0; j < i; j++) {
                        strings.add(null);
                        strings2.add(null);
                        tableModel.fireTableDataChanged();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Кол-во должно быть больше 2!");
                    throw new ArithmeticException();
                }
            } catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(this, "Пожалуйста, вводите цифры!");
            }
        });
    }

    private void checkTable() throws RowIsEmpty {
        if (strings.contains(null) | strings2.contains(null)) {
            throw new RowIsEmpty("Aй");
        }

    }

    public void listenerRow() {
        try {
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
                try {
                    checkTable();
                } catch (RowIsEmpty empty) {
                    empty.printStackTrace();
                }
                for (int j = 0; j < tableModel.getRowCount(); j++) {
                    x[j] = Double.parseDouble(tableModel.getValueAt(j, 0).toString());
                    y[j] = Double.parseDouble(tableModel.getValueAt(j, 1).toString());
                }
                function = Menu.factory.create(x, y);
                System.out.println(function.toString());
            });
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage());
        }
    }

    public int getCount() {
        return 0;
    }

    public Double getX(int i) {
        return null;
    }

    public Double getY(int i) {
        return null;
    }
}
