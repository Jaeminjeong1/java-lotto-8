package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.util.Validator;

public class InputView {

    private static final String MONEY_INPUT = "구입금액을 입력해 주세요.";

    private InputView() {
    }

    public static int inputMoney() {
        System.out.println(MONEY_INPUT);
        String input = Console.readLine();

        Validator.validateEmptyInput(input);
        Validator.validateNumberFormat(input);

        int money = Integer.parseInt(input);
        Validator.validatePositive(money);

        return money;
    }


}
