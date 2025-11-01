package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import static lotto.utils.Validator.validateEmptyInput;
import static lotto.utils.Validator.validateNumberFormat;

public class InputView {

    private static final String BUY_PRICE_INPUT = "구입금액을 입력해 주세요.";
    private static final String WINNER_NUM_INPUT = "당첨 번호를 입력해 주세요.";

    private InputView() {
    }
    public static InputView create() {
        return new InputView();
    }

    public long inputUserPrice() {
        System.out.println(BUY_PRICE_INPUT);
        String userInput = Console.readLine();
        validateEmptyInput(userInput);
        validateNumberFormat(userInput);

        return Long.parseLong(userInput);
    }

    public String inputWinnerNum() {
        System.out.println(WINNER_NUM_INPUT);
        String userInput = Console.readLine();
        validateEmptyInput(userInput);

        return userInput;
    }
}
