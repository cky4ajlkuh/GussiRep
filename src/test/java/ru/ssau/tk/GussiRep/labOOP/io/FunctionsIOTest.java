package ru.ssau.tk.GussiRep.labOOP.io;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import ru.ssau.tk.GussiRep.labOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.*;
import java.util.Objects;

import static org.testng.Assert.*;

public class FunctionsIOTest {

    private final static double[] xValues = new double[]{0., 1., 2., 3., 4., 5.};
    private final static double[] yValues = new double[]{0., 2., 4., 6., 8., 10};
    private final TabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

    @Test
    public void testWriteTabulatedFunction() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("temp/first.txt"))) {
            FunctionsIO.writeTabulatedFunction(writer, function);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadTabulatedFunction() {
        try (BufferedReader reader = new BufferedReader(new FileReader("temp/first.txt"))) {
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(reader, new LinkedListTabulatedFunctionFactory());

            assertEquals(function.getCount(), this.function.getCount());

            for (int i = 0; i < function.getCount(); i++) {
                assertEquals(function.getX(i), this.function.getX(i));
                assertEquals(function.getY(i), this.function.getY(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSerialize() {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("temp/serialize.bin"))) {
            FunctionsIO.serialize(out, function);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeserialize() {

    }

    @Test
    public void testXML() {
        ArrayTabulatedFunction arrayFunction = new ArrayTabulatedFunction(xValues, yValues);

        try (BufferedWriter out = new BufferedWriter(new FileWriter("temp/serialized array functions.XML"))) {
            FunctionsIO.serializeXml(out, arrayFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader in = new BufferedReader(new FileReader("temp/serialized array functions.XML"))) {
            TabulatedFunction resultArray = FunctionsIO.deserializeXml(in);

            assertEquals(arrayFunction.getCount(), resultArray.getCount());

            for (int i = 0; i < arrayFunction.getCount(); i++) {
                assertEquals(arrayFunction.getX(i), resultArray.getX(i));
                assertEquals(arrayFunction.getY(i), resultArray.getY(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void deleteOnExit() {
        for (File myFile :new File("temp").listFiles())
            if (myFile.isFile() && myFile.delete()) {
                System.out.println(myFile.getName() + " deleted");
            }
    }
}