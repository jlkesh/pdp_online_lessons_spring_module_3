package uz.pdp.springadvanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorRepeatedTest {
    Calculator calculator;
    Random random;
    Logger log = Logger.getLogger(CalculatorRepeatedTest.class.getName());

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        random = new Random();
    }


    @RepeatedTest(3)
    void div() {
        int a = random.nextInt(40, 80);
        int b = random.nextInt(2, 20);
        int expected = a / b;
        log.info(a + " / " + b + " = " + expected);
        assertEquals(expected, calculator.div(a, b));
    }


    @RepeatedTest(10)
    void sum() {
        int a = random.nextInt(40, 80);
        int b = random.nextInt(2, 20);
        int expected = a + b;
        log.info(a + " + " + b + " = " + expected);
        assertEquals(expected, calculator.sum(a, b));
    }
}