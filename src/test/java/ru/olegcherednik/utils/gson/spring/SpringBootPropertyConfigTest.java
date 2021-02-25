package ru.olegcherednik.utils.gson.spring;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.LongSerializationPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.testng.annotations.Test;
import ru.olegcherednik.utils.gson.spring.app.server.BookController;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Oleg Cherednik
 * @since 17.01.2021
 */
@Test
@Import(BookController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootPropertyConfigTest.SpringBootApp.class)
@PropertySource("classpath:spring.gson.properties")
public class SpringBootPropertyConfigTest extends BaseClientTest {

    @Autowired
    private GsonUtilsProperties properties;

    public void shouldReadPropertiesWhenPropertyExistsInConfigFile() {
        assertThat(properties.getVersion()).isEqualTo(666.14);
        assertThat(properties.getExcludeFieldsWithModifiers()).isEqualTo(Arrays.asList(
                GsonUtilsProperties.FieldModifier.PUBLIC,
                GsonUtilsProperties.FieldModifier.PRIVATE));
        assertThat(properties.getGenerateNonExecutableJson()).isTrue();
        assertThat(properties.getExcludeFieldsWithoutExposeAnnotation()).isTrue();
        assertThat(properties.getSerializeNulls()).isTrue();
        assertThat(properties.getDisableInnerClassSerialization()).isTrue();
        assertThat(properties.getLongSerializationPolicy()).isSameAs(LongSerializationPolicy.DEFAULT);
        assertThat(properties.getFieldNamingPolicy()).isSameAs(FieldNamingPolicy.IDENTITY);
        assertThat(properties.getLenient()).isTrue();
        assertThat(properties.getDisableHtmlEscaping()).isTrue();
        assertThat(properties.getSerializeSpecialFloatingPointValues()).isFalse();
        assertThat(properties.getPrettyPrinting()).isFalse();
    }

    @SuppressWarnings("EmptyClass")
    @SpringBootApplication
    public static class SpringBootApp {

    }

}
