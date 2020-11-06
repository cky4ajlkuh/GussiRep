package ru.ssau.tk.GussiRep.labOOP.operations;

import org.testng.annotations.Test;
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
    LinkedListTabulatedFunction functionSecond = new LinkedListTabulatedFunction(xValues, yValuesSecond);
    TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

    @Test
    public void testSetFactory() {
        service.setFactory(new LinkedListTabulatedFunctionFactory());
        TabulatedFunctionOperationService service1 = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());
        assertEquals(service1.getFactory(), ArrayTabulatedFunctionFactory.class);
        assertEquals(service.getFactory(), LinkedListTabulatedFunctionFactory.class);
    }

    @Test
    public void testGetFactory() {
        TabulatedFunctionOperationService service1 = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());
        TabulatedFunctionOperationService service2 = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());

        assertEquals(service1.getFactory(), ArrayTabulatedFunctionFactory.class);
        assertEquals(service2.getFactory(), LinkedListTabulatedFunctionFactory.class);

    }

    @Test
    public void testAsPoints() {
        Point[] pointsA = TabulatedFunctionOperationService.asPoints(functionFirst);
        Point[] pointsB = TabulatedFunctionOperationService.asPoints(functionSecond);
        for (int i = 0; i < xValues.length; i++) {
            assertEquals(pointsA[i].x, functionFirst.getX(i), 0.001);
            assertEquals(pointsA[i].y, functionFirst.getY(i), 0.001);
        }

        for (int i = 0 ; i < xValues.length ; i++){
            assertEquals(pointsB[i].x, functionSecond.getX(i), 0.001);
            assertEquals(pointsB[i].y, functionSecond.getY(i), 0.001);
        }
    }

    @Test
    public void testSum() {

    }

    @Test
    public void testSubtract() {
    }

    @Test
    public void testMultiply() {
    }

    @Test
    public void testDivider() {
    }
}