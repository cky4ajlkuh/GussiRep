package ru.ssau.tk.GussiRep.labOOP.operations;

import ru.ssau.tk.GussiRep.labOOP.functions.Point;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;

public class TabulatedFunctionOperationService {
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] points = new Point[tabulatedFunction.getCount()];
        for (Point newPoint : tabulatedFunction) {
            points[i++] = newPoint;
        }
        return points;
    }
}