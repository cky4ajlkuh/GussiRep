package ru.ssau.tk.GussiRep.labOOP.functions.factory;

import ru.ssau.tk.GussiRep.labOOP.functions.MathFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.StrictTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {

    default TabulatedFunction createStrict(double[] xValues, double[] yValues){
        return new StrictTabulatedFunction(create(xValues,yValues));
    };

    TabulatedFunction create(double[] xValues, double[] yValues);

    TabulatedFunction create(MathFunction source, double xFrom, double xTo, int count);
}
