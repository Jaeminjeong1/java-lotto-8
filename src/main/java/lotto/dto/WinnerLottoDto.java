package lotto.dto;

import lotto.domain.WinnerLotto;

import java.util.List;

public record WinnerLottoDto(List<Integer> numbers, int bonus) {

    public static WinnerLottoDto from(WinnerLotto winnerLotto) {
        return new WinnerLottoDto(winnerLotto.getWinnerLotto(), winnerLotto.getBonus());
    }
}
