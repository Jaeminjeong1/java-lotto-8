package lotto.domain;

import lotto.dto.LottoDto;

import java.util.List;

import static lotto.util.ErrorMessage.DUPLICATE_BONUS_NUM_ERROR;

public class WinnerLotto {

    private final List<Integer> winnerLotto;
    private final int bonus;

    private WinnerLotto(List<Integer> winnerLotto, int bonus) {
        validateBonusNum(winnerLotto, bonus);
        this.winnerLotto = List.copyOf(winnerLotto);
        this.bonus = bonus;
    }

    public static WinnerLotto from(List<Integer> winnerLotto, int bonus) {
        return new WinnerLotto(winnerLotto, bonus);
    }

    private void validateBonusNum(List<Integer> winnerLotto, int bonusNumber) {
        if (winnerLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_NUM_ERROR.getMessage());
        }
    }

    public List<Integer> getWinnerLotto() {
        return winnerLotto;
    }

    public int getBonus() {
        return bonus;
    }
}
