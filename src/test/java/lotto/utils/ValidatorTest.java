package lotto.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.utils.ErrorMessage.EMPTY_INPUT_ERROR;
import static lotto.utils.ErrorMessage.NUMBER_FORMAT_ERROR;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ValidatorTest {

    @DisplayName("입력이 null 또는 공백이면 예외가 발생한다")
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " ", "   "})
    void 입력_공백_빈값_테스트(String input) {
        assertThatThrownBy(() -> Validator.validateEmptyInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(EMPTY_INPUT_ERROR.getMessage());
    }

    @DisplayName("숫자가 아닌 문자를 포함하면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"a", "12a", "1 2", "1,2", "1.0"})
    void 숫자_형식_테스트(String input) {
        assertThatThrownBy(() -> Validator.validateNumberFormat(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NUMBER_FORMAT_ERROR.getMessage());
    }
}
