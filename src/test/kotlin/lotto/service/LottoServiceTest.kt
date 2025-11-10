package lotto.service

import lotto.domain.Result
import lotto.dto.LottoDto
import lotto.utils.ErrorMessage.BONUS_NUM_CONTAINS_ERROR
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class LottoServiceTest {

    private val lottoService = LottoService()

    @DisplayName("구매금액/1000 = 생성 장수")
    @ParameterizedTest
    @ValueSource(longs = [1000, 3000, 15000])
    fun `로또 장수 테스트`(userPrice: Long) {
        val lottos: List<LottoDto> = lottoService.generateLottos(userPrice)
        assertEquals((userPrice / 1000).toInt(), lottos.size)
    }

    @Test
    @DisplayName("trim된 정수로 잘 파싱하여 LottoDto로 반환")
    fun `파싱 테스트`() {
        val parsed = listOf(" 1", "2 ", " 3 ", "4", "5", "6")
        val winner: LottoDto = lottoService.generateWinnerLotto(parsed)

        val numbers = winner.numbers
        assertEquals(listOf(1, 2, 3, 4, 5, 6), numbers)
    }

    @Test
    @DisplayName("여러 로또에 대해 등수를 계산하고, 등수별 개수를 집계한다")
    fun `로또 등수 계산 및 개수 집계`() {
        val mine1 = LottoDto(listOf(1, 2, 3, 4, 5, 6))    // 6개 일치 -> FIRST
        val mine2 = LottoDto(listOf(1, 2, 3, 10, 11, 12)) // 3개 일치 -> FIFTH
        val mine3 = LottoDto(listOf(7, 8, 9, 10, 11, 12)) // 0개 + 보너스(7) 포함 -> MISS
        val lottos = listOf(mine1, mine2, mine3)

        val winner = LottoDto(listOf(1, 2, 3, 4, 5, 6)) // 당첨 로또
        val bonus = 7

        val results = lottoService.calculateResults(lottos, winner, bonus)
        assertEquals(listOf(Result.FIRST, Result.FIFTH, Result.MISS), results)

        val summary: Map<Result, Long> = lottoService.summarizeResults(results)
        assertEquals(1L, summary[Result.FIRST])
        assertEquals(1L, summary[Result.FIFTH])
        assertEquals(1L, summary[Result.MISS])
    }

    @DisplayName("5등 2개만 당첨된 경우 수익률을 계산한다")
    @ParameterizedTest
    @MethodSource("provideFifthOnlyCase")
    fun `수익률 계산 테스트`(summary: Map<Result, Long>, totalCount: Int, expectedRate: Double) {
        val rate = lottoService.calculateProfitRate(summary, totalCount)
        assertEquals(expectedRate, rate)
    }

    @DisplayName("보너스 번호가 당첨 번호에 포함되면 예외가 발생한다.")
    @Test
    fun `보너스 번호 중복 테스트`() {
        val lottoDto = LottoDto(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = 6

        val ex = assertThrows<IllegalArgumentException> {
            lottoService.validateAndReturnBonusNum(lottoDto, bonusNumber)
        }
        assertEquals(BONUS_NUM_CONTAINS_ERROR.getMessage(), ex.message)
    }

    companion object {
        @JvmStatic
        fun provideFifthOnlyCase(): Stream<Array<Any>> =
            Stream.of(
                arrayOf(mapOf(Result.FIFTH to 2L), 2, 500.0)
            )
    }
}
