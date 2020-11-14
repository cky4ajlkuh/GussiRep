package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import ru.ssau.tk.GussiRep.labOOP.exceptions.InterpolationException;
import ru.ssau.tk.GussiRep.labOOP.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.GussiRep.labOOP.exceptions.DifferentLengthOfArraysException;

import java.util.NoSuchElementException;
import java.util.Iterator;

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
    public void testException() {

        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(new double[]{0}, new double[]{1}));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(new double[]{}, new double[]{10}));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(new double[]{}, new double[]{}));

        assertThrows(DifferentLengthOfArraysException.class, () -> new LinkedListTabulatedFunction(new double[]{2., 3., 4.}, new double[]{0., 1., 2., 3., 4., 5}));
        assertThrows(DifferentLengthOfArraysException.class, () -> new LinkedListTabulatedFunction(new double[]{-3., -2.}, new double[]{0.}));
        assertThrows(DifferentLengthOfArraysException.class, () -> new LinkedListTabulatedFunction(new double[]{10., 11., 2}, new double[]{9., 1}));

        assertThrows(ArrayIsNotSortedException.class, () -> new LinkedListTabulatedFunction(new double[]{2, -1., 0}, new double[]{3., 4., 5.}));
        assertThrows(ArrayIsNotSortedException.class, () -> new LinkedListTabulatedFunction(new double[]{100, -100, 50, -50}, new double[]{100., 1000., 5000., 10000}));
        assertThrows(ArrayIsNotSortedException.class, () -> new LinkedListTabulatedFunction(new double[]{1., 2., 4., 3.}, new double[]{1, 2, 3, 4}));

        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(pow, 5, 5, 10));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(pow, 1, 0, 2));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(pow, 1, -5, 0));

    }

    @Test
    public void testRemove() {
        LinkedListTabulatedFunction kX = new LinkedListTabulatedFunction(valuesX, valuesY2);
        LinkedListTabulatedFunction parabola = new LinkedListTabulatedFunction(valuesX, valuesY);
        kX.remove(1);
        double[] arrayX = new double[]{10, 11, 12, 13};
        double[] arrayY = new double[]{10, 11, 12, 13};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(arrayX, arrayY);
        function.remove(3);
        for (int i = 0; i < function.getCount(); i++) {
            assertEquals(function.getY(i), arrayY[i], DELTA);
            assertEquals(function.getX(i), arrayX[i], DELTA);
        }
        function.remove(0);
        for (int i = 0; i < function.getCount(); i++) {
            assertEquals(function.getY(i), arrayY[i], DELTA);
            assertEquals(function.getX(i), arrayX[i], DELTA);
        }
        assertEquals(kX.getX(1), 2, DELTA);
        assertEquals(kX.getY(1), 4, DELTA);
        assertThrows(IllegalArgumentException.class, () -> kX.remove(-1));
        parabola.remove(3);
        assertEquals(parabola.getX(3), 4, DELTA);
        assertEquals(parabola.getY(3), 16, DELTA);
        assertThrows(IllegalArgumentException.class, () -> parabola.remove(10));
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
        assertThrows(IllegalArgumentException.class, () -> {
            kX().floorIndexOfX(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            parabola().floorIndexOfX(-5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            pow().floorIndexOfX(-100);
        });
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(kX().extrapolateLeft(-1), -2, DELTA);
        assertEquals(parabola().extrapolateLeft(0.5), 0.5, DELTA);
        assertEquals(pow().extrapolateLeft(0), -2, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(kX().extrapolateRight(6), 12, DELTA);
        assertEquals(parabola().extrapolateRight(5.5), 26.5, DELTA);
        assertEquals(pow().extrapolateRight(7), 8863, DELTA);
    }

    @Test
    public void testInterpolate() {
        assertEquals(kX().interpolate(2, 4), 4, DELTA);
        assertEquals(parabola().interpolate(3, 3), 9, DELTA);
        assertEquals(pow().interpolate(4, 2), 256, DELTA);

        assertThrows(InterpolationException.class, () -> parabola().interpolate(-1, 10));
        assertThrows(InterpolationException.class, () -> kX().interpolate(100, 100));
        assertThrows(InterpolationException.class, () -> pow().interpolate(-1, -1));
    }

    @Test
    public void testGetX() {
        assertEquals(kX().getX(2), 2, DELTA);
        assertEquals(parabola().getX(3), 3, DELTA);
        assertEquals(pow().getX(1), 2, DELTA);
        assertThrows(IllegalArgumentException.class, () -> {
            parabola().getX(100);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            parabola().getX(-200);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            parabola().getX(150);
        });
    }

    @Test
    public void testGetY() {
        assertEquals(kX().getY(4), 8, DELTA);
        assertEquals(parabola().getY(1), 1, DELTA);
        assertEquals(pow().getY(2), 27, DELTA);
        assertThrows(IllegalArgumentException.class, () -> {
            parabola().getY(555);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            pow().getY(-55);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            kX().getY(-132);
        });
    }

    @Test
    public void testSetY() {
        AbstractTabulatedFunction kX = new LinkedListTabulatedFunction(valuesX, valuesY2);
        AbstractTabulatedFunction parabola = new LinkedListTabulatedFunction(valuesX, valuesY);
        final MathFunction powFunc = new PowFunction();
        AbstractTabulatedFunction pow = new LinkedListTabulatedFunction(powFunc, 1, 5, 5);
        kX.setY(2, 3);
        parabola.setY(2, 4);
        pow.setY(1, 2);
        assertEquals(kX.getY(2), 3, DELTA);
        assertEquals(parabola.getY(2), 4, DELTA);
        assertEquals(pow.getY(1), 2, DELTA);


        assertThrows(IllegalArgumentException.class, () -> parabola().setY(666, 500));
        assertThrows(IllegalArgumentException.class, () -> kX().setY(21, -40));
        assertThrows(IllegalArgumentException.class, () -> pow().setY(99, 89));
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

        assertEquals(parabola().apply(4), 16, DELTA);
        assertEquals(parabola().apply(5), 23, DELTA);
        assertEquals(parabola().apply(0), 0, DELTA);
        assertEquals(parabola().apply(2.5), 6.5, DELTA);
        assertEquals(parabola().apply(-1), -1, DELTA);

        assertEquals(pow().apply(2), 4, DELTA);
        assertEquals(pow().apply(0), -2, DELTA);
        assertEquals(pow().apply(5.5), 4559.5, DELTA);
        assertEquals(pow().apply(4), 256, DELTA);

    }

    @Test
    public void testIterator() {

        LinkedListTabulatedFunction parabola = new LinkedListTabulatedFunction(valuesX, valuesY);
        final MathFunction powFunc = new PowFunction();
        LinkedListTabulatedFunction pow = new LinkedListTabulatedFunction(powFunc, 1, 5, 5);
        Iterator<Point> iterator = parabola.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(point.x, parabola.getX(i++));
        }
        assertEquals(i, 5, 0.001);
        assertThrows(NoSuchElementException.class, () -> iterator.next());

        int j = 0;

        for (Point point : parabola) {
            assertEquals(point.x, parabola.getX(j++));
        }
        assertEquals(j, 5, 0.01);

        Iterator<Point> iterator2 = pow.iterator();
        i = 0;
        while (iterator2.hasNext()) {
            Point point = iterator2.next();
            assertEquals(point.x, pow.getX(i++));
        }
        assertEquals(i, 5, 0.01);
        assertThrows(NoSuchElementException.class, () -> iterator.next());
        j = 0;
        for (Point point : pow) {
            assertEquals(point.x, pow.getX(j++));
        }
        assertEquals(j, 5, 0.001);
    }

}
