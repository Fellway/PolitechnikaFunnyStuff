package org.example;

public class HardCover extends BookDecorator{

    private final Publication book;

    public HardCover(final Publication book) {
        super(book.getAuthor(), book.getTitle(), book.getNumberOfPages());
        this.book = book;
    }

    @Override
    public String toString() {
        return super.toString() + " with hard cover";
    }
}
