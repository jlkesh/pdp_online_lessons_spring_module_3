package uz.pdp.springadvanced;

import com.sun.jdi.request.DuplicateRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

class JunitAnnotationSampleTest {

    JunitAnnotationSample junitAnnotationSample;

    @BeforeEach
    void setUp() {
        junitAnnotationSample = new JunitAnnotationSample();
    }

    @Test
    void m1() {
        assertTimeout(Duration.ofSeconds(4), () -> junitAnnotationSample.m1(-1));
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void testShouldWorkOnMacOS() {

    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void testShouldWorkOnMacOSOrLinux() {
    }

    @DisplayName("Ushbu test method Mac va Linux da ishlamasligi kerak")
    @Test
    @DisabledOnOs({OS.MAC, OS.LINUX})
    void testShouldNotWorkOnMacOSAndLinux() {
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_16)
    void testShouldWorkUpToJre1_8() {
    }

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    void testShouldWorkOnJre17() {
    }

    @Test
    @Disabled(value = "@Disabled Annotation ni test qilish uchun yaratilgan test method")
    void testShouldNotWork() {
    }


    @Test
    @DisabledIf(value = "uz.pdp.springadvanced.Calculator#testCondition", disabledReason = "Disabled because Random class returned true value")
    //@EnabledIf("condition")
    void testDisableIf() {

    }


    boolean condition() {
        return new Random().nextBoolean();
    }


}