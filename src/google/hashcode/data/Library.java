package google.hashcode.data;

import java.util.List;

public class Library implements Comparable<Library> {
    int index;
    List<Book> books;
    int signUp;
    int shipPerDay;
    int daysForShipping;

    public int getDaysForShipping() {
        return daysForShipping;
    }

    public void setDaysForShipping(int daysForShipping) {
        this.daysForShipping = daysForShipping;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

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
