package google.hashcode.data;

public class Book implements Comparable<Book> {
    int index;
    int score;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Book(int index, int score) {
        this.index = index;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Book next) {
        if(this.score < next.score)
            return 1;
        else if(this.score > next.score)
            return -1;
        else
            return 0;
    }
}
