package lotto.domain

import lotto.dto.LottoDto
import lotto.utils.ErrorMessage.*

class Lotto private constructor(
    private val numbers: List<Int>
) {

    init {
        validateLottoCount(numbers)
        validateNumberRange(numbers)
        validateDuplication(numbers)
    }

    companion object {
        const val LOTTO_NUM_COUNT: Int = 6
        private const val LOTTO_MIN_NUMBER: Int = 1
        private const val LOTTO_MAX_NUMBER: Int = 45

        @JvmStatic
        fun from(numbers: List<Int>): Lotto = Lotto(numbers)
    }

    private fun validateLottoCount(numbers: List<Int>) {
        if (numbers.size != LOTTO_NUM_COUNT) {
            throw IllegalArgumentException(LOTTO_NUM_COUNT_ERROR.getMessage())
        }
    }

    private fun validateNumberRange(numbers: List<Int>) {
        if (numbers.any { it < LOTTO_MIN_NUMBER || it > LOTTO_MAX_NUMBER }) {
            throw IllegalArgumentException(LOTTO_NUM_RANGE_ERROR.getMessage())
        }
    }

    private fun validateDuplication(numbers: List<Int>) {
        if (numbers.size != numbers.distinct().size) {
            throw IllegalArgumentException(DUPLICATE_ERROR_MESSAGE.getMessage())
        }
    }

    fun matchCountWith(winnerLotto: Lotto): Int =
        numbers.count { it in winnerLotto.numbers }

    fun contains(number: Int): Boolean =
        number in numbers

    fun toDto(): LottoDto =
        LottoDto(numbers.toList())
}