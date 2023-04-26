package uz.pdp.springadvanced;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorParametrizedTest {
    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest(name = "{displayName} => {index} : [{arguments}]")
    @ValueSource(strings = {"PDP", "JAVA", "LEARN", "FIND", "HIGH", "PAYED", "JOB"})
    @DisplayName("Test Method For ParametrizedTest Sum(String)")
    void testMethod(String message) {
        assertEquals(message, message.toUpperCase());
    }

    @ParameterizedTest(name = "{displayName} => {index} : {arguments}")
    @MethodSource("sumSource")
    void sum(SumArgument arg) {
        assertEquals(arg.expected, calculator.sum(arg.a, arg.b));
    }

    @ParameterizedTest(name = "{displayName} => {index} : {arguments}")
    @CsvSource(value = {
            "a, b, sum",
            "1, 2, 3",
            "3, 1, 4"
    },
            useHeadersInDisplayName = true)
    void sum(int a, int b, int expected) {
        assertEquals(expected, calculator.sum(a, b));
    }

    @ParameterizedTest(name = "{displayName} => {index} : {arguments}")
    @CsvFileSource(resources = "/source.csv",
            useHeadersInDisplayName = true,
            numLinesToSkip = 1)
    void sumCsvFile(int a, int b, int expected) {
        assertEquals(expected, calculator.sum(a, b));
    }
    /*@ParameterizedTest(name = "{displayName} => {index} : [{arguments}]")
    @MethodSource
    void div(DivArgument arg) {
        assertEquals(arg.expected, calculator.sum(arg.a, arg.b));
    }*/

    static Stream<SumArgument> sumSource() {
        return Stream.of(
                new SumArgument(2, 3, 5),
                new SumArgument(1, -3, -2),
                new SumArgument(15, -30, -15)
        );
    }

    public static class SumArgument {
        int a;
        int b;
        int expected;

        public SumArgument(int a, int b, int expected) {
            this.a = a;
            this.b = b;
            this.expected = expected;
        }

        @Override
        public String toString() {
            return "[a : %s, b : %s, expected  : %s]".formatted(a, b, expected);
        }
    }
}