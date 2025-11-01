package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoPrice;
import lotto.domain.Result;
import lotto.dto.LottoDto;
import lotto.utils.Parser;
import lotto.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static lotto.utils.ErrorMessage.BONUS_NUM_CONTAINS_ERROR;

public class LottoService {

    public LottoService() {
    }

    public List<LottoDto> generateLottos(long userPrice) {
        List<LottoDto> result = new ArrayList<>();
        long lottoCount = LottoPrice.from(userPrice).getLottoCount();

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

    public List<Result> findMatchCount(List<LottoDto> lottos, LottoDto winnerLotto, int bonusNum) {
        return lottos.stream()
                .map(lotto -> {
                    long matchCount = lotto.numbers().stream()
                            .filter(winnerLotto.numbers()::contains)
                            .count();

                    boolean bonusMatch = lotto.numbers().contains(bonusNum);

                    return findResult((int) matchCount, bonusMatch);
                })
                .toList();
    }

    private Result findResult(int matchCount, boolean bonusMatch) {
        return Arrays.stream(Result.values())
                .filter(r -> r.getMatchCount() == matchCount && r.isBonusMatch() == bonusMatch)
                .findFirst()
                .orElse(Result.MISS);
    }

    public Map<Result, Long> summarizeResults(List<Result> results) {
        return results.stream()
                .collect(Collectors.groupingBy(result -> result, Collectors.counting()));
    }
}
