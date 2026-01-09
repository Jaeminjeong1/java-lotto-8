package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Prize;
import lotto.domain.Winner;
import lotto.dto.LottoDto;
import lotto.service.LottoService;
import lotto.util.Retry;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoController {

    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void start() {
        // 구입금액을 입력받는다
        List<Lotto> lottos = inputMoney();

        // 당첨 번호를 입력받는다.
        Lotto winnerLotto = inputWinnerLotto();

        // 보너스 번호를 입력받고, 당첨 로또 완성.
        Winner winner = inputBonusNum(winnerLotto);

        // 당첨 통계를 낸 후 출력한다.
        calculateAndPrintResult(lottos, winner);
    }

    private List<Lotto> inputMoney() {
        return Retry.retryUntilSuccess(() -> {
            int money = InputView.inputMoney();
            List<Lotto> lottos = lottoService.generateLottos(money);
            OutputView.printLottos(LottoDto.of(lottos));
            return lottos;
        });
    }

    private Lotto inputWinnerLotto() {
        return Retry.retryUntilSuccess(() -> {
            List<Integer> winnerLottoNum = InputView.inputWinnerLottoNum();
            return lottoService.validateWinnerLotto(winnerLottoNum);
        });
    }

    private Winner inputBonusNum(Lotto winnerLotto) {
        return Retry.retryUntilSuccess(() -> {
            int bonus = InputView.inputBonusNum();
            return lottoService.generateWinnerLotto(winnerLotto, bonus);
        });
    }

    private void calculateAndPrintResult(List<Lotto> lottos, Winner winner) {
        EnumMap<Prize, Integer> result = lottoService.calculateResult(lottos, winner);
        double revenue = lottoService.calculateRevenue(result, lottos.size());
        OutputView.printResult(result, revenue);
    }

}
