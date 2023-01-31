package org.example.converter;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class ToBigDecimalConverter implements GenericConverter {
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Set.of(new ConvertiblePair(int.class, BigDecimal.class));
    }

    @Override
    public BigDecimal convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        var number = (int) source;
        return BigDecimal.valueOf(number);
    }
}
