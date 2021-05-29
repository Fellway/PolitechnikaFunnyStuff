package org.example;

public abstract class BookDecorator extends Book {

    public BookDecorator(final String author, final String title, final Integer numberOfPages) {
        super(author, title, numberOfPages);
    }
}
