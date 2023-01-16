package utils;

public class NumberGenerator {

    public static int generateNumber() {
        return (int) Math.ceil(Math.random() * (101 - 51 + 1) + 49);
    }
}
