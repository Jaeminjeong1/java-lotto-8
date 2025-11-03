package lotto.view;

import lotto.domain.Result;

public final class ResultFormatter {

    private static final String STATISTICS_RESULT = "%d개 일치%s (%,d원) - %d개%n";
    private static final String BONUS_MATCHED_SUFFIX = ", 보너스 볼 일치";

    private ResultFormatter() {}

    public static String line(Result result, long count) {
        String bonus = result.isBonusMatch() ? BONUS_MATCHED_SUFFIX : "";
        return String.format(
                STATISTICS_RESULT,
                result.getMatchCount(),
                bonus,
                result.getPrice(),
                count
        );
    }
}
