package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnitFunctionTest {

    private final UnitFunction unit = new UnitFunction();

    @Test
    public void testGetConstant() {
        assertEquals(unit.apply(10), 1.0, 0.001);
        assertEquals(unit.apply(9), 1.0, 0.001);
        assertEquals(unit.apply(-2), 1.0, 0.001);
    }

    @Test
    public void testApply() {
        assertEquals(unit.getConstant(), 1.0);
    }
}