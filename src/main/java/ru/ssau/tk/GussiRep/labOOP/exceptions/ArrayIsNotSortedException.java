package ru.ssau.tk.GussiRep.labOOP.exceptions;

public class ArrayIsNotSortedException extends RuntimeException {
    private static final long serialVersionUID = -529930192912911674L;

    public ArrayIsNotSortedException() {
        super();
    }

    public ArrayIsNotSortedException(String message) {
        super(message);
    }
}
