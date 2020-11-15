package ru.ssau.tk.GussiRep.labOOP.io;

import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.operations.TabulatedDifferentialOperator;

import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("input/binary function.bin"))) {
            TabulatedFunction tabulatedFunction = FunctionsIO.readTabulatedFunction(bufferedInputStream, new ArrayTabulatedFunctionFactory());
            System.out.println(tabulatedFunction.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Введите размер и значения функции");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            LinkedListTabulatedFunctionFactory linkedListTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();

            TabulatedFunction tabulatedFunction = FunctionsIO.readTabulatedFunction(bufferedReader, linkedListTabulatedFunctionFactory);

            TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator(linkedListTabulatedFunctionFactory);

            System.out.println(tabulatedDifferentialOperator.derive(tabulatedFunction).toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
