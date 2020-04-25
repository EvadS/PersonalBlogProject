package com.se.blog.personalblog.constraint.impl;

import com.se.blog.personalblog.constraint.PublishingDateConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PublishingDateValidator implements ConstraintValidator<PublishingDateConstraint, String> {
    public void initialize(PublishingDateConstraint constraint) {
    }

    public boolean isValid(String obj, ConstraintValidatorContext context) {
        //TODO: dae validation logic
        return true;
    }
}
