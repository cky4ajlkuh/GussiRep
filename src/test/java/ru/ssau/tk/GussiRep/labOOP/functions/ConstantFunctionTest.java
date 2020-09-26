package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {

    @Test
    public void testGetConstant() {

    }

    @Test
    public void testApply() {
        MathFunction constant = new ConstantFunction(2);
        assertEquals(constant.apply(-6),2.0, 0.001);
        assertEquals(constant.apply(9),2.0, 0.001);
        assertEquals(constant.apply(2),2.0, 0.001);
    }
}