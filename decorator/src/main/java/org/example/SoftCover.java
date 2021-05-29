package org.example;

public class SoftCover extends BookDecorator{

    private final Publication book;

    public SoftCover(final Publication book) {
        super(book.getAuthor(), book.getTitle(), book.getNumberOfPages());
        this.book = book;
    }

    @Override
    public String toString() {
        return super.toString() + " with soft cover";
    }
}
