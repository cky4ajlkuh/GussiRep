package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    @Test
    public void testApply() {
        MathFunction G = new PowFunction();
        MathFunction H = new IdentityFunction();
        MathFunction F = new CompositeFunction(H,G);
        assertEquals(F.apply(2), 4, 0.0001);
    }
}