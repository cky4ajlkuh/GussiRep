package ru.ssau.tk.GussiRep.labOOP.ui2;

import ru.ssau.tk.GussiRep.labOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class MyWindowForSettings extends JDialog {
    private final JLabel label = new JLabel("Выберите тип фабрики функции:");
    private final JButton okButton = new JButton("OK");
    private final Map<String, TabulatedFunctionFactory> nameFunctionMap = new HashMap<>();
    private final JComboBox<String> functionComboBox = new JComboBox<>();
    TabulatedFunctionFactory factory;

    public MyWindowForSettings(TabulatedFunctionFactory factory) {
        setModal(true);
        this.factory = factory;
        setTitle("Выберите тип фабрики");
        setSize(300, 100);
        fillMap();
        compose();
        addButtonListeners();
        setLocationRelativeTo(null);
    }

    public static void main(TabulatedFunctionFactory factory) {
        MyWindowForSettings frame = new MyWindowForSettings(factory);
        frame.setVisible(true);
    }

    public void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(label)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(functionComboBox)
                        .addComponent(okButton))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(label)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(functionComboBox)
                        .addComponent(okButton)
                ));
    }

    public void fillMap() {
        nameFunctionMap.put("Массив", new ArrayTabulatedFunctionFactory());
        nameFunctionMap.put("Связный список", new LinkedListTabulatedFunctionFactory());
        String[] functions = new String[2];
        int i = 0;
        for (String string : nameFunctionMap.keySet()) {
            functions[i++] = string;
        }
        Arrays.sort(functions);
        for (String string : functions) {
            functionComboBox.addItem(string);
        }
    }

    public void addButtonListeners() {
        okButton.addActionListener(event -> {
            try {
                String func = (String) functionComboBox.getSelectedItem();
                this.factory = nameFunctionMap.get(func);
                this.dispose();
            } catch (Exception e) {
               MyErrorWind myErrorWind = new MyErrorWind(this, e);
                myErrorWind.showErrorWindow(this, e);
            }
        });
    }
}
