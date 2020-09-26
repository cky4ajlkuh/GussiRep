package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnitFunctionTest {

    UnitFunction FreakFunk = new UnitFunction();
    @Test
    public void testGetConstant() {
        assertEquals(FreakFunk.apply(10),1.0, 0.001);
        assertEquals(FreakFunk.apply(9),1.0, 0.001);
        assertEquals(FreakFunk.apply(-2),1.0, 0.001);
    }

    @Test
    public void testApply() {
        assertEquals(FreakFunk.getConstant(),1.0);
    }
}