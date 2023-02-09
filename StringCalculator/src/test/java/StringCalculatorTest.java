import jdk.jfr.StackTrace;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @BeforeEach
    void setup() {
        stringCalculator = new StringCalculator();
    }
    @Test
    void shouldReturn0_whenEmptyStringProvided() {
        int sum = stringCalculator.add("");
        assertEquals(0, sum);
    }

    @Test
    void shouldReturnGivenNumber_whenOneNumberProvided() {
        int sum = stringCalculator.add("1");
        assertEquals(1, sum);

        sum = stringCalculator.add("2");
        assertEquals(2, sum);
    }

    @Test
    void shouldReturnSumOfNumbers_whenTwoNumbersProvided() {
        int sum = stringCalculator.add("1,2");
        assertEquals(3, sum);

        sum = stringCalculator.add("10, 20");
        assertEquals(30, sum);
    }

    @Test
    void shouldReturnSum_whenAnyNumbersOfNumbersProvided() {
        int sum = stringCalculator.add("1, 2, 3");
        assertEquals(6, sum);

        sum = stringCalculator.add("10, 20, 30, 10");
        assertEquals(70, sum);
    }

    @Test
    void shouldHandleBothCommaAndNewlineSeparator() {
        int sum = stringCalculator.add("1,2\n3");
        assertEquals(6, sum);

        sum = stringCalculator.add("2,\n3");
        assertEquals(5, sum);
    }

    @Test
    void shouldThrowException_ifSeparatorAtTheEnd() {
        assertThrows(StringCalculator.InvalidNumbersException.class, () -> {
            stringCalculator.add("1,2,");
        });
    }

    @Test
    void shouldHandleOtherDelimeters_whenDelimeterProvided() {
        int sum = stringCalculator.add("//;\n1;3");
        assertEquals(4, sum);

        sum = stringCalculator.add("//|\n1|2|3");
        assertEquals(6, sum);

        sum = stringCalculator.add("//sep\n2sep5");
        assertEquals(7, sum);
    }

    @Test
    void shouldThrowException_whenDelimeterProvidedButNumbersSeparatedDifferently() {
        Exception exception = assertThrows(StringCalculator.InvalidNumbersException.class, () -> {
            stringCalculator.add("//|\n1|2,3");
        });

        assertEquals("'|' expected but ',' found at position 3.", exception.getMessage());
    }
}
