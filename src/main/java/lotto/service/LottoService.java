package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.util.RandomGenerator;

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

}
