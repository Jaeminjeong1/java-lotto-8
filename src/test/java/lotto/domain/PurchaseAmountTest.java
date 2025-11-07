package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.utils.ErrorMessage.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PurchaseAmountTest {

    @DisplayName("구매 금액이 1000원 미만 또는 음수면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 999 })
    void 로또_한장_가격보다_적으면_예외(int input) {
        assertThatThrownBy(() -> PurchaseAmount.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(PRICE_LACK_ERROR.getMessage());
    }

    @DisplayName("구매 금액이 1000원 단위가 아니면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {1500, 1999, 12345})
    void 구매금액이_1000원_단위_아닌_경우(int input) {
        assertThatThrownBy(() -> PurchaseAmount.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(PRICE_UNIT_ERROR.getMessage());
    }

    @DisplayName("구매 금액이 1000원 단위면 `로또수 = 금액 / 1000`")
    @ParameterizedTest
    @ValueSource(ints = { 1000, 8000, 15000 })
    void 로또_개수_계산_검증(int input) {
        PurchaseAmount amount = PurchaseAmount.from(input);
        assertThat(amount.calculateLottoCount()).isEqualTo(input / 1000);
    }

}
