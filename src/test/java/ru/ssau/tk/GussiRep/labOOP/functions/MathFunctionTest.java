package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {
    private final MathFunction x = new PowFunction();
    private final MathFunction firstSqr = new SqrFunction();
    private final MathFunction one = new UnitFunction();
    private final MathFunction sqrX = firstSqr.andThen(x);
    private final MathFunction arcsine = new AsinFunction();
    private final MathFunction secondSqr = new SqrFunction();
    private final MathFunction sqrArcsine = secondSqr.andThen(arcsine);
    private final MathFunction two = new UnitFunction();
    private final MathFunction sqr = new SqrFunction();
    private final MathFunction asin = new AsinFunction();
    private final MathFunction pow = new PowFunction();
    private final MathFunction asinX = asin.andThen(sqr);

    @Test
    public void testAndThen() {
        assertEquals(asinX.apply(-0.7), 0.601, 0.001);
        assertNotEquals(asinX.andThen(pow).apply(100), 1, 0.001);
        assertEquals(asinX.andThen(asinX).apply(0.5), 0.0771, 0.001);
        assertEquals(sqrX.apply(1), 1.0, 0.001);
        assertEquals(sqrX.andThen(one).apply(10), 1.0, 0.001);
        assertEquals(sqrX.andThen(sqrX).apply(-1), 1.0, 0.001);
        assertNotEquals(sqrX.andThen(sqrX).apply(4), 1.5, 0.001);
        assertEquals(sqrArcsine.apply(1), Math.PI / 2, 0.001);
        assertEquals(sqrArcsine.andThen(two).apply(3), 1, 0.001);
        assertEquals(sqrArcsine.andThen(sqrArcsine).apply(0), 0, 0.001);

        final double[] valuesX = new double[]{0., 1., 2., 3., 4., 5., 6., 7., 8., 9.};
        final double[] valuesY = new double[10];
        for (int i = 0; i < 10; i++) {
            valuesY[i] = sqr.apply(valuesX[i]);
        }

        TabulatedFunction parabola = new LinkedListTabulatedFunction(valuesX, valuesY);
        assertEquals(parabola.andThen(sqr).apply(2), 16, 0.001);
        assertEquals(parabola.andThen(sqr).apply(0), 0, 0.001);
        assertEquals(parabola.andThen(sqr).apply(3), 81, 0.001);
        assertEquals(parabola.andThen(sqr).apply(-1), 1, 0.001);
        assertEquals(parabola.andThen(sqr).apply(9), 6561, 0.001);
        assertEquals(parabola.andThen(sqr).apply(2.5), 42.25, 0.001);
        assertEquals(parabola.andThen(sqr).apply(10), 9604, 0.001);


        final double[] valuesX2 = new double[]{0., 1., 2., 3., 4., 5.};
        final double[] valuesY2 = new double[6];
        for (int i = 0; i < 6; i++) {
            valuesY2[i] = sqr.apply(valuesX[i] * valuesX[i]);
        }
        TabulatedFunction pow = new ArrayTabulatedFunction(valuesX2, valuesY2);
        assertEquals(pow.andThen(sqr).apply(2), 256, 0.01);
        assertEquals(pow.andThen(sqr).apply(-1), 1, 0.01);
        assertEquals(pow.andThen(sqr).apply(6), 988036, 0.01);
        assertEquals(pow.andThen(sqr).apply(1.5), 72.25, 0.01);
        assertEquals(pow.andThen(sqr).apply(1), 1, 0.01);
    }
}
