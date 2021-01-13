package ru.ssau.tk.GussiRep.labOOP.ui2;

import ru.ssau.tk.GussiRep.labOOP.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.GussiRep.labOOP.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.GussiRep.labOOP.exceptions.InconsistentFunctionsException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MyErrorWind {
    MyErrorWind(Component parent, Exception e) {
        showErrorWindow(parent, e);
    }

    public void showErrorWindow(Component parent, Exception e) {
        String head = generateMessageForException(e);
        JOptionPane.showMessageDialog(parent, "Ошибка!", head, JOptionPane.ERROR_MESSAGE);
    }

    private String generateMessageForException(Exception e) {
        if (e instanceof NumberFormatException) {
            return "Неверный формат числа";
        } else if (e instanceof DifferentLengthOfArraysException) {
            return "Неверное значение количства точек";
        } else if (e instanceof ArrayIsNotSortedException) {
            return "Массив точек неотсортирован";
        } else if (e instanceof IOException) {
            return "Ошибка ввода/вывода";
        } else if (e instanceof InconsistentFunctionsException) {
            return "Разная длина массивов";
        } else if (e instanceof IllegalArgumentException) {
            return "Задана некорректная функция";
        }
        return "Неверные данные!";
    }
}
