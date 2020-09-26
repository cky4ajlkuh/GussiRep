package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PowFunctionTest {

    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        MathFunction FreakFunk = new PowFunction();
        assertEquals(FreakFunk.apply(2), 4,DELTA);
        assertEquals(FreakFunk.apply(1), 1,DELTA);
        assertNotEquals(FreakFunk.apply(3), 2,DELTA);
    }
}