package ru.ssau.tk.GussiRep.labOOP.operations;

import ru.ssau.tk.GussiRep.labOOP.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.GussiRep.labOOP.functions.Point;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.GussiRep.labOOP.functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {
    TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        factory = new ArrayTabulatedFunctionFactory();
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public Class getFactory() {
        return factory.getClass();
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] points = new Point[tabulatedFunction.getCount()];
        for (Point newPoint : tabulatedFunction) {
            points[i++] = newPoint;
        }
        return points;
    }

    private interface BiOperation {
        double apply(double u, double v);
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {

        if (a.getCount() != b.getCount()) {
            throw new InconsistentFunctionsException("The number of points in the functions varies!");
        }
        Point[] pointsA = asPoints(a);
        Point[] pointsB = asPoints(b);
        double[] xValues = new double[a.getCount() - 1];
        double[] yValues = new double[b.getCount() - 1];
        for (int i = 0; i < xValues.length; i++) {
            if (pointsA[i].x - pointsB[i].x != 0) {
                throw new InconsistentFunctionsException("The X coordinates are different!");
            }
            xValues[i] = pointsA[i].x;
            yValues[i] = operation.apply(pointsA[i].y, pointsB[i].y);
        }
        return factory.create(xValues, yValues);
    }

    public TabulatedFunction sum(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, ((u, v) -> u + v));
    }

    public TabulatedFunction subtract(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, ((u, v) -> u - v));
    }

    public TabulatedFunction multiply(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, ((u, v) -> u * v));
    }

    public TabulatedFunction divider(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, ((u, v) -> u / v));
    }

}