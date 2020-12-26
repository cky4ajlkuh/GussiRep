package ru.ssau.tk.GussiRep.labOOP.ui;

import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.io.FunctionsIO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OperationsWithFunctions extends JDialog {

    JLabel noName = new JLabel(" ");
    JLabel nameFirst = new JLabel("Первая функция");
    JLabel nameSecond = new JLabel("Вторая функция");
    JLabel nameResult = new JLabel("Результат");

    JComboBox<String> comboBox = new JComboBox<>(new String[]{"", "Умножение", "Деление", "Сумма", "Разность"});
    JButton create = new JButton("Вычислить");

    JCheckBox boxArrayFirst = new JCheckBox("Массив");
    JCheckBox boxLLFirst = new JCheckBox("Связный список");
    JCheckBox boxArraySecond = new JCheckBox("Массив");
    JCheckBox boxLLSecond = new JCheckBox("Связный список");

    JButton createFirstFunction = new JButton("Создать");
    JButton createSecondFunction = new JButton("Создать");

    JButton loadingFirstFunction = new JButton("Загрузить");
    JButton loadingSecondFunction = new JButton("Загрузить");

    JButton saveFirstFunction = new JButton("Сохранить");
    JButton saveSecondFunction = new JButton("Сохранить");
    JButton saveResult = new JButton("Сохранить");

    List<String> xValues = new ArrayList<>();
    List<String> yValues = new ArrayList<>();
    AbstractTableModel firstFunction = new TableModel(xValues, yValues);
    JTable firstTable = new JTable(firstFunction);
    JScrollPane firstScroll = new JScrollPane(firstTable);

    List<String> xValuesSecond = new ArrayList<>();
    List<String> yValuesSecond = new ArrayList<>();
    AbstractTableModel secondFunction = new TableModel(xValuesSecond, yValuesSecond);
    JTable secondTable = new JTable(secondFunction);
    JScrollPane secondScroll = new JScrollPane(secondTable);

    List<String> xValuesResult = new ArrayList<>();
    List<String> yValuesResult = new ArrayList<>();
    AbstractTableModel result = new TableModel(xValuesResult, yValuesResult);
    JTable resultTable = new JTable(result);
    JScrollPane resultFunction = new JScrollPane(resultTable);

    public OperationsWithFunctions(Menu menu, String s, Boolean modal) {
        super(menu, s, modal);

        JFileChooser fileOpen = new JFileChooser();
        JFileChooser fileOpenSecond = new JFileChooser();
        JFileChooser fileSave = new JFileChooser();
        JFileChooser fileSaveSecond = new JFileChooser();
        JFileChooser fileSaveResult = new JFileChooser();

        fileSaveSecond.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileSaveSecond.setDialogTitle("Загрузка функции");
        fileSaveSecond.addChoosableFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        fileSaveSecond.setAcceptAllFileFilterUsed(false);

        fileSave.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileSave.setDialogTitle("Загрузка функции");
        fileSave.addChoosableFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        fileSave.setAcceptAllFileFilterUsed(false);

        fileSaveResult.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileSaveResult.setDialogTitle("Загрузка функции");
        fileSaveResult.addChoosableFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        fileSaveResult.setAcceptAllFileFilterUsed(false);

        fileOpen.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileOpen.setDialogTitle("Загрузка функции");
        fileOpen.addChoosableFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        fileOpen.setAcceptAllFileFilterUsed(false);

        fileOpenSecond.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileOpenSecond.setDialogTitle("Загрузка функции");
        fileOpenSecond.addChoosableFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        fileOpenSecond.setAcceptAllFileFilterUsed(false);

        resultFunction.setPreferredSize(new Dimension(350, 400));
        resultFunction.setMaximumSize(new Dimension(350, 400));
        resultFunction.setMinimumSize(new Dimension(350, 400));

        secondScroll.setPreferredSize(new Dimension(350, 400));
        secondScroll.setMaximumSize(new Dimension(350, 400));
        secondScroll.setMinimumSize(new Dimension(350, 400));

        firstScroll.setPreferredSize(new Dimension(350, 400));
        firstScroll.setMaximumSize(new Dimension(350, 400));
        firstScroll.setMinimumSize(new Dimension(350, 400));

        comboBox.setPreferredSize(new Dimension(120, 26));
        comboBox.setMaximumSize(new Dimension(120, 26));
        comboBox.setMinimumSize(new Dimension(120, 26));

        //костыль для нормальной высоты
        noName.setPreferredSize(new Dimension(1, 16));
        noName.setMaximumSize(new Dimension(1, 16));
        noName.setMinimumSize(new Dimension(1, 16));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nameFirst)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(boxArrayFirst)
                                .addComponent(boxLLFirst)
                        )
                        .addComponent(createFirstFunction)
                        .addComponent(firstScroll)

                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loadingFirstFunction)
                                .addComponent(saveFirstFunction)
                        )
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nameSecond)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(boxArraySecond)
                                .addComponent(boxLLSecond)
                        )
                        .addComponent(createSecondFunction)
                        .addComponent(secondScroll)

                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loadingSecondFunction)
                                .addComponent(saveSecondFunction)
                        )
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nameResult)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(comboBox)
                                .addComponent(create)
                        )
                        .addComponent(noName)
                        .addComponent(resultFunction)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(saveResult)
                        )
                )
        );
        layout.setVerticalGroup(layout.createParallelGroup()
                .addGroup(
                        layout.createSequentialGroup()
                                .addComponent(nameFirst)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(boxArrayFirst)
                                        .addComponent(boxLLFirst)
                                )
                                .addComponent(createFirstFunction)
                                .addComponent(firstScroll)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(loadingFirstFunction)
                                        .addComponent(saveFirstFunction)
                                )
                )
                .addGroup(
                        layout.createSequentialGroup()
                                .addComponent(nameSecond)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(boxArraySecond)
                                        .addComponent(boxLLSecond)
                                )
                                .addComponent(createSecondFunction)
                                .addComponent(secondScroll)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(loadingSecondFunction)
                                        .addComponent(saveSecondFunction)
                                )
                )
                .addGroup(
                        layout.createSequentialGroup()
                                .addComponent(nameResult)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(comboBox)
                                        .addComponent(create)

                                )
                                .addComponent(noName)
                                .addComponent(resultFunction)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(saveResult)
                                )
                )
        );
        loadingFirstFunction.addActionListener(e -> {
            fileOpen.showOpenDialog(menu);
            File file = fileOpen.getSelectedFile();
            if (file != null) {
                try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                    TabulatedFunction function = FunctionsIO.readTabulatedFunction(in, new ArrayTabulatedFunctionFactory());
                    for (int i = 0; i < function.getCount(); i++) {
                        xValues.add(i, String.valueOf(function.getX(i)));
                        yValues.add(i, String.valueOf(function.getY(i)));
                        firstFunction.fireTableDataChanged();
                    }
                    System.out.println(function.toString());
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
        });
        loadingSecondFunction.addActionListener(e -> {
            fileOpenSecond.showOpenDialog(menu);
            File file = fileOpenSecond.getSelectedFile();
            if (file != null) {
                try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                    TabulatedFunction function = FunctionsIO.readTabulatedFunction(in, new ArrayTabulatedFunctionFactory());
                    for (int i = 0; i < function.getCount(); i++) {
                        xValuesSecond.add(i, String.valueOf(function.getX(i)));
                        yValuesSecond.add(i, String.valueOf(function.getY(i)));
                        secondFunction.fireTableDataChanged();
                    }
                    System.out.println(function.toString());
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
        });

        saveFirstFunction.addActionListener(e -> {
            fileSave.showSaveDialog(menu);
            File file = fileSave.getSelectedFile();
            if (file != null) {
                TabulatedFunction function;
                TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
                if (boxArrayFirst.isSelected()) {
                    factory = new ArrayTabulatedFunctionFactory();
                }
                if (boxLLFirst.isSelected()) {
                    factory = new LinkedListTabulatedFunctionFactory();
                }
                double[] x = new double[firstFunction.getRowCount()];
                double[] y = new double[firstFunction.getRowCount()];
                for (int i = 0; i < firstFunction.getRowCount(); i++) {
                    x[i] = Double.parseDouble(firstFunction.getValueAt(i, 0).toString());
                    y[i] = Double.parseDouble(firstFunction.getValueAt(i, 1).toString());
                }

                function = factory.create(x, y);

                try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
                    FunctionsIO.writeTabulatedFunction(out, function);
                } catch (IOException error) {
                    error.printStackTrace();
                }
            }
        });

        saveResult.addActionListener(e -> {
            fileSaveResult.showSaveDialog(menu);
            File file = fileSaveResult.getSelectedFile();
            if (file != null) {
                TabulatedFunction function;
                double[] x = new double[result.getRowCount()];
                double[] y = new double[result.getRowCount()];
                for (int i = 0; i < result.getRowCount(); i++) {
                    x[i] = Double.parseDouble(result.getValueAt(i, 0).toString());
                    y[i] = Double.parseDouble(result.getValueAt(i, 1).toString());
                }

                function = Menu.factory.create(x, y);
                try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
                    FunctionsIO.writeTabulatedFunction(out, function);
                } catch (IOException error) {
                    error.printStackTrace();
                }
            }
        });

        saveSecondFunction.addActionListener(e -> {
            fileSaveSecond.showSaveDialog(menu);
            File file = fileSaveSecond.getSelectedFile();
            if (file != null) {
                TabulatedFunction function;
                TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();

                if (boxArraySecond.isSelected()) {
                    factory = new ArrayTabulatedFunctionFactory();
                }
                if (boxLLSecond.isSelected()) {
                    factory = new LinkedListTabulatedFunctionFactory();
                }
                double[] x = new double[secondFunction.getRowCount()];
                double[] y = new double[secondFunction.getRowCount()];
                for (int i = 0; i < secondFunction.getRowCount(); i++) {
                    x[i] = Double.parseDouble(secondFunction.getValueAt(i, 0).toString());
                    y[i] = Double.parseDouble(secondFunction.getValueAt(i, 1).toString());
                }

                function = factory.create(x, y);

                try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
                    FunctionsIO.writeTabulatedFunction(out, function);
                } catch (IOException error) {
                    error.printStackTrace();
                }
            }
        });

        checkBoxListener();
        createFirstFunListener();
        setVisible(false);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(new Dimension(1100, 600));
    }

    public void checkBoxListener() {
        boxArrayFirst.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                boxLLFirst.setEnabled(false);
            }
            if (e.getStateChange() != ItemEvent.SELECTED) {
                boxLLFirst.setEnabled(true);
            }
        });

        boxLLFirst.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                boxArrayFirst.setEnabled(false);
            }
            if (e.getStateChange() != ItemEvent.SELECTED) {
                boxArrayFirst.setEnabled(true);
            }
        });
        boxArraySecond.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                boxLLSecond.setEnabled(false);
            }
            if (e.getStateChange() != ItemEvent.SELECTED) {
                boxLLSecond.setEnabled(true);
            }
        });
        boxLLSecond.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                boxArraySecond.setEnabled(false);
            }
            if (e.getStateChange() != ItemEvent.SELECTED) {
                boxArraySecond.setEnabled(true);
            }
        });

    }

    public void createFirstFunListener() {
        createFirstFunction.addActionListener(e -> {
            if (boxArrayFirst.isSelected()) {
                CreateTabulatedFunction function = new CreateTabulatedFunction(this, "Создание функции", true);
                function.setVisible(true);
                for (int i = 0; i < function.tableModel.getRowCount(); i++) {
                    xValues.add(String.valueOf(function.function.getX(i)));
                    yValues.add(String.valueOf(function.function.getY(i)));
                    firstFunction.fireTableDataChanged();
                }
            }
            if (boxLLFirst.isSelected()) {
                CreateTabulatedFunction function = new CreateTabulatedFunction(this, "Создание функции", true);
                function.setVisible(true);
                for (int i = 0; i < function.tableModel.getRowCount(); i++) {
                    xValues.add(String.valueOf(function.function.getX(i)));
                    yValues.add(String.valueOf(function.function.getY(i)));
                    firstFunction.fireTableDataChanged();
                }
            }
        });
        createSecondFunction.addActionListener(e -> {
            if (boxLLSecond.isSelected()) {
                CreateTabulatedFunction function = new CreateTabulatedFunction(this, "Создание функции", true);
                function.setVisible(true);
                for (int i = 0; i < function.tableModel.getRowCount(); i++) {
                    xValuesSecond.add(String.valueOf(function.function.getX(i)));
                    yValuesSecond.add(String.valueOf(function.function.getY(i)));
                    secondFunction.fireTableDataChanged();
                }
            }
            if (boxArraySecond.isSelected()) {
                CreateTabulatedFunction function = new CreateTabulatedFunction(this, "Создание функции", true);
                function.setVisible(true);
                for (int i = 0; i < function.tableModel.getRowCount(); i++) {
                    xValuesSecond.add(String.valueOf(function.function.getX(i)));
                    yValuesSecond.add(String.valueOf(function.function.getY(i)));
                    secondFunction.fireTableDataChanged();
                }
            }
        });
    }
}
