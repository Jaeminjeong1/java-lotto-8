package lotto.view

import camp.nextstep.edu.missionutils.Console
import lotto.utils.Validator

class InputView private constructor() {

    companion object {
        private const val BUY_PRICE_INPUT = "구입금액을 입력해 주세요."
        private const val WINNER_NUM_INPUT = "당첨 번호를 입력해 주세요."
        private const val BONUS_NUM_INPUT = "보너스 번호를 입력해 주세요."

        fun create(): InputView = InputView()
    }


    fun inputUserPrice(): Long {
        println(BUY_PRICE_INPUT)
        val userInput = Console.readLine()

        Validator.validateEmptyInput(userInput)
        Validator.validateNumberFormat(userInput)

        return userInput.toLong()
    }

    fun inputWinnerNum(): String {
        println(WINNER_NUM_INPUT)
        val userInput = Console.readLine()

        Validator.validateEmptyInput(userInput)

        return userInput
    }

    fun inputBonusNum(): Int {
        println(BONUS_NUM_INPUT)
        val userInput = Console.readLine()

        Validator.validateEmptyInput(userInput)
        Validator.validateNumberFormat(userInput)

        return userInput.toInt()
    }
}