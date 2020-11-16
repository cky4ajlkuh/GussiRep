package ru.ssau.tk.GussiRep.labOOP.io;

import ru.ssau.tk.GussiRep.labOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        double[] x = {0, 1, 3};
        double[] y = {0, 10, 30};
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(x, y);
        TabulatedFunction listFunction = new LinkedListTabulatedFunction(x, y);
        try (BufferedWriter outArray = new BufferedWriter(new FileWriter("output/array function.txt"));
             BufferedWriter outList = new BufferedWriter(new FileWriter("output/linked list function.txt"))) {
            FunctionsIO.writeTabulatedFunction(outArray, arrayFunction);
            FunctionsIO.writeTabulatedFunction(outList, listFunction);
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
