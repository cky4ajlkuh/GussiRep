package ru.ssau.tk.GussiRep.labOOP.operations;

import ru.ssau.tk.GussiRep.labOOP.functions.Point;
import ru.ssau.tk.GussiRep.labOOP.functions.*;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.*;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {

    private TabulatedFunctionFactory factory;

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {

        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        double[] xValues = new double[function.getCount() - 1];
        double[] yValues = new double[function.getCount() - 1];
        for (int i = 0; i < xValues.length; i++) {
            xValues[i] = points[i].x;
        }
        for (int i = 0; i < yValues.length; i++) {
            yValues[i] = (points[i + 1].y - points[i].y) / (points[i + 1].x - points[i].x);
        }
        return factory.create(xValues, yValues);
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
    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }
}
