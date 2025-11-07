package lotto.view

import lotto.domain.Result
import lotto.dto.LottoDto

class OutputView private constructor() {

    companion object {
        private const val LOTTO_BUY_MESSAGE = "개를 구매했습니다."
        private const val WINNING_STATISTICS = "\n당첨 통계"
        private const val LINING = "---"
        private const val RATE_OF_RETURN_PRINT = "총 수익률은 %.1f%%입니다.%n"

        fun create(): OutputView = OutputView()
    }

    fun printLottos(lottos: List<LottoDto>) {
        println("\n${lottos.size}$LOTTO_BUY_MESSAGE")

        lottos.forEach { dto ->
            println(dto.numbers.sorted())
        }
        println()
    }

    fun printStatistics(statistics: Map<Result, Long>) {
        println(WINNING_STATISTICS)
        println(LINING)

        val ordered = listOf(
            Result.FIFTH,
            Result.FOURTH,
            Result.THIRD,
            Result.SECOND,
            Result.FIRST
        )

        ordered.forEach { result ->
            val count = statistics.getOrDefault(result, 0L)
            print(ResultFormatter.line(result, count))
        }
    }

    fun printProfitRate(profitRate: Double) {
        print(String.format(RATE_OF_RETURN_PRINT, profitRate))
    }

    fun printErrorMessage(e: IllegalArgumentException) {
        println(e.message)
    }
}