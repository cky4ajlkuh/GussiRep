package ru.ssau.tk.GussiRep.labOOP.functions;

import ru.ssau.tk.GussiRep.labOOP.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.GussiRep.labOOP.exceptions.DifferentLengthOfArraysException;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    protected int count;

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return (leftY + ((x - leftX) * (rightY - leftY)) / (rightX - leftX));
    }

    public double apply(double x) {
        int index = indexOfX(x);

        if (x < leftBound()) {
            return (extrapolateLeft(x));
        }
        if (x > rightBound()) {
            return (extrapolateRight(x));
        }
        if (index != -1) {
            return (getY(index));
        }
        return (interpolate(x, floorIndexOfX(x)));
    }

    protected static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        int xV, yV;
        xV = xValues.length;
        yV = yValues.length;
        if (xV != yV) {
            throw new DifferentLengthOfArraysException("Lengths of xValues and yValues are different");
        }
    }

    protected static void checkSorted(double[] xValues) {
        int xV;
        xV = xValues.length;
        for (int i = 0; (i + 1) < xV; i++) {
            if (xValues[i] > xValues[i + 1]) {
                throw new ArrayIsNotSortedException("Array is not sorted");
            }
            if (xValues[i] == xValues[i + 1]) {
                throw new ArrayIsNotSortedException("The array contains the same coordinates");
            }
        }
    }
}
