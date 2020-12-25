package ru.ssau.tk.GussiRep.labOOP.ui;

import ru.ssau.tk.GussiRep.labOOP.operations.TabulatedFunctionOperationService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OperationsWithFunctions extends JDialog {

    JMenu menu = new JMenu("Операции");

    JCheckBox boxArrayFirst = new JCheckBox("Массив");
    JCheckBox boxLLFirst = new JCheckBox("Связный список");
    JCheckBox boxArraySecond = new JCheckBox("Массив");
    JCheckBox boxLLSecond = new JCheckBox("Связный список");

    JLabel operation = new JLabel("Операция");
    JTextField operationText = new JTextField();

    TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

    JButton createFirstFunction = new JButton("Создать");
    JButton createSecondFunction = new JButton("Создать");

    JButton loadingFirstFunction = new JButton("Загрузить");
    JButton loadingSecondFunction = new JButton("Загрузить");

    JButton saveFirstFunction = new JButton("Сохранить");
    JButton saveSecondFunction = new JButton("Сохранить");
    JButton saveResult = new JButton("Сохранить");

    JButton create = new JButton("=");

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

        JMenuItem sum = new JMenuItem("Суммирование");
        JMenuItem division = new JMenuItem("Деление");
        JMenuItem multiplication = new JMenuItem("Умножение");
        JMenuItem difference = new JMenuItem("Разность");

        this.menu.add(sum);
        this.menu.add(division);
        this.menu.add(multiplication);
        this.menu.add(difference);

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(this.menu);
        setJMenuBar(jMenuBar);
        operationText.setPreferredSize(new Dimension(50,25));
        operationText.setMinimumSize(new Dimension(45,25));
        operationText.setMaximumSize(new Dimension(55,25));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
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
                .addGroup(layout.createParallelGroup()
                        .addComponent(operation)
                        .addComponent(operationText)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
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
                .addComponent(create)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(resultFunction)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(saveResult)
                        )

                )

        );
        layout.setVerticalGroup(layout.createParallelGroup()
                .addGroup(
                        layout.createSequentialGroup()
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
                .addGroup(layout.createSequentialGroup()
                        .addComponent(operation)
                        .addComponent(operationText)
                )
                .addGroup(
                        layout.createSequentialGroup()
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
                .addComponent(create)
                .addGroup(
                        layout.createSequentialGroup()
                                .addComponent(resultFunction)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(saveResult)
                                )
                )

        );
        setVisible(false);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(new Dimension(1200, 600));
    }
}
