package aaaa.dfs.model;

public class Coordinates {

    private double x,y;

    public Coordinates() {
    }

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public Coordinates setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public Coordinates setY(double y) {
        this.y = y;
        return this;
    }
}
