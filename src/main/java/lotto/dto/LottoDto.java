package lotto.dto;

import lotto.domain.Lotto;

import java.util.List;
import java.util.stream.Collectors;

public record LottoDto(List<Integer> numbers) {

    private static LottoDto from(Lotto lotto) {
        return new LottoDto(lotto.getNumbers());
    }

    public static List<LottoDto> of(List<Lotto> lottos) {
        return lottos.stream()
                .map(LottoDto::from)
                .collect(Collectors.toList());
    }

}
