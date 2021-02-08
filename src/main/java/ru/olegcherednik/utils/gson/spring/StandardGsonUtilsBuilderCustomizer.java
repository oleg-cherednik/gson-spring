package ru.olegcherednik.utils.gson.spring;

import org.springframework.boot.context.properties.PropertyMapper;
import ru.olegcherednik.utils.gson.GsonUtilsBuilder;

/**
 * @author Oleg Cherednik
 * @since 06.02.2021
 */
public class StandardGsonUtilsBuilderCustomizer implements GsonUtilsBuilderCustomizer {

    private final GsonUtilsProperties properties;

    public StandardGsonUtilsBuilderCustomizer(GsonUtilsProperties properties) {
        this.properties = properties;
    }

    @Override
    public boolean isPrettyPrint() {
        return properties.getPrettyPrinting() == Boolean.TRUE;
    }

    @Override
    public void accept(GsonUtilsBuilder builder) {
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();

        // ---------- extended ----------

        map.from(properties::getZoneModifier).to(builder::setZoneModifier);
//        map.from(properties::getZonedDateTimeFormatter).to(builder::setZonedDateTimeFormatter);
//        map.from(properties::getLocalDateTimeFormatter).to(builder::setLocalDateTimeFormatter);

        // ---------- GsonBuilder ----------

        map.from(properties::getVersion).to(builder::setVersion);
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
        map.from(properties::getLongSerializationPolicy).to(builder::setLongSerializationPolicy);
        map.from(properties::getFieldNamingPolicy).to(builder::setFieldNamingPolicy);
        map.from(properties::getFieldNamingStrategy).to(builder::setFieldNamingStrategy);
        map.from(properties::getExclusionStrategies).to(builder::setExclusionStrategies);
        map.from(properties::getSerializationExclusionStrategies).to(strategies -> strategies.forEach(builder::addSerializationExclusionStrategy));
        map.from(properties::getDeserializationExclusionStrategies)
           .to(strategies -> strategies.forEach(builder::addDeserializationExclusionStrategy));
        map.from(properties::getLenient).toCall(builder::setLenient);
        map.from(properties::getDisableHtmlEscaping).toCall(builder::disableHtmlEscaping);
        map.from(properties::getSerializeSpecialFloatingPointValues).toCall(builder::serializeSpecialFloatingPointValues);
    }

}
