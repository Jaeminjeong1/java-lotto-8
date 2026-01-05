package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.Result;
import lotto.domain.WinnerLotto;
import lotto.dto.LottoDto;
import lotto.dto.WinnerLottoDto;
import lotto.util.RandomGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LottoService {

    private static final int LOTTO_PRICE = 1000;


    public List<LottoDto> generateLottos(int purchaseAmount) {
        int lottoCount = LottoCount.from(purchaseAmount).getLottoCount();

        List<LottoDto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            List<Integer> randomNumbers = RandomGenerator.generateRandomNumber();
            lottos.add(LottoDto.from(Lotto.from(randomNumbers)));
        }

        return lottos;
    }

    public LottoDto generateWinnerLotto(List<Integer> parsedInput) {
        return LottoDto.from(Lotto.from(parsedInput));
    }

    public WinnerLottoDto addBonusNum(LottoDto winnerLotto, int bonusNumber) {


        return WinnerLottoDto.from(WinnerLotto.from(winnerLotto.numbers(), bonusNumber));
    }


    public List<Result> calculateResult(List<LottoDto> inputLottos, WinnerLottoDto winnerLotto) {
        List<Result> result = new ArrayList<>();

        for (LottoDto inputLotto : inputLottos) {
            Lotto lotto = Lotto.from(inputLotto.numbers());
            int matchCount = lotto.matchCount(winnerLotto.numbers());
            boolean bonusMatch = lotto.isBonusMatch(winnerLotto.bonus());
            result.add(Result.from(matchCount, bonusMatch));
        }

        return result;
    }

    public Map<Result, Integer> calculateStatistics(List<Result> results) {
        Map<Result, Integer> statistics = new HashMap<>();

        for (Result result : Result.values()) {
            statistics.put(result, 0);
        }

        for (Result result : results) {
            statistics.put(result, statistics.getOrDefault(result, 0) + 1);
        }

        return statistics;
    }

    public double calculateRevenue(Map<Result, Integer> statistics, List<LottoDto> inputLottos) {
        int purchaseAmount = inputLottos.size() * LOTTO_PRICE;

        int totalPrize = 0;

        for (Result result : statistics.keySet()) {
            int num = statistics.get(result);
            totalPrize += result.getPrize() * num;
        }

        return ((double) totalPrize / (double) purchaseAmount) * 100;
    }


}
