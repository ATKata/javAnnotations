package javAnnotations.example;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = BeginsWithACapitalLetterValidator.class)
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface BeginsWithACapitalLetter {
	String message() default "{javAnnotations.example.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
