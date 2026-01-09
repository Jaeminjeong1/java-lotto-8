package lotto.domain;

import static lotto.util.ErrorMessage.MONEY_ERROR;

public class Money {

    private static final int LOTTO_PRICE = 1000;

    private int money;

    private Money(int money) {
        validateMoney(money);
        this.money = money;
    }

    public static Money from(int money) {
        return new Money(money);
    }

    private void validateMoney(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(MONEY_ERROR.getMessage());
        }
    }

    public int calculateLottoCount() {
        return this.money / LOTTO_PRICE;
    }


}
