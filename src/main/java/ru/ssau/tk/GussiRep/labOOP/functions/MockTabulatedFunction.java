package ru.ssau.tk.GussiRep.labOOP.functions;

public class MockTabulatedFunction extends AbstractTabulatedFunction {

    private final double x0 = 2;
    private final double x1 = 3;
    private final double y0 = 1;
    private final double y1 = 4;

    @Override
    protected int floorIndexOfX(double x) {
        return 0;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x,x0,x1,y0,y1);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x,x0,x1,y0,y1);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return interpolate(x,x0,x1,y0,y1);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public double getX(int index) {
        return 0;
    }

    @Override
    public double getY(int index) {
        return 1;
    }

    @Override
    public void setY(int index, double value) {

    }

    @Override
    public int indexOfX(double x) {
        return 0;
    }

    @Override
    public int indexOfY(double y) {
        return 2;
    }

    @Override
    public double leftBound() {
        return 2;
    }

    @Override
    public double rightBound() {
        return 3;
    }
}
