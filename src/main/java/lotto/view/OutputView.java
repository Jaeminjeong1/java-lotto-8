package lotto.view;

import lotto.domain.Lotto;

import java.util.List;

public class OutputView {

    private static final String PRINT_LOTTO_COUNT = "%d개를 구매했습니다.\n";

    private OutputView() {
    }

    // 에러메세지 출력
    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public static void printLottos(List<Lotto> lottos) {

        System.out.printf(PRINT_LOTTO_COUNT, lottos.size());
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }
}
