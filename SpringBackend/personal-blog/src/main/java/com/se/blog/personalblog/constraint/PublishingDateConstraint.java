package com.se.blog.personalblog.constraint;


import com.se.blog.personalblog.constraint.impl.PublishingDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PublishingDateValidator.class)
public @interface PublishingDateConstraint {
    String message() default "{publishing.incorrect}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}