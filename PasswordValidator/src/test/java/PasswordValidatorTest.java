import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordValidatorTest {

    PasswordValidator passwordValidator;

    @BeforeEach
    void setup() {
        passwordValidator = new PasswordValidator();
    }

    @Test
    void shouldContainPasswordLengthErrorMessage_whenPasswordIsNot8CharactersLong() {
        List<String> errors = passwordValidator.validate("123");
        assertThat(errors).contains(PasswordValidator.TOO_SHORT_PASSWORD_ERROR_MESSAGE);
    }

    @Test
    void shouldNotContainPasswordLengthErrorMessage_whenPasswordIs8CharactersLong() {
        List<String> errors = passwordValidator.validate("abcdefgh");
        assertThat(errors).doesNotContain(PasswordValidator.TOO_SHORT_PASSWORD_ERROR_MESSAGE);
    }

    @Test
    void shouldNotContainPasswordLengthErrorMessage_whenPasswordLongerThan8Characters() {
        List<String> errors = passwordValidator.validate("1234567891011");
        assertThat(errors).doesNotContain(PasswordValidator.TOO_SHORT_PASSWORD_ERROR_MESSAGE);
    }

    @Test
    void shouldContainNumberCountErrorMessage_whenPasswordContainsLessThan2Numbers() {
        List<String> errors = passwordValidator.validate("abc");
        assertThat(errors).contains(PasswordValidator.TOO_LITTLE_NUMBER_COUNT_ERROR_MESSAGE);
    }

    @Test
    void shouldNotContainNumberCountErrorMessage_whenPasswordContainsAtLeast2Numbers() {
        List<String> errors = passwordValidator.validate("123");
        assertThat(errors).doesNotContain(PasswordValidator.TOO_LITTLE_NUMBER_COUNT_ERROR_MESSAGE);

        errors = passwordValidator.validate("12");
        assertThat(errors).doesNotContain(PasswordValidator.TOO_LITTLE_NUMBER_COUNT_ERROR_MESSAGE);
    }

    @Test
    void shouldContainMoreErrorMessages_whenMorePasswordErrors() {
        List<String> errors = passwordValidator.validate("a1");
        assertThat(errors).contains(
                PasswordValidator.TOO_LITTLE_NUMBER_COUNT_ERROR_MESSAGE,
                PasswordValidator.TOO_SHORT_PASSWORD_ERROR_MESSAGE);
    }

    @Test
    void shouldContainCapitalLetterAbsentErrorMessage_whenCapitalLetterAbsent() {
        List<String> errors = passwordValidator.validate("without_capital_letter");
        assertThat(errors).contains(PasswordValidator.CAPITAL_LETTER_IS_ABSENT_ERROR_MESSAGE);
    }

    @Test
    void shouldNotContainCapitalLetterAbsentErrorMessage_whenCapitalLetterPresent() {
        List<String> errors = passwordValidator.validate("With_capital_letter");
        assertThat(errors).doesNotContain(PasswordValidator.CAPITAL_LETTER_IS_ABSENT_ERROR_MESSAGE);
    }

    @Test
    void shouldContainSpecialCharacterAbsentErrorMessage_whenSpecialCharacterAbsent() {
        List<String> errors = passwordValidator.validate("withoutSpecialCharacter");
        assertThat(errors).contains(PasswordValidator.SPECIAL_CHARACTER_IS_ABSENT_ERROR_MESSAGE);
    }

    @Test
    void shouldNotContainSpecialCharacterAbsentErrorMessage_whenSpecialCharacterPresent() {
        List<String> errors = passwordValidator.validate("with#SpecialCharacter");
        assertThat(errors).doesNotContain(PasswordValidator.SPECIAL_CHARACTER_IS_ABSENT_ERROR_MESSAGE);
    }
}
