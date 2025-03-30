package Exceptions;

public class ImageNotFound extends RuntimeException {
    public ImageNotFound(String message) {
        super(message);
    }
}
