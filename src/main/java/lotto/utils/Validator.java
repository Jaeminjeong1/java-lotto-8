package lotto.utils;

import java.util.regex.Pattern;

import static lotto.utils.ErrorMessage.EMPTY_INPUT_ERROR;
import static lotto.utils.ErrorMessage.NUMBER_FORMAT_ERROR;

public class Validator {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    public static void validateEmptyInput(String userInput) {
        if (userInput == null ||userInput.isBlank()) {
            throw new IllegalArgumentException(EMPTY_INPUT_ERROR.getMessage());
        }
    }

    public static void validateNumberFormat(String userInput) {
        if (!NUMBER_PATTERN.matcher(userInput).matches()) {
            throw new IllegalArgumentException(NUMBER_FORMAT_ERROR.getMessage());
        }
    }
}
