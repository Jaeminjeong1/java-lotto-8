package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    @DisplayName("일치 개수와 보너스 여부에 따라 올바른 등수를 반환한다")
    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, false, FOURTH",
            "3, false, FIFTH",
            "2, true, MISS",
            "0, false, MISS"
    })
    void 등수_반환_테스트(int matchCount, boolean bonus, Result expected) {
        assertThat(Result.from(matchCount, bonus)).isEqualTo(expected);
    }
}
