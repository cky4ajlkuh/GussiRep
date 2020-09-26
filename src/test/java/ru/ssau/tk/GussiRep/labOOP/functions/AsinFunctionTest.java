package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AsinFunctionTest {

    private final static double pi = 3.14;

    @Test
    public void testApply() {
        MathFunction arcsine = new AsinFunction();
        assertEquals(arcsine.apply(1), pi/2, 0.001);
        assertEquals(arcsine.apply(0), 0, 0.001);
        assertEquals(arcsine.apply(-1), -pi/2,0.001 );
    }
}