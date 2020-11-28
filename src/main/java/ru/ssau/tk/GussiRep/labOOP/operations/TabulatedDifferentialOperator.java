package ru.ssau.tk.GussiRep.labOOP.operations;

import ru.ssau.tk.GussiRep.labOOP.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.Point;
import ru.ssau.tk.GussiRep.labOOP.functions.*;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.*;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {

    private TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator() {
        factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public TabulatedFunction deriveSynchronously(TabulatedFunction function) {
        if (function instanceof StrictTabulatedFunction) {
            return ((SynchronizedTabulatedFunction) function).doSynchronously(this::derive);
        }
        SynchronizedTabulatedFunction synchronizedTF = new SynchronizedTabulatedFunction(function);
        return synchronizedTF.doSynchronously(this::derive);
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {

        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        double[] xValues = new double[function.getCount()];
        double[] yValues = new double[function.getCount()];
        for (int i = 0; i < xValues.length; i++) {
            xValues[i] = points[i].x;
        }
        for (int i = 0; i < yValues.length - 1; i++) {
            yValues[i] = (points[i + 1].y - points[i].y) / (points[i + 1].x - points[i].x);
        }
        yValues[xValues.length - 1] = yValues[xValues.length - 2];
        return factory.create(xValues, yValues);
    }
}
