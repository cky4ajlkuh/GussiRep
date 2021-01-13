package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class UnmodifiableTabulatedFunctionTest {
    private final static double DELTA = 0.0001;

    TabulatedFunction array = new UnmodifiableTabulatedFunction(
            new ArrayTabulatedFunction(new double[]{-3., 10., 11.}, new double[]{-3., 5., 12.}));

    TabulatedFunction list = new UnmodifiableTabulatedFunction(
            new LinkedListTabulatedFunction(new double[]{-3., 10., 11., 15.}, new double[]{-3., 5., 12., 13}));

    TabulatedFunction unmodifiableInStrict = new StrictTabulatedFunction(array);

    @Test
    public void testGetCount() {
        assertEquals(array.getCount(), 3);
        assertEquals(list.getCount(), 4);
        assertEquals(unmodifiableInStrict.getCount(), 3);
    }

    @Test
    public void testGetX() {
        assertEquals(array.getX(1), 10., DELTA);
        assertEquals(list.getX(3), 15., DELTA);
        assertEquals(unmodifiableInStrict.getX(0), -3., DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(array.getY(1), 5., DELTA);
        assertEquals(list.getY(3), 13., DELTA);
        assertEquals(unmodifiableInStrict.getY(2), 12., DELTA);
    }

    @Test
    public void testSetY() {
        assertThrows(UnsupportedOperationException.class, () -> array.setY(0, 4.));
        assertThrows(UnsupportedOperationException.class, () -> list.setY(3, 1.));
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableInStrict.setY(1, 3.5));
    }

    @Test
    public void testIndexOfX() {
        assertEquals(array.indexOfX(3.), -1);
        assertEquals(list.indexOfX(1.), -1);
        assertEquals(unmodifiableInStrict.indexOfX(-3), 0);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(array.indexOfY(9.), -1);
        assertEquals(list.indexOfY(16.), -1);
        assertEquals(unmodifiableInStrict.indexOfY(12.), 2);
    }

    @Test
    public void testLeftBound() {
        assertEquals(array.leftBound(), -3., DELTA);
        assertEquals(list.leftBound(), -3., DELTA);
        assertEquals(unmodifiableInStrict.leftBound(), -3., DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(array.rightBound(), 11., DELTA);
        assertEquals(list.rightBound(), 15., DELTA);
        assertEquals(unmodifiableInStrict.rightBound(), 11., DELTA);
    }

    @Test
    public void testIterator() {
        assertEquals(array.iterator().next().x, -3., DELTA);
        assertEquals(list.iterator().next().x, -3., DELTA);
        assertEquals(unmodifiableInStrict.iterator().next().x, -3., DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(array.apply(-3), -3., DELTA);
        assertEquals(list.apply(-3), -3, DELTA);
        assertEquals(unmodifiableInStrict.apply(10.), 5., DELTA);
    }
}
