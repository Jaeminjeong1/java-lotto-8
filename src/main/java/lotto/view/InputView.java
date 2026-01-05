package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.util.Parser;
import lotto.util.Validator;

import java.util.List;

public class InputView {

    private static final String BUY_INPUT = "구입금액을 입력해 주세요.";
    private static final String WINNER_LOTTO_INPUT = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUM_INPUT = "\n보너스 번호를 입력해 주세요.";

    private InputView() {
    }

    public static int inputPurchaseAmount() {
        System.out.println(BUY_INPUT);
        String input = Console.readLine();

        Validator.validateEmptyInput(input);
        Validator.validateNumberFormat(input);

        return Integer.parseInt(input);
    }

    public static List<Integer> inputWinnerLotto() {
        System.out.println(WINNER_LOTTO_INPUT);
        String input = Console.readLine();

        Validator.validateEmptyInput(input);

        return Parser.parse(input);
    }

    public static int inputBonusNumber() {
        System.out.println(BONUS_NUM_INPUT);
        String input = Console.readLine();

        Validator.validateEmptyInput(input);
        Validator.validateNumberFormat(input);

        return Integer.parseInt(input);
    }

}
