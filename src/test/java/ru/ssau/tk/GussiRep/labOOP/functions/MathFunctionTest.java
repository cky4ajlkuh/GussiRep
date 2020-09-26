package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {
    MathFunction X = new PowFunction();
    MathFunction sqrX = new SqrFunction();
    MathFunction one = new UnitFunction();
    MathFunction andThen = sqrX.andThen(X);

    @Test
    public void testAndThen() {
        assertEquals(andThen.apply(1), 1.0, 0.001);
        assertEquals(andThen.andThen(one).apply(10), 1.0, 0.001);
        assertEquals(andThen.andThen(sqrX).apply(-1),1.0, 0.001);
        assertNotEquals(andThen.andThen(sqrX).apply(4), 1.5, 0.001);
    }
}