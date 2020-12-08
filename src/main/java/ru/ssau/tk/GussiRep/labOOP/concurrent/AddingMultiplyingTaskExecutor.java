package ru.ssau.tk.GussiRep.labOOP.concurrent;

import ru.ssau.tk.GussiRep.labOOP.functions.ZeroFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.ConstantFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.LinkedListTabulatedFunction;

public class AddingMultiplyingTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new ConstantFunction(2),1,100,100);
        Thread thread1 = new Thread(new MultiplyingTask(tabulatedFunction));
        Thread thread2 = new Thread(new MultiplyingTask(tabulatedFunction));
        Thread thread3 = new Thread(new AddingTask(tabulatedFunction));

        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(3000);
        System.out.println(tabulatedFunction.toString());
    }
}
