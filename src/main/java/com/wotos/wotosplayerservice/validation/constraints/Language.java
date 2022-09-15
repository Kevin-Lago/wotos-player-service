package com.wotos.wotosplayerservice.validation.constraints;

import com.wotos.wotosplayerservice.validation.LanguageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Constraint(validatedBy = LanguageValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Language {
    String message() default "Invalid Language Code";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
