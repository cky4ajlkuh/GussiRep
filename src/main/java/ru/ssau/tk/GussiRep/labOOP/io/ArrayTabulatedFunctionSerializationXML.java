package ru.ssau.tk.GussiRep.labOOP.io;

import ru.ssau.tk.GussiRep.labOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;

import java.io.*;

public class ArrayTabulatedFunctionSerializationXML {
    public static void main(String[] args) {
        File fileArray = new File("XML.txt");

        double[] xValue = new double[]{2, 4, 6, 8, 10};
        double[] yValue = new double[]{4, 8, 12, 16, 20};

        ArrayTabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValue, yValue);

        try (BufferedWriter out = new BufferedWriter(new FileWriter(fileArray))) {
            FunctionsIO.serializeXml(out, arrayFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader in = new BufferedReader(new FileReader(fileArray))) {
            TabulatedFunction func = FunctionsIO.deserializeXml(in);
            System.out.println(func.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
