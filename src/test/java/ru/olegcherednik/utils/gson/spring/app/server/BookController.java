package ru.olegcherednik.utils.gson.spring.app.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.olegcherednik.utils.gson.spring.app.dto.Book;

import java.util.List;
import java.util.Map;

/**
 * @author Oleg Cherednik
 * @since 30.01.2021
 */
@RestController
public class BookController {

    @PostMapping("book")
    public Book book(@RequestBody Book book) {
        book.setResponse("BookController");
        return book;
    }

    @PostMapping("book_list")
    public List<Book> bookList(@RequestBody List<Book> books) {
        int i = 1;

        for (Book book : books)
            book.setResponse("BookController" + i++);

        return books;
    }

    @PostMapping("book_map")
    public Map<Integer, List<Book>> bookMap(@RequestBody Map<Integer, List<Book>> books) {
        for (Map.Entry<Integer, List<Book>> entry : books.entrySet()) {
            int i = 1;

            for (Book book : entry.getValue())
                book.setResponse("BookController_" + entry.getKey() + '_' + i++);
        }

        return books;
    }

    @GetMapping("book_null")
    public Book bookNull() {
        return null;
    }

    @GetMapping("book_not_found")
    public ResponseEntity<Book> bookNotFound() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("book_list_not_found")
    public ResponseEntity<List<Book>> bookListNotFound() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("book_map_not_found")
    public ResponseEntity<Map<Integer, List<Book>>> bookMapNotFound() {
        return ResponseEntity.notFound().build();
    }
}
