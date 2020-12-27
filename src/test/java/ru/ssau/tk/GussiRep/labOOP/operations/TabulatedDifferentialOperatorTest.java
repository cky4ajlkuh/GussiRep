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
    @Test
    public void testDeriveSynchronously() {
        TabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new double[]{5, 6, 7, 8, 9}, new double[]{25, 36, 49, 64, 81});
        TabulatedDifferentialOperator differentialOperatorList = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction differentialFunctionList = differentialOperatorList.deriveSynchronously(linkedListTabulatedFunction);
        assertTrue(differentialFunctionList instanceof LinkedListTabulatedFunction);

        for (int i = 0; i < differentialFunctionList.getCount(); i++) {
            assertEquals(differentialFunctionList.getX(i), (5 + i));
        }

        assertEquals(differentialFunctionList.getY(0), 11);
        assertEquals(differentialFunctionList.getY(1), 13);
        assertEquals(differentialFunctionList.getY(2), 15);
        assertEquals(differentialFunctionList.getY(3), 17);
        assertEquals(differentialFunctionList.getY(4), 17);

        TabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(new double[]{7, 8, 9, 10, 11, 12}, new double[]{490, 640, 820, 1000, 1210, 1440});
        TabulatedDifferentialOperator differentialOperatorArray = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction differentialFunctionArray = differentialOperatorArray.deriveSynchronously(arrayTabulatedFunction);
        assertTrue(differentialFunctionArray instanceof ArrayTabulatedFunction);

        for (int i = 0; i < differentialFunctionArray.getCount(); i++) {
            assertEquals(differentialFunctionArray.getX(i), (7 + i));
        }

        assertEquals(differentialFunctionArray.getY(0), 150);
        assertEquals(differentialFunctionArray.getY(1), 180);
        assertEquals(differentialFunctionArray.getY(2), 180);
        assertEquals(differentialFunctionArray.getY(3), 210);
        assertEquals(differentialFunctionArray.getY(4), 230);
        assertEquals(differentialFunctionArray.getY(5), 230);
    }
}