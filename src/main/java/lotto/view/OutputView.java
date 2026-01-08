package lotto.view;

public class OutputView {

    private OutputView() {
    }

    // 에러메세지 출력
    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
