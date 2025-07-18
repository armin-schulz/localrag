package de.localrag.backend.api.auth.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OptionalStringValidator.class)
public @interface ValidOptionalString {
    String message() default "Field cannot be blank if provided";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}