package ru.ssau.tk.GussiRep.labOOP.functions;

import java.util.Iterator;

public class Point implements Iterable{
    public final double x;
    public final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}