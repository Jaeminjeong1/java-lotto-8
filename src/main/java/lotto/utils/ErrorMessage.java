package lotto.utils;

public enum ErrorMessage {

    EMPTY_INPUT_ERROR("입력이 비어있습니다. 다시 입력하세요."),
    NUMBER_FORMAT_ERROR("정수만 입력 가능합니다. 다시 입력하세요."),
    LOTTO_NUM_RANGE_ERROR("로또 번호는 1~45인 숫자만 가능합니다. 다시 입력하세요."),
    LOTTO_NUM_COUNT_ERROR("로또 번호는 6개여야 합니다. 다시 입력하세요."),
    PRICE_LACK_ERROR("한장당 로또 가격은 1000원 입니다. 다시 입력하세요."),
    PRICE_UNIT_ERROR("구매 단위는 1000원 입니다. 다시 입력하세요."),
    BONUS_NUM_CONTAINS_ERROR("이미 당첨 번호에 포함되어 있습니다. 다시 입력하세요.");


    private final static String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
