package uz.pdp.springadvanced;

import java.util.Random;

public class Calculator {
    public int sum(int a, int b) {
        return a + b;
    }

    public int div(int a, int b) {
        return a / b;
    }

    public static boolean testCondition() {
        return new Random().nextBoolean();
    }
}
