package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.WinnerLotto;
import lotto.dto.LottoDto;
import lotto.dto.WinnerLottoDto;
import lotto.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

import static lotto.util.ErrorMessage.DUPLICATE_BONUS_NUM_ERROR;

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

    public LottoDto generateWinnerLotto(List<Integer> parsedInput) {
        return LottoDto.from(Lotto.from(parsedInput));
    }

    public WinnerLottoDto addBonusNum(LottoDto winnerLotto, int bonusNumber) {


        return WinnerLottoDto.from(WinnerLotto.from(winnerLotto.numbers(), bonusNumber));
    }




}
