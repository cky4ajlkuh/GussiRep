package ru.ssau.tk.GussiRep.labOOP.concurrent;

import org.testng.annotations.Test;
import ru.ssau.tk.GussiRep.labOOP.functions.*;

import java.util.Iterator;

import static org.testng.Assert.*;

public class SynchronizedTabulatedFunctionTest {

    private final static double DELTA = 0.001;

    private final static TabulatedFunction functionLLF = new LinkedListTabulatedFunction(new double[]{1., 2., 3., 4.}, new double[]{3., 6., 9., 12.});
    private final static TabulatedFunction functionATF = new ArrayTabulatedFunction(new SqrFunction(), 1, 5, 5);
    private final static SynchronizedTabulatedFunction synchronizedLLF = new SynchronizedTabulatedFunction(functionLLF);
    private final static SynchronizedTabulatedFunction synchronizedATF = new SynchronizedTabulatedFunction(functionATF);

    @Test
    public void testDoSynchronously(){
        assertEquals((int) synchronizedLLF.doSynchronously(SynchronizedTabulatedFunction -> synchronizedLLF.getCount()),4);
        assertEquals((int) synchronizedATF.doSynchronously(SynchronizedTabulatedFunction -> synchronizedATF.getCount()),5);
        assertEquals((double) synchronizedLLF.doSynchronously(SynchronizedTabulatedFunction -> synchronizedLLF.getX(3)),4);
        assertEquals((double) synchronizedATF.doSynchronously(SynchronizedTabulatedFunction -> synchronizedATF.getX(4)),5);

    }

    @Test
    public void testGetCount() {
        assertEquals(synchronizedLLF.getCount(), 4, DELTA);
        assertEquals(synchronizedATF.getCount(), 5, DELTA);
    }

    @Test
    public void testGetX() {
        assertEquals(synchronizedLLF.getX(0), 1, DELTA);
        assertEquals(synchronizedLLF.getX(1), 2, DELTA);

        assertEquals(synchronizedATF.getX(0), 1, DELTA);
        assertEquals(synchronizedATF.getX(1), 2, DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(synchronizedLLF.getY(0), 3, DELTA);
        assertEquals(synchronizedLLF.getY(1), 6, DELTA);

        assertEquals(synchronizedATF.getY(0), 1, DELTA);
        assertEquals(synchronizedATF.getY(1), 4, DELTA);
    }

    @Test
    public void testSetY() {
        synchronizedATF.setY(0, 10);
        synchronizedLLF.setY(1, 11);
        assertEquals(synchronizedATF.getY(0), 10, DELTA);
        assertEquals(synchronizedLLF.getY(1), 11, DELTA);
        synchronizedATF.setY(0, 12);
        synchronizedLLF.setY(1, 13);
        assertEquals(synchronizedATF.getY(0), 12, DELTA);
        assertEquals(synchronizedLLF.getY(1), 13, DELTA);

    }

    @Test
    public void testIndexOfX() {
        assertEquals(synchronizedLLF.indexOfX(2), 1, DELTA);
        assertEquals(synchronizedATF.indexOfX(1), 0, DELTA);
        assertEquals(synchronizedLLF.indexOfX(4), 3, DELTA);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(synchronizedLLF.indexOfY(3), 0, DELTA);
        assertEquals(synchronizedATF.indexOfY(25), 4, DELTA);
        assertEquals(synchronizedLLF.indexOfY(12), 3, DELTA);
    }

    @Test
    public void testLeftBound() {
        assertEquals(synchronizedATF.leftBound(), 1, DELTA);
        assertEquals(synchronizedLLF.leftBound(), 1, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(synchronizedATF.rightBound(), 5, DELTA);
        assertEquals(synchronizedLLF.rightBound(), 4, DELTA);
    }

    @Test
    public void testIterator() {
        int i = 0;
        int j = 0;
        for (Point point : synchronizedLLF) {
            assertEquals(point.x, synchronizedLLF.getX(i++));
            assertEquals(point.y, synchronizedLLF.getY(j++));
        }
        assertEquals(i, synchronizedLLF.getCount(), DELTA);
        assertEquals(j, synchronizedLLF.getCount(), DELTA);
        i = 0;
        j = 0;
        for (Point point : synchronizedATF) {
            assertEquals(point.x, synchronizedATF.getX(i++));
            assertEquals(point.y, synchronizedATF.getY(j++));
        }
        assertEquals(i, synchronizedATF.getCount(), DELTA);
        assertEquals(j, synchronizedATF.getCount(), DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(synchronizedATF.apply(1), 1, DELTA);
        assertEquals(synchronizedLLF.apply(2), 6, DELTA);
        assertEquals(synchronizedLLF.apply(4), 12, DELTA);
    }
}