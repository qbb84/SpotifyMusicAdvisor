package Input.InputAnnotations;

import Errors.AnnotationProcessorError;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;


public record AnnotationProcessor(@NotNull Class<?>... searchClass) {
    
    @SuppressWarnings("unused")
    public @Nullable Object[] getValues(Class<? extends Annotation> annotationClass) {


        if (getAnnotation(annotationClass) == null) {
            try {
                throw new AnnotationProcessorError();
            } catch (AnnotationProcessorError e) {
                System.out.println(e.getMessage());
                return null;
            }
        }

        if (getAnnotation(annotationClass) instanceof InputPrefix) {

            InputPrefix annotationValues = getAnnotation(annotationClass);

            Object[] values = new Object[2];
            values[0] = annotationValues.name();
            values[1] = annotationValues.arguments();

            return values;
        }

        return null;
    }

    @Contract(pure = true)
    private <T extends Annotation> @Nullable T getAnnotation(Class<? extends Annotation> annotationClass) {
        for (Class<?> aClass : searchClass) {
            return (T) aClass.getAnnotation(annotationClass);
        }
        return null;
    }


}
