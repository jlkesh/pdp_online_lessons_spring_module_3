package uz.pdp.springadvanced;

import org.junit.jupiter.api.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calculator klasining methodlari uchun Test Klas")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CalculatorTest {
    Calculator calculator;
    static Logger log = Logger.getLogger(CalculatorTest.class.getName());


    @BeforeAll
    static void setUpAll() {
        log.info("@BeforeAll Called");
    }

    @AfterAll
    static void tearDownAll() {
        log.info("@AfterAll Called");
    }

    @BeforeEach
    void setUp() {
        log.info("Calculator Initializing");
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
        calculator = null;
        log.info("Calculator Object destroyed");
    }

    @Test
    @DisplayName("Calculator klasining sum methodi uchun test method")
    void test_for_sum_method_of_calculator_class() {
        log.info("Sum Method Test is being processed");
        assertEquals(10, calculator.sum(8, 2));
        assertEquals(12, calculator.sum(11, 1));
        assertEquals(13, calculator.sum(15, -2));
        assertEquals(1, calculator.sum(12, -11));
        assertNotEquals(12, calculator.sum(3, 4));
    }

    @Test
    @DisplayName("Calculator klasining div methodi uchun test method")
    void test_for_div_method_of_calculator_class() {
        log.info("Div Method Test is being processed");
        assertEquals(0, calculator.div(0, 2));
        assertEquals(12, calculator.div(12, 1));
        assertEquals(-7, calculator.div(14, -2));
        assertEquals(4, calculator.div(12, 3));
        assertThrows(ArithmeticException.class, () -> calculator.div(12, 0));
    }
}