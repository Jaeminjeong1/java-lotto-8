package lotto.controller;

import lotto.domain.Result;
import lotto.dto.LottoDto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void start() {
        List<LottoDto> lottos = getUserLottos();

        LottoDto winnerLotto = getWinnerLotto();
        int bonusNum = getBonusNum(winnerLotto);

        printResult(lottos, winnerLotto, bonusNum);
    }

    private List<LottoDto> getUserLottos() {
        while (true) {
            try {
                long userPrice = inputView.inputUserPrice();

                List<LottoDto> lottos = lottoService.generateLottos(userPrice);
                outputView.printLottos(lottos);
                return lottos;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private LottoDto getWinnerLotto() {
        while (true) {
            try {
                String winnerNumbers = inputView.inputWinnerNum();
                return lottoService.generateWinnerLotto(winnerNumbers);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private int getBonusNum(LottoDto winnerLotto) {
        while (true) {
            try {
                int bonusNum = inputView.inputBonusNum();
                return lottoService.validateBonusNum(winnerLotto, bonusNum);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private void printResult(List<LottoDto> lottos, LottoDto winnerLotto, int bonusNum) {
        List<Result> results = lottoService.findMatchCount(lottos, winnerLotto, bonusNum);
        Map<Result, Long> statistics = lottoService.summarizeResults(results);

        double profitRate = lottoService.calculateProfitRate(statistics, lottos.size());
        outputView.printStatistics(statistics);
        outputView.printProfitRate(profitRate);
    }
}
