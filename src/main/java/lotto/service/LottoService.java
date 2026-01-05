package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.dto.LottoDto;
import lotto.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoService {

    public List<LottoDto> generateLottos(int purchaseAmount) {
        int lottoCount = LottoCount.from(purchaseAmount).getLottoCount();

        List<LottoDto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            List<Integer> randomNumbers = RandomGenerator.generateRandomNumber();
            lottos.add(LottoDto.from(Lotto.from(randomNumbers)));
        }

        return lottos;
    }

}
