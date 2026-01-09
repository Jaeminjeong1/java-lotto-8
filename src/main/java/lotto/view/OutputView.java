package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Prize;
import lotto.dto.LottoDto;

import java.util.EnumMap;
import java.util.List;

public class OutputView {

    private static final String PRINT_LOTTO_COUNT = "\n%d개를 구매했습니다.\n";
    private static final String RESULT_INFO = "\n당첨 통계\n" + "---";
    private static final String MATCH_NOT_BONUS_PRINT = "%d개 일치 (%,d원) - %d개\n";
    private static final String MATCH_BONUS_PRINT = "%d개 일치, 보너스 볼 일치 (%,d원) - %d개\n";
    private static final String REVENUE_PRINT = "총 수익률은 %.1f%%입니다.";


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
    }

    public static void printResult(EnumMap<Prize, Integer> result, double revenue) {
        System.out.println(RESULT_INFO);
        for (Prize prize : result.keySet()) {
            if (prize.equals(Prize.SECOND)) {
                System.out.printf(MATCH_BONUS_PRINT, prize.getMatchCount(), prize.getMoney(), result.get(prize));
                continue;
            }
            System.out.printf(MATCH_NOT_BONUS_PRINT, prize.getMatchCount(), prize.getMoney(), result.get(prize));
        }
        System.out.printf(REVENUE_PRINT, revenue);
    }
}
