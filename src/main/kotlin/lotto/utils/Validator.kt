package lotto.utils

import lotto.domain.Lotto.Companion.LOTTO_NUM_COUNT
import java.util.regex.Pattern
import lotto.utils.ErrorMessage.*

object Validator {

    private val NUMBER_PATTERN: Pattern = Pattern.compile("\\d+")

    fun validateEmptyInput(userInput: String?) {
        if (userInput.isNullOrBlank()) {
            throw IllegalArgumentException(EMPTY_INPUT_ERROR.getMessage());
        }
    }

    fun validateNumberFormat(userInput: String?) {
        if(!NUMBER_PATTERN.matcher(userInput).matches()) {
            throw IllegalArgumentException(NUMBER_FORMAT_ERROR.getMessage());
        }
    }

    fun validateParsing(userInput: List<String>) {
        if (userInput.size != LOTTO_NUM_COUNT) {
            throw IllegalArgumentException(LOTTO_NUM_COUNT_ERROR.getMessage())
        }
    }

}
