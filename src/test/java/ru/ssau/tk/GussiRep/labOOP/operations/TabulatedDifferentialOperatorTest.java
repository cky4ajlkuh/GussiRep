package ru.ssau.tk.GussiRep.labOOP.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.GussiRep.labOOP.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.LinkedListTabulatedFunctionFactory;

import static org.testng.Assert.*;

public class TabulatedDifferentialOperatorTest {

    private final static ArrayTabulatedFunctionFactory factoryATF = new ArrayTabulatedFunctionFactory();
    private final static LinkedListTabulatedFunctionFactory factoryLLF = new LinkedListTabulatedFunctionFactory();
    private final static double[] xValues = new double[]{1., 4., 9., 16., 25., 36.};
    private final static double[] yValues = new double[]{1., 2., 3., 4., 5., 6.};
    private final static double[] xValuesSecond = new double[]{0., 2., 4., 6., 8., 10.};
    private final static double[] yValuesSecond = new double[]{6., 6., 6., 6., 6., 6.};
    private final static double[] yValuesDivider = new double[]{1 / 2., 1 / 4., 1 / 6., 1 / 8., 1 / 10., 1 / 12.};
    private final static double[] yValuesDivider2 = new double[]{0., 0., 0., 0., 0., 0.};
    private final static TabulatedFunction firstFunction = factoryATF.create(xValues, yValues);
    private final static TabulatedFunction secondFunction = factoryLLF.create(xValuesSecond, yValuesSecond);
    private final static TabulatedDifferentialOperator operator1 = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
    private final static TabulatedDifferentialOperator operator2 = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());

    @Test
    public void testDeriveSynchronously() {
        TabulatedFunction function1 = operator2.deriveSynchronously(firstFunction);
        TabulatedFunction function2 = operator1.deriveSynchronously(secondFunction);
        TabulatedFunction function3 = operator1.deriveSynchronously(firstFunction);
        TabulatedFunction function4 = operator2.deriveSynchronously(secondFunction);

        assertTrue(function1 instanceof ArrayTabulatedFunction);
        assertTrue(function2 instanceof LinkedListTabulatedFunction);
        assertTrue(function3 instanceof LinkedListTabulatedFunction);
        assertTrue(function4 instanceof ArrayTabulatedFunction);

        for (int i = 0; i < yValuesDivider.length; i++) {
            assertEquals(function1.getY(i), yValuesDivider[i], 1 / 3.);
            assertEquals(function2.getY(i), yValuesDivider2[i], 0.01);
            assertEquals(function3.getY(i), yValuesDivider[i], 1 / 3.);
            assertEquals(function4.getY(i), yValuesDivider2[i], 0.01);
        }
    }

    @Test
    public void testDerive() {
        final TabulatedFunction function1 = operator2.derive(firstFunction);
        final TabulatedFunction function2 = operator1.derive(secondFunction);
        final TabulatedFunction function3 = operator1.derive(firstFunction);
        final TabulatedFunction function4 = operator2.derive(secondFunction);
        for (int i = 0; i < yValuesDivider.length; i++) {
            assertEquals(function1.getY(i), yValuesDivider[i], 1 / 3.);
            assertEquals(function2.getY(i), yValuesDivider2[i], 0.01);
            assertEquals(function3.getY(i), yValuesDivider[i], 1 / 3.);
            assertEquals(function4.getY(i), yValuesDivider2[i], 0.01);
        }
        assertTrue(function1 instanceof ArrayTabulatedFunction);
        assertTrue(function2 instanceof LinkedListTabulatedFunction);
        assertTrue(function3 instanceof LinkedListTabulatedFunction);
        assertTrue(function4 instanceof ArrayTabulatedFunction);
    }

    @Test
    public void testSetFactory() {
        operator1.setFactory(new ArrayTabulatedFunctionFactory());
        operator2.setFactory(new LinkedListTabulatedFunctionFactory());
        assertTrue(operator1.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(operator2.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testGetFactory() {
        assertTrue(operator1.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        assertTrue(operator2.getFactory() instanceof ArrayTabulatedFunctionFactory);
    }
}