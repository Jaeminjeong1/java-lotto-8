package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoPrice;
import lotto.domain.Result;
import lotto.dto.LottoDto;
import lotto.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static lotto.domain.LottoPrice.LOTTO_PRICE;
import static lotto.utils.ErrorMessage.BONUS_NUM_CONTAINS_ERROR;

public class LottoService {

    public List<LottoDto> generateLottos(long userPrice) {
        List<LottoDto> result = new ArrayList<>();
        long lottoCount = LottoPrice.from(userPrice).getLottoCount();

        for (int i = 0; i < lottoCount; i++) {
            List<Integer> numbers = RandomGenerator.generateRandomNum();
            Lotto lotto = Lotto.from(numbers);
            result.add(lotto.toDto());
        }
        return result;
    }

    public LottoDto generateWinnerLotto(String[] parsedWinnerNumbers) {
        Lotto lotto = Lotto.from(Arrays.stream(parsedWinnerNumbers)
                .map(String::trim)
                .map(Integer::parseInt)
                .toList());

        return lotto.toDto();
    }

    public List<Result> calculateResults(List<LottoDto> lottos, LottoDto winnerLotto, int bonusNum) {
        Lotto winner = Lotto.from(winnerLotto.numbers());

        return lottos.stream()
                .map(dto -> Lotto.from(dto.numbers()))
                .map(lotto -> {
                    int match = lotto.matchCountWith(winner);
                    boolean bonus = lotto.contains(bonusNum);
                    return findResult(match, bonus);
                })
                .toList();
    }


    private Result findResult(int matchCount, boolean bonusMatch) {
        return Result.from(matchCount, bonusMatch);
    }

    public Map<Result, Long> summarizeResults(List<Result> results) {
        return results.stream()
                .collect(Collectors.groupingBy(result -> result, Collectors.counting()));
    }

    public double calculateProfitRate(Map<Result, Long> statistics, int totalLottoCount) {
        long totalPrize = statistics.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();

        long totalCost = (long) totalLottoCount * LOTTO_PRICE;

        return (double) totalPrize / totalCost * 100;
    }

    public int validateAndReturnBonusNum(LottoDto winnerDto, int bonusNumber) {
        if (winnerDto.numbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUM_CONTAINS_ERROR.getMessage());
        }
        return bonusNumber;
    }
}
