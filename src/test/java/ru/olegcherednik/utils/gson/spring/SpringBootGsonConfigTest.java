package ru.olegcherednik.utils.gson.spring;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.olegcherednik.utils.gson.GsonUtilsHelper;
import ru.olegcherednik.utils.gson.spring.app.client.BookClient;
import ru.olegcherednik.utils.gson.spring.app.dto.Book;
import ru.olegcherednik.utils.gson.spring.app.server.BookController;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.inOrder;

/**
 * @author Oleg Cherednik
 * @since 17.01.2021
 */
@Test
@Import(BookController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootGsonConfigTest.SpringBootApp.class)
public class SpringBootGsonConfigTest extends BaseClientTest {

    private BookClient client;

    @BeforeMethod
    public void setUp() {
        client = buildClient(BookClient.class);
    }

    public void shouldUseGsonOnBothFeignAndSpringSides() {
        InOrder order = inOrder(gson);
        Book request = new Book("title", "author");
        Book response = new Book("title", "author", "BookController");

        Book actual = client.book(request);
        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(response);

        order.verify(gson).toJson(eq(request), eq(Book.class), any(JsonWriter.class));
        order.verify(gson).fromJson(any(Reader.class), eq((Type)Book.class));
        order.verify(gson).toJson(eq(response), eq(Book.class), any(JsonWriter.class));
        order.verify(gson).fromJson(any(Reader.class), eq((Type)Book.class));
    }

    @SuppressWarnings("EmptyClass")
    @SpringBootApplication
    @Import(SpringBootApp.Config.class)
    public static class SpringBootApp {

        public static class Config {

            private final Gson gson = Mockito.spy(GsonUtilsHelper.DEFAULT_BUILDER.gson());

            @Bean
            public Supplier<Gson> gsonUtilsBuilder(List<GsonUtilsBuilderCustomizer> customizers) {
                return () -> gson;
            }

        }
    }

}
