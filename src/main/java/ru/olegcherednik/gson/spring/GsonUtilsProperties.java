package ru.olegcherednik.gson.spring;

//import com.google.gson.FieldNamingPolicy;
//import com.google.gson.LongSerializationPolicy;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author Oleg Cherednik
 * @since 06.02.2021
 */
@SuppressWarnings({ "FieldNamingConvention", "AssignmentOrReturnOfFieldWithMutableType", "MethodParameterNamingConvention" })
@ConfigurationProperties(prefix = "spring.gson")
public class GsonUtilsProperties {

    public static final boolean DEF_PRETTY_PRINT = false;

    // ---------- GsonBuilder ----------

    private Double version;
    private List<FieldModifier> excludeFieldsWithModifiers;
    private Boolean generateNonExecutableJson;
    private Boolean excludeFieldsWithoutExposeAnnotation;
    private Boolean serializeNulls;
    private Boolean disableInnerClassSerialization;
//    private LongSerializationPolicy longSerializationPolicy;
//    private FieldNamingPolicy fieldNamingPolicy;
    private Boolean lenient;
    private Boolean disableHtmlEscaping;
    private Boolean serializeSpecialFloatingPointValues;
    private Boolean prettyPrinting;

    // ---------- GsonBuilder ----------

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public List<FieldModifier> getExcludeFieldsWithModifiers() {
        return excludeFieldsWithModifiers;
    }

    public void setExcludeFieldsWithModifiers(List<FieldModifier> excludeFieldsWithModifiers) {
        this.excludeFieldsWithModifiers = excludeFieldsWithModifiers;
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

    @SuppressWarnings("MethodParameterNamingConvention")
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

//    public LongSerializationPolicy getLongSerializationPolicy() {
//        return longSerializationPolicy;
//    }

//    public void setLongSerializationPolicy(LongSerializationPolicy longSerializationPolicy) {
//        this.longSerializationPolicy = longSerializationPolicy;
//    }

//    public FieldNamingPolicy getFieldNamingPolicy() {
//        return fieldNamingPolicy;
//    }

//    public void setFieldNamingPolicy(FieldNamingPolicy fieldNamingPolicy) {
//        this.fieldNamingPolicy = fieldNamingPolicy;
//    }

    public Boolean getLenient() {
        return lenient;
    }

    public void setLenient(Boolean lenient) {
        this.lenient = lenient;
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

    public enum FieldModifier {
        PUBLIC(0x1),
        PRIVATE(0x2),
        PROTECTED(0x4),
        STATIC(0x8),
        FINAL(0x10),
        SYNCHRONIZED(0x20),
        VOLATILE(0x40),
        TRANSIENT(0x80),
        NATIVE(0x100),
        INTERFACE(0x200),
        ABSTRACT(0x400),
        STRICT(0x800);

        private final int code;

        FieldModifier(int code) {
            this.code = code;
        }

        public int code() {
            return code;
        }
    }
}
