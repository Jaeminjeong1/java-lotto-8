package lotto.controller;

import lotto.dto.LottoDto;
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
        // 구입 금액 입력 받기
        List<LottoDto> inputLottos = inputPurchaseAmountAndGenerateLotto();
        // 생성된 로또 출력

        // 구입 금액에 맞게 로또 생성
        // 당첨번호 입력받기
        // 보너스 번호 입력받기
        // 결과 출력
    }


    private List<LottoDto> inputPurchaseAmountAndGenerateLotto() {
         return Retry.retryUntilSuccess(() -> {
            int userInput = InputView.inputPurchaseAmount();
            List<LottoDto> lottos = lottoService.generateLottos(userInput);
             OutputView.printLottos(lottos);
            return lottos;
        });
    }
}
