package ru.ssau.tk.GussiRep.labOOP.ui;

import ru.ssau.tk.GussiRep.labOOP.functions.*;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.util.*;
import java.util.function.Consumer;

public class CreateMathFunction extends JDialog {
    private JComboBox<String> functionComboBox = new JComboBox<>();
    private JLabel fromLabel = new JLabel("От:");
    private JLabel toLabel = new JLabel("До:");
    private JLabel countLabel = new JLabel("Количество:");
    private JTextField countField = new JTextField();
    private JTextField fromField = new JTextField();
    private JTextField toField = new JTextField();
    private JButton okButton = new JButton("OK");
    private Map<String, MathFunction> nameFunctionMap = new HashMap<>();
    TabulatedFunctionFactory factory;
    TabulatedFunction function;

    public CreateMathFunction(TabulatedFunctionFactory factory, Consumer<? super TabulatedFunction> callback) {
        setModal(true);
        this.factory = factory;
        this.setBounds(300, 200, 500, 150);
        fillMap();
        compose();
        addButtonListeners(callback);
    }

    public CreateMathFunction(OperationsWithFunctions operationsWithFunctions, String создание_мат_функции, boolean b) {

    }

    public static void main(TabulatedFunctionFactory factory, Consumer<? super TabulatedFunction> callback) {
        CreateMathFunction app = new CreateMathFunction(factory, callback);
        app.setVisible(true);
    }

    public void fillMap() {
        nameFunctionMap.put("х в степени х функция", new PowFunction());
        nameFunctionMap.put("Арксинус-функция", new AsinFunction());
        nameFunctionMap.put("Квадратичная функция", new SqrFunction());
        nameFunctionMap.put("Нулевая функция", new ZeroFunction());
        nameFunctionMap.put("Постоянная функция", new ConstantFunction(20));
        String[] functions = new String[6];
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
//import ru.ssau.tk.GussiRep.labOOP.functions.*;
//import ru.ssau.tk.GussiRep.labOOP.functions.factory.ArrayTabulatedFunctionFactory;
//import ru.ssau.tk.GussiRep.labOOP.functions.factory.TabulatedFunctionFactory;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//public class CreateMathFunction extends JDialog {
//
//    private final JLabel fromLabel = new JLabel("От: ");
//    private final JLabel toLabel = new JLabel("До: ");
//    private final JComboBox<String> functionComboBox = new JComboBox<>();
//    private final JLabel countLabel = new JLabel("Укажите количество точек разбиения: ");
//    private final JTextField fromField = new JTextField();
//    private final JButton jButtonCreate = new JButton("Создать");
//    private final JTextField toField = new JTextField();
//    private final JTextField countField = new JTextField();
//    private final Map<String, MathFunction> nameMathFunctionMap = new HashMap<>();
//    private final TabulatedFunctionFactory factory = MainWindow.factory;
//    public TabulatedFunction function;
//
//    public CreateMathFunction(TabulatedFunctionFactory factory) {
//        getContentPane().setLayout(new FlowLayout());
//        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
//        setModal(true);
//        setSize(500, 150);
//        setTitle("Function");
//        stuffMap();
//        getContentPane().add(countLabel);
//        getContentPane().add(countField);
//        getContentPane().add(fromLabel);
//        getContentPane().add(fromField);
//        getContentPane().add(toLabel);
//        getContentPane().add(toField);
//        getContentPane().add(jButtonCreate);
//        getContentPane().add(functionComboBox);
//        compose();
//        //addActionListener(factory);
//    }
//
//    public CreateMathFunction() {
//        jButtonCreate.addActionListener(e -> {
//            try {
//                String func = (String) functionComboBox.getSelectedItem();
//                MathFunction selectedFunction = nameMathFunctionMap.get(func);
//                double from = Double.parseDouble(fromField.getText());
//                double to = Double.parseDouble(toField.getText());
//                int count = Integer.parseInt(countField.getText());
//                if (factory == null)
//                    MainWindow.setFactory(new ArrayTabulatedFunctionFactory());
//                function = factory.create(selectedFunction, from, to, count);
//                this.dispose();
//            } catch (NumberFormatException error) {
//                JOptionPane.showMessageDialog(this, "Пожалуйста, вводите цифры!");
//            }
//        });
//    }
//
//    void compose() {
//        GroupLayout layout = new GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setAutoCreateGaps(true);
//        layout.setAutoCreateContainerGaps(true);
//
//        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
//                .addGroup(layout.createSequentialGroup()
//                        .addComponent(fromLabel)
//                        .addComponent(fromField)
//                        .addComponent(toLabel)
//                        .addComponent(toField)
//                        .addComponent(countLabel)
//                        .addComponent(countField))
//                .addComponent(functionComboBox)
//                .addComponent(jButtonCreate)
//        );
//        layout.setVerticalGroup(layout.createSequentialGroup()
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addComponent(fromLabel)
//                        .addComponent(fromField)
//                        .addComponent(toLabel)
//                        .addComponent(toField)
//                        .addComponent(countLabel)
//                        .addComponent(countField))
//                .addComponent(functionComboBox)
//                .addComponent(jButtonCreate)
//        );
//    }
//
//    public void stuffMap() {
//        nameMathFunctionMap.put("х в степени х функция", new PowFunction());
//        nameMathFunctionMap.put("Арксинус-функция", new AsinFunction());
//        nameMathFunctionMap.put("Квадратичная функция", new SqrFunction());
//        nameMathFunctionMap.put("Нулевая функция", new ZeroFunction());
//        nameMathFunctionMap.put("Постоянная функция", new ConstantFunction(20));
//        String[] functions = new String[5];
//        int i = 0;
//        for (String string : nameMathFunctionMap.keySet()) {
//            functions[i++] = string;
//        }
//        Arrays.sort(functions);
//        for (String string : functions) {
//            functionComboBox.addItem(string);
//        }
//    }
//}
