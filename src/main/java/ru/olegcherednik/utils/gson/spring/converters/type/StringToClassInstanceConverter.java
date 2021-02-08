package ru.olegcherednik.utils.gson.spring.converters.type;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import ru.olegcherednik.utils.gson.GsonUtilsException;

import java.util.Collections;
import java.util.Set;

/**
 * @author Oleg Cherednik
 * @since 08.02.2021
 */
public class StringToClassInstanceConverter implements ConditionalGenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, Object.class));
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return !targetType.getType().isEnum();
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        try {
            if (source == null)
                return null;
            return Class.forName(source.toString()).getConstructor().newInstance();
        } catch(Exception e) {
            throw new GsonUtilsException(e);
        }
    }

}
