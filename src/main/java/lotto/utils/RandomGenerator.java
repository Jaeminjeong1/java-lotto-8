package lotto.utils;

import java.util.List;

import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;

public final class RandomGenerator {

    private static final int MIN_RANDOM_NUM = 1;
    private static final int MAX_RANDOM_NUM = 45;
    private static final int RANDOM_NUM_COUNT = 6;

    private RandomGenerator() {
    }

    public static List<Integer> generateRandomNum() {
        return List.copyOf(pickUniqueNumbersInRange(MIN_RANDOM_NUM, MAX_RANDOM_NUM, RANDOM_NUM_COUNT));
    }
}
