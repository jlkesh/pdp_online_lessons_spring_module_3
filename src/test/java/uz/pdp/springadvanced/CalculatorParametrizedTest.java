package uz.pdp.springadvanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.logging.Logger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorParametrizedTest {
    Calculator calculator;
    Logger log = Logger.getLogger(CalculatorParametrizedTest.class.getName());

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "PDP"})
    void stringParametrizedTest(String param) {
        log.info(param);
    }

    @ParameterizedTest(name = "Calculator sum test : index => {index}, argument : {arguments}")
    @ValueSource(ints = {3, 4, 1, 2, 9})
    void sum(int value) {
        assertEquals((value + value), calculator.sum(value, value));
    }

    @ParameterizedTest(name = "Calculator sum test : index => {index}, argument : {arguments}")
    @MethodSource("calculatorSumSourceStream")
    void calculatorMethodSource(CalculatorSumSource source) {
        assertEquals(source.expected, calculator.sum(source.a, source.b));
    }

    @ParameterizedTest(name = "Calculator sum test : index => {index}, argument : {arguments}")
    @CsvSource({
            "4, 2, 6",
            "-2, 2, 0",
            "0, -123, -123"
    })
    void calculatorCsvSource(int a, int b, int expected) {
        assertEquals(expected, calculator.sum(a, b));
    }

    @ParameterizedTest(name = "Calculator sum test : index => {index}, argument : {arguments}")
    @CsvFileSource(resources = "/calculator.csv", useHeadersInDisplayName = true, numLinesToSkip = 2)
    void calculatorCsvFileSource(int a, int b, int expected) {
        assertEquals(expected, calculator.sum(a, b));
    }

    @Test
    void div() {
    }


    static Stream<CalculatorSumSource> calculatorSumSourceStream() {
        return Stream.of(
                new CalculatorSumSource(4, 2, 6),
                new CalculatorSumSource(-2, 2, 0),
                new CalculatorSumSource(0, -123, -123)
        );
    }

    public static class CalculatorSumSource {
        int a;
        int b;
        int expected;

        public CalculatorSumSource(int a, int b, int expected) {
            this.a = a;
            this.b = b;
            this.expected = expected;
        }

        @Override
        public String toString() {
            return "[a : %s, b : %s, expected : %s]"
                    .formatted(a, b, expected);
        }
    }


}