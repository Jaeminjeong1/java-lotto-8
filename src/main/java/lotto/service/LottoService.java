package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.Winner;
import lotto.util.RandomGenerator;
import lotto.util.Validator;

import java.util.ArrayList;
import java.util.List;

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

}
