package com.salesianos.dam.tallerCultural.tallerCultural.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueTallerTitleValidator.class)
@Documented
public @interface UniqueTallerTitle {

    String message() default "El nombre del taller debe ser único";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
