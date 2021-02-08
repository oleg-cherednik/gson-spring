package ru.olegcherednik.utils.gson.spring.converters.type;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import ru.olegcherednik.utils.gson.GsonUtilsBuilder;
import ru.olegcherednik.utils.gson.GsonUtilsException;

import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Oleg Cherednik
 * @since 08.02.2021
 */
public class StringToFunctionConverter implements GenericConverter {

    protected final StringToClassInstanceConverter converter = new StringToClassInstanceConverter();

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, Function.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        try {
            if (source == null)
                return null;

            Object res = getGsonUtilsBuilderStaticField(source.toString());
            return res == null ? converter.convert(source, sourceType, targetType) : res;
        } catch(Exception e) {
            throw new GsonUtilsException(e);
        }
    }

    private static Object getGsonUtilsBuilderStaticField(String source) throws NoSuchFieldException, IllegalAccessException {
        if ("ZONE_MODIFIER_USE_ORIGINAL".equalsIgnoreCase(source))
            return GsonUtilsBuilder.ZONE_MODIFIER_USE_ORIGINAL;
        if ("ZONE_MODIFIER_TO_UTC".equalsIgnoreCase(source))
            return GsonUtilsBuilder.ZONE_MODIFIER_TO_UTC;
        return null;
    }

}
