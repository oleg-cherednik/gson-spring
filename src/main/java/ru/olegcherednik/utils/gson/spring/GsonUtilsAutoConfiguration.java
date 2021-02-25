package ru.olegcherednik.utils.gson.spring;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.olegcherednik.utils.gson.GsonUtilsBuilder;

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

    @Bean
    @ConditionalOnMissingBean
    public Supplier<Gson> gsonUtilsBuilder(List<GsonUtilsBuilderCustomizer> customizers) {
        GsonUtilsBuilder builder = new GsonUtilsBuilder();
        boolean prettyPrint = GsonUtilsProperties.DEF_PRETTY_PRINT;

        for (GsonUtilsBuilderCustomizer customizer : customizers) {
            prettyPrint = customizer.isPrettyPrint();
            customizer.accept(builder);
        }

        return prettyPrint ? builder::prettyPrintGson : builder::gson;
    }

    @Bean
    @ConditionalOnMissingBean
    public Gson gson(Supplier<Gson> supplier) {
        return supplier.get();
    }

    @Bean
    public DefaultGsonUtilsBuilderCustomizer defaultGsonUtilsBuilderCustomizer(GsonUtilsProperties gsonProperties) {
        return new DefaultGsonUtilsBuilderCustomizer(gsonProperties);
    }

}
