package ru.olegcherednik.gson.spring;

import org.springframework.boot.context.properties.PropertyMapper;
import ru.olegcherednik.gson.utils.GsonUtilsBuilder;

/**
 * @author Oleg Cherednik
 * @since 06.02.2021
 */
public class DefaultGsonUtilsBuilderCustomizer implements GsonUtilsBuilderCustomizer {

    private final GsonUtilsProperties properties;

    public DefaultGsonUtilsBuilderCustomizer(GsonUtilsProperties properties) {
        this.properties = properties;
    }

    @Override
    public boolean isPrettyPrint() {
        return properties.getPrettyPrinting() == Boolean.TRUE;
    }

    @Override
    public void accept(GsonUtilsBuilder builder) {
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();

        // ---------- GsonBuilder ----------

//        map.from(properties::getVersion).to(builder::setVersion);
        map.from(properties::getExcludeFieldsWithModifiers).to(fieldModifiers -> {
            int[] modifiers = fieldModifiers.stream()
                                            .mapToInt(GsonUtilsProperties.FieldModifier::code)
                                            .toArray();
            if (modifiers.length != 0)
                builder.excludeFieldsWithModifiers(modifiers);
        });
        map.from(properties::getGenerateNonExecutableJson).toCall(builder::generateNonExecutableJson);
        map.from(properties::getExcludeFieldsWithoutExposeAnnotation).toCall(builder::excludeFieldsWithoutExposeAnnotation);
        map.from(properties::getSerializeNulls).toCall(builder::serializeNulls);
        map.from(properties::getDisableInnerClassSerialization).toCall(builder::disableInnerClassSerialization);
//        map.from(properties::getLongSerializationPolicy).to(builder::setLongSerializationPolicy);
//        map.from(properties::getFieldNamingPolicy).to(builder::setFieldNamingPolicy);
        map.from(properties::getLenient).toCall(builder::setLenient);
        map.from(properties::getDisableHtmlEscaping).toCall(builder::disableHtmlEscaping);
        map.from(properties::getSerializeSpecialFloatingPointValues).toCall(builder::serializeSpecialFloatingPointValues);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRIORITY;
    }

}
