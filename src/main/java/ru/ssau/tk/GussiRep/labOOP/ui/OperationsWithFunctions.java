package ru.ssau.tk.GussiRep.labOOP.ui;

import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.io.FunctionsIO;
import ru.ssau.tk.GussiRep.labOOP.operations.TabulatedFunctionOperationService;
import ru.ssau.tk.GussiRep.labOOP.ui.Exceptions.TablesIsEmpty;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OperationsWithFunctions extends JDialog {
    private CreateMathFunction createMathFunction;
    private final JComboBox<String> comboBox = new JComboBox<>(new String[]{"", "Умножение", "Деление", "Сумма", "Разность"});
    private final JButton jButtonCreateOfMathFunction1 = new JButton("Создать из мат. функции");
    private final JButton jButtonCreateOfMathFunction2 = new JButton("Создать из мат. функции");
    private final JButton jButtonCreateOfTabulatedFunction1 = new JButton("Создать из массива функции");
    private final JButton jButtonCreateOfTabulatedFunction2 = new JButton("Создать из массива функции");
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
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JFileChooser fileOpen = new JFileChooser();
        JFileChooser fileOpenSecond = new JFileChooser();
        JFileChooser fileSave = new JFileChooser();
        JFileChooser fileSaveSecond = new JFileChooser();
        JFileChooser fileSaveResult = new JFileChooser();

        fileSaveSecond.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileSaveSecond.setDialogTitle("Сохранение функции");
        fileSaveSecond.addChoosableFileFilter(new FileNameExtensionFilter("Табулированная функция", "bin"));
        fileSaveSecond.setAcceptAllFileFilterUsed(false);

        fileSave.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileSave.setDialogTitle("Сохранение функции");
        fileSave.addChoosableFileFilter(new FileNameExtensionFilter("Табулированная функция", "bin"));
        fileSave.setAcceptAllFileFilterUsed(false);

        fileSaveResult.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileSaveResult.setDialogTitle("Сохранение функции");
        fileSaveResult.addChoosableFileFilter(new FileNameExtensionFilter("Табулированная функция", "bin"));
        fileSaveResult.setAcceptAllFileFilterUsed(false);

        fileOpen.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileOpen.setDialogTitle("Загрузка функции");
        fileOpen.addChoosableFileFilter(new FileNameExtensionFilter("Табулированная функция", "bin"));
        fileOpen.setAcceptAllFileFilterUsed(false);

        fileOpenSecond.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileOpenSecond.setDialogTitle("Загрузка функции");
        fileOpenSecond.addChoosableFileFilter(new FileNameExtensionFilter("Табулированная функция", "bin"));
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
                        .addComponent(jButtonCreateOfMathFunction1)
                        .addComponent(firstScroll)

                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loadingFirstFunction)
                                .addComponent(saveFirstFunction)
                        )
                )

                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nameFirst)
                        .addComponent(jButtonCreateOfTabulatedFunction1)
                        .addComponent(firstScroll)

                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loadingFirstFunction)
                                .addComponent(saveFirstFunction)
                        )
                )


               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nameSecond)
                        .addComponent(jButtonCreateOfMathFunction2)
                        .addComponent(secondScroll)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loadingSecondFunction)
                                .addComponent(saveSecondFunction)
                        )
                )

                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nameSecond)
                        .addComponent(jButtonCreateOfTabulatedFunction2)
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
                                .addComponent(jButtonCreateOfMathFunction1)
                                .addComponent(firstScroll)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(loadingFirstFunction)
                                        .addComponent(saveFirstFunction)
                                )
                )

                .addGroup(
                        layout.createSequentialGroup()
                                .addComponent(nameFirst)
                                .addComponent(jButtonCreateOfTabulatedFunction1)
                                .addComponent(firstScroll)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(loadingFirstFunction)
                                        .addComponent(saveFirstFunction)
                                )
                )


                .addGroup(
                        layout.createSequentialGroup()
                                .addComponent(nameSecond)
                                .addComponent(jButtonCreateOfMathFunction2)
                                .addComponent(secondScroll)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(loadingSecondFunction)
                                        .addComponent(saveSecondFunction)
                                )
                )

                .addGroup(
                        layout.createSequentialGroup()
                                .addComponent(nameSecond)
                                .addComponent(jButtonCreateOfTabulatedFunction2)
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
                xValues.clear();
                yValues.clear();
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                    TabulatedFunction function = FunctionsIO.deserialize(in);
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
                xValuesSecond.clear();
                yValuesSecond.clear();
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                    TabulatedFunction function = FunctionsIO.deserialize(in);
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
            if (xValues.isEmpty()) {
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
                    double[] x = new double[firstFunction.getRowCount()];
                    double[] y = new double[firstFunction.getRowCount()];
                    for (int i = 0; i < firstFunction.getRowCount(); i++) {
                        x[i] = Double.parseDouble(firstFunction.getValueAt(i, 0).toString());
                        y[i] = Double.parseDouble(firstFunction.getValueAt(i, 1).toString());
                    }

                    function = Menu.factory.create(x, y);
                    try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
                        FunctionsIO.serialize(out, function);
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(this, err.getMessage());
                    }
                }
            }
        });

        saveSecondFunction.addActionListener(e -> {
            if (xValuesSecond.isEmpty()) {
                try {
                    throw new FileNotFoundException();
                } catch (FileNotFoundException fileNotFoundException) {
                    JOptionPane.showMessageDialog(this, "Нельзя сохранить пустую функцию!");
                    fileNotFoundException.printStackTrace();
                }
            } else {
                fileSaveSecond.showSaveDialog(menu);
                File file = fileSaveSecond.getSelectedFile();
                if (file != null) {
                    TabulatedFunction function;
                    double[] x = new double[secondFunction.getRowCount()];
                    double[] y = new double[secondFunction.getRowCount()];
                    for (int i = 0; i < secondFunction.getRowCount(); i++) {
                        x[i] = Double.parseDouble(secondFunction.getValueAt(i, 0).toString());
                        y[i] = Double.parseDouble(secondFunction.getValueAt(i, 1).toString());
                    }

                    function = Menu.factory.create(x, y);
                    try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
                        FunctionsIO.serialize(out, function);
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(this, err.getMessage());
                    }
                }
            }
        });

        saveResult.addActionListener(e -> {
            if (xValuesResult.isEmpty()) {
                try {
                    throw new FileNotFoundException();
                } catch (FileNotFoundException fileNotFoundException) {
                    JOptionPane.showMessageDialog(this, "Нельзя сохранить пустую функцию!");
                    fileNotFoundException.printStackTrace();
                }
            } else {
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
                    try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
                        FunctionsIO.serialize(out, function);
                    } catch (Exception err) {
                        JOptionPane.showMessageDialog(this, err.getMessage());
                    }
                }
            }

        });

        comboBox.addActionListener(event -> {
            if (firstFunction.getRowCount() == 0 | secondFunction.getRowCount() == 0) {
                try {
                    throw new TablesIsEmpty();
                } catch (TablesIsEmpty empty) {
                    JOptionPane.showMessageDialog(this, "Нельзя оперировать пустыми(ой) функциями!");
                    empty.printStackTrace();
                }
            } else {
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
            }

        });

        create.addActionListener(e -> {
            if (firstFunction.getRowCount() == 0 | secondFunction.getRowCount() == 0) {
                try {
                    throw new TablesIsEmpty();
                } catch (TablesIsEmpty empty) {
                    JOptionPane.showMessageDialog(this, "Введите сначала значения!");
                    empty.printStackTrace();
                }
            }
            if (comboBox.getSelectedIndex() == 1) {
                xValuesResult.clear();
                yValuesResult.clear();
                TabulatedFunction function = service.multiply(firstF, secondF);
                result.setCount(function.getCount());
                for (int i = 0; i < function.getCount(); i++) {
                    xValuesResult.add(i, (function.getX(i)));
                    yValuesResult.add(i, (function.getY(i)));
                    result.fireTableDataChanged();
                }
                resultFunction.setEnabled(false);
            }
            if (comboBox.getSelectedIndex() == 2) {
                xValuesResult.clear();
                yValuesResult.clear();
                TabulatedFunction function = service.divider(firstF, secondF);
                result.setCount(function.getCount());
                for (int i = 0; i < function.getCount(); i++) {
                    xValuesResult.add(i, (function.getX(i)));
                    yValuesResult.add(i, (function.getY(i)));
                    result.fireTableDataChanged();
                }
            }
            if (comboBox.getSelectedIndex() == 3) {
                xValuesResult.clear();
                yValuesResult.clear();
                TabulatedFunction function = service.sum(firstF, secondF);
                result.setCount(function.getCount());
                for (int i = 0; i < function.getCount(); i++) {
                    xValuesResult.add(i, (function.getX(i)));
                    yValuesResult.add(i, (function.getY(i)));
                    result.fireTableDataChanged();
                }
            }
            if (comboBox.getSelectedIndex() == 4) {
                xValuesResult.clear();
                yValuesResult.clear();
                TabulatedFunction function = service.subtract(firstF, secondF);
                result.setCount(function.getCount());
                for (int i = 0; i < function.getCount(); i++) {
                    xValuesResult.add(i, (function.getX(i)));
                    yValuesResult.add(i, (function.getY(i)));
                    result.fireTableDataChanged();
                }
            }
        });

        createFunctionsListener();
        setVisible(false);
        setResizable(false);
        setSize(new Dimension(1600, 600));
    }

    public void createFunctionsListener() {
        /*jButtonCreateOfMathFunction1.addActionListener(args -> {
            try {
                createMathFunction  = new CreateMathFunction(this, "Создание мат функции", true);

                firstFunction = (TableModel) createMathFunction.getTabulatedFunction();
                xValues.add(function.function.getX(i));
                yValues.add(function.function.getY(i))
                //firstFunction.setFunction(firstTable);
                firstFunction.fireTableDataChanged();
            } catch (UnsupportedOperationException | NullPointerException e) {
                System.out.println();
            }
        });*/

        jButtonCreateOfTabulatedFunction1.addActionListener(e -> {
            CreateTabulatedFunction function = new CreateTabulatedFunction(this, "Создание функции из массива", true);
            function.setVisible(true);
            xValues.clear();
            yValues.clear();
            firstFunction.setCount(function.function.getCount());
            for (int i = 0; i < function.function.getCount(); i++) {
                xValues.add(function.function.getX(i));
                yValues.add(function.function.getY(i));
                firstFunction.fireTableDataChanged();
            }
        });

        jButtonCreateOfMathFunction2.addActionListener(e -> {
            CreateMathFunction function = new CreateMathFunction(this, "Создание мат функции", true);
            function.setVisible(true);
            xValues.clear();
            yValues.clear();
            firstFunction.setCount(function.function.getCount());
            for (int i = 0; i < function.function.getCount(); i++) {
                xValues.add(function.function.getX(i));
                yValues.add(function.function.getY(i));
                firstFunction.fireTableDataChanged();
            }
        });


        jButtonCreateOfTabulatedFunction2.addActionListener(e -> {
            CreateTabulatedFunction function = new CreateTabulatedFunction(this, "Создание функции из массива", true);
            function.setVisible(true);
            xValuesSecond.clear();
            yValuesSecond.clear();
            secondFunction.setCount(function.function.getCount());
            for (int i = 0; i < function.tableModel.getRowCount(); i++) {
                xValuesSecond.add((function.function.getX(i)));
                yValuesSecond.add((function.function.getY(i)));
                secondFunction.fireTableDataChanged();
            }
        });
    }
}
