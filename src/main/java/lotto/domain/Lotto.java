package lotto.domain;

import java.util.*;

import static lotto.util.ErrorMessage.*;

public class Lotto {

    private static final int MIN_LOTTO_NUM = 1;
    private static final int MAX_LOTTO_NUM = 45;
    private static final int LOTTO_COUNT = 6;

    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        validateRange(numbers);
        validateLottoCount(numbers);
        validateDuplicate(numbers);
        this.numbers = List.copyOf(numbers.stream()
                .sorted()
                .toList());
    }

    public static Lotto from(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    private void validateLottoCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_COUNT) {
            throw new IllegalArgumentException(LOTTO_COUNT_ERROR.getMessage());
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < MIN_LOTTO_NUM || number > MAX_LOTTO_NUM) {
                throw new IllegalArgumentException(LOTTO_RANGE_ERROR.getMessage());
            }
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);

        // 원본 리스트 크기와 Set 크기를 비교합니다.
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE.getMessage());
        }
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }

    public int matchCount(List<Integer> winnerLotto) {
        int count = 0;

        for (Integer number : numbers) {
            if (winnerLotto.contains(number)) count++;
        }

        return count;
    }

    public boolean isBonusMatch(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    // TODO: 추가 기능 구현
}
