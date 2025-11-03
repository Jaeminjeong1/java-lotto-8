package lotto.domain;


import java.util.Arrays;

public enum Result {

    MISS(0, false, 0),
    FIFTH(3, false, 5000),
    FOURTH(4, false, 50000),
    THIRD(5, false, 1500000),
    SECOND(5, true, 30000000),
    FIRST(6, false, 2000000000);

    private final int matchCount;
    private final boolean bonusMatch;
    private final int price;

    Result(int matchCount, boolean bonusMatch, int price) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.price = price;
    }

    public static Result from(int matchCount, boolean bonusMatch) {
        return Arrays.stream(Result.values())
                .filter(r -> r.matchCount == matchCount && r.bonusMatch == bonusMatch)
                .findFirst()
                .orElse(MISS);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }

    public int getPrice() {
        return price;
    }
}
