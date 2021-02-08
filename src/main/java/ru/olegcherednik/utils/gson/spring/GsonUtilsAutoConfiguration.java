package ru.olegcherednik.utils.gson.spring;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.olegcherednik.utils.gson.GsonUtilsBuilder;
import ru.olegcherednik.utils.gson.spring.converters.type.StringToDateTimeFormatterConverter;
import ru.olegcherednik.utils.gson.spring.converters.type.StringToExclusionStrategyConverter;
import ru.olegcherednik.utils.gson.spring.converters.type.StringToFieldNamingStrategyConverter;
import ru.olegcherednik.utils.gson.spring.converters.type.StringToFunctionConverter;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author Oleg Cherednik
 * @since 05.02.2021
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(Gson.class)
@EnableConfigurationProperties(GsonUtilsProperties.class)
@Import(GsonUtilsWebMvcConfigurer.class)
public class GsonUtilsAutoConfiguration {

    @Autowired
    public void registerTypeConverters() {
        ApplicationConversionService applicationConversionService = (ApplicationConversionService)ApplicationConversionService.getSharedInstance();
        applicationConversionService.addConverter(new StringToFieldNamingStrategyConverter());
        applicationConversionService.addConverter(new StringToExclusionStrategyConverter());
        applicationConversionService.addConverter(new StringToFunctionConverter());
        applicationConversionService.addConverter(new StringToDateTimeFormatterConverter());
    }

    @Bean
    @ConditionalOnMissingBean
    public Supplier<Gson> gsonUtilsBuilder(List<GsonUtilsBuilderCustomizer> customizers) {
        GsonUtilsBuilder builder = new GsonUtilsBuilder();
        customizers.forEach(customizer -> customizer.accept(builder));
        return isPrettyPrint(customizers) ? builder::prettyPrintGson : builder::gson;
    }

    @Bean
    @ConditionalOnMissingBean
    public Gson gson(Supplier<Gson> supplier) {
        return supplier.get();
    }

    @Bean
    public StandardGsonUtilsBuilderCustomizer standardGsonBuilderCustomizer(GsonUtilsProperties gsonProperties) {
        return new StandardGsonUtilsBuilderCustomizer(gsonProperties);
    }

    private static boolean isPrettyPrint(List<GsonUtilsBuilderCustomizer> customizers) {
        if (customizers.isEmpty())
            return GsonUtilsProperties.DEF_PRETTY_PRINT;
        return customizers.get(customizers.size() - 1).isPrettyPrint();
    }

}
