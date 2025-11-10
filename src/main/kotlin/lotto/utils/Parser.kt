package lotto.utils

object Parser {

    private const val DELIMITER: String = ","

    fun parse (userInput: String): List<String> =
        userInput.trim().split(DELIMITER)
}