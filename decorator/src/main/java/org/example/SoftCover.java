package org.example;

public class SoftCover extends BookDecorator {

    private final Publication book;

    public SoftCover(final Publication book) {
        super(book.getAuthor(), book.getTitle(), book.getNumberOfPages());
        if (book instanceof SoftCover || book instanceof HardCover) {
            throw new IllegalArgumentException("Book already has cover!");
        }
        this.book = book;
    }

    @Override
    public String toString() {
        return book.toString() + " | with soft cover";
    }
}
