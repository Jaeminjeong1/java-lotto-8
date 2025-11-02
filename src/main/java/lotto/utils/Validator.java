package lotto.utils;

import java.util.regex.Pattern;

import static lotto.domain.Lotto.LOTTO_NUM_COUNT;
import static lotto.utils.ErrorMessage.*;

public class Validator {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    public static void validateEmptyInput(String userInput) {
        if (userInput == null || userInput.isBlank()) {
            throw new IllegalArgumentException(EMPTY_INPUT_ERROR.getMessage());
        }
    }

    public static void validateNumberFormat(String userInput) {
        if (!NUMBER_PATTERN.matcher(userInput).matches()) {
            throw new IllegalArgumentException(NUMBER_FORMAT_ERROR.getMessage());
        }
    }

    public static void validateParsing(String[] userInput) {
        if (userInput.length != LOTTO_NUM_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUM_COUNT_ERROR.getMessage());
        }
    }
}
