package lotto.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static lotto.utils.ErrorMessage.*;
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

    @DisplayName("배열 길이가 6이 아니면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 5, 7})
    void 배열_크기_테스트(int length) {
        List<String> input = new ArrayList<>(length);
        assertThatThrownBy(() -> Validator.validateParsing(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_NUM_COUNT_ERROR.getMessage());
    }
}
