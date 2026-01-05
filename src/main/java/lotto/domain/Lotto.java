package lotto.domain;

import java.util.Collections;
import java.util.List;

import static lotto.util.ErrorMessage.LOTTO_COUNT_ERROR;
import static lotto.util.ErrorMessage.LOTTO_RANGE_ERROR;

public class Lotto {

    private static final int MIN_LOTTO_NUM = 1;
    private static final int MAX_LOTTO_NUM = 45;
    private static final int LOTTO_COUNT = 6;

    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        validateRange(numbers);
        validateLottoCount(numbers);
        sortNumbers(numbers);
        this.numbers = List.copyOf(numbers);
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

    private void sortNumbers(List<Integer> numbers) {
        Collections.sort(numbers);
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }

    // TODO: 추가 기능 구현
}
