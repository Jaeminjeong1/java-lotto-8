package lotto.utils

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullSource
import org.junit.jupiter.params.provider.ValueSource

class ValidatorTest {

    @DisplayName("입력이 null 또는 공백이면 예외가 발생한다")
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = ["", " ", "   "])
    fun `입력이 null 또는 공백이면 예외가 발생한다`(input: String?) {
        assertThatThrownBy { Validator.validateEmptyInput(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(ErrorMessage.EMPTY_INPUT_ERROR.getMessage())
    }

    @DisplayName("숫자가 아닌 문자를 포함하면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = ["a", "12a", "1 2", "1,2", "1.0"])
    fun `숫자가 아닌 문자를 포함하면 예외가 발생한다`(input: String) {
        assertThatThrownBy { Validator.validateNumberFormat(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(ErrorMessage.NUMBER_FORMAT_ERROR.getMessage())
    }

    @DisplayName("배열 길이가 6이 아니면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = [0, 5, 7])
    fun `배열 길이가 6이 아니면 예외가 발생한다`(length: Int) {
        val input: List<String> = List(length) { "" }

        assertThatThrownBy { Validator.validateParsing(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(ErrorMessage.LOTTO_NUM_COUNT_ERROR.getMessage())
    }
}
