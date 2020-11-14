package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StrictTabulatedFunctionTest {
    private final static double DELTA = 0.001;
    TabulatedFunction functionLLF = new StrictTabulatedFunction(new LinkedListTabulatedFunction(new double[]{0., 2., 4.}, new double[]{10., 20., 30.}));
    TabulatedFunction functionATF = new StrictTabulatedFunction(new ArrayTabulatedFunction(new double[]{1., 2., 3., 4.}, new double[]{3., 6., 9., 12.}));

    @Test
    public void testGetCount() {
        assertEquals(functionATF.getCount(), 4, DELTA);
        assertEquals(functionLLF.getCount(), 3, DELTA);
    }

    @Test
    public void testGetX() {
        assertEquals(functionLLF.getX(0), 0, DELTA);
        assertEquals(functionLLF.getX(2), 4, DELTA);
        assertEquals(functionLLF.getX(1), 2, DELTA);
        assertEquals(functionATF.getX(1), 2, DELTA);
        assertEquals(functionATF.getX(3), 4, DELTA);
        assertNotEquals(functionLLF.getX(0), 10, DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(functionLLF.getY(0), 10, DELTA);
        assertEquals(functionLLF.getY(2), 30, DELTA);
        assertEquals(functionLLF.getY(1), 20, DELTA);
        assertEquals(functionATF.getY(1), 6, DELTA);
        assertEquals(functionATF.getY(3), 12, DELTA);
    }

    @Test
    public void testSetY() {

        TabulatedFunction functionLLF2 = new StrictTabulatedFunction(new LinkedListTabulatedFunction(new double[]{0., 2., 4.}, new double[]{10., 20., 30.}));
        TabulatedFunction functionATF2 = new StrictTabulatedFunction(new ArrayTabulatedFunction(new double[]{1., 2., 3., 4.}, new double[]{3., 6., 9., 12.}));

        functionATF2.setY(0, 4);
        functionLLF2.setY(2, 33);
        assertEquals(functionATF2.getY(0), 4, DELTA);
        assertEquals(functionLLF2.getY(2), 33, DELTA);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(functionLLF.indexOfX(2), 1, DELTA);
        assertEquals(functionLLF.indexOfX(4), 2, DELTA);
        assertEquals(functionATF.indexOfX(3), 2, DELTA);
        assertEquals(functionATF.indexOfX(4), 3, DELTA);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(functionLLF.indexOfY(20), 1, DELTA);
        assertEquals(functionLLF.indexOfY(10), 0, DELTA);
        assertEquals(functionATF.indexOfY(3), 0, DELTA);
        assertEquals(functionATF.indexOfY(12), 3, DELTA);
    }

    @Test
    public void testLeftBound() {
        assertEquals(functionLLF.leftBound(), 0, DELTA);
        assertEquals(functionATF.leftBound(), 1, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(functionLLF.rightBound(), 4, DELTA);
        assertEquals(functionATF.rightBound(), 4, DELTA);
    }

    @Test
    public void testIterator() {
        assertEquals(functionATF.iterator().next().x, 1, DELTA);
        assertEquals(functionATF.iterator().next().y, 3, DELTA);
        assertEquals(functionLLF.iterator().next().x, 0, DELTA);
        assertEquals(functionLLF.iterator().next().y, 10, DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(functionATF.apply(2), 6, DELTA);
        assertEquals(functionLLF.apply(0), 10, DELTA);
        assertThrows(UnsupportedOperationException.class, () -> functionLLF.apply(90));
        assertThrows(UnsupportedOperationException.class, () -> functionLLF.apply(91));
    }
}