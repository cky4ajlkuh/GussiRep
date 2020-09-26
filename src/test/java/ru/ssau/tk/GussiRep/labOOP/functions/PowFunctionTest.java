package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PowFunctionTest {

    @Test
    public void testApply() {
        MathFunction pow = new PowFunction();
        assertEquals(pow.apply(2), 4,0.0001);
    }
}