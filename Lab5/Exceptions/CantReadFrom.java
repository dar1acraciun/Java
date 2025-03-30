package Exceptions;

public class CantReadFrom extends RuntimeException{
    public CantReadFrom(String message) {
        super(message);
    }
}
