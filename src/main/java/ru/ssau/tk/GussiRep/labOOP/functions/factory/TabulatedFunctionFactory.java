package ru.ssau.tk.GussiRep.labOOP.functions.factory;

import ru.ssau.tk.GussiRep.labOOP.functions.MathFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.StrictTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.UnmodifiableTabulatedFunction;

public interface TabulatedFunctionFactory {

    default TabulatedFunction createStrict(double[] xValues, double[] yValues){
        return new StrictTabulatedFunction(create(xValues,yValues));
    };

    default TabulatedFunction createStrictUnmodifiable(double[] xValues, double[] yValues) {
        return new UnmodifiableTabulatedFunction(new StrictTabulatedFunction(create(xValues, yValues)));
    }

    default TabulatedFunction createUnmodifiable(double[] xValues, double[] yValues) {
        return new UnmodifiableTabulatedFunction(create(xValues, yValues));
    }

    TabulatedFunction create(double[] xValues, double[] yValues);

    TabulatedFunction create(MathFunction source, double xFrom, double xTo, int count);
}
