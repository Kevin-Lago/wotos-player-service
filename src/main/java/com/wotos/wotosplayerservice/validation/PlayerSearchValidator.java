package com.wotos.wotosplayerservice.validation;

import com.wotos.wotosplayerservice.validation.constraints.SearchType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class SearchTypeValidator implements ConstraintValidator<SearchType, Object[]> {

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext constraintValidatorContext) {
        String test = "";
        return false;
    }

}
