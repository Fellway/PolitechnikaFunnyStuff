package org.example;

public class Book implements Publication {

    private String author;
    private String title;
    private Integer numberOfPages;

    public Book(final String author, final String title, final Integer numberOfPages) {
        this.author = author;
        this.title = title;
        this.numberOfPages = numberOfPages;
    }


    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    @Override
    public String toString() {
        return String.format("| %s | %s | %d |", author, title, numberOfPages);
    }
}
