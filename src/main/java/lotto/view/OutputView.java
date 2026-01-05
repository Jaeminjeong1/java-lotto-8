package lotto.view;

import lotto.domain.Lotto;
import lotto.dto.LottoDto;

import java.util.List;

public class OutputView {

    private static final String PRINT_LOTTO_COUNT = "%d개를 구매했습니다.\n";

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
}
