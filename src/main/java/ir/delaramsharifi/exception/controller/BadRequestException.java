package ir.delaramsharifi.exception.controller;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -2191734854416219718L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}