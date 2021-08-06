package ru.olegcherednik.gson.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.olegcherednik.gson.spring.GsonUtilsEnvironmentPostProcessor.PROPERTY_SOURCE_NAME;
import static ru.olegcherednik.gson.spring.GsonUtilsEnvironmentPostProcessor.SPRING_EXCLUDE_PROPERTY;

/**
 * @author Oleg Cherednik
 * @since 25.02.2021
 */
@Test
@SuppressWarnings("NewClassNamingConvention")
public class GsonUtilsEnvironmentPostProcessorTest {

    private final GsonUtilsEnvironmentPostProcessor processor = new GsonUtilsEnvironmentPostProcessor();

    public void shouldSetGsonAutoConfigurationToIgnoreWhenIgnoreListIsEmpty() {
        ConfigurableEnvironment environment = mock(ConfigurableEnvironment.class);
        SpringApplication application = mock(SpringApplication.class);
        MutablePropertySources propertySources = new MutablePropertySources();

        when(environment.getProperty(eq(SPRING_EXCLUDE_PROPERTY), any(String.class))).thenReturn("");
        when(environment.getPropertySources()).thenReturn(propertySources);

        processor.postProcessEnvironment(environment, application);

        MapPropertySource propertySource = (MapPropertySource)propertySources.get(PROPERTY_SOURCE_NAME);
        assertThat(propertySource).isNotNull();

        String actual = (String)propertySource.getProperty(SPRING_EXCLUDE_PROPERTY);
        assertThat(actual).isEqualTo("org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration");
    }

    public void shouldAddFirstGsonAutoConfigurationToIgnoreWhenIgnoreListIsNotEmpty() {
        ConfigurableEnvironment environment = mock(ConfigurableEnvironment.class);
        SpringApplication application = mock(SpringApplication.class);
        MutablePropertySources propertySources = new MutablePropertySources();

        when(environment.getProperty(eq(SPRING_EXCLUDE_PROPERTY), any(String.class))).thenReturn("xxx,yyy,zzz");
        when(environment.getPropertySources()).thenReturn(propertySources);

        processor.postProcessEnvironment(environment, application);

        MapPropertySource propertySource = (MapPropertySource)propertySources.get(PROPERTY_SOURCE_NAME);
        assertThat(propertySource).isNotNull();

        String actual = (String)propertySource.getProperty(SPRING_EXCLUDE_PROPERTY);
        assertThat(actual).isEqualTo("org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration,xxx,yyy,zzz");
    }

    public void shouldNotAddGsonAutoConfigurationToIgnoreWhenIgnoreListAlreadyContainIt() {
        ConfigurableEnvironment environment = mock(ConfigurableEnvironment.class);
        SpringApplication application = mock(SpringApplication.class);
        MutablePropertySources propertySources = new MutablePropertySources();

        when(environment.getProperty(eq(SPRING_EXCLUDE_PROPERTY), any(String.class)))
                .thenReturn("org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration,xxx,yyy,zzz");
        when(environment.getPropertySources()).thenReturn(propertySources);

        processor.postProcessEnvironment(environment, application);

        MapPropertySource propertySource = (MapPropertySource)propertySources.get(PROPERTY_SOURCE_NAME);
        assertThat(propertySource).isNotNull();

        String actual = (String)propertySource.getProperty(SPRING_EXCLUDE_PROPERTY);
        assertThat(actual).isEqualTo("org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration,xxx,yyy,zzz");
    }

}
