package lotto.service

import lotto.domain.Lotto
import lotto.domain.PurchaseAmount
import lotto.domain.PurchaseAmount.Companion.LOTTO_PRICE
import lotto.domain.Result
import lotto.dto.LottoDto
import lotto.utils.ErrorMessage.*
import lotto.utils.RandomGenerator

class LottoService {

    fun generateLottos(userPrice: Long): List<LottoDto> {
        val lottoCount = PurchaseAmount.from(userPrice).calculateLottoCount()

        return List(lottoCount.toInt()) {
            val numbers = RandomGenerator.generateRandomNum()
            Lotto.from(numbers).toDto()
        }
    }

    fun generateWinnerLotto(parsedWinnerNumbers: List<String>): LottoDto {
        val numbers = parsedWinnerNumbers
            .map { it.trim().toInt() }

        val lotto = Lotto.from(numbers)
        return lotto.toDto()
    }

    fun calculateResults(lottos: List<LottoDto>, winnerLotto: LottoDto, bonusNum: Int): List<Result> {
        val winner = Lotto.from(winnerLotto.numbers)

        return lottos.map { dto ->
            val lotto = Lotto.from(dto.numbers)
            findResult(lotto.matchCountWith(winner), lotto.contains(bonusNum))
        }
    }

    private fun findResult(matchCount: Int, bonusMatch: Boolean): Result =
        Result.from(matchCount, bonusMatch)

    fun summarizeResults(results: List<Result>): Map<Result, Long> =
        results.groupingBy { it }.eachCount().mapValues { it.value.toLong() }

    fun calculateProfitRate(statistics: Map<Result, Long>, totalLottoCount: Int): Double {
        val totalPrize = statistics.entries
            .sumOf { (result, count) -> result.price * count }

        val totalCost = totalLottoCount.toLong() * LOTTO_PRICE

        return (totalPrize.toDouble() / totalCost) * 100
    }

    fun validateAndReturnBonusNum(winnerDto: LottoDto, bonusNumber: Int): Int {
        if (try { winnerDto.numbers.contains(bonusNumber) } catch (_: Throwable) { winnerDto.numbers.contains(bonusNumber) }) {
            throw IllegalArgumentException(BONUS_NUM_CONTAINS_ERROR.getMessage())
        }
        return bonusNumber
    }

}