package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Price;
import lotto.dto.LottoDto;
import lotto.utils.Parser;
import lotto.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lotto.utils.ErrorMessage.BONUS_NUM_CONTAINS_ERROR;

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

    public LottoDto generateWinnerLotto(String winnerNumbers) {
        Lotto lotto = Lotto.from(Arrays.stream(Parser.parse(winnerNumbers))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList());

        return lotto.toDto();
    }

    public int validateBonusNum(LottoDto winnerDto, int bonusNumber) {
        if (winnerDto.numbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUM_CONTAINS_ERROR.getMessage());
        }
        return bonusNumber;
    }
}
