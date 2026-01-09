package lotto.controller;

import lotto.domain.Lotto;
import lotto.service.LottoService;
import lotto.util.Retry;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void start() {
        // 구입금액을 입력받는다
        inputMoney();

        // 로또를 생성하여 출력한다.

        // 당첨 번호를 입력받는다.

        // 보너스 번호를 입력받는다.

        // 당첨 통계를 낸 후 출력한다.

    }

    private void inputMoney() {
        Retry.retryUntilSuccess(() -> {
            int money = InputView.inputMoney();
            List<Lotto> lottos = lottoService.generateLottos(money);
            OutputView.printLottos(lottos);
        });
    }
}
