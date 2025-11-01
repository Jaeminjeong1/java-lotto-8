package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Price;
import lotto.utils.Parser;
import lotto.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Lotto generateWinnerLotto(String winnerNumbers) {
        return Lotto.from(Arrays.stream(Parser.parse(winnerNumbers))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList());
    }

    public int validateBonusNum(Lotto winnerLotto, String bonusNum) {
        int bonusNumber = Integer.parseInt(bonusNum);
        winnerLotto.validateContain(bonusNumber);

        return bonusNumber;
    }
}
