package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    @Test
    public void testApply() {
        MathFunction peremennaya=new SqrFunction();
        assertEquals(peremennaya.apply(4), 2 , 0.001);
        assertEquals(peremennaya.apply(16), 4, 0.001);
        assertEquals(peremennaya.apply(1), 1,0.001 );
    }
}