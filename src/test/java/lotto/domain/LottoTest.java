package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static lotto.utils.ErrorMessage.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> Lotto.from(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideInvalidLottoNumbers")
    void 로또_번호가_6개가_아니면_예외가_발생한다(List<Integer> numbers) {
        assertThatThrownBy(() -> Lotto.from(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(LOTTO_NUM_COUNT_ERROR.getMessage());
    }

    private static Stream<List<Integer>> provideInvalidLottoNumbers() {
        return Stream.of(
                List.of(1, 2, 3, 4, 5),
                List.of(1, 2, 3, 4, 5, 6, 7)
        );
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> Lotto.from(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 1~45가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void 로또_번호가_1_45가_아니면_예외가_발생한다(int input) {
        assertThatThrownBy(() -> Lotto.from(List.of(input, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(LOTTO_NUM_RANGE_ERROR.getMessage());
    }

    @DisplayName("당첨번호와의 일치 개수를 정확히 계산한다")
    @ParameterizedTest
    @MethodSource("provideLottos")
    void 당첨번호와의_일치_개수를_정확히_계산한다(List<Integer> mineNums, List<Integer> winNums, int expected) {
        Lotto mine = Lotto.from(mineNums);
        Lotto win = Lotto.from(winNums);
        assertThat(mine.matchCountWith(win)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideLottos() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 10, 20, 30), List.of(1, 2, 3, 4, 5, 6), 3),
                Arguments.of(List.of(7, 8, 9, 10, 11, 12), List.of(1, 2, 3, 4, 5, 6), 0),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 6), 6)
        );
    }

    @DisplayName("보너스 번호 포함 여부를 판단한다")
    @ParameterizedTest
    @CsvSource({
            "10, true",
            "13, false"
    })
    void 보너스_번호_포함_여부를_판단한다(int bonusNumber, boolean expected) {
        Lotto lotto = Lotto.from(List.of(7, 8, 9, 10, 11, 12));
        assertThat(lotto.contains(bonusNumber)).isEqualTo(expected);
    }
}
