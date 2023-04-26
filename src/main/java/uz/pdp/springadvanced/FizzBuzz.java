package uz.pdp.springadvanced;

public class FizzBuzz {
    public String convert(int a) {
        if (a % 3 == 0 && a % 5 == 0)
            return "FizzBuzz";
        if (a % 3 == 0)
            return "Fizz";
        if (a % 5 == 0)
            return "Buzz";
        return String.valueOf(a);
    }
}
