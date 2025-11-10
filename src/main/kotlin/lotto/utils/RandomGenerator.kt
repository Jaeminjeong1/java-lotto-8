package lotto.utils

import camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange

object RandomGenerator {

    private const val MIN_RANDOM_NUM: Int = 1;
    private const val MAX_RANDOM_NUM: Int = 45;
    private const val RANDOM_NUM_COUNT: Int = 6;

    fun generateRandomNum(): List<Int> =
        pickUniqueNumbersInRange(MIN_RANDOM_NUM, MAX_RANDOM_NUM, RANDOM_NUM_COUNT).toList()
}
