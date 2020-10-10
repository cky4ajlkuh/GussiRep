package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {

    private final static double DELTA = 0.0001;
    private final double[] valuesX = new double[]{-2., -1., 0., 1., 2.};
    private final double[] valuesY = new double[]{-1.5, -0.5, 0.5, 1.5, 2.5};
    private final LinkedListTabulatedFunction allValues = new LinkedListTabulatedFunction(valuesX, valuesY);
    private final MathFunction pow = new PowFunction();
    private final LinkedListTabulatedFunction testPow = new LinkedListTabulatedFunction(pow, 5, 25, 10);

    @Test
    public void testGetCount() {
        assertEquals(testPow.getCount(), 20, DELTA);
        assertEquals(allValues.getCount(), 5, DELTA);
    }

    @Test
    public void testFloorIndexOfX() {

    }

    @Test
    public void testExtrapolateLeft() {
    }

    @Test
    public void testExtrapolateRight() {
    }

    @Test
    public void testInterpolate() {
    }

    @Test
    public void testGetX() {
    }

    @Test
    public void testGetY() {
    }

    @Test
    public void testSetY() {
    }

    @Test
    public void testIndexOfX() {
    }

    @Test
    public void testIndexOfY() {
    }

    @Test
    public void testLeftBound() {
    }

    @Test
    public void testRightBound() {
    }
}