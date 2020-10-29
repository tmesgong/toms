package exception;

public class BookNotEnoughException extends Exception {
    public BookNotEnoughException() {
    }

    public BookNotEnoughException(String message) {
        super(message);
    }
}
