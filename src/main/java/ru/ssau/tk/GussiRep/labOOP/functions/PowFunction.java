package ru.ssau.tk.GussiRep.labOOP.functions;

public class PowFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return Math.pow(x, x);
    }
}
