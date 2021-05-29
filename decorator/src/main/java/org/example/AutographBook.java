package org.example;

public class AutographBook extends BookDecorator {
    private final Publication book;
    private String autograph;

    public AutographBook(final String autograph, final Publication book) {
        super(book.getAuthor(), book.getTitle(), book.getNumberOfPages());
        this.book = book;
        if (book instanceof AutographBook) {
            if (((AutographBook) book).getAutograph() != null) {
                throw new IllegalArgumentException("Autograph can be only one!");
            }
        } else {
            this.autograph = autograph;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " " + autograph;
    }

    public String getAutograph() {
        return this.autograph;
    }
}
