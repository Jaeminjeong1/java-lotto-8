package lotto

import lotto.config.AppConfig

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        val controller = AppConfig.createController()
        controller.start()
    }
}
