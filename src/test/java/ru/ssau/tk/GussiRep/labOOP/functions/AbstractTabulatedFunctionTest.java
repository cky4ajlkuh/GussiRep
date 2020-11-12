package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

import ru.ssau.tk.GussiRep.labOOP.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.GussiRep.labOOP.exceptions.DifferentLengthOfArraysException;

public class AbstractTabulatedFunctionTest {

    private final static double DELTA = 0.001;
    private final AbstractTabulatedFunction function = new MockTabulatedFunction();
    protected final double[] xArray = new double[]{1d, 6d, 7d};
    protected final double[] yArray = new double[]{2d, 6d, 7d};
    protected final double[] xArrayAnotherWrong = new double[]{81d, 36d, 49d};
    protected final double[] xArrayLong = new double[]{-1d, 0d, 6d, 7d, 8d, 9d};
    protected final double[] xArrayWrong = new double[]{1d, 36d, 64d, 49d, 81d};

    private ArrayTabulatedFunction function() {

        return new ArrayTabulatedFunction(xArray, xArrayLong);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(function.floorIndexOfX(2), 0, DELTA);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(function.extrapolateLeft(2), 1, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(function.extrapolateRight(6), 5, DELTA);
    }

    @Test
    public void testInterpolateFloorIndex() {
        assertEquals(function.interpolate(2, 3), 1, DELTA);
    }

    @Test
    public void testInterpolate() {
        assertEquals(function.interpolate(1, 1, 3, 5, 7), 5, DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(function.apply(1), 0, DELTA);
        assertEquals(function.apply(2), 1, DELTA);
        assertEquals(function.apply(6), 5, DELTA);
        assertEquals(function.apply(10), 9, DELTA);
    }

    @Test
    public void testCheckSorted() {
        assertThrows(ArrayIsNotSortedException.class, () -> AbstractTabulatedFunction.checkSorted(xArrayWrong));
        assertThrows(ArrayIsNotSortedException.class, () -> AbstractTabulatedFunction.checkSorted(xArrayAnotherWrong));
        AbstractTabulatedFunction.checkSorted(xArray);
    }

    @Test
    public void testCheckLengthIsTheSame() {
        assertThrows(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(xArray, xArrayLong));
        assertThrows(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(xArrayLong, xArray));
        AbstractTabulatedFunction.checkLengthIsTheSame(xArray, yArray);
    }

    @Test
    public void ToString() {
        double[] xValues = new double[]{1., 2., 3.};
        double[] yValues = new double[]{2., 4., 6.};
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(xValues, yValues);
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xValues, yValues);
        String stringLLT = "LinkedListTabulatedFunction Size = 3\n[1.0; 2.0]\n[2.0; 4.0]\n[3.0; 6.0]";
        String stringATF = "ArrayTabulatedFunction Size = 3\n[1.0; 2.0]\n[2.0; 4.0]\n[3.0; 6.0]";
        assertEquals(linkedListTabulatedFunction.toString(), stringLLT);
        assertEquals(arrayTabulatedFunction.toString(), stringATF);
    }

}