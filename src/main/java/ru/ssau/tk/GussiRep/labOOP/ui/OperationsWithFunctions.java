package ru.ssau.tk.GussiRep.labOOP.ui;

import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.io.FunctionsIO;
import ru.ssau.tk.GussiRep.labOOP.operations.TabulatedFunctionOperationService;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OperationsWithFunctions extends JDialog {

    private final JComboBox<String> comboBox = new JComboBox<>(new String[]{"", "Умножение", "Деление", "Сумма", "Разность"});

    public final JCheckBox boxArrayFirst = new JCheckBox("Массив");
    public final JCheckBox boxLLFirst = new JCheckBox("Связный список");
    public final JCheckBox boxArraySecond = new JCheckBox("Массив");
    public final JCheckBox boxLLSecond = new JCheckBox("Связный список");

    private final JButton createFirstFunction = new JButton("Создать");
    private final JButton createSecondFunction = new JButton("Создать");

    private List<Double> xValues = new ArrayList<>();
    private List<Double> yValues = new ArrayList<>();
    private TableModel firstFunction = new TableModel(xValues, yValues) {
        @Serial
        private static final long serialVersionUID = -7247776622311553601L;
        private static final int Y_COLUMN_NUMBER = 1;

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (columnIndex == Y_COLUMN_NUMBER) {
                return true;
            }
            return false;
        }
    };
    private final JTable firstTable = new JTable(firstFunction);
    private final JScrollPane firstScroll = new JScrollPane(firstTable);

    private List<Double> xValuesSecond = new ArrayList<>();
    private List<Double> yValuesSecond = new ArrayList<>();
    private TableModel secondFunction = new TableModel(xValuesSecond, yValuesSecond) {
        @Serial
        private static final long serialVersionUID = -7725822446193357493L;
        private static final int Y_COLUMN_NUMBER = 1;

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (columnIndex == Y_COLUMN_NUMBER) {
                return true;
            }
            return false;
        }
    };
    private final JTable secondTable = new JTable(secondFunction);
    private final JScrollPane secondScroll = new JScrollPane(secondTable);

    private List<Double> xValuesResult = new ArrayList<>();
    private List<Double> yValuesResult = new ArrayList<>();
    private TableModel result = new TableModel(xValuesResult, yValuesResult) {

        @Serial
        private static final long serialVersionUID = 4546851026558366597L;

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };

    private final JTable resultTable = new JTable(result);
    private final JScrollPane resultFunction = new JScrollPane(resultTable);

    private final TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(Menu.factory);
    private TabulatedFunction firstF;
    private TabulatedFunction secondF;

    public OperationsWithFunctions(Menu menu, String s, Boolean modal) {
        super(menu, s, modal);

        JFileChooser fileOpen = new JFileChooser();
        JFileChooser fileOpenSecond = new JFileChooser();
        JFileChooser fileSave = new JFileChooser();
        JFileChooser fileSaveSecond = new JFileChooser();
        JFileChooser fileSaveResult = new JFileChooser();

        fileSaveSecond.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileSaveSecond.setDialogTitle("Сохранение функции");
        fileSaveSecond.addChoosableFileFilter(new FileNameExtensionFilter("Табулированная функция", "txt"));
        fileSaveSecond.setAcceptAllFileFilterUsed(false);

        fileSave.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileSave.setDialogTitle("Сохранение функции");
        fileSave.addChoosableFileFilter(new FileNameExtensionFilter("Табулированная функция", "txt"));
        fileSave.setAcceptAllFileFilterUsed(false);

        fileSaveResult.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileSaveResult.setDialogTitle("Сохранение функции");
        fileSaveResult.addChoosableFileFilter(new FileNameExtensionFilter("Табулированная функция", "txt"));
        fileSaveResult.setAcceptAllFileFilterUsed(false);

        fileOpen.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileOpen.setDialogTitle("Загрузка функции");
        fileOpen.addChoosableFileFilter(new FileNameExtensionFilter("Табулированная функция", "txt"));
        fileOpen.setAcceptAllFileFilterUsed(false);

        fileOpenSecond.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileOpenSecond.setDialogTitle("Загрузка функции");
        fileOpenSecond.addChoosableFileFilter(new FileNameExtensionFilter("Табулированная функция", "txt"));
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
        JLabel noName = new JLabel(" ");
        noName.setPreferredSize(new Dimension(1, 16));
        noName.setMaximumSize(new Dimension(1, 16));
        noName.setMinimumSize(new Dimension(1, 16));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JButton create = new JButton("Вычислить");

        JLabel nameFirst = new JLabel("Первая функция");
        JLabel nameSecond = new JLabel("Вторая функция");
        JLabel nameResult = new JLabel("Результат");

        JButton saveFirstFunction = new JButton("Сохранить");
        JButton saveSecondFunction = new JButton("Сохранить");
        JButton saveResult = new JButton("Сохранить");
        JButton loadingSecondFunction = new JButton("Загрузить");
        JButton loadingFirstFunction = new JButton("Загрузить");

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
                firstFunction.setNulls();
                try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                    TabulatedFunction function = FunctionsIO.readTabulatedFunction(in, Menu.factory);
                    firstFunction.setCount(function.getCount());
                    for (int i = 0; i < function.getCount(); i++) {
                        xValues.add(i, (function.getX(i)));
                        yValues.add(i, (function.getY(i)));
                        firstFunction.fireTableDataChanged();
                    }
                    System.out.println(function.toString());
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, err.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Файл не найдет");
            }
        });

        loadingSecondFunction.addActionListener(e -> {
            fileOpenSecond.showOpenDialog(menu);
            File file = fileOpenSecond.getSelectedFile();
            if (file != null) {
                secondFunction.setNulls();
                try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                    TabulatedFunction function = FunctionsIO.readTabulatedFunction(in, new ArrayTabulatedFunctionFactory());
                    secondFunction.setCount(function.getCount());
                    for (int i = 0; i < function.getCount(); i++) {
                        xValuesSecond.add(i, (function.getX(i)));
                        yValuesSecond.add(i, (function.getY(i)));
                        secondFunction.fireTableDataChanged();
                    }
                    System.out.println(function.toString());
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, err.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Файл не найдет");
            }
        });

        saveFirstFunction.addActionListener(e -> {
            fileSave.showSaveDialog(menu);
            File file = fileSave.getSelectedFile();
            if (file != null) {
                if (xValues.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Нельзя сохранить пустую функцию!");
                }
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
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, err.getMessage());
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
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, err.getMessage());
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
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this, err.getMessage());
                }
            }
        });

        comboBox.addActionListener(event -> {
            double[] x1 = new double[firstFunction.getRowCount()];
            double[] y1 = new double[firstFunction.getRowCount()];
            for (int i = 0; i < firstFunction.getRowCount(); i++) {
                x1[i] = Double.parseDouble(firstFunction.getValueAt(i, 0).toString());
                y1[i] = Double.parseDouble(firstFunction.getValueAt(i, 1).toString());
            }
            double[] x2 = new double[secondFunction.getRowCount()];
            double[] y2 = new double[secondFunction.getRowCount()];
            for (int i = 0; i < secondFunction.getRowCount(); i++) {
                x2[i] = Double.parseDouble(secondFunction.getValueAt(i, 0).toString());
                y2[i] = Double.parseDouble(secondFunction.getValueAt(i, 1).toString());
            }
            firstF = Menu.factory.create(x1, y1);
            secondF = Menu.factory.create(x2, y2);
        });

        create.addActionListener(e -> {
            if (comboBox.getSelectedIndex() == 1) {
                try {
                    result.setNulls();
                    TabulatedFunction function = service.multiply(firstF, secondF);
                    result.setCount(function.getCount());
                    for (int i = 0; i < function.getCount(); i++) {
                        xValuesResult.add(i, (function.getX(i)));
                        yValuesResult.add(i, (function.getY(i)));
                        result.fireTableDataChanged();
                    }
                    resultFunction.setEnabled(false);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage());
                }
            }
            if (comboBox.getSelectedIndex() == 2) {
                try {
                    result.setNulls();
                    TabulatedFunction function = service.divider(firstF, secondF);
                    result.setCount(function.getCount());
                    for (int i = 0; i < function.getCount(); i++) {
                        xValuesResult.add(i, (function.getX(i)));
                        yValuesResult.add(i, (function.getY(i)));
                        result.fireTableDataChanged();
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage());
                }
            }
            if (comboBox.getSelectedIndex() == 3) {
                try {
                    result.setNulls();
                    TabulatedFunction function = service.sum(firstF, secondF);
                    result.setCount(function.getCount());
                    for (int i = 0; i < function.getCount(); i++) {
                        xValuesResult.add(i, (function.getX(i)));
                        yValuesResult.add(i, (function.getY(i)));
                        result.fireTableDataChanged();
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage());
                }
            }
            if (comboBox.getSelectedIndex() == 4) {
                try {
                    result.setNulls();
                    TabulatedFunction function = service.subtract(firstF, secondF);
                    result.setCount(function.getCount());
                    for (int i = 0; i < function.getCount(); i++) {
                        xValuesResult.add(i, (function.getX(i)));
                        yValuesResult.add(i, (function.getY(i)));
                        result.fireTableDataChanged();
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage());
                }
            }
        });

        checkBoxListener();
        createFunctionsListener();
        setVisible(false);
        setResizable(false);
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

    public void createFunctionsListener() {
        createFirstFunction.addActionListener(e -> {
            if (boxArrayFirst.isSelected()) {
                CreateTabulatedFunction function = new CreateTabulatedFunction(this, "Создание функции", true);
                function.setVisible(true);
                firstFunction.setNulls();
                firstFunction.setCount(function.function.getCount());
                for (int i = 0; i < function.function.getCount(); i++) {
                    xValues.add(function.function.getX(i));
                    yValues.add(function.function.getY(i));
                    firstFunction.fireTableDataChanged();
                }
            }
            if (boxLLFirst.isSelected()) {
                CreateTabulatedFunction function = new CreateTabulatedFunction(this, "Создание функции", true);
                function.setVisible(true);
                firstFunction.setNulls();
                firstFunction.setCount(function.function.getCount());
                for (int i = 0; i < function.tableModel.getRowCount(); i++) {
                    xValues.add((function.function.getX(i)));
                    yValues.add((function.function.getY(i)));
                    firstFunction.fireTableDataChanged();
                }
            }
        });
        createSecondFunction.addActionListener(e -> {
            if (boxLLSecond.isSelected()) {
                CreateTabulatedFunction function = new CreateTabulatedFunction(this, "Создание функции", true);
                function.setVisible(true);
                secondFunction.setNulls();
                secondFunction.setCount(function.function.getCount());
                for (int i = 0; i < function.tableModel.getRowCount(); i++) {
                    xValuesSecond.add((function.function.getX(i)));
                    yValuesSecond.add((function.function.getY(i)));
                    secondFunction.fireTableDataChanged();
                }
            }
            if (boxArraySecond.isSelected()) {
                CreateTabulatedFunction function = new CreateTabulatedFunction(this, "Создание функции", true);
                function.setVisible(true);
                secondFunction.setNulls();
                secondFunction.setCount(function.function.getCount());
                for (int i = 0; i < function.tableModel.getRowCount(); i++) {
                    xValuesSecond.add((function.function.getX(i)));
                    yValuesSecond.add((function.function.getY(i)));
                    secondFunction.fireTableDataChanged();
                }
            }
        });
    }
}
