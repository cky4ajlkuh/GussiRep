package ru.ssau.tk.GussiRep.labOOP.exceptions;

public class InconsistentFunctionsException extends RuntimeException{
    private static final long serialVersionUID = -8947102551582695150L;

    public InconsistentFunctionsException() {
        super();
    }
    public InconsistentFunctionsException(String message) {
        super(message);
    }
}
