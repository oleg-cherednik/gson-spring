package ru.olegcherednik.utils.gson.spring;

import org.springframework.context.annotation.Import;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.olegcherednik.utils.gson.spring.app.client.BookClient;
import ru.olegcherednik.utils.gson.spring.app.dto.Book;
import ru.olegcherednik.utils.gson.spring.app.server.BookController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Oleg Cherednik
 * @since 17.01.2021
 */
@SuppressWarnings("unused")
@Test
@Import(BookController.class)
public class BookClientTest extends SpringClientTest {

    private BookClient client;

    @BeforeMethod
    public void setUp() {
        client = buildClient(BookClient.class);
    }

    public void shouldReceiveObjectWhenSendObject() {
        Book actual = client.book(new Book("title", "author"));
        assertThat(actual).isNotNull();
        assertThat(actual).extracting(Book::getTitle).isEqualTo("title");
        assertThat(actual).extracting(Book::getAuthor).isEqualTo("author");
        assertThat(actual).extracting(Book::getResponse).isEqualTo("BookController");
    }

    public void shouldReceiveListWhenSendList() {
        List<Book> actual = client.bookList(Arrays.asList(
                new Book("title1", "author1"),
                new Book("title2", "author2")));

        assertThat(actual).isNotNull();
        assertThat(actual).hasSize(2);
        assertThat(actual).flatExtracting(Book::getTitle, Book::getAuthor, Book::getResponse).contains(
                "title1", "author1", "BookController1",
                "title2", "author2", "BookController2");
    }

    public void shouldReceiveMapWhenSendMap() {
        Map<Integer, List<Book>> books = new HashMap<>();
        books.put(1, Arrays.asList(
                new Book("title1", "author1"),
                new Book("title2", "author2")));
        books.put(2, Arrays.asList(
                new Book("title3", "author3"),
                new Book("title4", "author4")));

        Map<Integer, List<Book>> actual = client.bookMap(books);
        assertThat(actual).isNotNull();
        assertThat(actual).hasSize(2);
        assertThat(actual.get(1)).flatExtracting(Book::getTitle, Book::getAuthor, Book::getResponse).contains(
                "title1", "author1", "BookController_1_1",
                "title2", "author2", "BookController_1_2");
        assertThat(actual.get(2)).flatExtracting(Book::getTitle, Book::getAuthor, Book::getResponse).contains(
                "title3", "author3", "BookController_2_1",
                "title4", "author4", "BookController_2_2");
    }

    public void shouldReceiveNullWhenSendNull() {
        Book actual = client.bookNull();
        assertThat(actual).isNull();
    }

    public void shouldReceiveDefaultObjectWhenNotFound() {
        assertThat(client.bookNotFound()).isNull();
        assertThat(client.bookListNotFound()).isEmpty();
        assertThat(client.bookMapNotFound()).isEmpty();
    }

}
