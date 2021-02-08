package ru.olegcherednik.utils.gson.spring.converters.type;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import ru.olegcherednik.utils.gson.GsonUtilsException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Set;

/**
 * @author Oleg Cherednik
 * @since 08.02.2021
 */
public class StringToDateTimeFormatterConverter implements GenericConverter {

    protected final StringToClassInstanceConverter converter = new StringToClassInstanceConverter();

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, DateTimeFormatter.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        try {
            if (source == null)
                return null;

            Object res = getDateTimeFormatterStaticField(source.toString());
            return res == null ? converter.convert(source, sourceType, targetType) : res;
        } catch(Exception e) {
            throw new GsonUtilsException(e);
        }
    }

    private static Object getDateTimeFormatterStaticField(String source) throws NoSuchFieldException, IllegalAccessException {
        if ("ISO_ZONED_DATE_TIME".equalsIgnoreCase(source))
            return DateTimeFormatter.ISO_ZONED_DATE_TIME;
        if ("ISO_LOCAL_DATE_TIME".equalsIgnoreCase(source))
            return DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return null;
    }

}
