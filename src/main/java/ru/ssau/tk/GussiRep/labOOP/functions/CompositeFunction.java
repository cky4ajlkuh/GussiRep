package ru.ssau.tk.GussiRep.labOOP.functions;

public class CompositeFunction implements MathFunction {

    private MathFunction firstFunction;

    private MathFunction secondFunction;

    public CompositeFunction(MathFunction secondFunction, MathFunction firstFunction) {
        this.firstFunction = firstFunction;
        this.secondFunction = secondFunction;
    }

    @Override
    public double apply(double x) {
        return firstFunction.apply(secondFunction.apply(x));
    }
}
