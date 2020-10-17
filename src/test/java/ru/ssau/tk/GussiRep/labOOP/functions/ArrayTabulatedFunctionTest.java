package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    private final static double DELTA = 0.00001;
    private final double[] valuesX = new double[]{-18., -4., -2., -1., 0., 1., 3., 9.};
    private final double[] valuesY = new double[]{32., 16., 4., 1., 0, 1., 9, 81.};
    private final MathFunction sqrFunc = new SqrFunction();

    private ArrayTabulatedFunction getDefinedThroughArrays() {
        return new ArrayTabulatedFunction(valuesX, valuesY);
    }

    private ArrayTabulatedFunction getDefinedThroughMathFunction() {
        return new ArrayTabulatedFunction(sqrFunc, 0, 25, 50);
    }

    private ArrayTabulatedFunction getUnitArray() {
        return new ArrayTabulatedFunction(sqrFunc, 3, 3, 1);
    }

    @Test
    public void testGetCount() {

        assertEquals(getDefinedThroughArrays().getCount(), 3, DELTA);
        assertEquals(getDefinedThroughMathFunction().getCount(), 50, DELTA);
        assertEquals(getUnitArray().getCount(), 1);
    }

    @Test
    public void testGetX() {

        assertEquals(getDefinedThroughArrays().getX(0), -18);
        assertEquals(getDefinedThroughArrays().getX(4), 1);
        assertEquals(getDefinedThroughArrays().getX(5), 3);

        assertEquals(getDefinedThroughMathFunction().getX(0), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().getX(19), 10, DELTA);
        assertEquals(getDefinedThroughMathFunction().getX(18), 9.473684210526317, DELTA);

        assertEquals(getUnitArray().getX(0), 3, DELTA);
    }

    @Test
    public void testGetY() {

        assertEquals(getDefinedThroughArrays().getY(0), -5, DELTA);
        assertEquals(getDefinedThroughArrays().getY(4), 1, DELTA);
        assertEquals(getDefinedThroughArrays().getY(5), 2, DELTA);

        assertEquals(getDefinedThroughMathFunction().getY(0), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(6), 9.972299168975066, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(19), 100.00000000000004, DELTA);
        assertEquals(getUnitArray().getY(0), 36, DELTA);
    }

    @Test
    public void testSetY() {

        getDefinedThroughArrays().setY(5, 100500.);
        getDefinedThroughMathFunction().setY(0, 1009.);
        assertEquals(getDefinedThroughArrays().getY(5), 100500., DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(0), 1009., DELTA);
    }

    @Test
    public void testLeftBound() {

        assertEquals(getDefinedThroughArrays().leftBound(), -3., DELTA);
        assertEquals(getDefinedThroughMathFunction().leftBound(), 0., DELTA);
    }

    @Test
    public void testRightBound() {

        assertEquals(getDefinedThroughArrays().rightBound(), 3., DELTA);
        assertEquals(getDefinedThroughMathFunction().rightBound(), 20., DELTA);
    }

    @Test
    public void testIndexOfX() {

        assertEquals(getDefinedThroughArrays().indexOfX(1.), 4);
        assertEquals(getDefinedThroughMathFunction().indexOfX(1.1), -1);
        assertEquals(getDefinedThroughArrays().indexOfX(0.), 0);
        assertEquals(getDefinedThroughMathFunction().indexOfX(0.1), -1);
    }

    @Test
    public void testIndexOfY() {

        assertEquals(getDefinedThroughArrays().indexOfY(1.), 2);
        assertEquals(getDefinedThroughMathFunction().indexOfY(1.1), -1);
        assertEquals(getDefinedThroughArrays().indexOfY(0.), 0);
        assertEquals(getDefinedThroughMathFunction().indexOfY(0.1), -1);
    }

    @Test
    public void testFloorIndexOfX() {

        assertEquals(getDefinedThroughArrays().floorIndexOfX(30.), 7);
        assertEquals(getDefinedThroughMathFunction().floorIndexOfX(20.1), 21);
    }

    @Test
    public void testExtrapolateLeft() {

        assertEquals(getDefinedThroughArrays().extrapolateLeft(-4), 14., DELTA);
        assertEquals(getDefinedThroughMathFunction().extrapolateLeft(-4.5), 16.5, DELTA);
        assertEquals(getDefinedThroughArrays().extrapolateLeft(-4), -4., DELTA);
        assertEquals(getDefinedThroughMathFunction().extrapolateLeft(-4.3), -4.3, DELTA);
    }

    @Test
    public void testExtrapolateRight() {

        assertEquals(getDefinedThroughArrays().extrapolateRight(4.), 14., DELTA);
        assertEquals(getDefinedThroughMathFunction().extrapolateRight(4.8), 18., DELTA);
        assertEquals(getDefinedThroughArrays().extrapolateRight(4.), -224., DELTA);
        assertEquals(getDefinedThroughMathFunction().extrapolateRight(4.8), -192.799, DELTA);
    }

    @Test
    public void testInterpolate() {

        assertEquals(getDefinedThroughArrays().interpolate(-1.5, 1), 2.5, DELTA);
        assertEquals(getDefinedThroughMathFunction().interpolate(0.5, 3), 0.5, DELTA);
        assertEquals(getDefinedThroughArrays().interpolate(14.9, 14), 222.1, DELTA);
        assertEquals(getDefinedThroughMathFunction().interpolate(1.3, 1), 1.9, DELTA);
        assertEquals(getDefinedThroughArrays().interpolate(10.8, 8), 111.6, DELTA);
    }

    @Test
    public void testApply() {

        assertEquals(getDefinedThroughMathFunction().apply(-1.5), 2.5, DELTA);
        assertEquals(getDefinedThroughArrays().apply(14.9), 222.1, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(4.0), 14.0, DELTA);
        assertEquals(getDefinedThroughArrays().apply(22.0), 478.0, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(-4.0), 14.0, DELTA);
        assertEquals(getDefinedThroughArrays().apply(-5.0), -5.0, DELTA);
    }

}