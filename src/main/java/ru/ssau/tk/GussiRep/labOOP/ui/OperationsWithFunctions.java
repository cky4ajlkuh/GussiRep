package ru.ssau.tk.GussiRep.labOOP.ui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OperationsWithFunctions extends JDialog {
    JButton createFirstFunction = new JButton();
    JButton createSecondFunction = new JButton();
    JButton loadingFirstFunction = new JButton();
    JButton loadingSecondFunction = new JButton();
    JButton saveFirstFunction = new JButton();
    JButton saveSecondFunction = new JButton();
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
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                //  .addComponent()
                // .addComponent()
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                // .addComponent()
                //.addComponent()
        );
        setVisible(false);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(new Dimension(500, 500));
    }
}
