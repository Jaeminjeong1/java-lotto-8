package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Price;
import lotto.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoService {

    public LottoService() {
    }

    public List<Lotto> lottoGenerate(long userPrice) {
        List<Lotto> lottos = new ArrayList<>();
        long lottoCount = Price.from(userPrice).getLottoCount();

        for (int i = 0; i < lottoCount; i++) {
            lottos.add(Lotto.from(RandomGenerator.generateRandomNum()));
        }
        return lottos;
    }
}
