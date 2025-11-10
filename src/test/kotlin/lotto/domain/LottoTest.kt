package lotto.domain

import lotto.utils.ErrorMessage.LOTTO_NUM_COUNT_ERROR
import lotto.utils.ErrorMessage.LOTTO_NUM_RANGE_ERROR
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.*
import java.util.stream.Stream
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LottoTest {

    @Test
    fun `로또 번호의 개수가 6개가 넘어가면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto.from(listOf(1, 2, 3, 4, 5, 6, 7))
        }
    }

    @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideInvalidLottoNumbers")
    fun `로또 번호가 6개가 아니면 예외가 발생한다`(numbers: List<Int>) {
        val ex = assertThrows<IllegalArgumentException> {
            Lotto.from(numbers)
        }

        assertTrue(ex.message?.contains(LOTTO_NUM_COUNT_ERROR.getMessage()) == true)
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    fun `로또 번호에 중복된 숫자가 있으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto.from(listOf(1, 2, 3, 4, 5, 5))
        }
    }

    @DisplayName("로또 번호가 1~45가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = [0, 46])
    fun `로또 번호가 1_45가 아니면 예외가 발생한다`(input: Int) {
        val ex = assertThrows<IllegalArgumentException> {
            Lotto.from(listOf(input, 2, 3, 4, 5, 6))
        }
        assertTrue(ex.message?.contains(LOTTO_NUM_RANGE_ERROR.getMessage()) == true)
    }

    @DisplayName("당첨번호와의 일치 개수를 정확히 계산한다")
    @ParameterizedTest
    @MethodSource("provideLottos")
    fun `당첨번호와의 일치 개수를 정확히 계산한다`(
        mineNums: List<Int>,
        winNums: List<Int>,
        expected: Int
    ) {
        val mine = Lotto.from(mineNums)
        val win = Lotto.from(winNums)
        assertEquals(expected, mine.matchCountWith(win))
    }

    @DisplayName("보너스 번호 포함 여부를 판단한다")
    @ParameterizedTest
    @CsvSource(
        "10, true",
        "13, false"
    )
    fun `보너스 번호 포함 여부를 판단한다`(bonusNumber: Int, expected: Boolean) {
        val lotto = Lotto.from(listOf(7, 8, 9, 10, 11, 12))
        assertEquals(expected, lotto.contains(bonusNumber))
    }

    companion object {
        @JvmStatic
        fun provideInvalidLottoNumbers(): Stream<List<Int>> =
            Stream.of(
                listOf(1, 2, 3, 4, 5),
                listOf(1, 2, 3, 4, 5, 6, 7)
            )

        @JvmStatic
        fun provideLottos(): Stream<Arguments> =
            Stream.of(
                Arguments.of(listOf(1, 2, 3, 10, 20, 30), listOf(1, 2, 3, 4, 5, 6), 3),
                Arguments.of(listOf(7, 8, 9, 10, 11, 12), listOf(1, 2, 3, 4, 5, 6), 0),
                Arguments.of(listOf(1, 2, 3, 4, 5, 6),    listOf(1, 2, 3, 4, 5, 6), 6)
            )
    }
}
