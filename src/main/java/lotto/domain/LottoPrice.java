package lotto.domain;

import static lotto.utils.ErrorMessage.PRICE_LACK_ERROR;
import static lotto.utils.ErrorMessage.PRICE_UNIT_ERROR;

public class LottoPrice {

    public static final int LOTTO_PRICE = 1000;

    private final long userPrice;

    private LottoPrice(long userPrice) {
        validateMinimumPrice(userPrice);
        validatePriceUnit(userPrice);
        this.userPrice = userPrice;
    }

    public static LottoPrice from(long userPrice) {
        return new LottoPrice(userPrice);
    }

    private void validateMinimumPrice(long userPrice) {
        if (userPrice < LOTTO_PRICE) {
            throw new IllegalArgumentException(PRICE_LACK_ERROR.getMessage());
        }
    }

    private void validatePriceUnit(long userPrice) {
        if (userPrice % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(PRICE_UNIT_ERROR.getMessage());
        }
    }

    public long getLottoCount() {
        return userPrice / LOTTO_PRICE;
    }

}
