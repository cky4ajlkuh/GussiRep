package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {

    private final static double DELTA = 0.001;
    private final double[] valuesX = new double[]{0., 1., 2., 3., 4.};
    private final double[] valuesY = new double[]{0., 1., 4., 9., 16.};
    private final double[] valuesY2 = new double[]{0., 2., 4., 6., 8.};
    private final LinkedListTabulatedFunction parabola = new LinkedListTabulatedFunction(valuesX, valuesY);
    private final LinkedListTabulatedFunction kX = new LinkedListTabulatedFunction(valuesX, valuesY2);
    private final MathFunction pow = new PowFunction();
    private final LinkedListTabulatedFunction testPow = new LinkedListTabulatedFunction(pow, 5, 25, 10);

    @Test
    public void testGetCount() {
        assertEquals(testPow.getCount(), 10, DELTA);
        assertEquals(parabola.getCount(), 5, DELTA);
        assertEquals(kX.getCount(), 5, DELTA);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(kX.floorIndexOfX(2), 1, DELTA);
        assertEquals(parabola.floorIndexOfX(3), 2, DELTA);
        assertEquals(testPow.floorIndexOfX(4), 0, DELTA);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(kX.extrapolateLeft(3), 6, DELTA);
        assertEquals(parabola.extrapolateLeft(0.5), 0.5, DELTA);
        assertEquals(testPow.extrapolateLeft(1.5), -2496863.7354, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(kX.extrapolateRight(4), 8, DELTA);
        assertEquals(parabola.extrapolateRight(3), 9, DELTA);
        assertEquals(testPow.extrapolateRight(1), -8.703247946839531E35, DELTA);
    }

    @Test
    public void testInterpolate() {
        assertEquals(kX.interpolate(2, 4), 4, DELTA);
        assertEquals(parabola.interpolate(3, 3), 9, DELTA);
        assertEquals(testPow.interpolate(4, 2), -6.863169316945458E12, DELTA);
    }

    @Test
    public void testGetX() {
        assertEquals(kX.getX(2), 2, DELTA);
        assertEquals(parabola.getX(3), 2, DELTA);
        assertEquals(testPow.getX(1), 7.2222, DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(kX.getY(4), 6, DELTA);
        assertEquals(parabola.getY(5), 16, DELTA);
        assertEquals(testPow.getY(2), 1.6218081817900252E9, DELTA);
    }

    @Test
    public void testSetY() {
        kX.setY(5, 10);
        parabola.setY(1, 1.5);
        testPow.setY(2, 4);
        assertEquals(kX.getY(5), 10, DELTA);
        assertEquals(parabola.getY(1), 1.5, DELTA);
        assertEquals(testPow.getY(2), 4, DELTA);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(kX.indexOfX(2), 2, DELTA);
        assertEquals(parabola.indexOfX(1), 1, DELTA);
        assertEquals(testPow.indexOfX(3), -1, DELTA);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(kX.indexOfY(3), -1, DELTA);
        assertEquals(parabola.indexOfY(2), -1, DELTA);
        assertEquals(testPow.indexOfY(0.5), -1, DELTA);
    }

    @Test
    public void testLeftBound() {
        assertEquals(kX.leftBound(), 0, DELTA);
        assertEquals(parabola.leftBound(), 0, DELTA);
        assertEquals(testPow.leftBound(), 5, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(kX.rightBound(), 4, DELTA);
        assertEquals(parabola.rightBound(), 4, DELTA);
        assertEquals(testPow.rightBound(), 24.9999, DELTA);
    }
}
