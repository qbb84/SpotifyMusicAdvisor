package Errors;

public class InvalidFeatureException extends Exceptions {


    public InvalidFeatureException() {
        super("Invalid Feature", new Throwable("A feature was entered that doesn't exist... See input.start()"));
    }
}
