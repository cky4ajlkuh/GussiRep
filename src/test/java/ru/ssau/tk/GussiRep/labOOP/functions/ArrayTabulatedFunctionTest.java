package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ru.ssau.tk.GussiRep.labOOP.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.GussiRep.labOOP.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.GussiRep.labOOP.exceptions.InterpolationException;

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
    public void testException() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(new double[]{0}, new double[]{1}));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(new double[]{}, new double[]{10}));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(new double[]{}, new double[]{}));

        assertThrows(DifferentLengthOfArraysException.class, () -> new ArrayTabulatedFunction(new double[]{2., 3., 4.}, new double[]{0., 1., 2., 3., 4., 5}));
        assertThrows(DifferentLengthOfArraysException.class, () -> new ArrayTabulatedFunction(new double[]{-3., -2.}, new double[]{0.}));
        assertThrows(DifferentLengthOfArraysException.class, () -> new ArrayTabulatedFunction(new double[]{10., 11., 2}, new double[]{9., 1}));

        assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(new double[]{5., -4., 1}, new double[]{1, 2, 0}));
        assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(new double[]{1., -1., 4}, new double[]{3., -4., 5.}));
        assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(new double[]{1, 2, 0}, new double[]{10, 1, 2}));

        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(sqrFunc, 5, 5, 10));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(sqrFunc, 1, 0, 2));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(sqrFunc, 1, -5, 0));
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

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            getUnitArray().getX(44);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            getDefinedThroughMathFunction().getX(-100);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
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
        assertEquals(getUnitArray().getY(2), 9, DELTA);
        assertEquals(getUnitArray().getY(3), 16, DELTA);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            getUnitArray().getY(83);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            getDefinedThroughMathFunction().getY(100);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
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


        assertThrows(ArrayIndexOutOfBoundsException.class, () -> functionFirst.setY(10, 100));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> functionSecond.setY(-10, -100));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> functionThird.setY(41, 51));
    }

    @Test
    public void testLeftBound() {

        assertEquals(getDefinedThroughArrays().leftBound(), -6, DELTA);
        assertEquals(getDefinedThroughMathFunction().leftBound(), 0., DELTA);
        assertEquals(getUnitArray().leftBound(), 1, DELTA);
    }

    @Test
    public void testRightBound() {

        assertEquals(getDefinedThroughArrays().rightBound(), 9, DELTA);
        assertEquals(getDefinedThroughMathFunction().rightBound(), 25, DELTA);
        assertEquals(getUnitArray().rightBound(), 5, DELTA);
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

        assertEquals(getUnitArray().floorIndexOfX(4), 3, DELTA);
        assertEquals(getDefinedThroughArrays().floorIndexOfX(2), 5, DELTA);
        assertEquals(getDefinedThroughMathFunction().floorIndexOfX(6), 6, DELTA);
        assertThrows(IllegalArgumentException.class, () -> getDefinedThroughArrays().floorIndexOfX(-89));
        assertThrows(IllegalArgumentException.class, () -> getDefinedThroughMathFunction().floorIndexOfX(-19));
        assertThrows(IllegalArgumentException.class, () -> getUnitArray().floorIndexOfX(-44));

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

        assertEquals(getDefinedThroughArrays().interpolate(3, 6), 9, DELTA);
        assertEquals(getDefinedThroughMathFunction().interpolate(4.5, 4), 20.5, DELTA);
        assertEquals(getUnitArray().interpolate(3, 2), 9, DELTA);

        assertThrows(InterpolationException.class, () -> getUnitArray().interpolate(10, 0));
        assertThrows(InterpolationException.class, () -> getDefinedThroughArrays().interpolate(-41, 3));
        assertThrows(InterpolationException.class, () -> getDefinedThroughMathFunction().interpolate(-100, 2));
    }

    @Test
    public void testApply() {

        assertEquals(getDefinedThroughMathFunction().apply(4), 16, DELTA);
        assertEquals(getDefinedThroughArrays().apply(2.0), 5.0, DELTA);
        assertEquals(getUnitArray().apply(1), 1, DELTA);
    }

    @Test
    public void insert() {
        double[] xValues = new double[]{1., 2., 3., 4., 5., 6., 7., 8., 9., 10.};
        double[] yValues = new double[]{2., 4., 6., 8., 10., 12., 14., 16., 18., 20.};

        ArrayTabulatedFunction functionFirst = new ArrayTabulatedFunction(xValues, yValues);

        functionFirst.insert(0, 0);

        for (int i = 0; i < functionFirst.getCount(); i++) {
            assertEquals(functionFirst.getX(i), i, DELTA);
            assertEquals(functionFirst.getY(i), i * 2, DELTA);
        }

        ArrayTabulatedFunction functionSecond = new ArrayTabulatedFunction(xValues, yValues);

        functionSecond.insert(12, 22);

        for (int i = 1; i < functionSecond.getCount(); i++) {
            assertEquals(functionSecond.getX(i - 1), i, DELTA);
            assertEquals(functionSecond.getY(i - 1), 2 * i, DELTA);
        }
        double[] xValues2 = new double[]{1., 2., 3., 4., 5., 7., 8., 9., 10.};
        double[] yValues2 = new double[]{2., 4., 6., 8., 10., 14., 16., 18., 20.};


        ArrayTabulatedFunction functionThird = new ArrayTabulatedFunction(xValues2, yValues2);
        functionThird.insert(6., 12.);

        for (int i = 1; i < functionThird.getCount(); i++) {
            assertEquals(functionThird.getX(i - 1), i, DELTA);
            assertEquals(functionThird.getY(i - 1), i * 2, DELTA);
        }

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        function.insert(10, 21);

        assertEquals(function.getX(9), 10, DELTA);
        assertEquals(function.getY(9), 21, DELTA);
        for (int i = 1; i < functionThird.getCount() - 1; i++) {
            assertEquals(function.getX(i - 1), i, DELTA);
            assertEquals(function.getY(i - 1), i * 2, DELTA);
        }
    }

    @Test
    public void testIterator() {
        ArrayTabulatedFunction functionFirst = new ArrayTabulatedFunction(valuesX, valuesY);
        Iterator<Point> iterator = functionFirst.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(point.x, functionFirst.getX(i++));
        }
        assertEquals(i, 8, 0.01);
        assertThrows(NoSuchElementException.class, () -> iterator.next());

        int j = 0;
        for (Point point : functionFirst) {
            assertEquals(point.x, functionFirst.getX(j++));
        }
        assertEquals(i, 8, 0.01);

        SqrFunction sqr = new SqrFunction();
        ArrayTabulatedFunction functionSecond = new ArrayTabulatedFunction(sqr, 1, 5, 5);
        Iterator<Point> secondIterator = functionSecond.iterator();
        i = 0;
        while (secondIterator.hasNext()) {
            Point point = secondIterator.next();
            assertEquals(point.x, functionSecond.getX(i++));
        }
        assertEquals(i, 5, 0.01);
        assertThrows(NoSuchElementException.class, () -> iterator.next());
        j = 0;
        for (Point point : functionSecond) {
            assertEquals(point.x, functionSecond.getX(j++));
        }
        assertEquals(j, 5, 0.01);
    }
}