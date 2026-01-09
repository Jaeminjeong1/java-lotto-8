package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.util.ErrorMessage.*;

public class Lotto {

    private static final int MIN_LOTTO_NUM = 1;
    private static final int MAX_LOTTO_NUM = 45;
    private static final int LOTTO_NUM_COUNT = 6;

    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        validateLottoCount(numbers);
        validateLottoNumRange(numbers);
        validateDuplicate(numbers);
        this.numbers = List.copyOf(numbers.stream()
                .sorted()
                .toList());
    }

    public static Lotto of(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    private void validateLottoCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUM_COUNT) {
            throw new IllegalArgumentException(LOTTO_COUNT_ERROR.getMessage());
        }
    }

    private void validateLottoNumRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number > MAX_LOTTO_NUM || number < MIN_LOTTO_NUM) {
                throw new IllegalArgumentException(LOTTO_NUMBER_ERROR.getMessage());
            }
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>(numbers);

        if (set.size() != numbers.size()) {
            throw new IllegalArgumentException(NUM_DUPLICATE_ERROR.getMessage());
        }
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }
}
