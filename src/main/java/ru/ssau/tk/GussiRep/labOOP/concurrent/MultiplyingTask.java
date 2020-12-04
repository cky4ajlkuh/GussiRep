package ru.ssau.tk.GussiRep.labOOP.concurrent;

import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;

public class MultiplyingTask implements Runnable {
    private final TabulatedFunction tabulatedFunction;

    public MultiplyingTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    public void run() {
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            double currentX = tabulatedFunction.getX(i);
            double currentY = tabulatedFunction.getY(i);
            System.out.println(Thread.currentThread().getName() + "i =" + i + " x = " + currentX + " old y = " + currentY);
            tabulatedFunction.setY(i, currentY * 10);
            currentY = tabulatedFunction.getY(i);
            System.out.println(Thread.currentThread().getName() + "i =" + i + " x = " + currentX + " new y = " + currentY);
        }
    }
}
