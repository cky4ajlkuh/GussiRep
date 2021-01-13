package ru.ssau.tk.GussiRep.labOOP.ui2;

import ru.ssau.tk.GussiRep.labOOP.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyMainWindow extends JFrame {
    private final JButton buttonCreateTabulatedFunction = new JButton("Создать табулированную функцию из массивов");
    private final JButton buttonSettings = new JButton("Настройки");
    private final JButton buttonCreateMathFunction = new JButton("Создать табулированную функцию с помощью другой функции");
    private final JButton openButton = new JButton("Открыть функцию");
    private final JButton saveButton = new JButton("Сохранить функцию");
    private final ArrayList<Double> xValues = new ArrayList<>();
    private final ArrayList<Double> yValues = new ArrayList<>();
    private final MyGreatTable tableModel = new MyGreatTable();
    private final JTable table = new JTable(tableModel);
    private TabulatedFunctionFactory factory;

    public MyMainWindow() {
        super("Основное окно");
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 1200, 500);
        getContentPane().add(buttonCreateTabulatedFunction);
        getContentPane().add(buttonCreateMathFunction);
        getContentPane().add(buttonSettings);
        getContentPane().add(openButton);
        getContentPane().add(saveButton);
        setLocationRelativeTo(null);
        compose();
        addButtonListeners();
        setVisible(true);
    }

    public void wrapTable(int countOld, int countNew) {
        tableModel.fireTableDataChanged();
        for (int i = 0; i < countOld; i++) {
            if (xValues.size() != 0) xValues.remove(countOld - i - 1);
            if (yValues.size() != 0) yValues.remove(countOld - i - 1);
        }
        for (int i = 0; i < countNew; i++) {
            xValues.add(tableModel.getFunction().getX(i));
            yValues.add(tableModel.getFunction().getY(i));
        }
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane = new JScrollPane(table);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonCreateTabulatedFunction)
                        .addComponent(buttonCreateMathFunction)
                        .addComponent(buttonSettings)
                        .addComponent(openButton)
                        .addComponent(saveButton))
                .addComponent(tableScrollPane)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(buttonCreateTabulatedFunction)
                        .addComponent(buttonCreateMathFunction)
                        .addComponent(buttonSettings)
                        .addComponent(openButton)
                        .addComponent(saveButton))
                .addComponent(tableScrollPane)
        );
    }

    private void addButtonListeners() {
        buttonCreateTabulatedFunction.addActionListener(event -> {
                    try {
                        int countOld = xValues.size();
                        MyTabFunction.main(factory, tableModel::setFunction);
                        int countNew = tableModel.getFunction().getCount();
                        wrapTable(countOld, countNew);
                    } catch (Exception e) {
                        if (e instanceof NullPointerException) {
                            e.printStackTrace();
                        } else
                            new MyErrorWind(this, e);
                    }
                }
        );

        buttonCreateMathFunction.addActionListener(event -> {
                    try {
                        int countOld = xValues.size();
                        MyMathFunction.main(factory, tableModel::setFunction);
                        int countNew = tableModel.getFunction().getCount();
                        wrapTable(countOld, countNew);
                    } catch (Exception e) {
                        if (e instanceof NullPointerException) {
                            e.printStackTrace();
                        } else
                            new MyErrorWind(this, e);
                    }
                }
        );

        buttonSettings.addActionListener(event -> {
            try {
                MyWindowForSettings.main(factory);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new MyErrorWind(this, e);
            }
        });

        openButton.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                MyFileReader.main(tableModel::setFunction);
                int countNew = tableModel.getFunction().getCount();
                wrapTable(countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new MyErrorWind(this, e);
            }
        });

        saveButton.addActionListener(event -> {
            try {
                MyFileWriter.main(tableModel.getFunction());
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new MyErrorWind(this, e);
            }
        });
    }


    public static void main(String[] args) {
        MyMainWindow window = new MyMainWindow();
        window.setVisible(true);
    }
}
