package ru.ssau.tk.GussiRep.labOOP.io;

import ru.ssau.tk.GussiRep.labOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.LinkedListTabulatedFunction;

import java.io.*;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        ArrayTabulatedFunction functionATF = new ArrayTabulatedFunction(new double[]{1., 2., 3., 4,}, new double[]{2., 4., 6., 8.});
        LinkedListTabulatedFunction functionLLF = new LinkedListTabulatedFunction(new double[]{1., 2., 3., 4,}, new double[]{3., 6., 9., 12.});
        try (BufferedOutputStream outputStreamATF = new BufferedOutputStream(new FileOutputStream(new File("output/array function.bin")));
             BufferedOutputStream outputStreamLLF = new BufferedOutputStream(new FileOutputStream(new File("output/array function.bin")))) {
            FunctionsIO.writeTabulatedFunction(outputStreamATF, functionATF);
            FunctionsIO.writeTabulatedFunction(outputStreamLLF, functionLLF);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
