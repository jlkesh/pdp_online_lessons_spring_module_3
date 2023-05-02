package uz.pdp.springadvanced.commands;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.ParameterValidationException;
import org.springframework.shell.command.CommandHandlingResult;
import org.springframework.shell.command.annotation.ExceptionResolver;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import uz.pdp.springadvanced.annotation.NonZero;
import uz.pdp.springadvanced.service.CalculatorService;

import java.util.Arrays;
import java.util.Set;
import java.util.StringJoiner;

@ShellComponent
@RequiredArgsConstructor
public class CalculatorCommand {
    private final CalculatorService calculatorService;
    private boolean loggedIn;

    @ShellMethod(value = "Add Method Command", key = "add")
    /*@ShellMethodAvailability("availabilityForLogin")*/
    public double addTwoNumber(
            @ShellOption(value = "-n") double a,
            @ShellOption(value = "-m", defaultValue = "-10") double b) {
        return calculatorService.add(a, b);
    }

    @ShellMethod(value = "Sum Of Array", key = "sum")
    /*@ShellMethodAvailability("availabilityForLogin")*/
    public double sum(@ShellOption(arity = -1) double[] nums) {
        return Arrays.stream(nums).sum();
    }

    @ShellMethod
    /*@ShellMethodAvailability("availabilityForLogin")*/
    public double sub(@ShellOption(value = "-n") double a,
                      @ShellOption(value = "-m") @Positive double b) {
        return calculatorService.sub(a, b);
    }

    @ShellMethod
    /*@ShellMethodAvailability("availabilityForLogin")*/
    public double mul(double a, double b) {
        return calculatorService.mul(a, b);
    }

    @ShellMethod
    /*@ShellMethodAvailability("availabilityForLogin")*/
    public int div(@ShellOption(value = "-n") int a,
                   @ShellOption(value = "-m", help = "This argument can not be zero\n" + "Throws Exception if you provide zero")
                   @NonZero int b) {
        return calculatorService.div(a, b);
    }

    @ShellMethod
    public String login(@ShellOption(value = "-u") String username,
                        @ShellOption(value = "-p") String password) {
        // check db
        loggedIn = true;
        return "Successfully signed in";
    }

    @ShellMethod
    public String logout() {
        loggedIn = false;
        return "Successfully signed out";
    }

    @ShellMethodAvailability({"login"})
    public Availability availabilityForLogout() {
        return loggedIn ? Availability.unavailable(" => Logout First") : Availability.available();
    }

    @ShellMethodAvailability({"add", "sum", "sub", "mul", "div", "logout"})
    public Availability availabilityForLogin() {
        return loggedIn ? Availability.available() : Availability.unavailable(" => Login First");
    }

    @ExceptionResolver({ParameterValidationException.class})
    CommandHandlingResult errorHandler(ParameterValidationException e) {
        Set<ConstraintViolation<Object>> constraintViolations = e.getConstraintViolations();
        StringJoiner joiner = new StringJoiner("\n", "", "\n");
        for (ConstraintViolation<Object> violation : constraintViolations) {
            String arg = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            joiner.add(arg + " : " + message);
        }
        return CommandHandlingResult.of(joiner.toString(), 400);
    }

}
