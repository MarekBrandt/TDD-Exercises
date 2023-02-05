import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzTest {
    FizzBuzz fizzBuzz;

    @BeforeEach
    void setup() {
        fizzBuzz = new FizzBuzz();
    }

    @ParameterizedTest
    @MethodSource("provideNumbersAndStrings")
    void shouldReturnNumberAsString_GivenNumberNotMultiplesOf3Or5(int value, String valueAsString) {
        String result = fizzBuzz.fizzBuzz(value);
        assertEquals(valueAsString, result);
    }

    @Test
    void shouldReturnFizz_GivenMultiplesOf3() {
        String result = fizzBuzz.fizzBuzz(3);
        assertEquals("Fizz", result);

        String result2 = fizzBuzz.fizzBuzz(6);
        assertEquals("Fizz", result2);
    }

    @Test
    void shouldReturnBuzz_GivenMultiplesOf5() {
        String result = fizzBuzz.fizzBuzz(5);
        assertEquals("Buzz", result);
    }

    @Test
    void shouldReturnFizzBuzz_GivenMultiplesBothOf3And5() {
        String result = fizzBuzz.fizzBuzz(15);
        assertEquals("FizzBuzz", result);
    }
    public static Stream<Arguments> provideNumbersAndStrings() {
        return Stream.of(
                Arguments.of(16, "16"),
                Arguments.of(13, "13")
        );
    }
}
