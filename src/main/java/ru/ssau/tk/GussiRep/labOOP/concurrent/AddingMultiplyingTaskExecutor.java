package ru.ssau.tk.GussiRep.labOOP.concurrent;

import ru.ssau.tk.GussiRep.labOOP.functions.ConstantFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.LinkedListTabulatedFunction;

import java.util.concurrent.CountDownLatch;

public class AddingMultiplyingTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new ConstantFunction(2), 1, 100, 100);

        CountDownLatch countDownLatch = new CountDownLatch(3);

        AddingTask addingTask = new AddingTask(tabulatedFunction, countDownLatch::countDown);
        MultiplyingTask multiplyingTask = new MultiplyingTask(tabulatedFunction, countDownLatch::countDown);

        Thread thread1 = new Thread(multiplyingTask);
        thread1.start();
        Thread thread2 = new Thread(multiplyingTask);
        thread2.start();
        Thread thread3 = new Thread(addingTask);
        thread3.start();

        countDownLatch.await();

        System.out.println(tabulatedFunction.toString());
    }
}
