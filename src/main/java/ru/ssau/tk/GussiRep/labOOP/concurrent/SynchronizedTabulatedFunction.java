package ru.ssau.tk.GussiRep.labOOP.concurrent;

import ru.ssau.tk.GussiRep.labOOP.functions.Point;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;

import java.util.Iterator;

public class SynchronizedTabulatedFunction implements TabulatedFunction {
    private final TabulatedFunction tabulatedFunction;

    public SynchronizedTabulatedFunction(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public int getCount() {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.getCount();
        }
    }

    @Override
    public double getX(int index) {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.getY(index);
        }
    }

    @Override
    public void setY(int index, double value) {
        synchronized (tabulatedFunction) {
            tabulatedFunction.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x) {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y) {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.indexOfY(y);
        }
    }

    @Override
    public double leftBound() {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.leftBound();
        }
    }

    @Override
    public double rightBound() {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.rightBound();
        }
    }

    @Override
    public Iterator<Point> iterator() {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.iterator();
        }
    }

    @Override
    public double apply(double x) {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.apply(x);
        }
    }
}
