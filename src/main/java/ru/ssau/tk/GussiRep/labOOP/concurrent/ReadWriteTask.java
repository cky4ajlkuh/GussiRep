package ru.ssau.tk.GussiRep.labOOP.concurrent;

import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;

public class ReadWriteTask implements Runnable{
    private final TabulatedFunction tabulatedFunction;
    public ReadWriteTask(TabulatedFunction tabulatedFunction){
        this.tabulatedFunction = tabulatedFunction;
    }
    @Override
    public void run() {

    }
}
