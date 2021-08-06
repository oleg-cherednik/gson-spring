package ru.olegcherednik.gson.spring;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.testng.annotations.Test;
import ru.olegcherednik.gson.spring.app.dto.Book;
import ru.olegcherednik.gson.utils.GsonUtils;
import ru.olegcherednik.gson.utils.GsonUtilsHelper;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author Oleg Cherednik
 * @since 17.01.2021
 */
@Test
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootGsonConfigTest.SpringBootApp.class)
public class SpringBootGsonConfigTest extends BaseClientTest {

    public void shouldUseGsonOnSpringSide() throws Exception {
        Book request = new Book("title", "author");
        Book response = new Book("title", "author", "BookController");
        InOrder order = inOrder(gson);

        MvcResult result = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(GsonUtils.writeValue(request)))
                                  .andReturn();
        String json = result.getResponse().getContentAsString();
        assertThat(json).isNotBlank();
        assertThat(json).contains("\n");

        Book actual = GsonUtils.readValue(json, Book.class);
        assertThat(actual).isEqualTo(response);

        order.verify(gson).fromJson(any(Reader.class), eq((Type)Book.class));
        order.verify(gson).toJson(eq(response), eq(Book.class), any(JsonWriter.class));
    }

    @SpringBootApplication
    @Import(SpringBootApp.Config.class)
    public static class SpringBootApp {

        public static void main(String... args) {
            SpringApplication.run(SpringBootApp.class, args);
        }

        public static class Config {

            private final Gson gson = Mockito.spy(GsonUtilsHelper.createPrettyPrintGson());

            @Bean
            public Supplier<Gson> gsonSupplier() {
                return () -> gson;
            }

        }

    }

}
