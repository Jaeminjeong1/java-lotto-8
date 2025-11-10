package lotto.config

import lotto.controller.LottoController
import lotto.service.LottoService
import lotto.view.InputView
import lotto.view.OutputView

object AppConfig {

    private fun createService(): LottoService =
        LottoService()

    fun createController(): LottoController =
        LottoController(
            InputView.create(),
            OutputView.create(),
            createService()
        )
}
