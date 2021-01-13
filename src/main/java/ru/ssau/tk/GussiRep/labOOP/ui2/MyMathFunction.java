package ru.ssau.tk.GussiRep.labOOP.ui2;

import ru.ssau.tk.GussiRep.labOOP.functions.*;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.util.*;
import java.util.function.Consumer;

public class MyMathFunction extends JDialog {
    private final JComboBox<String> functionComboBox = new JComboBox<>();
    private final JLabel fromLabel = new JLabel("От:");
    private final JLabel toLabel = new JLabel("До:");
    private final JLabel countLabel = new JLabel("Количество:");
    private final JTextField countField = new JTextField();
    private final JTextField fromField = new JTextField();
    private final JTextField toField = new JTextField();
    private final JButton okButton = new JButton("OK");
    private final Map<String, MathFunction> nameFunctionMap = new HashMap<>();
    TabulatedFunctionFactory factory;
    TabulatedFunction function;

    public MyMathFunction(TabulatedFunctionFactory factory, Consumer<? super TabulatedFunction> callback) {
        setModal(true);
        this.factory = factory;
        this.setBounds(200, 300, 400, 200);
        fillMap();
        compose();
        addButtonListeners(callback);
    }


    public static void main(TabulatedFunctionFactory factory, Consumer<? super TabulatedFunction> callback) {
        ru.ssau.tk.GussiRep.labOOP.ui.CreateMathFunction app = new ru.ssau.tk.GussiRep.labOOP.ui.CreateMathFunction(factory, callback);
        app.setVisible(true);
    }

    public void fillMap() {
        nameFunctionMap.put("х в степени х функция", new PowFunction());
        nameFunctionMap.put("Арксинус-функция", new AsinFunction());
        nameFunctionMap.put("Квадратичная функция", new SqrFunction());
        nameFunctionMap.put("Нулевая функция", new ZeroFunction());
        nameFunctionMap.put("Постоянная функция", new ConstantFunction(20));
        String[] functions = new String[5];
        int i = 0;
        for (String string : nameFunctionMap.keySet()) {
            functions[i++] = string;
        }
        Arrays.sort(functions);
        for (String string : functions) {
            functionComboBox.addItem(string);
        }
    }

    public void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(fromLabel)
                        .addComponent(fromField)
                        .addComponent(toLabel)
                        .addComponent(toField)
                        .addComponent(countLabel)
                        .addComponent(countField))
                .addComponent(functionComboBox)
                .addComponent(okButton)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fromLabel)
                        .addComponent(fromField)
                        .addComponent(toLabel)
                        .addComponent(toField)
                        .addComponent(countLabel)
                        .addComponent(countField))
                .addComponent(functionComboBox)
                .addComponent(okButton)
        );
    }

    public void addButtonListeners(Consumer<? super TabulatedFunction> callback) {
        okButton.addActionListener(event -> {
            try {
                String func = (String) functionComboBox.getSelectedItem();
                MathFunction selectedFunction = nameFunctionMap.get(func);
                double from = Double.parseDouble(fromField.getText());
                double to = Double.parseDouble(toField.getText());
                int count = Integer.parseInt(countField.getText());
                function = factory.create(selectedFunction, from, to, count);
                callback.accept(function);
                this.dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        });
    }
}
