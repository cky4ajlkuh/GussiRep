package ru.ssau.tk.GussiRep.labOOP.io;

import com.thoughtworks.xstream.XStream;
import ru.ssau.tk.GussiRep.labOOP.functions.Point;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.functions.ArrayTabulatedFunction;

import java.io.*;
import java.util.Locale;
import java.text.NumberFormat;
import java.text.ParseException;

public final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(function.getCount());
        for (Point point : function) {
            printWriter.printf("%f %f\n", point.x, point.y);
        }
        printWriter.flush();
    }

    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(function.getCount());
        for (Point point : function) {
            dataOutputStream.writeDouble(point.x);
            dataOutputStream.writeDouble(point.y);
        }
        dataOutputStream.flush();
    }

    static void serializeXml(BufferedWriter writer, ArrayTabulatedFunction function) throws IOException {
        XStream xmlWriter = new XStream();
        String xmlString = xmlWriter.toXML(function);
        writer.write(xmlString);
        writer.flush();
    }

    static ArrayTabulatedFunction deserializeXml(BufferedReader reader) {
        XStream xmlReader = new XStream();
        return (ArrayTabulatedFunction) xmlReader.fromXML(reader);
    }

    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        int count = Integer.parseInt(reader.readLine());

        double[] xValues = new double[count];
        double[] yValues = new double[count];
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
        for (int i = 0; i < count; i++) {
            String line = reader.readLine();
            String[] splitLine = line.split(" ");
            try {
                xValues[i] = numberFormat.parse(splitLine[0]).doubleValue();
                yValues[i] = numberFormat.parse(splitLine[1]).doubleValue();
            } catch (ParseException eParse) {
                throw new IOException(eParse);
            }
        }

        return factory.create(xValues, yValues);
    }

    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int count = dataInputStream.readInt();
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        for (int i = 0; i < xValues.length; i++) {
            xValues[i] = dataInputStream.readDouble();
            yValues[i] = dataInputStream.readDouble();
        }
        return factory.create(xValues, yValues);
    }

    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(stream);
        out.writeObject(function);
        out.flush();
    }

    public static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        return (TabulatedFunction) new ObjectInputStream(stream).readObject();
    }

}
