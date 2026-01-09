package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.Prize;
import lotto.domain.Winner;
import lotto.util.RandomGenerator;
import lotto.util.Validator;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoService {

    public List<Lotto> generateLottos(int money) {
        int lottoCount = Money.from(money).calculateLottoCount();

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            List<Integer> numbers = RandomGenerator.generateRandomNum();
            Lotto lotto = Lotto.of(numbers);
            lottos.add(lotto);
        }

        return lottos;
    }

    public Lotto validateWinnerLotto(List<Integer> winnerLottoNum) {
        return Lotto.of(winnerLottoNum);
    }

    public Winner generateWinnerLotto(Lotto winnerLotto, int bonus) {
        Validator.validateRange(bonus);
        return Winner.of(winnerLotto, bonus);
    }

    public EnumMap<Prize, Integer> calculateResult(List<Lotto> lottos, Winner winner) {
        EnumMap<Prize, Integer> result = new EnumMap<>(Prize.class);
        for (Prize prize : Prize.values()) {
            result.put(prize, 0);
        }

        for (Lotto lotto : lottos) {
            int matchCount = winner.getMatchingCount(lotto);
            boolean isBonus = winner.isBonus(lotto);
            Prize prize = Prize.of(matchCount, isBonus);
            if (prize == null) continue;
            result.put(prize, result.getOrDefault(prize, 0) + 1);
        }
        return result;
    }

    public double calculateRevenue(EnumMap<Prize, Integer> result, int lottoCount) {
        int total = 0;

        for (Map.Entry<Prize, Integer> prizeIntegerEntry : result.entrySet()) {
            Prize prize = prizeIntegerEntry.getKey();
            int count = prizeIntegerEntry.getValue();
            total += prize.getMoney() * count;
        }

        return ((double) total / ((double) lottoCount * 1000) ) * 100;
    }

}
