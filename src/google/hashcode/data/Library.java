package google.hashcode.data;

import java.util.List;

public class Library implements Comparable<Library> {
    List<Book> books;
    int signUp;
    int shipPerDay;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getSignUp() {
        return signUp;
    }

    public void setSignUp(int signUp) {
        this.signUp = signUp;
    }

    public int getShipPerDay() {
        return shipPerDay;
    }

    public void setShipPerDay(int shipPerDay) {
        this.shipPerDay = shipPerDay;
    }

    @Override
    public int compareTo(Library o) {
        return 0;
        // to be implemented later
    }
}
