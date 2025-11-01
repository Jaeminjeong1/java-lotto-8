package lotto.domain;

import java.util.List;

import static lotto.utils.ErrorMessage.LOTTO_NUM_COUNT_ERROR;

public class Lotto {

    private static final int LOTTO_NUM_COUNT = 6;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateLottoCount(numbers);
        this.numbers = numbers;
    }

    private void validateLottoCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUM_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUM_COUNT_ERROR.getMessage());
        }
    }

    // TODO: 추가 기능 구현
}
