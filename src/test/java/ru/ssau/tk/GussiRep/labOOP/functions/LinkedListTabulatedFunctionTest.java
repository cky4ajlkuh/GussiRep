package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {

    private final static double DELTA = 0.001;
    private final double[] valuesX = new double[]{0., 1., 2., 3., 4.};
    private final double[] valuesY = new double[]{0., 1., 4., 9., 16.};
    private final double[] valuesY2 = new double[]{0., 2., 4., 6., 8.};
    private final MathFunction pow = new PowFunction();

    private AbstractTabulatedFunction parabola() {
        return new LinkedListTabulatedFunction(valuesX, valuesY);
    }

    private AbstractTabulatedFunction kX() {
        return new LinkedListTabulatedFunction(valuesX, valuesY2);
    }

    private AbstractTabulatedFunction pow() {
        return new LinkedListTabulatedFunction(pow, 1, 5, 5);
    }

    @Test
    public void testGetCount() {
        assertEquals(pow().getCount(), 5, DELTA);
        assertEquals(parabola().getCount(), 5, DELTA);
        assertEquals(kX().getCount(), 5, DELTA);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(kX().floorIndexOfX(2), 1, DELTA);
        assertEquals(parabola().floorIndexOfX(3), 2, DELTA);
        assertEquals(pow().floorIndexOfX(4), 2, DELTA);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(kX().extrapolateLeft(3), 6, DELTA);
        assertEquals(parabola().extrapolateLeft(0.5), 0.5, DELTA);
        assertEquals(pow().extrapolateLeft(1.5), 2.5, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(kX().extrapolateRight(4), 8, DELTA);
        assertEquals(parabola().extrapolateRight(3), 9, DELTA);
        assertEquals(pow().extrapolateRight(1), -8351, DELTA);
    }

    @Test
    public void testInterpolate() {
        assertEquals(kX().interpolate(2, 4), 4, DELTA);
        assertEquals(parabola().interpolate(3, 3), 9, DELTA);
        assertEquals(pow().interpolate(4, 2), 256, DELTA);
    }

    @Test
    public void testGetX() {
        assertEquals(kX().getX(2), 2, DELTA);
        assertEquals(parabola().getX(3), 3, DELTA);
        assertEquals(pow().getX(1), 2, DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(kX().getY(4), 8, DELTA);
        assertEquals(parabola().getY(1), 1, DELTA);
        assertEquals(pow().getY(2), 27, DELTA);
    }

    @Test
    public void testSetY() {
        kX().setY(2, 3);
        parabola().setY(2, 4);
        pow().setY(1, 2);
        assertEquals(kX().getY(2), 4, DELTA);
        assertEquals(parabola().getY(2), 4, DELTA);
        assertEquals(pow().getY(1), 4, DELTA);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(kX().indexOfX(2), 2, DELTA);
        assertEquals(parabola().indexOfX(1), 1, DELTA);
        assertEquals(pow().indexOfX(3), 2, DELTA);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(kX().indexOfY(3), -1, DELTA);
        assertEquals(parabola().indexOfY(2), -1, DELTA);
        assertEquals(pow().indexOfY(4), 1, DELTA);
    }

    @Test
    public void testLeftBound() {
        assertEquals(kX().leftBound(), 0, DELTA);
        assertEquals(parabola().leftBound(), 0, DELTA);
        assertEquals(pow().leftBound(), 1, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(kX().rightBound(), 4, DELTA);
        assertEquals(parabola().rightBound(), 4, DELTA);
        assertEquals(pow().rightBound(), 5, DELTA);
    }

    @Test
    public void testApply() {

        assertEquals(kX().apply(-1), -2, DELTA);
        assertEquals(kX().apply(2), 4, DELTA);
        assertEquals(kX().apply(3.5), 7, DELTA);
        assertEquals(kX().apply(5), 10, DELTA);

        assertEquals(parabola().apply(4),16, DELTA);
        assertEquals(parabola().apply(5),23, DELTA);
        assertEquals(parabola().apply(0), 0, DELTA);
        assertEquals(parabola().apply(2.5), 6.5, DELTA);
        assertEquals(parabola().apply(-1), -1, DELTA);

        assertEquals(pow().apply(2),4, DELTA);
        assertEquals(pow().apply(0),-2, DELTA);
        assertEquals(pow().apply(5.5), 4559.5, DELTA);
        assertEquals(pow().apply(4), 256, DELTA);

    }
}
