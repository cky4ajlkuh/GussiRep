package ru.ssau.tk.GussiRep.labOOP.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {

    private final static double DELTA = 0.001;
    private final AbstractTabulatedFunction function = new MockTabulatedFunction();

    @Test
    public void testFloorIndexOfX() {
        assertEquals(function.floorIndexOfX(2), 0, DELTA);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(function.extrapolateLeft(2), 1, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(function.extrapolateRight(6), 13, DELTA);
    }

    @Test
    public void testInterpolateFloorIndex() {
        assertEquals(function.interpolate(2, 3), 1, DELTA);
    }

    @Test
    public void testInterpolate() {
        assertEquals(function.interpolate(1, 1, 3, 5, 7), 5, DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(function.apply(1), 0, DELTA);
        assertEquals(function.apply(2), 1, DELTA);
        assertEquals(function.apply(6), 5, DELTA);
        assertEquals(function.apply(10), 9, DELTA);
    }
}