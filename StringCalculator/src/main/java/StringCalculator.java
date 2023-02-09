import java.util.StringTokenizer;

public class StringCalculator {
    String numbers;
    String delimiters = ",\n";
    public int add(String numbers) {
        this.numbers = numbers;

        if (areNumbersNonEmpty()) makeNumbersTokenizable();

        if (areNumbersWithDelimiterDefinition()) setDelimitersAndNumbers();

        return calculateSum();
    }

    private boolean areNumbersNonEmpty() {
        return !numbers.isBlank();
    }

    private void makeNumbersTokenizable() {
        numbers += " ";
    }

    private boolean areNumbersWithDelimiterDefinition() {
        return numbers.startsWith("//");
    }

    private void setDelimitersAndNumbers() {
        String[] delimitersAndNumbers = numbers.substring(2).split("\n", 2);
        delimiters = delimitersAndNumbers[0];
        numbers = delimitersAndNumbers[1];
    }

    private int calculateSum() {
        int sum = 0;
        StringTokenizer tokens = new StringTokenizer(numbers, delimiters);
        int counter = 1;
        while (tokens.hasMoreTokens()) {
            counter++;
            String trimmedNumber = tokens.nextToken().trim();
            if (trimmedNumber.isBlank()) {
                throw new InvalidNumbersException();
            }
            sum += parseNumber(counter, trimmedNumber);
        }
        return sum;
    }

    private int parseNumber(int counter, String trimmedNumber) {
        try {
            return Integer.parseInt(trimmedNumber);
        } catch (NumberFormatException e) {
            throwBecauseNumbersHaveInvalidDelimiter(counter, trimmedNumber);
            return -1;
        }
    }

    private void throwBecauseNumbersHaveInvalidDelimiter(int counter, String trimmedNumber) {
        StringBuilder illegalCharacters = new StringBuilder();
        char[] chars = trimmedNumber.toCharArray();
        for (Character character : chars) {
            if (!Character.isDigit(character)) {
                illegalCharacters.append(character);
            }
        }
        String illegalChars = illegalCharacters.toString();
        throw new InvalidNumbersException("'" + delimiters + "' expected but '" + illegalChars + "' found at position " + counter + ".");
    }

    public static class InvalidNumbersException extends RuntimeException {

        public InvalidNumbersException() {
            super();
        }

        public InvalidNumbersException(String message) {
            super(message);
        }
    }
}
