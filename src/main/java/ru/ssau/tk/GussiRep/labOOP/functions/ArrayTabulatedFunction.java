package ru.ssau.tk.GussiRep.labOOP.functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable {

    private final double[] xValues;
    private final double[] yValues;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2 & yValues.length < 2) {
            throw new IllegalArgumentException("Count of points less then 2");
        }
        count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom >= xTo) {
            throw new IllegalArgumentException("Count of points less then 2");
        }
        this.count = count;
        xValues = new double[count];
        yValues = new double[count];

        double step = (xTo - xFrom) / (count - 1);
        double xMomentValue = xFrom;

        for (int i = 0; i < count; i++) {
            xValues[i] = xMomentValue;
            yValues[i] = source.apply(xMomentValue);
            xMomentValue += step;
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        checkBorders(index);
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        checkBorders(index);
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        yValues[index] = value;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < xValues[0]) {
            throw new IllegalArgumentException("X less than the left border");
        }
        for (int i = 0; i + 1 < count; i++) {
            if (xValues[i + 1] > x) {
                return i;
            }
        }
        return count;
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (count == 1) {
            return x;
        }
        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (count == 1) {
            return x;
        }
        return interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return x;
        }
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }

    @Override
    public void insert(int x, int y) {
        double[] xValues2 = new double[count];
        double[] yValues2 = new double[count];
        for (int i = 0; i < xValues.length; i++) {
            if (xValues[i] == x) {
                setY(x, y);
            }
        }
        if (x == rightBound()) {
            System.arraycopy(xValues, 0, xValues2, 0, count + 1);
            System.arraycopy(yValues, 0, yValues2, 0, count + 1);
            xValues2[count] = x;
            yValues2[count] = y;

        }
        if (x == leftBound()) {
            System.arraycopy(xValues, 0, xValues2, 0, count + 1);
            System.arraycopy(yValues, 0, yValues2, 0, count + 1);
            xValues2[0] = x;
            yValues2[0] = y;
        }
        if (x > rightBound()) {
            System.arraycopy(xValues, 0, xValues2, 0, count + 2);
            System.arraycopy(yValues, 0, yValues2, 0, count + 2);
            xValues2[count + 1] = x;
            yValues2[count + 1] = y;
        }
        if (x < leftBound()) {
            xValues2[0] = x;
            yValues2[0] = y;
            System.arraycopy(xValues, 0, xValues2, 0, count);
            System.arraycopy(yValues, 0, yValues2, 0, count);
        }

    }

    public void checkBorders(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Invalid index");
        }
    }

    @Override
    public void remove(int index) {

    }
}