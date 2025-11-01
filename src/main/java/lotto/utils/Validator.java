package lotto.utils;

import static lotto.utils.ErrorMessage.EMPTY_INPUT_ERROR;

public class Validator {

    public static void validateEmptyInput(String userInput) {
        if (userInput == null ||userInput.isBlank()) {
            throw new IllegalArgumentException(EMPTY_INPUT_ERROR.getMessage());
        }
    }
}
