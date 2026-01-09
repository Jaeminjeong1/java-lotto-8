package lotto.domain;

import static lotto.util.ErrorMessage.NUM_DUPLICATE_ERROR;

public class Winner {

    private final Lotto winnerLottoNum;
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
        if (winnerLottoNum != null && winnerLottoNum.getNumbers().contains(bonus)) {
            throw new IllegalArgumentException(NUM_DUPLICATE_ERROR.getMessage());
        }
    }

    public int getMatchingCount(Lotto lotto) {
        int count = 0;
        for (Integer winnerNumber : winnerLottoNum.getNumbers()) {
            if (lotto.getNumbers().contains(winnerNumber)) count++;
        }
        return count;
    }

    public boolean isBonus(Lotto lotto) {
        return lotto.getNumbers().contains(bonus);
    }

}
