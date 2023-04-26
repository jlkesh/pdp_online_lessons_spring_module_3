package uz.pdp.springadvanced;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;
import java.util.Random;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Test Class For Calculator Class")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// @DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
public class CalculatorTest {

    Calculator calculator;
    private static final Logger LOGGER = Logger.getLogger(CalculatorTest.class.getName());

    @BeforeEach
    void setUp() {
        LOGGER.info("@BeforeEach(setUp method working)");
        calculator = new Calculator(); // 1
    }

    @AfterEach
    void tearDown() {
        calculator = null;
        LOGGER.info("@AfterEach(tearDown method working)");
    }

    @BeforeAll
    static void setUpAll() {
        LOGGER.info("@BeforeAll(setUpAll method working)");
    }

    @AfterAll
    static void tearDownAll() {
        LOGGER.info("@AfterAll(tearDownAll method working)");
    }

    @Test
    @Order(1)
    @DisplayName("Test For Sum Method")
    void test_for_sum_method() {
        // LOGGER.info("(testForSum method working[2 + 3 = 5])");
        int actual = calculator.sum(2, 3); // 2
        String message = "Excepted Value And Actual Value Did not much [expected : %s, actual : %s]"
                .formatted(6, actual);
        assertNotEquals(6, actual, message);  // 3
        assertEquals(10, calculator.sum(3, 7));
    }

    @Test
    @Order(2)
    @DisplayName("Test For Div Method")
    void test_for_div_method() {
        int expected = 5;
        // LOGGER.info("(testForDiv method working[10 / 2 = 5])");
        int actual = calculator.div(10, 2); // 2
        assertEquals(expected, actual);  // 3
    }

    @Test
    @Order(3)
    @DisplayName("Test For Div Method Which Throws Exception")
    void test_for_div_method_which_throws_exception() {
        ArithmeticException e = assertThrows(ArithmeticException.class, () -> calculator.div(10, 0));
        e.printStackTrace();
    }

    @Test
    @Order(4)
    @DisplayName("Test For Div Method Which Has Timeout")
    void test_for_div_method_which_has_timeout() {
        assertTimeout(Duration.ofMillis(3000), () -> calculator.div(10, -2));
    }

    @Test
    @Disabled("This Test Method Disabled for checking @Disabled annotation")
    void ignored() {
    }

    @Test
    @DisabledIf(value = "testCondition", disabledReason = "Disabled reason")
    void disabledWithCondition() {
    }

    @Test
    @EnabledIf(value = "testCondition", disabledReason = "Enabled reason")
    void enabledWithCondition() {
    }

    boolean testCondition() { // false
        return new Random().nextBoolean();
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void shouldWorkOnWindowsOnly(){}
    @Test
    @EnabledOnOs({OS.WINDOWS, OS.LINUX})
    void shouldWorkOnWindowsOrLinuxOnly(){}

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void shouldNotWorkOnWindows(){}
    @Test
    @DisabledOnOs({OS.WINDOWS, OS.LINUX})
    void shouldNotWorkOnWindowsOrLinux(){}

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void shouldWorkOnJRE_8_Only(){}
    @Test
    @DisabledOnJre(JRE.JAVA_8)
    void shouldNotWorkOnJRE_8_Only(){}

    @Test
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_20)
    void shouldWorOnJRE_Between_8_and_20(){}
    @Test
    @DisabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_20)
    void shouldNotWorOnJRE_Between_8_and_20(){}


}
