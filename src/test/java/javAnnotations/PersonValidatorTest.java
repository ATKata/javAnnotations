package javAnnotations;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class PersonValidatorTest {

	private Person person;

	@Before
	public void setUp() {
		person = new Person();
	}

	@Test
	public void nameShouldFailValidationIfNull() {
		// Given
		person.forename = null;

		// When
		Set<ConstraintViolation<Object>> violations = validateProperty(person, "forename");

		// Then
		assertEquals(1, violations.size());
	}

	@Test
	public void nameShouldFailValidationIfBlank() {
		// Given
		person.forename = "";

		// When
		Set<ConstraintViolation<Object>> violations = validateProperty(person, "forename");

		// Then
		assertEquals(1, violations.size());
	}

	@Test
	public void nameShouldFailValidationIfFirstCharacterNotUpperCase() {
		// Given
		person.forename = "name";

		// When
		Set<ConstraintViolation<Object>> violations = validateProperty(person, "forename");

		// Then
		assertEquals(1, violations.size());
	}

	@Test
	public void ageShouldFailValidationIfLessThanZero() {
		// Given
		person.age = -1;

		// When
		Set<ConstraintViolation<Object>> violations = validateProperty(person, "age");

		// Then
		assertEquals(1, violations.size());
	}

	@Test
	public void emailShouldFailValidationIfInvalidEmailFormat() {
		// Given
		person.email = "justsometext";

		// When
		Set<ConstraintViolation<Object>> violations = validateProperty(person, "email");

		// Then
		assertEquals(1, violations.size());
	}

	@Test
	public void favouriteCarShouldFailValidationIfBMW() {
		// Given
		person.favouriteCar = CarMake.BMW;

		// When
		Set<ConstraintViolation<Object>> violations = validateProperty(person, "favouriteCar");

		// Then
		assertEquals(1, violations.size());
	}

	@Test
	public void favouriteCarShouldPassValidationIfFord() {
		// Given
		person.favouriteCar = CarMake.FORD;

		// When
		Set<ConstraintViolation<Object>> violations = validateProperty(person, "favouriteCar");

		// Then
		assertEquals(0, violations.size());
	}

	@Test
	public void phoneShouldFailValidationIfInvalidPhoneFormat() {
		// Given
		person.phoneNumber = "123";

		// When
		Set<ConstraintViolation<Object>> violations = validateProperty(person, "phoneNumber");

		// Then
		assertEquals(1, violations.size());
	}

	private void print(Set<ConstraintViolation<Object>> violations) {
		// Code Smell.... This shouldn't be necessary to debug
		for (ConstraintViolation<Object> v : violations) {
			System.out.println(v.getMessage());
		}
	}

	private Set<ConstraintViolation<Object>> validateProperty(Object obj, String propertyName) {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		return validator.validateProperty(obj, propertyName);
	}
}
