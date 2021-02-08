package ru.olegcherednik.utils.gson.spring.app.dto;

/**
 * @author Oleg Cherednik
 * @since 30.01.2021
 */
public class Book {

    private String title;
    private String author;
    private String response;

    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
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
}
