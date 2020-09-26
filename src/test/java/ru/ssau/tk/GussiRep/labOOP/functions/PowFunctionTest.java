package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PowFunctionTest {

    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        MathFunction pow = new PowFunction();
        assertEquals(pow.apply(2), 4,DELTA);
        assertEquals(pow.apply(1), 1,DELTA);
        assertNotEquals(pow.apply(3), 2,DELTA);
    }
}