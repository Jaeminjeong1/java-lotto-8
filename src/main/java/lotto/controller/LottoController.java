package lotto.controller;

import lotto.service.LottoService;
import lotto.view.InputView;

public class LottoController {

    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void start() {
        // 구입금액을 입력받는다
        int money = InputView.inputMoney();

        // 로또를 생성하여 출력한다.

        // 당첨 번호를 입력받는다.

        // 보너스 번호를 입력받는다.

        // 당첨 통계를 낸 후 출력한다.

    }
}
