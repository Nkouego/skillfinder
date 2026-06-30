package co.kozao.skillfinder.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public final class ValidationUtil {

    private static final ValidatorFactory FACTORY =
            Validation.buildDefaultValidatorFactory();

    private static final Validator VALIDATOR =
            FACTORY.getValidator();

    private ValidationUtil() {
    }

    public static <T> Map<String, String> validate(T object) {

        Set<ConstraintViolation<T>> violations =
                VALIDATOR.validate(object);

        Map<String, String> errors = new HashMap<>();

        for (ConstraintViolation<T> violation : violations) {

            String field = violation.getPropertyPath().toString();

            errors.putIfAbsent(field, violation.getMessage());
        }

        return errors;
    }

}