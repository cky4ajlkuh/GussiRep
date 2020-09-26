package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        MathFunction pow = new PowFunction();
        MathFunction identity = new IdentityFunction();
        MathFunction composite = new CompositeFunction(identity,pow);
        assertEquals(composite.apply(2), 4, DELTA);
        assertEquals(composite.apply(1), 1, DELTA);
        assertNotEquals(composite.apply(3), 9, DELTA);
        assertEquals(composite.apply(-2), 0.25, DELTA);
    }
}