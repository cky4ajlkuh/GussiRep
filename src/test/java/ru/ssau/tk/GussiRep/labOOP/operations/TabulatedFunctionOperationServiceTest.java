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

    private final static TabulatedFunction functionFirst = new ArrayTabulatedFunction(xValues, yValues);
    private final static TabulatedFunction functionSecond = new LinkedListTabulatedFunction(xValues, yValuesSecond);

    @Test
    public void testSetFactory() {
        final TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
        service.setFactory(new LinkedListTabulatedFunctionFactory());
        final TabulatedFunctionOperationService service1 = new TabulatedFunctionOperationService();
        service1.setFactory(new ArrayTabulatedFunctionFactory());
        final TabulatedFunctionOperationService service2 = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());
        service2.setFactory(new LinkedListTabulatedFunctionFactory());
        assertTrue(service.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        assertTrue(service1.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(service2.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testGetFactory() {
        final TabulatedFunctionOperationService service1 = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());
        final TabulatedFunctionOperationService service2 = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());

        assertTrue(service1.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(service2.getFactory() instanceof LinkedListTabulatedFunctionFactory);

    }

    @Test
    public void testAsPoints() {
        final Point[] pointsA = TabulatedFunctionOperationService.asPoints(functionFirst);
        final Point[] pointsB = TabulatedFunctionOperationService.asPoints(functionSecond);
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
        final LinkedListTabulatedFunctionFactory factoryLLF = new LinkedListTabulatedFunctionFactory();
        final TabulatedFunctionOperationService service1 = new TabulatedFunctionOperationService();
        final TabulatedFunctionOperationService service2 = new TabulatedFunctionOperationService(factoryLLF);
        final TabulatedFunction a = new ArrayTabulatedFunctionFactory().create(xValues, yValues);
        final TabulatedFunction b = factoryLLF.create(xValues, yValuesSecond);
        final TabulatedFunction sumATF = service1.sum(a, b);
        final TabulatedFunction sumLLF = service2.sum(a, b);
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

        assertThrows(InconsistentFunctionsException.class, () ->
                service1.sum(factoryLLF.create(new double[]{1., 4., 9., 16., 24., 36.}, new double[]{1., 2., 3., 4., 5., 6.}), a));

        assertTrue(sumATF instanceof ArrayTabulatedFunction);
        assertTrue(sumLLF instanceof LinkedListTabulatedFunction);
    }

    @Test
    public void testSubtract() {
        final LinkedListTabulatedFunctionFactory factoryLLF = new LinkedListTabulatedFunctionFactory();
        final TabulatedFunctionOperationService service1 = new TabulatedFunctionOperationService();
        final TabulatedFunctionOperationService service2 = new TabulatedFunctionOperationService(factoryLLF);
        final ArrayTabulatedFunctionFactory factoryATF = new ArrayTabulatedFunctionFactory();
        final TabulatedFunction a = new ArrayTabulatedFunctionFactory().create(xValues, yValues);
        final TabulatedFunction b = factoryLLF.create(xValues, yValuesSecond);
        final TabulatedFunction subtractATF = service1.subtract(a, b);
        final TabulatedFunction subtractLLF = service2.subtract(a, b);
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

        assertThrows(InconsistentFunctionsException.class, () ->
                service2.subtract(factoryATF.create(new double[]{1., 4., 9., 16., 25., 37.}, new double[]{1., 2., 3., 4., 5., 6.}), b));


        assertTrue(subtractATF instanceof ArrayTabulatedFunction);
        assertTrue(subtractLLF instanceof LinkedListTabulatedFunction);
    }

    @Test
    public void testMultiply() {
        final double[] arrayX = new double[]{-10, -5, 0, 5, 10, 15};
        final double[] arrayY = new double[]{0.5, 1.5, 2.5, 3.5, 4.5, 5.5};
        final double[] secondArrayY = new double[]{0., 2., 4., 6., 8., 10.};
        final LinkedListTabulatedFunctionFactory factoryLLF = new LinkedListTabulatedFunctionFactory();
        final ArrayTabulatedFunctionFactory factoryATF = new ArrayTabulatedFunctionFactory();
        final TabulatedFunctionOperationService service1 = new TabulatedFunctionOperationService();
        final TabulatedFunctionOperationService service2 = new TabulatedFunctionOperationService(factoryLLF);
        final TabulatedFunction a = new ArrayTabulatedFunctionFactory().create(arrayX, arrayY);
        final TabulatedFunction b = factoryLLF.create(arrayX, secondArrayY);
        final TabulatedFunction multiplyATF = service1.multiply(a, b);
        final TabulatedFunction multiplyLLF = service2.multiply(a, b);
        int i = 0;
        for (Point point : multiplyATF) {
            assertEquals(point.x, arrayX[i]);
            assertEquals(point.y, arrayY[i] * secondArrayY[i++]);
        }
        int j = 0;
        for (Point point : multiplyLLF) {
            assertEquals(point.x, arrayX[j]);
            assertEquals(point.y, arrayY[j] * secondArrayY[j++]);
        }
        assertThrows(InconsistentFunctionsException.class, () ->
                service1.subtract(factoryLLF.create(new double[]{1, 1.1, 1.2, 1.998, 1.999}, new double[]{6, 23, 511, 1000.01, 1000.2}),
                        factoryATF.create(new double[]{1., 1.1, 1.2, 1.998}, new double[]{23, 511., 900., 1000.})));

        assertThrows(InconsistentFunctionsException.class, () ->
                service1.sum(factoryATF.create(new double[]{1., 4., 8., 16., 25., 36.}, new double[]{10., 21., 31., 41., 55.,65}), a));

        assertTrue(multiplyATF instanceof ArrayTabulatedFunction);
        assertTrue(multiplyLLF instanceof LinkedListTabulatedFunction);

    }

    @Test
    public void testDivider() {
        final LinkedListTabulatedFunctionFactory factoryLLF = new LinkedListTabulatedFunctionFactory();
        final TabulatedFunctionOperationService service1 = new TabulatedFunctionOperationService();
        final TabulatedFunctionOperationService service2 = new TabulatedFunctionOperationService(factoryLLF);
        final TabulatedFunction a = new ArrayTabulatedFunctionFactory().create(xValues, yValues);
        final TabulatedFunction b = factoryLLF.create(xValues, yValuesSecond);
        final TabulatedFunction dividerATF = service1.divider(a, b);
        final TabulatedFunction dividerLLF = service2.divider(a, b);
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
                service1.subtract(factoryLLF.create(new double[]{6., 7., 8.9, 10., 23}, new double[]{1., 10., 100., 1000., 10000.}), a));

        assertThrows(InconsistentFunctionsException.class, () ->
                service1.sum(factoryLLF.create(new double[]{-5., -4.5, -2.5, -2.}, new double[]{201., 301., 404., 505.}),
                        factoryLLF.create(new double[]{-5., -4., -3., -2.,}, new double[]{6.6, 6.66, 6.666, 6.6666})));


        assertTrue(dividerATF instanceof ArrayTabulatedFunction);
        assertTrue(dividerLLF instanceof LinkedListTabulatedFunction);
    }

}