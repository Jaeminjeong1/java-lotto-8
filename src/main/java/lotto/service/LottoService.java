package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Price;
import lotto.dto.LottoDto;
import lotto.utils.Parser;
import lotto.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoService {

    public LottoService() {
    }

    public List<LottoDto> generateLottos(long userPrice) {
        List<LottoDto> result = new ArrayList<>();
        long lottoCount = Price.from(userPrice).getLottoCount();

        for (int i = 0; i < lottoCount; i++) {
            List<Integer> numbers = RandomGenerator.generateRandomNum();
            Lotto lotto = Lotto.from(numbers);

            result.add(new LottoDto(numbers));
        }
        return result;
    }

    public Lotto generateWinnerLotto(String winnerNumbers) {
        return Lotto.from(Arrays.stream(Parser.parse(winnerNumbers))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList());
    }

    public int validateBonusNum(Lotto winnerLotto, int bonusNumber) {
        winnerLotto.validateContain(bonusNumber);

        return bonusNumber;
    }
}
