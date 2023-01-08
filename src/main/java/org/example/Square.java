package org.example;

public class Square {

    private double squareSide;

    public Square(double squareSide) {
        this.squareSide = squareSide;
    }

    public double getSquareArea() {
        return squareSide * squareSide;
    }
}
