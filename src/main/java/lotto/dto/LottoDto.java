package lotto.dto;

import java.util.List;

public record LottoDto(List<Integer> numbers) {
    public List<Integer> sorted() {
        return numbers.stream()
                .sorted()
                .toList();
    }
}
