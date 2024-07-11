package aaaa.dfs.MyRcyclerView;

public class Item {
    private String date;
    private int background, place, score;


    public Item(String date, int background, int place, int score) {
        this.date = date;
        this.background = background;
        this.place = place;
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public Item setDate(String date) {
        this.date = date;
        return this;
    }

    public int getBackground() {
        return background;
    }

    public Item setBackground(int background) {
        this.background = background;
        return this;
    }

    public int getPlace() {
        return place;
    }

    public Item setPlace(int place) {
        this.place = place;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Item setScore(int score) {
        this.score = score;
        return this;
    }
}
