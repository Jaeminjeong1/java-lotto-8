package lotto.domain

import lotto.utils.ErrorMessage.*

data class PurchaseAmount private constructor(
    val userPrice: Long
) {

    init {
        validateMinimumPrice(userPrice)
        validatePriceUnit(userPrice)
    }

    companion object {
        const val LOTTO_PRICE: Int = 1000

        fun from(userPrice: Long): PurchaseAmount = PurchaseAmount(userPrice)

        private fun validateMinimumPrice(userPrice: Long) {
            if (userPrice < LOTTO_PRICE) {
                throw IllegalArgumentException(PRICE_LACK_ERROR.getMessage())
            }
        }

        private fun validatePriceUnit(userPrice: Long) {
            if (userPrice % LOTTO_PRICE != 0L) {
                throw IllegalArgumentException(PRICE_UNIT_ERROR.getMessage())
            }
        }
    }

    fun calculateLottoCount(): Long = userPrice / LOTTO_PRICE
}