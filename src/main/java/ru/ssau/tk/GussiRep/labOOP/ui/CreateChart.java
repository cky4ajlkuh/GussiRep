package ru.ssau.tk.GussiRep.labOOP.ui;

import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CreateChart extends JDialog {

    private final JButton button = new JButton("Ввод");
    private final JTextField textField = new JTextField(5);
    private final List<Double> x = new ArrayList<>();
    private final List<Double> y = new ArrayList<>();
    TableModel tableModel = new TableModel(x, y);
    JTable table = new JTable(tableModel);
    JButton save = new JButton("Сохранить");
    JButton load = new JButton("Загрузить");
    JButton apply = new JButton("Применять");
    JButton create = new JButton("Создать");
    JScrollPane scrollPane = new JScrollPane(table);
    TabulatedFunction function;
    private int enter;


    public CreateChart(Menu menu, String name, boolean modal) {

        super(menu, name, modal);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JLabel label = new JLabel("Количество точек:");
        layout.setVerticalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(label)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(textField).addComponent(button))
                        .addComponent(scrollPane)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(create)
                                .addComponent(save)
                                .addComponent(load)
                                .addComponent(apply)))
        );
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(label)
                        .addGroup(layout.createSequentialGroup().addComponent(textField).addComponent(button))
                        .addComponent(scrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(create)
                                .addComponent(save)
                                .addComponent(load)
                                .addComponent(apply))
                )

        );

        enterListener();
        createListener();
        setSize(600, 600);
        setVisible(false);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void enterListener() {
        button.addActionListener(e -> {
            try {
                x.clear();
                y.clear();
                enter = Integer.parseInt(textField.getText());
                tableModel.setCount(enter);
                for (int i = 0; i < enter; i++) {
                    x.add(null);
                    y.add(null);
                }
                tableModel.fireTableDataChanged();

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        });
    }

    private void createListener() {
        create.addActionListener(e -> {
            double[] x = new double[tableModel.getRowCount()];
            double[] y = new double[tableModel.getRowCount()];
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                x[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
                y[i] = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
            }
            function = Menu.factory.create(x, y);
            ShowChart showChart = new ShowChart(function, this, "График", false);
            showChart.setVisible(true);
            System.out.println(function);
        });
    }


}
