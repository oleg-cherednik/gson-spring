package ru.olegcherednik.utils.gson.spring.app.client;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.olegcherednik.utils.gson.spring.app.dto.Book;

import java.util.List;
import java.util.Map;

/**
 * @author Oleg Cherednik
 * @since 30.01.2021
 */
@SuppressWarnings("InterfaceNeverImplemented")
public interface BookClient {

    @PostMapping(value = "book", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Book book(@RequestBody Book book);

    @PostMapping(value = "book_list", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Book> bookList(List<Book> books);

    @PostMapping(value = "book_map", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<Integer, List<Book>> bookMap(Map<Integer, List<Book>> books);

    @GetMapping("book_null")
    Book bookNull();

    @GetMapping("book_not_found")
    Book bookNotFound();

    @GetMapping("book_list_not_found")
    List<Book> bookListNotFound();

    @GetMapping("book_map_not_found")
    Map<Integer, List<Book>> bookMapNotFound();
}
