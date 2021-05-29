package org.example;

public class App {
    public static void main(String[] args) {
        Publication k1 = new Book("Adam Mickiewicz", "Pan Tadeusz", 340);
        Publication k2 = new Book("Adam Mickiewicz", "Dziady", 130);

        Publication kk1 = new SoftCover(k1);
        Publication kk2 = new HardCover(k2);

//        Publication fakeBook = new BookWrapper(k1);
        Publication kkk2 = new BookWrapper(kk2);
        System.out.println(kkk2);

//        Publication trash = new BookWrapper(kkk2);

        Publication dziadyWithAutograph = new AutographBook("Drogiej Hani - Adam Mickiewicz", kk2);
        System.out.println(dziadyWithAutograph);

        Publication dziadyWithTwoAutographs = new AutographBook("Haniu, to nieprawda, Dziady napisałem ja Julek Słowacki!", dziadyWithAutograph);

    }
}
