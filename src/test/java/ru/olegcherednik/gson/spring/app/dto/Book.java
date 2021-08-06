package ru.olegcherednik.gson.spring.app.dto;

import java.util.Objects;

/**
 * @author Oleg Cherednik
 * @since 30.01.2021
 */
public class Book {

    private String title;
    private String author;
    private String response;

    @SuppressWarnings("unused")
    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author, String response) {
        this(title, author);
        this.response = response;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Book))
            return false;

        Book book = (Book)obj;

        return Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(response, book.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, response);
    }
}
