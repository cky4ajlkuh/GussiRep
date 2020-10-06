package ru.ssau.tk.GussiRep.labOOP.functions;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    protected int count;

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return (leftY + (x - leftX) * (rightY - leftY) / (rightX - leftX));
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

    ;
}
