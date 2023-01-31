package org.example.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ToHexConverter implements Converter<Integer, String> {
    @Override
    public String convert(Integer source) {
        return Integer.toHexString(source);
    }
}
