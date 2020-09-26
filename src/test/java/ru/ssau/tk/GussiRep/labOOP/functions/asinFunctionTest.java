package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class asinFunctionTest {

    @Test
    public void testApply() {
        final double pi = 3.14;
        MathFunction peremennaya=new asinFunction();
        assertEquals(peremennaya.apply(1), pi/2 , 0.001);
        assertEquals(peremennaya.apply(0), 0, 0.001);
        assertEquals(peremennaya.apply(-1), -pi/2,0.001 );
    }
}