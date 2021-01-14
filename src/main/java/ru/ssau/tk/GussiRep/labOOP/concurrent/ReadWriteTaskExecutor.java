package ru.ssau.tk.GussiRep.labOOP.concurrent;

import ru.ssau.tk.GussiRep.labOOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.ZeroFunction;

import java.util.concurrent.CountDownLatch;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new ZeroFunction(), 1, 10, 10);
        List<Thread> list = new ArrayList<>();
        int countThread = 20;
        CountDownLatch countDownLatch = new CountDownLatch(countThread);
        ReadWriteTask newTask = new ReadWriteTask(tabulatedFunction, countDownLatch::countDown);
        for (int i = 0; i <= 20; i++) {
            list.add(new Thread(newTask));
        }
        for (Thread thread : list) {
            thread.start();
        }
        countDownLatch.await();
        System.out.println(tabulatedFunction.toString());
    }
}
