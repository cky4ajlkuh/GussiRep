package ru.ssau.tk.GussiRep.labOOP.operations;

import ru.ssau.tk.GussiRep.labOOP.functions.MathFunction;

public interface DifferentialOperator<T> extends MathFunction {
    T derive(T function);
}
