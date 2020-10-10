package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {

    private final static double DELTA = 0.001;
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
        assertEquals(testPow.floorIndexOfX(25), 20, DELTA);
        assertEquals(allValues.floorIndexOfX(1), 5, DELTA);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(allValues.extrapolateLeft(1.5), Double.NaN, DELTA);
        assertEquals(testPow.extrapolateLeft(0), Double.NaN, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(testPow.extrapolateRight(49.9),1.0839283274984995E36,DELTA);
        assertEquals(allValues.extrapolateRight(4), 4.5, DELTA);
    }

    @Test
    public void testInterpolate() {
        assertEquals(allValues.interpolate(0.5, 4), 1, DELTA);
        assertEquals(testPow.interpolate(25, 5), Double.NaN, DELTA);
    }

    @Test
    public void testGetX() {
        assertEquals(testPow.getX(1), 5, DELTA);
        assertEquals(allValues.getX(3), 0, DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(testPow.getY(1), 3125, DELTA);
        assertEquals(allValues.getY(5), 2.5, DELTA);
    }

    @Test
    public void testSetY() {
        testPow.setY(3, 1);
        allValues.setY(1, 2);
        assertEquals(testPow.getY(3), 1, DELTA);
        assertEquals(allValues.getY(1), 2, DELTA);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(testPow.floorIndexOfX(4), 0, DELTA);
        assertEquals(allValues.floorIndexOfX(2), 5, DELTA);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(testPow.indexOfY(1), -1, DELTA);
        assertEquals(allValues.indexOfY(3), -1, DELTA);
    }

    @Test
    public void testLeftBound() {
        assertEquals(testPow.leftBound(), 5, DELTA);
        assertEquals(allValues.leftBound(), -2, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(testPow.rightBound(), 24.9999, DELTA);
        assertEquals(allValues.rightBound(), 2, DELTA);
    }
}