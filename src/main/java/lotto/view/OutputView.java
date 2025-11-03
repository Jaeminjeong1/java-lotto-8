package lotto.view;

import lotto.domain.Result;
import lotto.dto.LottoDto;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String LOTTO_BUY_MESSAGE = "개를 구매했습니다.";
    private static final String WINNING_STATISTICS = "\n당첨 통계";
    private static final String LINING = "---";
    private static final String RATE_OF_RETURN_PRINT = "총 수익률은 %.1f%%입니다.%n";


    private OutputView() {
    }

    public static OutputView create() {
        return new OutputView();
    }

    public void printLottos(List<LottoDto> lottos) {
        System.out.println("\n" + lottos.size() + LOTTO_BUY_MESSAGE);

        for (LottoDto dto : lottos) {
            System.out.println(dto.numbers().stream()
                    .sorted()
                    .toList());
        }

        System.out.println();
    }

    public void printStatistics(Map<Result, Long> statistics) {
        printStatisticsStart();
        printRank(statistics);
    }

    private static void printRank(Map<Result, Long> statistics) {
        List<Result> ordered = List.of(
                Result.FIFTH,
                Result.FOURTH,
                Result.THIRD,
                Result.SECOND,
                Result.FIRST
        );

        for (Result result : ordered) {
            long count = statistics.getOrDefault(result, 0L);
            System.out.print(ResultFormatter.line(result, count));
        }
    }

    private void printStatisticsStart() {
        System.out.println(WINNING_STATISTICS);
        System.out.println(LINING);
    }

    public void printProfitRate(double profitRate) {
        System.out.printf(RATE_OF_RETURN_PRINT, profitRate);
    }

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
