package lotto.util;

public enum ErrorMessage {

    INPUT_ERROR("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    TYPE_ERROR("타입이 일치하지 않습니다. 다시 입력해 주세요."),
    NUMBER_FORMAT_ERROR("숫자형식이 아닙니다. 다시 입력해 주세요."),
    POSITIVE_NUMBER_ERROR("값이 양수가 아닙니다."),
    RANGE_ERROR("주어진 범위에 만족하지 않습니다."),
    MONEY_ERROR("구입 금액은 1000원 단위여야 합니다."),
    LOTTO_NUMBER_ERROR("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    LOTTO_COUNT_ERROR("로또 번호는 6개여야 합니다."),
    NUM_DUPLICATE_ERROR("로또 번호가 중복됩니다.")
    ;

    private final static String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
