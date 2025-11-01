package lotto.view;

import lotto.domain.Result;
import lotto.dto.LottoDto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String WINNING_STATISTICS = "\n당첨 통계";
    private static final String LINING = "-----------------";
    private static final String STATISTICS_RESULT = "%d개 일치%s (%d원) - %d개%n";
    private static final String BONUS_NOT_MATCHED = ", 보너스 볼 일치";
    private static final String RATE_OF_RETURN_PRINT = "총 수익률은 %.2f%%입니다.%n";


    private OutputView() {}
    public static OutputView create() {
        return new OutputView();
    }

    public void printLottos(List<LottoDto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");

        for (LottoDto dto : lottos) {
            System.out.println(dto.sorted());
        }

        System.out.println();
    }

    public void printStatistics(Map<Result, Long> statistics) {
        printStatisticsStart();
        printRank(statistics);
    }

    private static void printRank(Map<Result, Long> statistics) {
        Arrays.stream(Result.values())
                .filter(result -> result != Result.MISS) // 꽝 제외
                .forEach(result -> {
                    long count = statistics.getOrDefault(result, 0L);
                    String bonusMessage = "";
                    if (result.isBonusMatch()) bonusMessage = BONUS_NOT_MATCHED;

                    System.out.printf(STATISTICS_RESULT,
                            result.getMatchCount(),
                            bonusMessage,
                            result.getPrice(),
                            count);
                });
    }

    private void printStatisticsStart() {
        System.out.println(WINNING_STATISTICS);
        System.out.println(LINING);
    }

    public void printProfitRate(double profitRate) {
        System.out.printf(RATE_OF_RETURN_PRINT, profitRate);
    }

}
