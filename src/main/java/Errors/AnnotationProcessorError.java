package Errors;

public class AnnotationProcessorError extends Exceptions {
    
    public AnnotationProcessorError() {
        super("No Annotation found.", new Throwable("Check class passed."));
    }
}
