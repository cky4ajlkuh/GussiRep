package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {

    @Test
    public void testApply() {
        MathFunction apply = new IdentityFunction();
        assertEquals(apply.apply(5), 5, 0.0001);
        assertEquals(apply.apply(100), 100, 0.0001);
        assertEquals(apply.apply(0.1), 0.1, 0.0001);
    }
}