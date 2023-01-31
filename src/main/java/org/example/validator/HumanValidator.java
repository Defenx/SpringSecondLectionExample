package org.example.validator;

import org.example.dto.Human;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class HumanValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Human.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name is empty");
        var human = (Human) target;
        var age = human.getAge();
        var name = human.getName();
        if(name.contains(" ") || age < 0 || age > 100){
            errors.reject("human params is invalid");
        }

        if(errors.hasErrors()) {
            var allErrors = errors.getAllErrors();
            allErrors.stream().map(DefaultMessageSourceResolvable::getCode).forEach(System.out::println);
        }
    }
}
