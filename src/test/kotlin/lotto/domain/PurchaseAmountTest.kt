package lotto.domain

import lotto.utils.ErrorMessage.PRICE_LACK_ERROR
import lotto.utils.ErrorMessage.PRICE_UNIT_ERROR
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals

class PurchaseAmountTest {

    @DisplayName("구매 금액이 1000원 미만 또는 음수면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 999])
    fun `구매 금액이 1000원 미만 또는 음수면 예외가 발생한다`(input: Int) {
        val exception = assertThrows<IllegalArgumentException> {
            PurchaseAmount.from(input.toLong())
        }
        assertEquals(PRICE_LACK_ERROR.getMessage(), exception.message)
    }

    @DisplayName("구매 금액이 1000원 단위가 아니면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = [1500, 1999, 12345])
    fun `구매 금액이 1000원 단위가 아니면 예외가 발생한다`(input: Int) {
        val exception = assertThrows<IllegalArgumentException> {
            PurchaseAmount.from(input.toLong())
        }
        assertEquals(PRICE_UNIT_ERROR.getMessage(), exception.message)
    }

    @DisplayName("구매 금액이 1000원 단위면 로또 수 = 금액 / 1000")
    @ParameterizedTest
    @ValueSource(ints = [1000, 8000, 15000])
    fun `구매 금액이 1000원 단위면 로또 수를 정확히 계산한다`(input: Int) {
        val amount = PurchaseAmount.from(input.toLong())
        assertEquals(input / 1000L, amount.calculateLottoCount())
    }
}
