package javAnnotations.example;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BeginsWithACapitalLetterValidator implements ConstraintValidator<BeginsWithACapitalLetter, String> {

	@Override
	public void initialize(BeginsWithACapitalLetter constraintAnnotation) {
		// Fetch attributes/settings on the custom constraint if necessary
		// For example can define extra properties e.g @SomeAnnotation(firstLetter = "H") and pull this back here
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value != null && value.length() > 0) {
			return Character.isUpperCase(value.charAt(0));
		}

		return false;
	}

}
