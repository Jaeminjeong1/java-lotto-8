package lotto.utils

import lotto.domain.Lotto.LOTTO_NUM_COUNT
import java.util.regex.Pattern
import lotto.utils.ErrorMessage.*

object Validator {

    private val NUMBER_PATTERN: Pattern = Pattern.compile("\\d+")

    @JvmStatic
    fun validateEmptyInput(userInput: String?) {
        if (userInput.isNullOrBlank()) {
            throw IllegalArgumentException(EMPTY_INPUT_ERROR.getMessage());
        }
    }

    @JvmStatic
    fun validateNumberFormat(userInput: String?) {
        if(!NUMBER_PATTERN.matcher(userInput).matches()) {
            throw IllegalArgumentException(NUMBER_FORMAT_ERROR.getMessage());
        }
    }

    @JvmStatic
    fun validateParsing(userInput: List<String>) {
        if (userInput.size != LOTTO_NUM_COUNT) {
            throw IllegalArgumentException(LOTTO_NUM_COUNT_ERROR.getMessage())
        }
    }

}


//public class Validator {
//
//    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
//
//    public static void validateEmptyInput(String userInput) {
//        if (userInput == null || userInput.isBlank()) {
//            throw new IllegalArgumentException(EMPTY_INPUT_ERROR.getMessage());
//        }
//    }
//
//    public static void validateNumberFormat(String userInput) {
//        if (!NUMBER_PATTERN.matcher(userInput).matches()) {
//            throw new IllegalArgumentException(NUMBER_FORMAT_ERROR.getMessage());
//        }
//    }
//
//    public static void validateParsing(List<String> userInput) {
//        if (userInput.size() != LOTTO_NUM_COUNT) {
//            throw new IllegalArgumentException(LOTTO_NUM_COUNT_ERROR.getMessage());
//        }
//    }
//}
