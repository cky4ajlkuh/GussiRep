package ru.ssau.tk.GussiRep.labOOP.concurrent;

import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;

public class ReadWriteTask implements Runnable {
    private final TabulatedFunction tabulatedFunction;

    public ReadWriteTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public void run() {
        double x = 0;
        double y = 0;
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            x = tabulatedFunction.getX(i);
            y = tabulatedFunction.getY(i);
            System.out.printf(" %s, before write:  i = %d, x = %f, y = %f ", Thread.currentThread().getName(), i, x, y);
            tabulatedFunction.setY(i, y + 1);
            y = tabulatedFunction.getY(i);
            System.out.printf(" %s, after write: i = %d, x = %f, y = %f ", Thread.currentThread().getName(), i, x, y);
            System.out.println();
        }
    }
}
