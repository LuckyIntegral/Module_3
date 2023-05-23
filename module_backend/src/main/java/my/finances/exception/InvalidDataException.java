package my.finances.exception;

public class InvalidDataException extends SecurityException {
    public InvalidDataException(String text) {
        super(text);
    }
}
