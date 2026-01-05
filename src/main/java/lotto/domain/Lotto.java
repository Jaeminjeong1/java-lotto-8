package lotto.domain;

import java.util.Collections;
import java.util.List;

import static lotto.util.ErrorMessage.LOTTO_COUNT_ERROR;

public class Lotto {

    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        sortNumbers(numbers);
        validateLottoCount(numbers);
        this.numbers = List.copyOf(numbers);
    }

    public static Lotto from(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    private void validateLottoCount(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(LOTTO_COUNT_ERROR.getMessage());
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
