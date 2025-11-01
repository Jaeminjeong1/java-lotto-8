package lotto.domain;

import java.util.List;

import static lotto.utils.ErrorMessage.LOTTO_NUM_COUNT_ERROR;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateLottoCount(numbers);
        this.numbers = numbers;
    }

    private void validateLottoCount(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(LOTTO_NUM_COUNT_ERROR.getMessage());
        }
    }

    // TODO: 추가 기능 구현
}
