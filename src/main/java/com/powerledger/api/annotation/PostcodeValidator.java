package com.powerledger.api.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostcodeValidator implements ConstraintValidator<Postcode, String> {
    private boolean optional;

    @Override
    public void initialize(Postcode constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.optional = constraintAnnotation.optional();
    }

    @Override
    public boolean isValid(String postcode, ConstraintValidatorContext context) {
        // If set as optional and the value is empty, ignore validation
        if (this.optional && postcode == null) {
            return true;
        }

        Pattern pattern = Pattern.compile("^(0[289][0-9]{2})|([1-9][0-9]{3})$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(postcode);
        return matcher.find();
    }
}
