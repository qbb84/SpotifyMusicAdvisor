package Errors;

public abstract class Exceptions extends Exception {

    public Exceptions(String reason, Throwable cause) {
        super(reason, cause);
    }
}
