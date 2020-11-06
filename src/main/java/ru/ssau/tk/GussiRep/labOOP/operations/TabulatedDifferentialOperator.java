package ru.ssau.tk.GussiRep.labOOP.operations;

import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.*;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {

    private TabulatedFunctionFactory factory;

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        int count = function.getCount();
        double[] xValues = new double[count-1];
        double[] yValues = new double[count-1];
        return null;
    }

    @Override
    public double apply(double x) {
        return x;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public Class getFactory() {
        return factory.getClass();
    }

    public TabulatedDifferentialOperator() {
        factory = new ArrayTabulatedFunctionFactory();
    }
}
