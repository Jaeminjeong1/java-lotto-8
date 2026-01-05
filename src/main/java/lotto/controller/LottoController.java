package lotto.controller;

import lotto.domain.Result;
import lotto.domain.WinnerLotto;
import lotto.dto.LottoDto;
import lotto.dto.WinnerLottoDto;
import lotto.service.LottoService;
import lotto.util.Retry;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;

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
        calculateAndPrintResult(inputLottos, finalWinnerLotto);
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

    private void calculateAndPrintResult(List<LottoDto> inputLottos, WinnerLottoDto winnerLotto) {
        printResult(calculateResult(inputLottos, winnerLotto), inputLottos);
    }

    private Map<Result, Integer> calculateResult(List<LottoDto> inputLottos, WinnerLottoDto winnerLotto) {
        List<Result> results = lottoService.calculateResult(inputLottos, winnerLotto);
        return lottoService.calculateStatistics(results);
    }

    private void printResult(Map<Result, Integer> statistics, List<LottoDto> inputLottos){

        double revenue = lottoService.calculateRevenue(statistics, inputLottos);
        OutputView.printStatistics(statistics);
        OutputView.printRevenue(revenue);
    }
}
