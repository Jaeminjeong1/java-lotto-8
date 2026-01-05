package lotto.controller;

import lotto.domain.WinnerLotto;
import lotto.dto.LottoDto;
import lotto.dto.WinnerLottoDto;
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

        // 구입 금액에 맞게 로또 생성
        // 당첨번호 입력받기
        LottoDto winnerLotto = inputWinnerLotto();
        // 보너스 번호 입력받기
        WinnerLottoDto finalWinnerLotto = inputBonusNumber(winnerLotto);
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

    private LottoDto inputWinnerLotto() {
        return Retry.retryUntilSuccess(() -> {
            List<Integer> input = InputView.inputWinnerLotto();
            return lottoService.generateWinnerLotto(input);
        });
    }

    private WinnerLottoDto inputBonusNumber(LottoDto winnerLotto) {
        return Retry.retryUntilSuccess(() -> {
           int bonusNumber = InputView.inputBonusNumber();
            return lottoService.addBonusNum(winnerLotto, bonusNumber);
        });
    }

}
