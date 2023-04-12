import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {
    public static final String SPECIAL_CHARACTER_IS_ABSENT_ERROR_MESSAGE = "Password must contain at least one special character";
    final static String CAPITAL_LETTER_IS_ABSENT_ERROR_MESSAGE = "Password must contain at least one capital letter";
    final static String TOO_LITTLE_NUMBER_COUNT_ERROR_MESSAGE = "The password must contain at least 2 numbers";
    final static String TOO_SHORT_PASSWORD_ERROR_MESSAGE = "Password must be at least 8 characters";

    private String password;
    private List<String> errors;

    public List<String> validate(String password) {
        this.password = password;
        errors = new ArrayList<>();
        validateLength();
        validateNumbersCount();
        validateCapitalLetter();
        validateSpecialCharacter();
        return errors;
    }

    private void validateLength() {
        if (password.length() < 8) {
            errors.add(TOO_SHORT_PASSWORD_ERROR_MESSAGE);
        }
    }

    private void validateNumbersCount() {
        char[] letters = password.toCharArray();
        int counter = 0;
        for (char letter : letters) if (Character.isDigit(letter)) counter++;
        if (counter < 2) {
            errors.add(TOO_LITTLE_NUMBER_COUNT_ERROR_MESSAGE);
        }
    }

    private void validateCapitalLetter() {
        char[] letters = password.toCharArray();
        int counter = 0;
        for (char letter : letters) if (Character.isUpperCase(letter)) counter++;
        if (counter < 1) {
            errors.add(CAPITAL_LETTER_IS_ABSENT_ERROR_MESSAGE);
        }
    }

    private void validateSpecialCharacter() {
        char[] letters = password.toCharArray();
        int counter = 0;
        for (char letter : letters) if (!Character.isAlphabetic(letter)) counter++;
        if (counter < 1) {
            errors.add(SPECIAL_CHARACTER_IS_ABSENT_ERROR_MESSAGE);
        }
    }
}
