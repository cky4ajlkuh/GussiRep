package ru.ssau.tk.GussiRep.labOOP.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.io.FunctionsIO;
import ru.ssau.tk.GussiRep.labOOP.ui.Exceptions.TablesIsEmpty;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CreateChart extends JDialog {

    private final JButton button = new JButton("Ввод");
    private final JTextField textField = new JTextField(5);
    private final List<Double> x = new ArrayList<>();
    private final List<Double> y = new ArrayList<>();
    private TableModel tableModel = new TableModel(x, y);
    private final JButton create = new JButton("Создать");
    private TabulatedFunction function;
    private int enter;
    private final XYSeries series = new XYSeries("График");

    public CreateChart(Menu menu, String name, boolean modal) {
        super(menu, name, modal);
        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory
                .createXYLineChart("Табулированная функция", "x", "y",
                        xyDataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);
        ChartPanel panel = new ChartPanel(chart);
        getContentPane().add(panel);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel label = new JLabel("Количество точек:");
        JButton save = new JButton("Сохранить");
        JButton load = new JButton("Загрузить");
        JButton apply = new JButton("Вычислить");
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

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
                                .addComponent(apply)
                        )
                )
                .addComponent(panel)
        );
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(label)
                                .addGroup(layout.createSequentialGroup().addComponent(textField).addComponent(button))
                                .addComponent(scrollPane)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(create)
                                        .addComponent(save)
                                        .addComponent(load)
                                        .addComponent(apply)
                                )
                        )
                )
                .addComponent(panel)
        );

        load.addActionListener(e -> {
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fileOpen.setDialogTitle("Загрузка функции");
            fileOpen.addChoosableFileFilter(new FileNameExtensionFilter("Табулированная функция", "bin"));
            fileOpen.setAcceptAllFileFilterUsed(false);

            fileOpen.showOpenDialog(menu);
            File file = fileOpen.getSelectedFile();
            if (file != null) {
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                    TabulatedFunction function = FunctionsIO.deserialize(in);
                    tableModel.setCount(function.getCount());
                    for (int i = 0; i < function.getCount(); i++) {
                        x.add(i, (function.getX(i)));
                        y.add(i, (function.getY(i)));
                        tableModel.fireTableDataChanged();
                    }
                    System.out.println(function.toString());
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, err.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Файл не найдет");
            }

        });

        save.addActionListener(e -> {
            if (tableModel.getRowCount() == 0) {
                try {
                    throw new TablesIsEmpty();
                } catch (TablesIsEmpty empty) {
                    empty.printStackTrace();
                }
            } else {
                JFileChooser fileSave = new JFileChooser();
                fileSave.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                fileSave.setDialogTitle("Сохранение функции");
                fileSave.addChoosableFileFilter(new FileNameExtensionFilter("Табулированная функция", "bin"));
                fileSave.setAcceptAllFileFilterUsed(false);
                if (x.isEmpty() | y.isEmpty()) {
                    try {
                        throw new FileNotFoundException();
                    } catch (FileNotFoundException fileNotFoundException) {
                        JOptionPane.showMessageDialog(this, "Нельзя сохранить пустую функцию!");
                        fileNotFoundException.printStackTrace();
                    }
                } else {
                    fileSave.showSaveDialog(menu);
                    File file = fileSave.getSelectedFile();
                    if (file != null) {
                        TabulatedFunction function;
                        double[] x = new double[tableModel.getRowCount()];
                        double[] y = new double[tableModel.getRowCount()];
                        for (int i = 0; i < tableModel.getRowCount(); i++) {
                            x[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
                            y[i] = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
                        }
                        function = Menu.factory.create(x, y);
                        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
                            FunctionsIO.serialize(out, function);
                        } catch (Exception err) {
                            JOptionPane.showMessageDialog(this, err.getMessage());
                        }
                    }
                }
            }
        });

        enterListener();
        createListener();
        setSize(1000, 600);
        setVisible(false);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void enterListener() {
        button.addActionListener(e -> {
            try {
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
            if (tableModel.getRowCount() == 0) {
                try {
                    throw new TablesIsEmpty();
                } catch (TablesIsEmpty empty) {
                    empty.printStackTrace();
                }
            }
            double[] x = new double[tableModel.getRowCount()];
            double[] y = new double[tableModel.getRowCount()];
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                x[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
                y[i] = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
            }
            function = Menu.factory.create(x, y);
            for (int i = 0; i < function.getCount(); i++) {
                series.add(function.getX(i), function.getY(i));
            }
            System.out.println(function);
        });
    }


}
