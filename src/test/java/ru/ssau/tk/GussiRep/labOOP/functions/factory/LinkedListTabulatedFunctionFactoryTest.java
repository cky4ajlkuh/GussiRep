package ru.ssau.tk.GussiRep.labOOP.functions.factory;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertThrows;

import ru.ssau.tk.GussiRep.labOOP.functions.StrictTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.UnmodifiableTabulatedFunction;

public class LinkedListTabulatedFunctionFactoryTest {
    private final double[] xValues = new double[]{1., 4., 8.};
    private final double[] yValues = new double[]{1., 16., 64.};
    private final TabulatedFunctionFactory linkedList = new LinkedListTabulatedFunctionFactory();

    @Test
    public void testCreate() {
        TabulatedFunction function = linkedList.create(xValues, yValues);
        assertTrue(function instanceof LinkedListTabulatedFunction);
    }

    @Test
    public void testCreateStrict() {
        TabulatedFunction function = linkedList.createStrict(xValues, yValues);
        assertTrue(function instanceof StrictTabulatedFunction);
    }
    @Test
    public void testCreateUnmodifiable() {
        TabulatedFunction function = linkedList.createUnmodifiable(xValues, yValues);
        assertTrue(function instanceof UnmodifiableTabulatedFunction);
    }
    @Test
    public void testCreateStrictUnmodifiable(){
        TabulatedFunction function = linkedList.createStrictUnmodifiable(xValues, yValues);
        assertThrows(UnsupportedOperationException.class, () -> function.setY(0, 0));
        assertThrows(UnsupportedOperationException.class, () -> function.apply(0));
    }
}