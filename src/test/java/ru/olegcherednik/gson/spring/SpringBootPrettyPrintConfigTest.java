package ru.olegcherednik.gson.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.testng.annotations.Test;
import ru.olegcherednik.gson.spring.app.dto.Book;
import ru.olegcherednik.gson.utils.GsonUtils;
import ru.olegcherednik.gson.utils.GsonUtilsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Oleg Cherednik
 * @since 17.01.2021
 */
@Test
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootPrettyPrintConfigTest.SpringBootApp.class)
public class SpringBootPrettyPrintConfigTest extends BaseClientTest {

    public void shouldUsePrettyPrintJsonWhenPrettyPrintConfigured() {
        Book book = new Book("title", "author");
        String actual = gson.toJson(book);
        String notPrettyPrintJson = GsonUtils.print().writeValue(book);
        assertThat(actual.length()).isGreaterThan(notPrettyPrintJson.length());
    }

    @SuppressWarnings("EmptyClass")
    @SpringBootApplication
    @Import(SpringBootApp.Config.class)
    public static class SpringBootApp {

        @SuppressWarnings("InnerClassTooDeeplyNested")
        public static class Config {

            @Bean
            public GsonUtilsBuilderCustomizer customGsonUtilsBuilderCustomizer() {
                return new GsonUtilsBuilderCustomizer() {

                    @Override
                    public boolean isPrettyPrint() {
                        return true;
                    }

                    @Override
                    public void accept(GsonUtilsBuilder builder) {
                    }

                    @Override
                    public int getOrder() {
                        return DEFAULT_PRIORITY;
                    }
                };
            }

        }

    }

}
