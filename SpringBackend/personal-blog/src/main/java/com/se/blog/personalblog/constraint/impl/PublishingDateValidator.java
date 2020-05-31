package com.se.blog.personalblog.constraint.impl;

import com.se.blog.personalblog.constraint.PublishingDateConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class PublishingDateValidator implements ConstraintValidator<PublishingDateConstraint, Date> {

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext){
        LocalDate localDate = LocalDate.now();
      LocalDateTime currentDateTime = LocalDateTime.now();
      // TODO: move converter to one place's
      LocalDateTime  publishingDate = date.toInstant()
              .atZone(ZoneId.systemDefault())
              .toLocalDateTime();

      return  publishingDate.isAfter(currentDateTime);
    }
}
