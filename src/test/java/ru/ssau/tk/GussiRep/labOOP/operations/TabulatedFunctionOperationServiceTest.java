package ru.ssau.tk.GussiRep.labOOP.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.GussiRep.labOOP.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.GussiRep.labOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.Point;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.LinkedListTabulatedFunctionFactory;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {

    private final static double[] xValues = new double[]{1., 4., 9., 16., 25., 36.};
    private final static double[] yValues = new double[]{5., 12., 89., 5., 56., 1.};
    private final static double[] yValuesSecond = new double[]{1., 4., 7., 16., 15., 3.};

    TabulatedFunction functionFirst = new ArrayTabulatedFunction(xValues, yValues);
    TabulatedFunction functionSecond = new LinkedListTabulatedFunction(xValues, yValuesSecond);

    @Test
    public void testSetFactory() {
        assertTrue(new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory()).getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory()).getFactory() instanceof LinkedListTabulatedFunctionFactory);

    }

    @Test
    public void testGetFactory() {
        TabulatedFunctionOperationService service1 = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());
        TabulatedFunctionOperationService service2 = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());

        assertTrue(service1.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(service2.getFactory() instanceof LinkedListTabulatedFunctionFactory);

    }

    @Test
    public void testAsPoints() {
        Point[] pointsA = TabulatedFunctionOperationService.asPoints(functionFirst);
        Point[] pointsB = TabulatedFunctionOperationService.asPoints(functionSecond);
        for (int i = 0; i < xValues.length; i++) {
            assertEquals(pointsA[i].x, functionFirst.getX(i), 0.001);
            assertEquals(pointsA[i].y, functionFirst.getY(i), 0.001);
        }

        for (int i = 0; i < xValues.length; i++) {
            assertEquals(pointsB[i].x, functionSecond.getX(i), 0.001);
            assertEquals(pointsB[i].y, functionSecond.getY(i), 0.001);
        }
    }

    @Test
    public void testSum() {
        LinkedListTabulatedFunctionFactory factoryLLF = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService service1 = new TabulatedFunctionOperationService();
        TabulatedFunctionOperationService service2 = new TabulatedFunctionOperationService(factoryLLF);
        TabulatedFunction a = new ArrayTabulatedFunctionFactory().create(xValues, yValues);
        TabulatedFunction b = factoryLLF.create(xValues, yValuesSecond);
        TabulatedFunction sumATF = service1.sum(a, b);
        TabulatedFunction sumLLF = service2.sum(a, b);
        int i = 0;
        for (Point point : sumATF) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValues[i] + yValuesSecond[i++]);
        }
        int j = 0;
        for (Point point : sumLLF) {
            assertEquals(point.x, xValues[j]);
            assertEquals(point.y, yValues[j] + yValuesSecond[j++]);
        }
        assertThrows(InconsistentFunctionsException.class, () ->
                service1.sum(factoryLLF.create(new double[]{1., 2., 3}, new double[]{2., 6., 5.}), b));


        assertTrue(a instanceof ArrayTabulatedFunction);
        assertTrue(b instanceof LinkedListTabulatedFunction);
    }

    @Test
    public void testSubtract() {
        LinkedListTabulatedFunctionFactory factoryLLF = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService service1 = new TabulatedFunctionOperationService();
        TabulatedFunctionOperationService service2 = new TabulatedFunctionOperationService(factoryLLF);
        ArrayTabulatedFunctionFactory factoryATF = new ArrayTabulatedFunctionFactory();
        TabulatedFunction a = new ArrayTabulatedFunctionFactory().create(xValues, yValues);
        TabulatedFunction b = factoryLLF.create(xValues, yValuesSecond);
        TabulatedFunction subtractATF = service1.subtract(a, b);
        TabulatedFunction subtractLLF = service2.subtract(a, b);
        int i = 0;
        for (Point point : subtractATF) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValues[i] - yValuesSecond[i++]);
        }
        int j = 0;
        for (Point point : subtractLLF) {
            assertEquals(point.x, xValues[j]);
            assertEquals(point.y, yValues[j] - yValuesSecond[j++]);
        }
        assertThrows(InconsistentFunctionsException.class, () ->
                service2.subtract(factoryATF.create(new double[]{-4., 0., 3., 18.}, new double[]{-5., -2., 5., 6.}), b));


        assertTrue(a instanceof ArrayTabulatedFunction);
        assertTrue(b instanceof LinkedListTabulatedFunction);
    }

    @Test
    public void testMultiply() {
        double[] arrayX = new double[]{-10, -5, 0, 5, 10, 15};
        double[] arrayY = new double[]{0.5, 1.5, 2.5, 3.5, 4.5, 5.5};
        double[] secondArrayY = new double[]{0., 2., 4., 6., 8., 10.};
        LinkedListTabulatedFunctionFactory factoryLLF = new LinkedListTabulatedFunctionFactory();
        ArrayTabulatedFunctionFactory factoryATF = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService service1 = new TabulatedFunctionOperationService();
        TabulatedFunctionOperationService service2 = new TabulatedFunctionOperationService(factoryLLF);
        TabulatedFunction a = new ArrayTabulatedFunctionFactory().create(arrayX, arrayY);
        TabulatedFunction b = factoryLLF.create(arrayX, secondArrayY);
        TabulatedFunction subtractATF = service1.multiply(a, b);
        TabulatedFunction subtractLLF = service2.multiply(a, b);
        int i = 0;
        for (Point point : subtractATF) {
            assertEquals(point.x, arrayX[i]);
            assertEquals(point.y, arrayY[i] * secondArrayY[i++]);
        }
        int j = 0;
        for (Point point : subtractLLF) {
            assertEquals(point.x, arrayX[j]);
            assertEquals(point.y, arrayY[j] * secondArrayY[j++]);
        }
        assertThrows(InconsistentFunctionsException.class, () ->
                service1.subtract(factoryLLF.create(new double[]{1, 1.1, 1.2, 1.998, 1.999}, new double[]{6, 23, 511, 1000.01, 1000.2}),
                        factoryATF.create(new double[]{1., 1.1, 1.2, 1.998}, new double[]{23, 511., 900., 1000.})));

        assertTrue(a instanceof ArrayTabulatedFunction);
        assertTrue(b instanceof LinkedListTabulatedFunction);

    }

    @Test
    public void testDivider() {
        LinkedListTabulatedFunctionFactory factoryLLF = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService service1 = new TabulatedFunctionOperationService();
        TabulatedFunctionOperationService service2 = new TabulatedFunctionOperationService(factoryLLF);
        TabulatedFunction a = new ArrayTabulatedFunctionFactory().create(xValues, yValues);
        TabulatedFunction b = factoryLLF.create(xValues, yValuesSecond);
        TabulatedFunction dividerATF = service1.divider(a, b);
        TabulatedFunction dividerLLF = service2.divider(a, b);
        int i = 0;
        for (Point point : dividerATF) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValues[i] / yValuesSecond[i++]);
        }
        int j = 0;
        for (Point point : dividerLLF) {
            assertEquals(point.x, xValues[j]);
            assertEquals(point.y, yValues[j] / yValuesSecond[j++]);
        }
        assertThrows(InconsistentFunctionsException.class, () ->
                service1.subtract(factoryLLF.create(new double[]{6., 7., 8.9, 10., 23}, new double[]{1.,10.,100.,1000.,10000.}), a));

        assertTrue(a instanceof ArrayTabulatedFunction);
        assertTrue(b instanceof LinkedListTabulatedFunction);
    }

}