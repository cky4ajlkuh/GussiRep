package ru.ssau.tk.GussiRep.labOOP.functions.factory;

import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}
