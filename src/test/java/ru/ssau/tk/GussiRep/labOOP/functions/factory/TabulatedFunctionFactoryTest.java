package ru.ssau.tk.GussiRep.labOOP.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.GussiRep.labOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.StrictTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;

import static org.testng.Assert.*;

public class TabulatedFunctionFactoryTest {

    @Test
    public void testCreate() {
        double[] xValues = new double[]{1., 2., 3., 4., 5., 6., 7., 8., 9., 10.};
        double[] yValues = new double[]{11., 12., 13., 14., 15., 16., 17., 18., 19., 20.};
        double[] xValues2 = new double[]{21., 22., 23., 24., 25., 26., 27., 28., 29., 30.};
        double[] yValues2 = new double[]{31., 32., 33., 34., 35., 36., 37., 38., 39., 40.};
        double[] xValues3 = new double[]{41., 42., 43., 44., 45., 46., 47., 48., 49., 50.};
        double[] yValues3 = new double[]{51., 52., 53., 54., 55., 56., 57., 58., 59., 60.};
        LinkedListTabulatedFunctionFactory firstFunction = new LinkedListTabulatedFunctionFactory();
        ArrayTabulatedFunctionFactory secondFunction = new ArrayTabulatedFunctionFactory();

        assertTrue(firstFunction.create(xValues, yValues) instanceof LinkedListTabulatedFunction);
        assertTrue(secondFunction.create(xValues2, yValues2) instanceof ArrayTabulatedFunction);
        assertTrue(firstFunction.create(xValues3, yValues3) instanceof LinkedListTabulatedFunction);
        assertTrue(secondFunction.create(xValues3, yValues3) instanceof ArrayTabulatedFunction);
    }

    @Test
    public void testCreateStrict() {
        double[] xValues = new double[]{1., 2., 3., 4., 5., 6., 7., 8., 9., 10.};
        double[] yValues = new double[]{11., 12., 13., 14., 15., 16., 17., 18., 19., 20.};

        TabulatedFunctionFactory tabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction function = tabulatedFunctionFactory.create(xValues, yValues);
        TabulatedFunction function1 = tabulatedFunctionFactory.createStrict(xValues, yValues);

        TabulatedFunctionFactory tabulatedFunctionFactory1 = new ArrayTabulatedFunctionFactory();
        TabulatedFunction function2 = tabulatedFunctionFactory1.create(xValues, yValues);
        TabulatedFunction function3 = tabulatedFunctionFactory1.createStrict(xValues, yValues);
        assertTrue(function instanceof LinkedListTabulatedFunction);
        assertTrue(function1 instanceof StrictTabulatedFunction);
        assertTrue(function2 instanceof ArrayTabulatedFunction);
        assertTrue(function3 instanceof StrictTabulatedFunction);

    }
}