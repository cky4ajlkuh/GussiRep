package ru.ssau.tk.GussiRep.labOOP.functions;

import ru.ssau.tk.GussiRep.labOOP.exceptions.InterpolationException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Iterable<Point> {

    private double[] xValues;
    private double[] yValues;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2 & yValues.length < 2) {
            throw new IllegalArgumentException("Count of points less then 2");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
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
        return xValues[index];
    }

    @Override
    public double getY(int index) {
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
            throw new IllegalArgumentException("X is less of the left border");
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

        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    protected double extrapolateRight(double x) {

        return interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (x < xValues[floorIndex] || x > xValues[floorIndex + 1]) {
            throw new InterpolationException("X is out of bounds");
        }
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }

    @Override
    public void insert(double x, double y) {
        int indexX = indexOfX(x);
        if (indexX != -1) {
            setY(indexX, y);
        } else {
            int index = x < xValues[0] ? 0 : floorIndexOfX(x);
            count++;
            if (xValues.length == (count - 1)) {
                double[] valuesX = new double[(int) (count * 1.1)];
                double[] valuesY = new double[(int) (count * 1.1)];
                if (index == 0) {
                    valuesX[0] = x;
                    valuesY[0] = y;
                    System.arraycopy(xValues, 0, valuesX, 1, count - 1);
                    System.arraycopy(yValues, 0, valuesY, 1, count - 1);
                } else if (index == count - 1) {
                    System.arraycopy(xValues, 0, valuesX, 0, count - 1);
                    System.arraycopy(yValues, 0, valuesY, 0, count - 1);
                    valuesX[index] = x;
                    valuesY[index] = y;
                } else {
                    System.arraycopy(xValues, 0, valuesX, 0, index + 1);
                    System.arraycopy(yValues, 0, valuesY, 0, index + 1);
                    valuesX[index + 1] = x;
                    valuesY[index + 1] = y;
                    System.arraycopy(xValues, index + 1, valuesX, index + 2, (count - index - 2));
                    System.arraycopy(yValues, index + 1, valuesY, index + 2, (count - index - 2));
                }
                this.xValues = valuesX;
                this.yValues = valuesY;
            }
        }
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(xValues[i], yValues[i]);
                i++;
                return point;
            }
        };
    }
}
