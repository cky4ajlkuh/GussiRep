package ru.ssau.tk.GussiRep.labOOP.concurrent;

import ru.ssau.tk.GussiRep.labOOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.ZeroFunction;

import java.util.ArrayList;
import java.util.List;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new ZeroFunction(), 1, 10, 10);
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            Thread thread = new Thread(new ReadWriteTask(tabulatedFunction));
            list.add(thread);
        }
        for(Thread thread: list){
            thread.start();
        }
        Thread.sleep(3000);

    }
}
