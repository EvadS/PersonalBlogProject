package com.se.blog.personalblog.constraint;

import com.se.blog.personalblog.constraint.impl.PublishingDateValidator;
import com.se.blog.personalblog.model.entity.Post;
import com.se.blog.personalblog.model.request.post.NewPostRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;


import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Slf4j
public class PublishingDateValidatorTest {

    // Инициализация Validator
    private static Validator validator;
    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    LocalDateTime publishDate = LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40);

    List<String> tagList = new ArrayList<>();

    @Test
   public void isValid() {
        Date pubDate = Date.from( publishDate.atZone( ZoneId.systemDefault()).toInstant());;

        log.debug("publishing date: {}  ", pubDate.toString());
        NewPostRequest post  = NewPostRequest.builder()
                .title("title")
                .content("content")
                .description("description")
                .previewText("preview text")
                .publishingDate(publishDate)
                .tags(tagList)
                .previewImage(null)
                .build();

        Set<ConstraintViolation<NewPostRequest>> validates = validator.validate(post);
        Assert.assertTrue(validates.size() > 0);
        validates.stream().map(v -> v.getMessage())
                .forEach(System.out::println);
    }
}