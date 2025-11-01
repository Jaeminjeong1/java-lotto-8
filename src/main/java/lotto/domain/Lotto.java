package lotto.domain;

import java.util.List;

import static lotto.utils.ErrorMessage.LOTTO_NUM_COUNT_ERROR;
import static lotto.utils.ErrorMessage.LOTTO_NUM_RANGE_ERROR;

public class Lotto {

    private static final int LOTTO_NUM_COUNT = 6;
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;

    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        validateLottoCount(numbers);
        validateNumberRange(numbers);
        this.numbers = numbers;
    }

    public static Lotto from(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    private void validateLottoCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUM_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUM_COUNT_ERROR.getMessage());
        }
    }

    private void validateNumberRange(List<Integer> numbers) {
        numbers.stream()
                .filter(number -> number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER)
                .findAny()
                .ifPresent(n -> {
                    throw new IllegalArgumentException(LOTTO_NUM_RANGE_ERROR.getMessage());
                });
    }

    // TODO: 추가 기능 구현
}
