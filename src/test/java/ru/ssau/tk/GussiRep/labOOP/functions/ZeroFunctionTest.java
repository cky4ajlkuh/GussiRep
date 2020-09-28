package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {

    private final ZeroFunction zeroFunction = new ZeroFunction();

    @Test
    public void testGetConstant() {
        assertEquals(zeroFunction.apply(10), 0.0, 0.001);
        assertEquals(zeroFunction.apply(9), 0.0, 0.001);
        assertEquals(zeroFunction.apply(2), 0.0, 0.001);
    }

    @Test
    public void testApply() {
        assertEquals(zeroFunction.getConstant(), 0.0);
    }
}