package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {

    ZeroFunction FreakFunk = new ZeroFunction();
    @Test
    public void testGetConstant() {
        assertEquals(FreakFunk.apply(10),0.0, 0.001);
        assertEquals(FreakFunk.apply(9),0.0, 0.001);
        assertEquals(FreakFunk.apply(2),0.0, 0.001);
    }

    @Test
    public void testApply() {
        assertEquals(FreakFunk.getConstant(),0.0);
    }
}