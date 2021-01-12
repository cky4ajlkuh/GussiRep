package ru.ssau.tk.GussiRep.labOOP.ui;

import ru.ssau.tk.GussiRep.labOOP.functions.*;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CreateMathFunction extends JDialog {

    private final JLabel fromLabel = new JLabel("От: ");
    private final JLabel toLabel = new JLabel("До: ");
    private final JComboBox<String> functionComboBox = new JComboBox<>();
    private final JLabel countLabel = new JLabel("Укажите количество точек разбиения: ");
    private final JTextField fromField = new JTextField();
    private final JButton jButtonCreate = new JButton("Создать");
    private final JTextField toField = new JTextField();
    private final JTextField countField = new JTextField();
    private final Map<String, MathFunction> nameMathFunctionMap = new HashMap<>();
    private final TabulatedFunctionFactory factory = MainWindow.factory;
    public CreateTabulatedFunction function;
    //private TabulatedFunction function;

    public CreateMathFunction(OperationsWithFunctions operationsWithFunctions, String создание_мат_функции, boolean b) {
        jButtonCreate.addActionListener(args -> {
            try {
                String func = (String) functionComboBox.getSelectedItem();
                MathFunction selectedFunction = nameMathFunctionMap.get(func);
                double from = Double.parseDouble(fromField.getText());
                double to = Double.parseDouble(toField.getText());
                int count = Integer.parseInt(countField.getText());
                function = (CreateTabulatedFunction) factory.create(selectedFunction, from, to, count);
                this.dispose();
            } catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(this, "Пожалуйста, вводите цифры!");
            }
        });
        setModal(true);

        stuffMap();
        compose();
        setMaximumSize(new Dimension(500, 150));
        setMinimumSize(new Dimension(500, 150));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void compose() {
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
                .addComponent(jButtonCreate)
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
                .addComponent(jButtonCreate)
        );
    }

    public void stuffMap() {
        nameMathFunctionMap.put("х в степени х функция", new PowFunction());
        nameMathFunctionMap.put("Квадратичная функция", new SqrFunction());
        nameMathFunctionMap.put("Нулевая функция", new ZeroFunction());
        nameMathFunctionMap.put("Арксинус-функция", new AsinFunction());
        nameMathFunctionMap.put("Постоянная функция", new ConstantFunction(20));
        String[] functions = new String[5];
        int i = 0;
        for (String string : nameMathFunctionMap.keySet()) {
            functions[i++] = string;
        }
        Arrays.sort(functions);
        for (String string : functions) {
            functionComboBox.addItem(string);
        }
    }

    //public TabulatedFunction getTabulatedFunction() {
      //  return function;
    //}
}
