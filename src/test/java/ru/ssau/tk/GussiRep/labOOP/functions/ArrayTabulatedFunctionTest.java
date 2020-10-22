package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    private final static double DELTA = 0.001;
    private final double[] valuesX = new double[]{-6., -4., -2., -1., 0., 1., 3., 9.};
    private final double[] valuesY = new double[]{36., 16., 4., 1., 0, 1., 9, 81.};
    private final MathFunction sqrFunc = new SqrFunction();

    private ArrayTabulatedFunction getDefinedThroughArrays() {
        return new ArrayTabulatedFunction(valuesX, valuesY);
    }

    private ArrayTabulatedFunction getDefinedThroughMathFunction() {
        return new ArrayTabulatedFunction(sqrFunc, 0, 25, 26);
    }

    private ArrayTabulatedFunction getUnitArray() {
        return new ArrayTabulatedFunction(sqrFunc, 1, 5, 5);
    }

    @Test
    public void testGetCount() {

        assertEquals(getDefinedThroughArrays().getCount(), 8, DELTA);
        assertEquals(getDefinedThroughMathFunction().getCount(), 26, DELTA);
        assertEquals(getUnitArray().getCount(), 5, DELTA);
    }

    @Test
    public void testGetX() {

        assertEquals(getDefinedThroughArrays().getX(0), -6, DELTA);
        assertEquals(getDefinedThroughArrays().getX(4), 0, DELTA);
        assertEquals(getDefinedThroughArrays().getX(5), 1, DELTA);

        assertEquals(getDefinedThroughMathFunction().getX(0), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().getX(19), 19, DELTA);
        assertEquals(getDefinedThroughMathFunction().getX(18), 18, DELTA);

        assertEquals(getUnitArray().getX(0), 1, DELTA);

        assertThrows(IllegalArgumentException.class, () -> {
            getUnitArray().getX(44);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            getDefinedThroughMathFunction().getX(-100);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            getDefinedThroughArrays().getX(10);
        });
    }

    @Test
    public void testGetY() {

        assertEquals(getDefinedThroughArrays().getY(0), 36, DELTA);
        assertEquals(getDefinedThroughArrays().getY(4), 0, DELTA);
        assertEquals(getDefinedThroughArrays().getY(5), 1, DELTA);

        assertEquals(getDefinedThroughMathFunction().getY(0), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(6), 36, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(19), 361, DELTA);

        assertEquals(getUnitArray().getY(0), 1, DELTA);
        assertEquals(getUnitArray().getY(2),9, DELTA);
        assertEquals(getUnitArray().getY(3), 16, DELTA);

        assertThrows(IllegalArgumentException.class, () -> {
            getUnitArray().getY(83);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            getDefinedThroughMathFunction().getY(100);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            getDefinedThroughArrays().getY(26);
        });
    }

    @Test
    public void testSetY() {

        AbstractTabulatedFunction functionFirst = new ArrayTabulatedFunction(sqrFunc, 1, 5, 5);
        AbstractTabulatedFunction functionSecond = new ArrayTabulatedFunction(sqrFunc, 0, 25, 26);
        AbstractTabulatedFunction functionThird = new ArrayTabulatedFunction(valuesX, valuesY);

        functionFirst.setY(1, 1);
        functionSecond.setY(2, 2);
        functionThird.setY(3, 3);
        assertEquals(functionFirst.getY(1), 1, DELTA);
        assertEquals(functionSecond.getY(2), 2, DELTA);
        assertEquals(functionThird.getY(3), 3, DELTA);
    }

    @Test
    public void testLeftBound() {

        assertEquals(getDefinedThroughArrays().leftBound(), -6, DELTA);
        assertEquals(getDefinedThroughMathFunction().leftBound(), 0., DELTA);
        assertEquals(getUnitArray().leftBound(),1, DELTA);
    }

    @Test
    public void testRightBound() {

        assertEquals(getDefinedThroughArrays().rightBound(), 9, DELTA);
        assertEquals(getDefinedThroughMathFunction().rightBound(), 25, DELTA);
        assertEquals(getUnitArray().rightBound(),5, DELTA);
    }

    @Test
    public void testIndexOfX() {

        assertEquals(getDefinedThroughArrays().indexOfX(1), 5, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfX(3), 3, DELTA);
        assertEquals(getDefinedThroughArrays().indexOfX(0), 4, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfX(4), 4, DELTA);
    }

    @Test
    public void testIndexOfY() {

        assertEquals(getDefinedThroughArrays().indexOfY(1.), 3, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfY(4), 2, DELTA);
        assertEquals(getDefinedThroughArrays().indexOfY(8), -1, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfY(5), -1, DELTA);
    }

    @Test
    public void testFloorIndexOfX() {

        assertEquals(getUnitArray().floorIndexOfX(4),3, DELTA);
        assertEquals(getDefinedThroughArrays().floorIndexOfX(2), 5, DELTA);
        assertEquals(getDefinedThroughMathFunction().floorIndexOfX(6), 6, DELTA);
        assertThrows(IllegalArgumentException.class, () -> {
            getDefinedThroughArrays().floorIndexOfX(-10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            getDefinedThroughMathFunction().floorIndexOfX(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            getUnitArray().floorIndexOfX(0);
        });
    }

    @Test
    public void testExtrapolateLeft() {

        assertEquals(getDefinedThroughArrays().extrapolateLeft(-7), 46, DELTA);
        assertEquals(getDefinedThroughMathFunction().extrapolateLeft(-1), -1, DELTA);
        assertEquals(getDefinedThroughArrays().extrapolateLeft(0), -24, DELTA);
        assertEquals(getUnitArray().extrapolateLeft(-2), -8, DELTA);
    }

    @Test
    public void testExtrapolateRight() {

        assertEquals(getDefinedThroughArrays().extrapolateRight(10), 93, DELTA);
        assertEquals(getDefinedThroughMathFunction().extrapolateRight(26), 674, DELTA);
        assertEquals(getDefinedThroughArrays().extrapolateRight(6), 45, DELTA);
        assertEquals(getUnitArray().extrapolateRight(7), 43, DELTA);
    }

    @Test
    public void testInterpolate() {

        assertEquals(getDefinedThroughArrays().interpolate(2, 5), 5, DELTA);
        assertEquals(getDefinedThroughMathFunction().interpolate(4.5, 4), 20.5, DELTA);
        assertEquals(getUnitArray().interpolate(1.5, 3), -6.5, DELTA);
    }

    @Test
    public void testApply() {

        assertEquals(getDefinedThroughMathFunction().apply(4), 16, DELTA);
        assertEquals(getDefinedThroughArrays().apply(2.0), 5.0, DELTA);
        assertEquals(getUnitArray().apply(1),1 ,DELTA);
    }

    @Test
    public void insert(){
        //getDefinedThroughArrays().insert(-6,100);
        //assertEquals(getDefinedThroughArrays().getY(0),-7, DELTA);
    }
}