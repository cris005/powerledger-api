package com.powerledger.api.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

/**
 * The annotated {@code CharSequence} must match the
 * regular expression for a valid Australian postcode.
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PostcodeValidator.class)
public @interface Postcode {
    String message() default "Postcode must fall within the following ranges: 0200-0299, 0800-0999 and 1000-9999";
    boolean optional() default false;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
