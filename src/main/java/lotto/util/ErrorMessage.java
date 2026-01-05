package lotto.util;

public enum ErrorMessage {

    INPUT_ERROR("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    TYPE_ERROR("타입이 일치하지 않습니다. 다시 입력해 주세요."),
    NUMBER_FORMAT_ERROR("숫자형식이 아닙니다. 다시 입력해 주세요."),
    LOTTO_COUNT_ERROR("로또 번호는 6개여야 합니다."),
    UNIT_ERROR("구입가격은 1000원 단위여야합니다.");

    private final static String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
