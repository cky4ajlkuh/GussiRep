package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        MathFunction functionG = new PowFunction();
        MathFunction functionH = new IdentityFunction();
        MathFunction functionF = new CompositeFunction(functionH,functionG);
        assertEquals(functionF.apply(2), 4, DELTA);
        assertEquals(functionF.apply(1), 1, DELTA);
        assertNotEquals(functionF.apply(3), 9, DELTA);
    }
}