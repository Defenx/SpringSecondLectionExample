package org.example.config;

import lombok.RequiredArgsConstructor;
import org.example.converter.ToBigDecimalConverter;
import org.example.converter.ToBigDecimalOnConditionConverter;
import org.example.converter.ToHexConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
@RequiredArgsConstructor
public class DefaultConversionServiceConfig {

    final ToBigDecimalConverter toBigDecimalConverter;
    final ToBigDecimalOnConditionConverter toBigDecimalOnConditionConverter;
    final ToHexConverter toHexConverter;

    @Bean
    public DefaultConversionService getDefaultConversionService(){
        var defaultConversionService = new DefaultConversionService();
        defaultConversionService.addConverter(toHexConverter);
        defaultConversionService.addConverter(toBigDecimalConverter);
        defaultConversionService.addConverter(toBigDecimalOnConditionConverter);
        return defaultConversionService;
    }
}
