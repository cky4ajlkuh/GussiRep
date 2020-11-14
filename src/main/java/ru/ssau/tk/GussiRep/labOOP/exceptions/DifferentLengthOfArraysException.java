package ru.ssau.tk.GussiRep.labOOP.exceptions;

public class DifferentLengthOfArraysException extends RuntimeException {
    private static final long serialVersionUID = -1974698215355048952L;

    public DifferentLengthOfArraysException() {
        super();
    }

    public DifferentLengthOfArraysException(String message) {
        super(message);
    }
}

