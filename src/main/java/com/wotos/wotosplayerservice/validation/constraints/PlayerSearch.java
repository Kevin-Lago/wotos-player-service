package com.wotos.wotosplayerservice.validation.constraints;

import com.wotos.wotosplayerservice.validation.SearchTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Constraint(validatedBy = SearchTypeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SearchType {
    String message() default "Invalid Search Type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
