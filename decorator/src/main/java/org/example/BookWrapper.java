package org.example;

public class BookWrapper extends BookDecorator {

    private final Publication book;

    public BookWrapper(final Publication book) {
        super(book.getAuthor(), book.getTitle(), book.getNumberOfPages());
        if (book instanceof SoftCover || book instanceof HardCover) {
            this.book = book;
        } else if (book instanceof BookWrapper) {
            throw new IllegalArgumentException("Wrapper can be only one!");
        } else {
            throw new IllegalArgumentException("You cannot wrap book without cover!");
        }
    }

    @Override
    public String toString() {
        return book.toString() + " | With wrapper ";
    }
}
