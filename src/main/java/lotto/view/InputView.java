package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.util.Parser;
import lotto.util.Validator;

import java.util.List;

public class InputView {

    private static final String MONEY_INPUT = "구입금액을 입력해 주세요.";
    private static final String WINNER_LOTTO_INPUT = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUM_INPUT = "보너스 번호를 입력해 주세요.";

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

    public static List<Integer> inputWinnerLottoNum() {
        System.out.println(WINNER_LOTTO_INPUT);
        String input = Console.readLine();

        return Parser.parse(input);
    }

    public static int inputBonusNum() {
        System.out.println(BONUS_NUM_INPUT);
        String input = Console.readLine();

        Validator.validateEmptyInput(input);
        Validator.validateNumberFormat(input);

        int bonus = Integer.parseInt(input);
        Validator.validatePositive(bonus);
        return bonus;
    }


}
