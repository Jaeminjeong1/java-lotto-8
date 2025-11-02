package lotto.utils;

public final class Parser {

    private static final String DELIMITER = ",";

    private Parser() {
    }

    public static String[] parse(String userInput) {
        return userInput.trim().split(DELIMITER, -1);
    }
}
