package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {
    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        MathFunction identity = new IdentityFunction();
        assertEquals(identity.apply(5), 5, DELTA);
        assertEquals(identity.apply(100), 100, DELTA);
        assertEquals(identity.apply(-0.1), -0.1, DELTA);
        assertNotEquals(identity.apply(1), 2, DELTA);
    }
}