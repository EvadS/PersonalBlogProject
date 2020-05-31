package com.se.blog.personalblog.constraint;

import com.se.blog.personalblog.constraint.impl.ContentTypeMultipartFileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * The annotated element must have specified content type.
 *
 * Supported types are:
 * <ul>
 * <li><code>MultipartFile</code></li>
 * </ul>
 *
 * @author SE
 */
//@Documented
//@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = {ContentTypeMultipartFileValidator.class})
//@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ContentTypeMultipartFileValidator.class})
public @interface ContentType {

    String message() default  "Invalid image file";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Specify accepted content types.
     *
     * Content type example :
     * <ul>
     * <li>application/pdf - accepts PDF documents only</li>
     * <li>application/msword - accepts MS Word documents only</li>
     * <li>images/png - accepts PNG images only</li>
     * </ul>
     *
     * @return accepted content types
     */
    String[] value();
}