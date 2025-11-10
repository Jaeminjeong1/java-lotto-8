package lotto

import lotto.config.AppConfig

object Application {
    fun main(args: Array<String>) {
        val controller = AppConfig.createController()
        controller.start()
    }
}
