package ru.ssau.tk.GussiRep.labOOP.io;

import ru.ssau.tk.GussiRep.labOOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.operations.TabulatedDifferentialOperator;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {

        File file = new File("output/serialized linked list functions.bin");

        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {

            LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new double[]{1., 2., 3., 4., 5., 6}, new double[]{2., 4., 6., 8., 10., 12.});
            TabulatedFunction firstDerivative = new TabulatedDifferentialOperator().derive(function);
            TabulatedFunction secondDerivative = new TabulatedDifferentialOperator().derive(firstDerivative);

            FunctionsIO.serialize(bufferedOutputStream, function);
            FunctionsIO.serialize(bufferedOutputStream, firstDerivative);
            FunctionsIO.serialize(bufferedOutputStream, secondDerivative);

            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

            TabulatedFunction function1 = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction function2 = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction function3 = FunctionsIO.deserialize(bufferedInputStream);

            System.out.println("________________");
            System.out.println(function1.toString());
            System.out.println("________________");
            System.out.println(function2.toString());
            System.out.println("________________");
            System.out.println(function3.toString());
            System.out.println("________________");

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
