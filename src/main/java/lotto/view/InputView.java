package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.util.Validator;

public class InputView {

    private static final String BUY_INPUT = "구입금액을 입력해 주세요.";

    private InputView() {
    }

    public static int inputPurchaseAmount() {
        System.out.println(BUY_INPUT);
        String input = Console.readLine();

        Validator.validateEmptyInput(input);
        Validator.validateNumberFormat(input);
        int userInput = Integer.parseInt(input);

        return userInput;
    }


}
