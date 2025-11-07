package lotto.view

import lotto.domain.Result

object ResultFormatter {

    private const val STATISTICS_RESULT = "%d개 일치%s (%,d원) - %d개%n"
    private const val BONUS_MATCHED_SUFFIX = ", 보너스 볼 일치"

    fun line(result: Result, count: Long): String {
        var bonus = ""
        if (result.isBonusMatch) {
            bonus = BONUS_MATCHED_SUFFIX
        }
        return String.format(
            STATISTICS_RESULT,
            result.matchCount,
            bonus,
            result.price,
            count
        )
    }
}