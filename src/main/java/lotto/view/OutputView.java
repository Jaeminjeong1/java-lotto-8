package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Result;
import lotto.dto.LottoDto;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String PRINT_LOTTO_COUNT = "\n%d개를 구매했습니다.\n";
    private static final String STATISTICS_INFO =
            "\n당첨 통계\n" +
            "---";
    private static final String STATISTICS = "%d개 일치 (%,d원) - %d개\n";
    private static final String STATISTICS_BONUS = "%d개 일치, 보너스 불 일치 (%,d원) - %d개\n";
    private static final String TOTAL_REVENUE = "총 수익률은 %.1f%%입니다.";



    private OutputView() {
    }

    // 에러메세지 출력
    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public static void printLottos(List<LottoDto> lottos) {

        System.out.printf(PRINT_LOTTO_COUNT, lottos.size());
        for (LottoDto lotto : lottos) {
            System.out.println(lotto.numbers());
        }
        System.out.println();
    }

    public static void printStatistics(Map<Result, Integer> statistics) {
        System.out.println(STATISTICS_INFO);

        for (Result result : Result.values()) {
            if (result.equals(Result.MISS)) continue;
            if (result.equals(Result.SECOND)) {
                System.out.printf(STATISTICS_BONUS, result.getMatchCount(), result.getPrize(), statistics.get(result));
                continue;
            }
            System.out.printf(STATISTICS, result.getMatchCount(), result.getPrize(), statistics.get(result));
        }
    }

    public static void printRevenue(double revenue) {
        System.out.printf(TOTAL_REVENUE, revenue);
    }
}
