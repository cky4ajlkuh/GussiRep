package ru.ssau.tk.GussiRep.labOOP.functions.factory;

import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

import ru.ssau.tk.GussiRep.labOOP.functions.StrictTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.UnmodifiableTabulatedFunction;

public class ArrayTabulatedFunctionFactoryTest {
    private final double[] xValues = new double[]{1., 4., 8.};
    private final double[] yValues = new double[]{1., 16., 64.};
    private final TabulatedFunctionFactory array = new ArrayTabulatedFunctionFactory();

    @Test
    public void testCreate() {
        TabulatedFunction function = array.create(xValues, yValues);
        assertTrue(function instanceof ArrayTabulatedFunction);
    }

    @Test
    public void testCreateStrict() {
        TabulatedFunction function = array.createStrict(xValues, yValues);
        assertTrue(function instanceof StrictTabulatedFunction);
    }
    @Test
    public void testCreateUnmodifiable() {
        TabulatedFunction function = array.createUnmodifiable(xValues, yValues);
        assertTrue(function instanceof UnmodifiableTabulatedFunction);
    }
    @Test
    public void testCreateStrictUnmodifiable(){
        TabulatedFunction function = array.createStrictUnmodifiable(xValues, yValues);
        assertThrows(UnsupportedOperationException.class, () -> function.setY(0, 0));
        assertThrows(UnsupportedOperationException.class, () -> function.apply(0));
    }
}
