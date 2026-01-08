package lotto.util;

import lotto.view.OutputView;

import java.util.function.Supplier;

public class Retry {

    private Retry() {}

    // 값을 반환 받음
    public static <T> T retryUntilSuccess(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

    // 값 반환없이 행동 보장만
    public static void retryUntilSuccess(Runnable action) {
        while (true) {
            try {
                action.run();
                return;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }


}
