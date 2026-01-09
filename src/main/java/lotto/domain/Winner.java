package lotto.domain;

import static lotto.util.ErrorMessage.NUM_DUPLICATE_ERROR;

public class Winner {

    private Lotto winnerLottoNum;
    private int bonus;

    private Winner(Lotto winnerLottoNum, int bonus) {
        validateDuplicate(bonus);
        this.winnerLottoNum = winnerLottoNum;
        this.bonus = bonus;
    }

    public static Winner of(Lotto winnerLottoNum, int bonus) {
        return new Winner(winnerLottoNum, bonus);
    }

    private void validateDuplicate(int bonus) {
        if (winnerLottoNum.getNumbers().contains(bonus)) {
            throw new IllegalArgumentException(NUM_DUPLICATE_ERROR.getMessage());
        }
    }

}
