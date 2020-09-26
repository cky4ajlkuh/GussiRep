package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {
    MathFunction X = new PowFunction();
    MathFunction sqrX = new SqrFunction();
    MathFunction one = new UnitFunction();
    MathFunction FreakFunk = sqrX.andThen(X);

    @Test
    public void testAndThen() {
        assertEquals(FreakFunk.apply(1), 1.0, 0.001);
        assertEquals(FreakFunk.andThen(one).apply(10), 1.0, 0.001);
        assertEquals(FreakFunk.andThen(sqrX).apply(1),1.0, 0.001);
        assertEquals(FreakFunk.andThen(sqrX).apply(1), 1, 0.001);
    }
}