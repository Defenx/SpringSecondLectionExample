package org.example;

import org.example.config.StudentConfiguration;
import org.example.dto.Human;
import org.example.dto.Statistic;
import org.example.service.ResourceReaderService;
import org.example.validator.HumanValidator;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.validation.DataBinder;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.Objects;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("org.example");
        Map<String, String> properties = ofEntries(
                entry("name", "Valera"),
                entry("age", "22")
        );

        var human = applicationContext.getBean(Human.class);
        System.out.println(human);
//        var beanWrapper = applicationContext.getBean(BeanWrapper.class);
//        beanWrapper.setPropertyValues(properties);

        var dataBinder = new DataBinder(human);
        var propertyValues = new MutablePropertyValues(properties);
        dataBinder.bind(propertyValues);

        var validator = applicationContext.getBean(HumanValidator.class);
        dataBinder.addValidators(validator);
        dataBinder.validate();
        System.out.println(human);

        var resourceReaderService = applicationContext.getBean(ResourceReaderService.class);
        resourceReaderService.readOrderList("orders.yaml");

        var defaultConversionService = applicationContext.getBean(DefaultConversionService.class);

        var resultOfConverting1 = defaultConversionService.convert(human.getAge(), String.class);
        System.out.println(resultOfConverting1);


        var resultOfConverting2 = defaultConversionService.convert(human.getAge(), BigDecimal.class);
        if (Objects.nonNull(resultOfConverting2)) {
            System.out.println(resultOfConverting2.pow(10000));
        }

        var convertResultIfPrimitive = defaultConversionService.convert(100, BigDecimal.class);
        System.out.println(convertResultIfPrimitive);

        var convertResultIfNotPrimitive = defaultConversionService.convert(100.0, BigDecimal.class);
        System.out.println(convertResultIfNotPrimitive);

        Statistic statistic = new Statistic(LocalDate.of(2050, Month.JANUARY, 1), "123 @", -1);

        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            var validator1 = validatorFactory.getValidator();
            var validate = validator1.validate(statistic);

            if(!validate.isEmpty()) {
                validate.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            }
        }

        var studentConfiguration = applicationContext.getBean(StudentConfiguration.class);
        System.out.println(studentConfiguration.getStudents());

    }
}
