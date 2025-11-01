package lotto.utils;

public enum ErrorMessage {

    EMPTY_INPUT_ERROR("입력이 비어있습니다. 다시 입력하세요."),
    NUMBER_FORMAT_ERROR("정수만 입력 가능합니다. 다시 입력하세요.");


    private final static String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
