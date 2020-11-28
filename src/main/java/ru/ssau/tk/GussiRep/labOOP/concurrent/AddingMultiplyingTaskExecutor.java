package ru.ssau.tk.GussiRep.labOOP.concurrent;

import ru.ssau.tk.GussiRep.labOOP.functions.ConstantFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.GussiRep.labOOP.functions.TabulatedFunction;

public class AddingMultiplyingTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new ConstantFunction(2), 1, 100, 100);
        Thread thread = new Thread(new MultiplyingTask(tabulatedFunction));
        Thread thread1 = new Thread(new MultiplyingTask(tabulatedFunction));
        Thread thread2 = new Thread(new AddingTask(tabulatedFunction));

        thread.start();
        thread1.start();
        thread2.start();


        thread.sleep(2000);

    }

}
