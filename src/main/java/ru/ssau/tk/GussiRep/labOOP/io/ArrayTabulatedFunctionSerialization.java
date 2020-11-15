package ru.ssau.tk.GussiRep.labOOP.io;

import ru.ssau.tk.GussiRep.labOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        File file = new File("output/serialized array functions.bin");

        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            double[] xValues = new double[]{-3., -2., -1., 0., 1., 2., 3.};
            double[] yValues = new double[]{9., 4., 1., 0., 1., 4., 9.};
            ArrayTabulatedFunction functionATF = new ArrayTabulatedFunction(xValues, yValues);
            TabulatedFunction firstDerivative = new TabulatedDifferentialOperator().derive(functionATF);
            TabulatedFunction secondDerivative = new TabulatedDifferentialOperator().derive(firstDerivative);

            FunctionsIO.serialize(bufferedOutputStream, functionATF);
            FunctionsIO.serialize(bufferedOutputStream, firstDerivative);
            FunctionsIO.serialize(bufferedOutputStream, secondDerivative);

            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

            TabulatedFunction function = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction function1 = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction function2 = FunctionsIO.deserialize(bufferedInputStream);

            System.out.println("________________");
            System.out.println(function.toString());
            System.out.println("________________");
            System.out.println(function1.toString());
            System.out.println("________________");
            System.out.println(function2.toString());
            System.out.println("________________");

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

}
