package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    private final static double DELTA = 0.0001;
    private final static double pi = 3.1415;

    @Test
    public void testApply() {
        MathFunction pow = new PowFunction();
        MathFunction identity = new IdentityFunction();
        MathFunction arcsine = new AsinFunction();
        MathFunction sqr = new SqrFunction();
        CompositeFunction firstFunction = new CompositeFunction(pow, identity);
        assertEquals(firstFunction.apply(2), 4, DELTA);
        assertEquals(firstFunction.apply(1), 1, DELTA);
        assertNotEquals(firstFunction.apply(3), 9, DELTA);
        CompositeFunction secondFunction = new CompositeFunction(identity, arcsine);
        assertEquals(secondFunction.apply(1), pi / 2, DELTA);
        assertNotEquals(secondFunction.apply(2), -1, DELTA);
        assertEquals(secondFunction.apply(0), 0, DELTA);
        CompositeFunction thirdFunction = new CompositeFunction(pow, sqr);
        assertEquals(thirdFunction.apply(2), 16, DELTA);
        assertEquals(thirdFunction.apply(-2), 0.0625, DELTA);
        assertEquals(thirdFunction.apply(5), 9765625.0, DELTA);
        CompositeFunction fourthFunction = new CompositeFunction(sqr, arcsine);
        assertEquals(fourthFunction.apply(1), Math.PI / 2, DELTA);
        assertEquals(fourthFunction.apply(0), 0, DELTA);
        assertEquals(fourthFunction.apply(-1), Math.PI / 2, DELTA);
    }
}