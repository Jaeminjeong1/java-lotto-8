package lotto.controller

import lotto.domain.Result
import lotto.dto.LottoDto
import lotto.service.LottoService
import lotto.utils.Parser
import lotto.utils.Validator.validateParsing
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val lottoService: LottoService
) {

    fun start() {
        val lottos = getUserLottos()
        val winnerLotto = getWinnerLotto()
        val bonusNum = getBonusNum(winnerLotto)

        printResult(lottos, winnerLotto, bonusNum)
    }

    private fun getUserLottos(): List<LottoDto> {
        while (true) {
            try {
                val userPrice = inputView.inputUserPrice()
                val lottos = lottoService.generateLottos(userPrice)
                outputView.printLottos(lottos)
                return lottos
            } catch (e: IllegalArgumentException) {
                outputView.printErrorMessage(e)
            }
        }
    }

    private fun getWinnerLotto(): LottoDto {
        while (true) {
            try {
                val winnerNumbers = inputView.inputWinnerNum()
                val parsedWinnerNumbers = Parser.parse(winnerNumbers)

                validateParsing(parsedWinnerNumbers)
                return lottoService.generateWinnerLotto(parsedWinnerNumbers)
            } catch (e: IllegalArgumentException) {
                outputView.printErrorMessage(e)
            }
        }
    }

    private fun getBonusNum(winnerLotto: LottoDto): Int {
        while (true) {
            try {
                val bonusNum = inputView.inputBonusNum()
                return lottoService.validateAndReturnBonusNum(winnerLotto, bonusNum)
            } catch (e: IllegalArgumentException) {
                outputView.printErrorMessage(e)
            }
        }
    }

    private fun printResult(lottos: List<LottoDto>, winnerLotto: LottoDto, bonusNum: Int) {
        val results: List<Result> = lottoService.calculateResults(lottos, winnerLotto, bonusNum)
        val statistics = lottoService.summarizeResults(results)
        val profitRate = lottoService.calculateProfitRate(statistics, lottos.size)

        outputView.printStatistics(statistics)
        outputView.printProfitRate(profitRate)
    }
}
