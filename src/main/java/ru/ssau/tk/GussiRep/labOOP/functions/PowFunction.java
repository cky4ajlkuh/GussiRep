package ru.ssau.tk.GussiRep.labOOP.functions;

public class PowFunction implements MathFunction {
/*
    public final double y;
    public PowFunction(double y){
        this.y=y;
    } */
    @Override
    public double apply(double x) {
        return Math.pow(x, x);
    }
}
