package lotto.domain;

import static lotto.util.ErrorMessage.UNIT_ERROR;

public class LottoCount {

    private int lottoCount;

    private static final int LOTTO_PRICE = 1000;

    private LottoCount(int purchaseAmount) {
        validateRange(purchaseAmount);
        validatePriceUnit(purchaseAmount);
        this.lottoCount = (purchaseAmount / LOTTO_PRICE);
    }

    public static LottoCount from(int purchaseAmount) {
        return new LottoCount(purchaseAmount);
    }

    private void validateRange(int price) {
        if (price < LOTTO_PRICE) {
            throw new IllegalArgumentException(UNIT_ERROR.getMessage());
        }
    }

    private void validatePriceUnit(int price) {
        if (price % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(UNIT_ERROR.getMessage());
        }
    }

    public int getLottoCount() {
        return lottoCount;
    }
}
