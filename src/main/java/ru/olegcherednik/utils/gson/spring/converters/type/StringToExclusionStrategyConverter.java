package ru.olegcherednik.utils.gson.spring.converters.type;

import com.google.gson.ExclusionStrategy;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Collections;
import java.util.Set;

/**
 * @author Oleg Cherednik
 * @since 08.02.2021
 */
public class StringToExclusionStrategyConverter implements ConditionalGenericConverter {

    protected final StringToClassInstanceConverter converter = new StringToClassInstanceConverter();

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, ExclusionStrategy.class));
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return converter.matches(sourceType, targetType);
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        return converter.convert(source, sourceType, targetType);
    }

}
