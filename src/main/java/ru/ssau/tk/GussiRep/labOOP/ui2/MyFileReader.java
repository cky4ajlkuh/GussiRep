package ru.ssau.tk.GussiRep.labOOP.ui2;

import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.io.FunctionsIO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.function.Consumer;

public class MyFileReader extends JDialog {
    public MyFileReader(Consumer<? super TabulatedFunction> callback) {
        setModal(true);
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(
                new FileNameExtensionFilter("Bin files", "bin"));

        chooser.setAcceptAllFileFilterUsed(false);
        int rVal = chooser.showOpenDialog(MyFileReader.this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
            if (file != null) {
                try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                    TabulatedFunction function = FunctionsIO.readTabulatedFunction(inputStream, factory);
                    callback.accept(function);
                } catch (Exception e) {
                    new MyErrorWind(this, e);
                }
            }
        }
    }

    public static void main(Consumer<? super TabulatedFunction> callback) {
        new MyFileReader(callback);
    }
}
