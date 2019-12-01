package com.fadergs.snakeapp.classess;

import java.util.Objects;

public class Cordinate {
    public Cordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cordinate cordinate = (Cordinate) o;

        return x == cordinate.x &&
                y == cordinate.y;
    }

}
