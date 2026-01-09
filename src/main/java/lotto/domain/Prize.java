package lotto.domain;

public enum Prize {

    FIFTH(3, false, 5_000),
    FORTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000)
    ;

    private int matchCount;
    private boolean isBonus;
    private int money;

    Prize(int matchCount, boolean isBonus, int money) {
        this.matchCount = matchCount;
        this.isBonus = isBonus;
        this.money = money;
    }


    public static Prize of(int matchCount, boolean isBonus) {
        if (matchCount < 3) {
            return null;
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        if (matchCount == 4) {
            return FORTH;
        }
        if (matchCount == 5 && !isBonus) {
            return THIRD;
        }
        if (matchCount == 5) {
            return SECOND;
        }
        return FIRST;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonus() {
        return isBonus;
    }

    public int getMoney() {
        return money;
    }
}
