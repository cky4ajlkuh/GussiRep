package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {

    @Test
    public void testGetConstant() {
    }

    @Test
    public void testApply() {
        ConstantFunction FreakFunk = new ConstantFunction(2);
        assertEquals(FreakFunk.apply(10),2.0, 0.001);
        assertEquals(FreakFunk.apply(9),2.0, 0.001);
        assertEquals(FreakFunk.apply(2),2.0, 0.001);
    }
}