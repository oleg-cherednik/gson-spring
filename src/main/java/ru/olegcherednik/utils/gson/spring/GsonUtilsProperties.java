package ru.olegcherednik.utils.gson.spring;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.LongSerializationPolicy;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.ZoneId;
import java.util.List;
import java.util.function.Function;

/**
 * @author Oleg Cherednik
 * @since 06.02.2021
 */
@SuppressWarnings({ "FieldNamingConvention", "MethodParameterNamingConvention" })
@ConfigurationProperties(prefix = "spring.gson")
public class GsonUtilsProperties {

    public static final boolean DEF_PRETTY_PRINT = false;

    // ---------- extended ----------

    private Function<ZoneId, ZoneId> zoneModifier;
//    private DateTimeFormatter zonedDateTimeFormatter;
//    private DateTimeFormatter localDateTimeFormatter;

    // ---------- GsonBuilder ----------

    private Double version;
    private List<FieldModifier> excludeFieldsWithModifiers;
    private Boolean generateNonExecutableJson;
    private Boolean excludeFieldsWithoutExposeAnnotation;
    private Boolean serializeNulls;
    private Boolean disableInnerClassSerialization;
    private LongSerializationPolicy longSerializationPolicy;
    private FieldNamingPolicy fieldNamingPolicy;
    private FieldNamingStrategy fieldNamingStrategy;
    private ExclusionStrategy[] exclusionStrategies;
    private List<ExclusionStrategy> serializationExclusionStrategies;
    private List<ExclusionStrategy> deserializationExclusionStrategies;
    private Boolean lenient;
    private Boolean disableHtmlEscaping;
    private Boolean serializeSpecialFloatingPointValues;
    private Boolean prettyPrinting;

    public enum FieldModifier {
        PUBLIC(0x00000001),
        PRIVATE(0x00000002),
        PROTECTED(0x00000004),
        STATIC(0x00000008),
        FINAL(0x00000010),
        SYNCHRONIZED(0x00000020),
        VOLATILE(0x00000040),
        TRANSIENT(0x00000080),
        NATIVE(0x00000100),
        INTERFACE(0x00000200),
        ABSTRACT(0x00000400),
        STRICT(0x00000800);

        private final int code;

        FieldModifier(int code) {
            this.code = code;
        }

        public int code() {
            return code;
        }
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public Boolean getLenient() {
        return lenient;
    }

    public void setLenient(Boolean lenient) {
        this.lenient = lenient;
    }

    public Boolean getGenerateNonExecutableJson() {
        return generateNonExecutableJson;
    }

    public void setGenerateNonExecutableJson(Boolean generateNonExecutableJson) {
        this.generateNonExecutableJson = generateNonExecutableJson;
    }

    public Boolean getExcludeFieldsWithoutExposeAnnotation() {
        return excludeFieldsWithoutExposeAnnotation;
    }

    public void setExcludeFieldsWithoutExposeAnnotation(Boolean excludeFieldsWithoutExposeAnnotation) {
        this.excludeFieldsWithoutExposeAnnotation = excludeFieldsWithoutExposeAnnotation;
    }

    public Boolean getSerializeNulls() {
        return serializeNulls;
    }

    public void setSerializeNulls(Boolean serializeNulls) {
        this.serializeNulls = serializeNulls;
    }

    public Boolean getDisableInnerClassSerialization() {
        return disableInnerClassSerialization;
    }

    public void setDisableInnerClassSerialization(Boolean disableInnerClassSerialization) {
        this.disableInnerClassSerialization = disableInnerClassSerialization;
    }

    public LongSerializationPolicy getLongSerializationPolicy() {
        return longSerializationPolicy;
    }

    public void setLongSerializationPolicy(LongSerializationPolicy longSerializationPolicy) {
        this.longSerializationPolicy = longSerializationPolicy;
    }

    public FieldNamingPolicy getFieldNamingPolicy() {
        return fieldNamingPolicy;
    }

    public void setFieldNamingPolicy(FieldNamingPolicy fieldNamingPolicy) {
        this.fieldNamingPolicy = fieldNamingPolicy;
    }

    public Boolean getDisableHtmlEscaping() {
        return disableHtmlEscaping;
    }

    public void setDisableHtmlEscaping(Boolean disableHtmlEscaping) {
        this.disableHtmlEscaping = disableHtmlEscaping;
    }

    public Boolean getSerializeSpecialFloatingPointValues() {
        return serializeSpecialFloatingPointValues;
    }

    public void setSerializeSpecialFloatingPointValues(Boolean serializeSpecialFloatingPointValues) {
        this.serializeSpecialFloatingPointValues = serializeSpecialFloatingPointValues;
    }

    public Boolean getPrettyPrinting() {
        return prettyPrinting;
    }

    public void setPrettyPrinting(Boolean prettyPrinting) {
        this.prettyPrinting = prettyPrinting;
    }

    public List<FieldModifier> getExcludeFieldsWithModifiers() {
        return excludeFieldsWithModifiers;
    }

    public void setExcludeFieldsWithModifiers(List<FieldModifier> excludeFieldsWithModifiers) {
        this.excludeFieldsWithModifiers = excludeFieldsWithModifiers;
    }

    public FieldNamingStrategy getFieldNamingStrategy() {
        return fieldNamingStrategy;
    }

    public void setFieldNamingStrategy(FieldNamingStrategy fieldNamingStrategy) {
        this.fieldNamingStrategy = fieldNamingStrategy;
    }

    public ExclusionStrategy[] getExclusionStrategies() {
        return exclusionStrategies;
    }

    public void setExclusionStrategies(ExclusionStrategy[] exclusionStrategies) {
        this.exclusionStrategies = exclusionStrategies;
    }

    public List<ExclusionStrategy> getSerializationExclusionStrategies() {
        return serializationExclusionStrategies;
    }

    public void setSerializationExclusionStrategies(List<ExclusionStrategy> serializationExclusionStrategies) {
        this.serializationExclusionStrategies = serializationExclusionStrategies;
    }

    public List<ExclusionStrategy> getDeserializationExclusionStrategies() {
        return deserializationExclusionStrategies;
    }

    public void setDeserializationExclusionStrategies(List<ExclusionStrategy> deserializationExclusionStrategies) {
        this.deserializationExclusionStrategies = deserializationExclusionStrategies;
    }

    public Function<ZoneId, ZoneId> getZoneModifier() {
        return zoneModifier;
    }

    public void setZoneModifier(Function<ZoneId, ZoneId> zoneModifier) {
        this.zoneModifier = zoneModifier;
    }

}
