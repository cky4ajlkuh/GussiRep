package ru.ssau.tk.GussiRep.labOOP.ui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
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
    JScrollPane firstScroll = new JScrollPane(new JTable(firstFunction));

    List<String> xValuesSecond = new ArrayList<>();
    List<String> yValuesSecond = new ArrayList<>();
    AbstractTableModel secondFunction = new TableModel(xValuesSecond, yValuesSecond);
    JScrollPane secondScroll = new JScrollPane(new JTable(secondFunction));

    List<String> xValuesResult = new ArrayList<>();
    List<String> yValuesResult = new ArrayList<>();
    AbstractTableModel result = new TableModel(xValuesResult, yValuesResult);
    JScrollPane resultFunction = new JScrollPane(new JTable(result));

    public OperationsWithFunctions(Menu menu, String s, Boolean modal) {
        super(menu, s, modal);

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
            }
            if (boxLLFirst.isSelected()) {
                CreateTabulatedFunction function = new CreateTabulatedFunction(this, "Создание функции", true);
                function.setVisible(true);
                boxArrayFirst.setEnabled(false);
            }
        });
        createSecondFunction.addActionListener(e -> {
            if (boxLLSecond.isSelected()) {
                CreateTabulatedFunction function = new CreateTabulatedFunction(this, "Создание функции", true);
                function.setVisible(true);
            }
            if (boxArraySecond.isSelected()) {
                CreateTabulatedFunction function = new CreateTabulatedFunction(this, "Создание функции", true);
                function.setVisible(true);
                boxArrayFirst.setEnabled(false);
            }
        });
    }
}
